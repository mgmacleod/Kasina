package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.DocumentState;
import com.bitwig.extension.controller.api.SettableEnumValue;

public class OctatrackExtension extends ControllerExtension {



	private Octatrack octatrack;

	protected OctatrackExtension(ControllerExtensionDefinition definition, ControllerHost host) {
		super(definition, host);
	}

	@Override
	public void init() {
		final ControllerHost host = getHost();
		host.showPopupNotification("Octatrack Initialized");
		
		final DocumentState documentState = host.getDocumentState();
		final SettableEnumValue configTypeSetting = documentState.getEnumSetting("Type", "Configuration",
				Octatrack.CONFIG_TYPE_OPTIONS, Octatrack.CONFIG_TYPE_OPTIONS[0]);

		octatrack = new Octatrack(host, configTypeSetting);
	}

	@Override
	public void exit() {
		getHost().showPopupNotification("Octatrack Exited");
	}

	@Override
	public void flush() {
	}

}
