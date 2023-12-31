package com.github.mgmacleod.kasina.octatrack;

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
		octatrack = new Octatrack(getHost());
	}

	@Override
	public void exit() {
		return;
	}

	@Override
	public void flush() {
		return;
	}

}
