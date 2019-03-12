package org.dominokit.samples.contacts.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Contacts")
public class ContactsClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactsClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Contacts frontend module ...");
		new ModuleConfigurator().configureModule(new ContactsModuleConfiguration());
	}
}
