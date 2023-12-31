package com.github.mgmacleod.kasina.octatrack.config;

import com.github.mgmacleod.kasina.octatrack.track.OtMidiTrack;

public abstract class OtMidiConfiguration {

	protected OtMidiTrack[] tracks;

	public OtMidiConfiguration() {
		tracks = new OtMidiTrack[8];
	}

}
