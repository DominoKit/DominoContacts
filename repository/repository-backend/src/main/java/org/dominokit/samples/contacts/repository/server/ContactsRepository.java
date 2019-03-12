package org.dominokit.samples.contacts.repository.server;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;
import org.dominokit.samples.contacts.model.shared.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class ContactsRepository {

    public static final Logger LOGGER = LoggerFactory.getLogger(ContactsRepository.class);
    public static final ContactsRepository INSTANCE = new ContactsRepository();
    public static final String CONTACTS = "contacts";
    private MongoClient client;
    private static long id = 0;

    private static final List<String> PREDEFINED_GROUPS_NAME = new ArrayList<>();

    static {
        PREDEFINED_GROUPS_NAME.add("Family");
        PREDEFINED_GROUPS_NAME.add("School");
        PREDEFINED_GROUPS_NAME.add("Friends");
        PREDEFINED_GROUPS_NAME.add("Work");
        PREDEFINED_GROUPS_NAME.add("Social");
        PREDEFINED_GROUPS_NAME.add("Weekend");
        PREDEFINED_GROUPS_NAME.add("Neighbours");
        PREDEFINED_GROUPS_NAME.add("World wide");
        PREDEFINED_GROUPS_NAME.add("Clubs");
        PREDEFINED_GROUPS_NAME.add("Gaming");
    }

    private ContactsRepository() {
    }

    void init(MongoClient client, List<Contact> initialContacts) {
        this.client = client;

        initialContacts
                .stream()
                .map(contact -> {
                    contact.set_id("_" + id);
                    id++;
                    Random random = new Random();
                    int i = random.nextInt(10);
                    if (i > 6) {
                        contact.setFavourite(true);
                    }

                    for (int index = 0; index < i; index++) {
                        contact.getGroups().add(PREDEFINED_GROUPS_NAME.get(random.nextInt(10)));
                    }

                    return contact;
                })
                .map(JsonObject::mapFrom)
                .forEach(jsonObject -> client.insert(CONTACTS, jsonObject, event -> {
                }));
    }

    public void list(Consumer<List<Contact>> consumer) {

        client.find(CONTACTS, new JsonObject(), event -> {
            if (event.succeeded()) {
                List<Contact> contacts = new ArrayList<>();
                List<JsonObject> result = event.result();
                result.stream()
                        .map(jsonObject -> Json.decodeValue(jsonObject.encode(), Contact.class))
                        .forEach(contacts::add);
                consumer.accept(contacts);

            }
        });
    }

    public void findById(String contactId, Consumer<Contact> consumer) {
        JsonObject filter = new JsonObject();
        filter.put("_id", contactId);
        FindOptions options = new FindOptions();
        options.setLimit(1);
        client.findWithOptions(CONTACTS, filter, options, event -> {
            if (event.succeeded()) {
                List<JsonObject> result = event.result();

                JsonObject json = result.get(0);
                consumer.accept(Json.decodeValue(json.encode(), Contact.class));
            }
        });
    }

    public void update(Contact contact) {
        JsonObject filter = new JsonObject();
        filter.put("_id", contact.get_id());

        JsonObject update = new JsonObject().put("$set", JsonObject.mapFrom(contact));
        client.findOneAndUpdate(CONTACTS, filter, update, event -> {
            if (event.succeeded()) {
                LOGGER.info("SUCCESS : " + event.result().toString());
            } else {
                LOGGER.error("FAILED : ", event.cause());
            }
        });
    }

    public void delete(String id) {
        JsonObject filter = new JsonObject();
        filter.put("_id", id);
        client.removeDocument(CONTACTS, filter, event -> {
        });
    }
}
