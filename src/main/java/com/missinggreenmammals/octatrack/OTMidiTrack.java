package com.missinggreenmammals.octatrack;

import java.util.Arrays;

import com.bitwig.extension.controller.api.AbsoluteHardwareKnob;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareButton;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MidiIn;
import com.bitwig.extension.controller.api.MidiOut;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public abstract class OTMidiTrack {

	public static final int CC1 = 7;
	public static final int CC2 = 1;
	public static final int CC3 = 2;
	public static final int CC4 = 10;
	public static final int CC5 = 71;
	public static final int CC6 = 72;
	public static final int CC7 = 73;
	public static final int CC8 = 74;
	public static final int CC9 = 75;
	public static final int CC10 = 76;

	protected final int channel;
	protected final int trackNumber;
	protected final String name;
	protected final HardwareSurface hardwareSurface;
	protected final MidiIn midiIn;
	protected final MidiOut midiOut;
	protected final ControllerHost host;
	protected int[] ccCache;

	protected final AbsoluteHardwareKnob pbKnob;
	protected final AbsoluteHardwareKnob cc1knob;
	protected final AbsoluteHardwareKnob cc2knob;
	protected final AbsoluteHardwareKnob cc3knob;
	protected final AbsoluteHardwareKnob cc4knob;
	protected final AbsoluteHardwareKnob cc5knob;
	protected final AbsoluteHardwareKnob cc6knob;
	protected final AbsoluteHardwareKnob cc7knob;
	protected final AbsoluteHardwareKnob cc8knob;
	protected final AbsoluteHardwareKnob cc9knob;
	protected final AbsoluteHardwareKnob cc10knob;
	protected final TrackBank trackBank;

	public OTMidiTrack(final String name, final int channel, final int trackNumber, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		midiIn = host.getMidiInPort(0);
		midiOut = host.getMidiOutPort(0);
		this.host = host;
		this.channel = channel;
		this.trackNumber = trackNumber;
		this.name = name;
		this.hardwareSurface = hardwareSurface;
		trackBank = host.createMainTrackBank(11, 0, 0);
		ccCache = new int[11];
		Arrays.fill(ccCache, -1);

		pbKnob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("PB"));
		cc1knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC1"));
		cc2knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC2"));
		cc3knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC3"));
		cc4knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC4"));
		cc5knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC5"));
		cc6knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC6"));
		cc7knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC7"));
		cc8knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC8"));
		cc9knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC9"));
		cc10knob = hardwareSurface
				.createAbsoluteHardwareKnob(createId("CC10"));

		initValueMatchers(channel);
		setBindings(host);
		initButtons(channel, host);
	}

	private void initButtons(final int channel, final ControllerHost host) {
		final HardwareButton playButton = this.hardwareSurface
				.createHardwareButton(createId("PLAY"));
		final HardwareButton stopButton = this.hardwareSurface
				.createHardwareButton(createId("STOP"));
		final Transport transport = host.createTransport();
		playButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 48));
		stopButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 50));
		playButton.pressedAction().setBinding(transport.playAction());
		stopButton.pressedAction().setBinding(transport.stopAction());

	}

	private void initValueMatchers(final int channel) {
		pbKnob.setAdjustValueMatcher(midiIn.createAbsolutePitchBendValueMatcher(channel));
		cc1knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC1));
		cc2knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC2));
		cc3knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC3));
		cc4knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC4));
		cc5knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC5));
		cc6knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC6));
		cc7knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC7));
		cc8knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC8));
		cc9knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC9));
		cc10knob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC10));
	}

	protected abstract void setBindings(ControllerHost host);
	
	protected abstract void updateCCs();

	private String createId(String name) {
		return String.format("OT_MIDI_%d_%d_%s", trackNumber, channel, name);
	}

}
