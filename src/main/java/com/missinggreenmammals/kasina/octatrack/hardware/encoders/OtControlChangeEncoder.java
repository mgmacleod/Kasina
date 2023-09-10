package com.missinggreenmammals.kasina.octatrack.hardware.encoders;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class OtControlChangeEncoder extends OtEncoder {

	public OtControlChangeEncoder(final int midiChannel, final int otTrack, final int ccNumber, final String name, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		super(midiChannel, otTrack, name, host, hardwareSurface);

		knob.setAdjustValueMatcher(host.getMidiInPort(0).createAbsoluteCCValueMatcher(midiChannel, ccNumber));
	}

}
