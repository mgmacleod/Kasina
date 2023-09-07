package com.missinggreenmammals.kasina.octatrack.hardware;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareButton;
import com.bitwig.extension.controller.api.HardwareSurface;

/** 
 * Represents an abstract key on the Octatrack.
 */
public abstract class OtKey extends OtHardwareElement {
	
	protected final HardwareButton button;
	protected final int midiNote;
	
	public OtKey(final int midiNote, final int midiChannel, final int otTrack, final String id, final ControllerHost host,
			final HardwareSurface hardwareSurface) {
		super(midiChannel, otTrack);

		this.midiNote = midiNote;

		button = hardwareSurface.createHardwareButton(id);
		button.pressedAction().setActionMatcher(host.getMidiInPort(0).createNoteOnActionMatcher(midiChannel, midiNote));
	}

	public int getMidiNote() {
		return midiNote;
	}

}
