package org.dominokit.samples.contacts.repository.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.auto.service.AutoService;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.runtime.Network;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.SLF4JLogDelegateFactory;
import io.vertx.ext.mongo.MongoClient;
import org.dominokit.domino.api.server.entrypoint.ServerAppEntryPoint;
import org.dominokit.domino.api.server.entrypoint.VertxContext;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@AutoService(ServerAppEntryPoint.class)
public class InitRepositoryEntryPoint implements ServerAppEntryPoint<VertxContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitRepositoryEntryPoint.class);

    @Override
    public void onModulesLoaded(VertxContext vertxContext) {

        initMongo(vertxContext);
    }

    private void initMongo(VertxContext vertxContext) {
        IMongodConfig embeddedConfig = null;
        JsonObject mongoConfig = new JsonObject();
        try {
            String version = vertxContext.config().getString("version", "3.4.3");

            version = "V" + version.replace('.', '_');
            final Version versionEnum = Version.valueOf(version);
            embeddedConfig = new MongodConfigBuilder().
                    version(versionEnum)
                    .net(new Net(vertxContext.config().getInteger("mongo.port", 27017), Network.localhostIsIPv6()))
                    .build();

            Logger logger = (Logger) new SLF4JLogDelegateFactory()
                    .createDelegate(InitRepositoryEntryPoint.class.getCanonicalName()).unwrap();

            Command command = Command.MongoD;

            IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                    .defaultsWithLogger(command, logger)
                    .build();

            MongodExecutable exe = MongodStarter.getInstance(runtimeConfig)
                    .prepare(embeddedConfig);
            exe.start();

            int mongoPort = embeddedConfig.net().getPort();

            mongoConfig.put("db_name", "contacts_db");
            mongoConfig.put("useObjectId", false);
            mongoConfig.put("port", mongoPort);
            mongoConfig.put("host", "localhost");


            LOGGER.info("::::::::::::::::::: MONGO STARTED ON PORT ::::::::::::::::::::::");
            LOGGER.info(":::::::::::::::::::::::: " + mongoPort + " :::::::::::::::::::::::::::");

            vertxContext.vertx().eventBus()
                    .publish("mongo-config", mongoConfig);


        } catch (IOException e) {
            e.printStackTrace();
        }

        URL fileUrl = InitRepositoryEntryPoint.this.getClass().getResource("/app/contacts.json");
        try {
            Path path = Paths.get(fileUrl.toURI());
            String fileContent = new String(Files.readAllBytes(path));
            List<Contact> contacts = Json.decodeValue(fileContent, new TypeReference<List<Contact>>() {
            });
            MongoClient client = MongoClient.createShared(vertxContext.vertx(), mongoConfig);
            client.createCollection("contacts", event -> {
                if (event.succeeded()) {
                   LOGGER.info("Contacts DB client have been created");
                }
            });
            ContactsRepository.INSTANCE.init(client, contacts);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
