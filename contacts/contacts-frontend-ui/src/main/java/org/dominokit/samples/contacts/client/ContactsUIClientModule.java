package org.dominokit.samples.contacts.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="ContactsUI")
public class ContactsUIClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactsUIClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Contacts frontend UI module ...");
		new ModuleConfigurator().configureModule(new ContactsUIModuleConfiguration());
	}
}
