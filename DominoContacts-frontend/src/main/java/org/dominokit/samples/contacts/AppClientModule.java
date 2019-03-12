package org.dominokit.samples.contacts;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ClientApp;
import org.dominokit.domino.api.client.DynamicServiceRoot;

import java.util.logging.Logger;

public class AppClientModule implements EntryPoint {

    private static final Logger LOGGER = Logger.getLogger(AppClientModule.class.getName());

    public void onModuleLoad() {
        ClientApp.make().run();
        LOGGER.info("DominoContacts Application frontend have been initialized.");
    }
}
