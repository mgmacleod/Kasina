package com.missinggreenmammals.octatrack.track.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.octatrack.layout.OTTransportLayout;
import com.missinggreenmammals.octatrack.layout.monoparam.OTVolumeMonoLayout;
import com.missinggreenmammals.octatrack.track.OTMidiTrack;

public class OTVolumes extends OTMidiTrack {

	public OTVolumes(ControllerHost host, HardwareSurface hardwareSurface) {
		this("VOLUMES", 8, 1, host, hardwareSurface);
	}

	private OTVolumes(String name, int channel, int trackNumber, ControllerHost host, HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected OTTransportLayout createLayout(ControllerHost host) {
		return new OTVolumeMonoLayout(host);
	}

}