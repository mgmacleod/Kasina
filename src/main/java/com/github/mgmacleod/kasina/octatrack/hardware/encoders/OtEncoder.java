package com.github.mgmacleod.kasina.octatrack.hardware.encoders;

import com.bitwig.extension.controller.api.AbsoluteHardwareKnob;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.github.mgmacleod.kasina.octatrack.hardware.OtHardwareElement;

import lombok.Getter;

@Getter
public abstract class OtEncoder extends OtHardwareElement {

	protected final AbsoluteHardwareKnob knob;

	public OtEncoder(final int midiChannel, final int otTrack, final String name, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		super(midiChannel, otTrack);

		knob = hardwareSurface.createAbsoluteHardwareKnob(createId(name));
	}

	public void setBinding(final HardwareBindable bindable) {
		knob.setBinding(bindable);
	}

}
