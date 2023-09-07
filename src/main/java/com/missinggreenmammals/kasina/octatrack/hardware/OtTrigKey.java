package com.missinggreenmammals.kasina.octatrack.hardware;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.HardwareSurface;

import lombok.Setter;

/**
 * Represents a key on the chromatic keyboard of the Octatrack with support for shift functions.
 *
 */
@Setter
public class OtTrigKey extends OtKey implements Shiftable {
	
	/**
	 * The {@link HardwareBindable} that represents the regular, non-shift binding
	 */
	private HardwareBindable regularBinding;
	
	/**
	 * The {@link HardwareBindable} that represents the shift binding
	 */
	private HardwareBindable shiftBinding;
	
	public OtTrigKey(final int midiNote, final int midiChannel, final int otTrack, final String id, 
			final ControllerHost host, final HardwareSurface hardwareSurface) {
		
		super(midiNote, midiChannel, otTrack, id, host, hardwareSurface);
		
		disableShiftMode();
	}

	@Override
	public void enableShiftMode() {
		button.pressedAction().setBinding(shiftBinding);
	}

	@Override
	public void disableShiftMode() {
		button.pressedAction().setBinding(regularBinding);
	}

}
