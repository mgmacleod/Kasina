package com.missinggreenmammals.kasina.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.kasina.octatrack.config.OTDefaultParamConfig;

public class Octatrack {

	private HardwareSurface hardwareSurface;
	@SuppressWarnings("unused")
	private OTDefaultParamConfig config;

	public Octatrack(ControllerHost host) {
		hardwareSurface = host.createHardwareSurface();
		config = new OTDefaultParamConfig(host, hardwareSurface);
	}

}
