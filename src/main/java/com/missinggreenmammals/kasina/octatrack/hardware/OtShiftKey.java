package com.missinggreenmammals.kasina.octatrack.hardware;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.HardwareSurface;

/**
 * Represents the 'shift' key on the Octatrack, which is in fact the [PAGE] key.
 *
 */
public class OtShiftKey extends OtKey {
	
	/**
	 * The {@link HardwareBindable} that represents the binding when the shift key is pressed
	 */
	private final HardwareBindable noteOnBinding;
	
	/**
	 * The {@link HardwareBindable} that represents the binding when the shift key is released
	 */
	private final HardwareBindable shiftBinding;

	public OtShiftKey(final int midiNote, final int midiChannel, final int otTrack, final String id, final HardwareBindable noteOnBinding, final HardwareBindable shiftBinding, final ControllerHost host, final HardwareSurface hardwareSurface) {
		super(midiNote, midiChannel, otTrack, id, host, hardwareSurface);
		
		this.noteOnBinding = noteOnBinding;
		this.shiftBinding = shiftBinding;
		button.releasedAction().setActionMatcher(host.getMidiInPort(0).createNoteOffActionMatcher(midiChannel, midiNote));
	}

}
