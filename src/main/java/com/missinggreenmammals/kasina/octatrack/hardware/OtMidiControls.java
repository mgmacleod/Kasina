package com.missinggreenmammals.kasina.octatrack.hardware;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.kasina.octatrack.hardware.encoders.OtAftertouchEncoder;
import com.missinggreenmammals.kasina.octatrack.hardware.encoders.OtControlChangeEncoder;
import com.missinggreenmammals.kasina.octatrack.hardware.encoders.OtPitchBendEncoder;
import com.missinggreenmammals.kasina.octatrack.hardware.keys.OtKeyboard;

import lombok.Getter;

@Getter
public class OtMidiControls {
	public static final int NUM_CC_ENCODERS = 10;

	private static final int[] CC_NUMS = { 7, 1, 2, 10, 71, 72, 73, 74, 75, 76 };

	private final int midiChannel;
	private final int otTrack;
	private final OtKeyboard keyboard;
	private final OtPitchBendEncoder pbEncoder;
	private final OtAftertouchEncoder atEncoder;

	private final OtControlChangeEncoder[] ccEncoders;

	public OtMidiControls(final int midiChannel, final int otTrack, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		this.midiChannel = midiChannel;
		this.otTrack = otTrack;

		keyboard = new OtKeyboard(midiChannel, otTrack, host, hardwareSurface);
		pbEncoder = new OtPitchBendEncoder(midiChannel, otTrack, "PB", host, hardwareSurface);
		atEncoder = new OtAftertouchEncoder(midiChannel, otTrack, "AT", host, hardwareSurface);
		ccEncoders = new OtControlChangeEncoder[NUM_CC_ENCODERS];

		initCcEncoders(host, hardwareSurface);
	}

	private void initCcEncoders(final ControllerHost host, final HardwareSurface hardwareSurface) {
		for (int i = 0; i < ccEncoders.length; i++) {
			ccEncoders[i] = new OtControlChangeEncoder(midiChannel, otTrack, CC_NUMS[i], "CC" + i, host,
					hardwareSurface);
		}
	}

}
