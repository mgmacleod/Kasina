package com.missinggreenmammals.kasina.octatrack.config;

import com.missinggreenmammals.kasina.octatrack.track.OtMidiTrack;

public abstract class OtMidiConfiguration {

	protected OtMidiTrack[] tracks;

	public OtMidiConfiguration() {
		tracks = new OtMidiTrack[8];
	}
	
}
