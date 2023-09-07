package com.missinggreenmammals.kasina.octatrack.hardware.keys;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.HardwareSurface;

import lombok.Setter;

/**
 * Represents the 'shift' key on the Octatrack, which is in fact the [PAGE] key.
 *
 */
@Setter
public class OtShiftKey extends OtKey {
	
	/**
	 * The {@link HardwareBindable} that represents the binding when the shift key is pressed
	 */
	private HardwareBindable noteOnBinding;
	
	/**
	 * The {@link HardwareBindable} that represents the binding when the shift key is released
	 */
	private HardwareBindable noteOffBinding;

	public OtShiftKey(final int midiNote, final int midiChannel, final int otTrack, final String name,
			final ControllerHost host, final HardwareSurface hardwareSurface) {

		super(midiNote, midiChannel, otTrack, name, host, hardwareSurface);
		
		button.releasedAction().setActionMatcher(host.getMidiInPort(0).createNoteOffActionMatcher(midiChannel, midiNote));
	}

}
