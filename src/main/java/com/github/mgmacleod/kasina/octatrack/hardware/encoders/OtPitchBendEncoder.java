package com.github.mgmacleod.kasina.octatrack.hardware.encoders;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class OtPitchBendEncoder extends OtEncoder {

	public OtPitchBendEncoder(final int midiChannel, final int otTrack, final String name, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		super(midiChannel, otTrack, name, host, hardwareSurface);

		knob.setAdjustValueMatcher(host.getMidiInPort(0).createAbsolutePitchBendValueMatcher(midiChannel));
	}
}
