package com.missinggreenmammals.kasina.octatrack.config;

import com.missinggreenmammals.kasina.octatrack.track.OTMidiTrack;

public abstract class OTMidiConfiguration {

	protected OTMidiTrack[] tracks;

	public OTMidiConfiguration() {
		tracks = new OTMidiTrack[8];
	}
	
	public abstract void doPersistence();

}
