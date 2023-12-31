package com.github.mgmacleod.kasina.octatrack.hardware.keys;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareButton;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.github.mgmacleod.kasina.octatrack.hardware.OtHardwareElement;

/**
 * Represents an abstract key on the Octatrack.
 */
public abstract class OtKey extends OtHardwareElement {

	protected final HardwareButton button;
	protected final int midiNote;

	public OtKey(final int midiNote, final int midiChannel, final int otTrack, final String name,
			final ControllerHost host,
			final HardwareSurface hardwareSurface) {
		super(midiChannel, otTrack);

		this.midiNote = midiNote;

		button = hardwareSurface.createHardwareButton(createId(name));
		button.pressedAction().setActionMatcher(host.getMidiInPort(0).createNoteOnActionMatcher(midiChannel, midiNote));
	}

	public int getMidiNote() {
		return midiNote;
	}

	public void clearBindings() {
		button.pressedAction().clearBindings();
	}

}
