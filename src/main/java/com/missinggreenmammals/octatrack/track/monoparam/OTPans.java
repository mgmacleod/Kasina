package com.missinggreenmammals.octatrack.track.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.octatrack.layout.OTTransportLayout;
import com.missinggreenmammals.octatrack.layout.monoparam.OTPanMonoLayout;
import com.missinggreenmammals.octatrack.track.OTMidiTrack;

public class OTPans extends OTMidiTrack {

	public OTPans(ControllerHost host, HardwareSurface hardwareSurface) {
		this("PANS", 9, 2, host, hardwareSurface);
	}

	private OTPans(String name, int channel, int trackNumber, ControllerHost host, HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected OTTransportLayout createLayout(ControllerHost host) {
		return new OTPanMonoLayout(host);
	}



}