package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class OTPans extends OTMidiTrack {

	public OTPans(ControllerHost host, HardwareSurface hardwareSurface) {
		this("PANS", 9, 2, host, hardwareSurface);
	}

	private OTPans(String name, int channel, int trackNumber, ControllerHost host, HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected OTConfiguration createConfig(ControllerHost host) {
		return new OTPanConfig(host);
	}



}
