package com.missinggreenmammals.octatrack.track.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.octatrack.layout.OTTransportLayout;
import com.missinggreenmammals.octatrack.layout.monoparam.OTProjectRemoteMonoLayout;
import com.missinggreenmammals.octatrack.track.OTMidiTrack;

public class OTProjectRemotes extends OTMidiTrack {

	public OTProjectRemotes(ControllerHost host, HardwareSurface hardwareSurface) {
		this("PROJECT_REMOTES", 13, 6, host, hardwareSurface);
	}

	private OTProjectRemotes(String name, int channel, int trackNumber, ControllerHost host,
			HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected OTTransportLayout createLayout(ControllerHost host) {
		return new OTProjectRemoteMonoLayout(host);
	}



}
