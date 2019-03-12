package org.dominokit.samples.components.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="Components")
public class ComponentsClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComponentsClientModule.class);

	public void onModuleLoad() {
	}
}
