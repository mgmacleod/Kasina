package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.octatrack.layout.OTTransportLayout;

public abstract class OTMidiTrack {

	protected final String name;
	protected final HardwareSurface hardwareSurface;
	protected final ControllerHost host;
	protected final OTMidiHardwareControls controls;
	protected final OTTransportLayout config;

	public OTMidiTrack(final String name, final int channel, final int trackNumber, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		this.host = host;
		this.name = name;
		this.hardwareSurface = hardwareSurface;

		controls = new OTMidiHardwareControls(channel, trackNumber, host, hardwareSurface);
		config = createConfig(host);
		config.applyTo(controls);
	}

	protected abstract OTTransportLayout createConfig(ControllerHost host);

}
