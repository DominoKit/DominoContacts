package org.dominokit.samples.contacts.layout.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Layout")
public class LayoutClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(LayoutClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Layout frontend module ...");
		new ModuleConfigurator().configureModule(new LayoutModuleConfiguration());
	}
}
