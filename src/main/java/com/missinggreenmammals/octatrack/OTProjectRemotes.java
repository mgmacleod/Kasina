package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class OTProjectRemotes extends OTMidiTrack {

	public OTProjectRemotes(ControllerHost host, HardwareSurface hardwareSurface) {
		this("PROJECT_REMOTES", 13, 6, host, hardwareSurface);
	}

	private OTProjectRemotes(String name, int channel, int trackNumber, ControllerHost host,
			HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected OTConfiguration createConfig(ControllerHost host) {
		return new OTProjectRemoteConfig(host);
	}



}
