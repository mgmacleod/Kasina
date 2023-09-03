package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.octatrack.config.OTMidiConfiguration;
import com.missinggreenmammals.octatrack.config.OTPolyParamConfig;

public class Octatrack {
	public static final String[] CONFIG_TYPE_OPTIONS = { "Single", "Multi" };

	private ControllerHost host;
	private HardwareSurface hardwareSurface;
	private OTMidiConfiguration config;

	public Octatrack(ControllerHost host) {
		this.host = host;
		hardwareSurface = host.createHardwareSurface();
		config = new OTPolyParamConfig(host, hardwareSurface);
	}

}
