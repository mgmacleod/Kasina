package com.missinggreenmammals.kasina.octatrack.hardware;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents an abstract HardwareElement and contains the basic properties
 * common to all, which are the MIDI channel and the OT track they are on.
 *
 */
@RequiredArgsConstructor
@Getter
public abstract class OtHardwareElement {
	
	protected final int midiChannel;
	protected final int track;
	
	protected String createId(final String name) {
		return String.format("OT_MIDI_%d_%d_%s", track, midiChannel, name);
	}

}
