package com.missinggreenmammals.kasina.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.kasina.octatrack.config.OtDefaultConfiguration;

public class Octatrack {

	private HardwareSurface hardwareSurface;
	@SuppressWarnings("unused")
	private OtDefaultConfiguration config;

	public Octatrack(ControllerHost host) {
		hardwareSurface = host.createHardwareSurface();
		config = new OtDefaultConfiguration(host, hardwareSurface);
	}

}
