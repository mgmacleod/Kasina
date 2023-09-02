package com.missinggreenmammals.octatrack.config;

import com.missinggreenmammals.octatrack.track.OTMidiTrack;

public abstract class OTMidiConfiguration {

	protected OTMidiTrack[] tracks;

	public OTMidiConfiguration() {
		tracks = new OTMidiTrack[8];
	}

}
