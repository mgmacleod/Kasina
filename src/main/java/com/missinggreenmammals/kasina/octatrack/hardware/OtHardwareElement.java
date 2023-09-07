package com.missinggreenmammals.kasina.octatrack.hardware;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * represents an abstract HardwareElement
 *
 */
@RequiredArgsConstructor
@Getter
public abstract class OtHardwareElement {
	
	protected final int midiChannel;
	protected final int track;
	
	protected String createId(String name) {
		return String.format("OT_MIDI_%d_%d_%s", track, midiChannel, name);
	}

}
