package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.SettableEnumValue;
import com.missinggreenmammals.octatrack.config.OTMidiConfiguration;
import com.missinggreenmammals.octatrack.config.OTPolyParamConfig;

public class Octatrack {
	public static final String[] CONFIG_TYPE_OPTIONS = { "Single", "Multi" };

	private ControllerHost host;
	private HardwareSurface hardwareSurface;
	private OTMidiConfiguration config;

	public Octatrack(ControllerHost host, SettableEnumValue configTypeSetting) {
		this.host = host;
		hardwareSurface = host.createHardwareSurface();
		config = new OTPolyParamConfig(host, hardwareSurface);
	}

//	public void updateConfigType(final String type) {
//		config = null;
//
//		switch (type) {
//		case "Single":
//			config = new OTMonoParamConfig(host, hardwareSurface);
//			return;
//
//		case "Multi":
//			config = null;// TODO the real thing
//			return;
//
//		default:
//			return;
//
//		}
//	}

}
