package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.octatrack.config.OTMidiConfiguration;
import com.missinggreenmammals.octatrack.config.OTDefaultParamConfig;

public class Octatrack {

	private HardwareSurface hardwareSurface;
	@SuppressWarnings("unused")
	private OTMidiConfiguration config;

	public Octatrack(ControllerHost host) {
		hardwareSurface = host.createHardwareSurface();
		config = new OTDefaultParamConfig(host, hardwareSurface);
	}

}
