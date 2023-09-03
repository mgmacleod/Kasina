package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

public class OctatrackExtension extends ControllerExtension {
	@SuppressWarnings("unused")
	private Octatrack octatrack;

	protected OctatrackExtension(ControllerExtensionDefinition definition, ControllerHost host) {
		super(definition, host);
	}

	@Override
	public void init() {
		final ControllerHost host = getHost();
		host.showPopupNotification("Octatrack Initialized");

		octatrack = new Octatrack(host);
	}

	@Override
	public void exit() {
		getHost().showPopupNotification("Octatrack Exited");
	}

	@Override
	public void flush() {
	}

}
