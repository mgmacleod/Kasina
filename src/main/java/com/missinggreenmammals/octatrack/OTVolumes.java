package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class OTVolumes extends OTMidiTrack {

	public OTVolumes(ControllerHost host, HardwareSurface hardwareSurface) {
		this("VOLUMES", 8, 1, host, hardwareSurface);
	}

	private OTVolumes(String name, int channel, int trackNumber, ControllerHost host, HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected OTTransportLayout createConfig(ControllerHost host) {
		return new OTVolumeConfig(host);
	}

}
