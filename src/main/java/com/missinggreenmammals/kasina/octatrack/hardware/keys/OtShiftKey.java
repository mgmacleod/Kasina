package com.missinggreenmammals.kasina.octatrack.hardware.keys;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.HardwareSurface;

/**
 * Represents the 'shift' key on the Octatrack, which is in fact the [PAGE] key.
 *
 */
public class OtShiftKey extends OtKey {

	public OtShiftKey(final int midiNote, final int midiChannel, final int otTrack, final String name,
			final ControllerHost host, final HardwareSurface hardwareSurface) {

		super(midiNote, midiChannel, otTrack, name, host, hardwareSurface);
		
		button.releasedAction().setActionMatcher(host.getMidiInPort(0).createNoteOffActionMatcher(midiChannel, midiNote));
	}

	public void setNoteOnBinding(final HardwareBindable bindable) {
		button.pressedAction().setBinding(bindable);
	}

	public void setNoteOffBinding(final HardwareBindable bindable) {
		button.releasedAction().setBinding(bindable);
	}

}
