package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.TrackBank;

public abstract class OTTrackBasedMidiTrack extends OTMidiTrack {
	protected TrackBank trackBank;

	public OTTrackBasedMidiTrack(String name, int channel, int trackNumber, ControllerHost host,
			HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}



}
