package com.github.mgmacleod.kasina.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.github.mgmacleod.kasina.octatrack.config.OtDefaultConfiguration;

/**
 * Simple class to instantiate an {@link OtDefaultConfiguration} and pass it an
 * instance of {@link HardwareSurface}. Could probably be eliminated in the
 * future.
 */
public class Octatrack {

	private final HardwareSurface hardwareSurface;
	@SuppressWarnings("unused")
	private final OtDefaultConfiguration config;

	public Octatrack(final ControllerHost host) {
		hardwareSurface = host.createHardwareSurface();
		config = new OtDefaultConfiguration(host, hardwareSurface);
	}

}
