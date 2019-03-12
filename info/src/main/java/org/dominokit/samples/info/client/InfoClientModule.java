package org.dominokit.samples.info.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Info")
public class InfoClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfoClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing Info frontend module ...");
		new ModuleConfigurator().configureModule(new InfoModuleConfiguration());
	}
}
