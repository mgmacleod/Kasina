package com.github.mgmacleod.kasina.octatrack.hardware.encoders;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class OtAftertouchEncoder extends OtEncoder {

	public OtAftertouchEncoder(final int midiChannel, final int otTrack, final String name, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		super(midiChannel, otTrack, name, host, hardwareSurface);

		knob.setAdjustValueMatcher(
				host.getMidiInPort(0).createAbsoluteValueMatcher(createAftertouchExpression(), "data1", 7));
	}

	private String createAftertouchExpression() {
		return String.format("status == 0xD%s", Integer.toHexString(midiChannel));
	}

}
