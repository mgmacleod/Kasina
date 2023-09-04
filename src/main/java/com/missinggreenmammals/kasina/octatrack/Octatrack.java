package com.missinggreenmammals.kasina.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.kasina.octatrack.config.OTDefaultParamConfig;
import com.missinggreenmammals.kasina.octatrack.config.OTMidiConfiguration;

public class Octatrack {

	private HardwareSurface hardwareSurface;
	@SuppressWarnings("unused")
	private OTMidiConfiguration config;

	public Octatrack(ControllerHost host) {
		hardwareSurface = host.createHardwareSurface();
		config = new OTDefaultParamConfig(host, hardwareSurface);
	}

}
