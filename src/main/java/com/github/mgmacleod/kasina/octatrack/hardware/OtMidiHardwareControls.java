package com.github.mgmacleod.kasina.octatrack.hardware;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.github.mgmacleod.kasina.octatrack.hardware.encoders.OtAftertouchEncoder;
import com.github.mgmacleod.kasina.octatrack.hardware.encoders.OtControlChangeEncoder;
import com.github.mgmacleod.kasina.octatrack.hardware.encoders.OtPitchBendEncoder;
import com.github.mgmacleod.kasina.octatrack.hardware.keys.OtKeyboard;

import lombok.Getter;

/**
 * Represents the hardware controls on the Octatrack. Leverages
 * {@link OtKeyboard} to receive messages from the trig keys and page key, which
 * is used as a shift key. Also represents all of the encoders and handles their
 * messages. Doesn't deal with Bitwig functionality directly but is focused on
 * hardware communication.
 */
@Getter
public class OtMidiHardwareControls {

	public static final int NUM_CC_ENCODERS = 10;

	private static final int[] CC_NUMS = { 7, 1, 2, 10, 71, 72, 73, 74, 75, 76 };

	private final int midiChannel;
	private final int otTrack;
	private final OtKeyboard keyboard;
	private final OtPitchBendEncoder pbEncoder;
	private final OtAftertouchEncoder atEncoder;

	private final OtControlChangeEncoder[] ccEncoders;

	public OtMidiHardwareControls(final int midiChannel, final int otTrack, final ControllerHost host,
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
