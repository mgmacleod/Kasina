package com.missinggreenmammals.octatrack;

import java.util.Arrays;
import java.util.List;

import com.bitwig.extension.controller.api.AbsoluteHardwareControlBinding;
import com.bitwig.extension.controller.api.AbsoluteHardwareKnob;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareActionBinding;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.HardwareButton;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MidiIn;
import com.bitwig.extension.controller.api.MidiOut;

public class OTMidiHardwareControls {
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
	protected List<AbsoluteHardwareKnob> ccKnobs;

	protected final HardwareButton playButton;
	protected final HardwareButton stopButton;
	protected final HardwareButton prevButton;
	protected final HardwareButton nextButton;

	protected final MidiIn midiIn;
	protected final MidiOut midiOut;

	protected final int channel;
	protected final int trackNumber;

	public OTMidiHardwareControls(final int channel, final int trackNumber, final ControllerHost host,
			final HardwareSurface hardwareSurface) {
		midiIn = host.getMidiInPort(0);
		midiOut = host.getMidiOutPort(0);
		this.channel = channel;
		this.trackNumber = trackNumber;

		pbKnob = hardwareSurface.createAbsoluteHardwareKnob(createId("PB"));
		cc1knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC1"));
		cc2knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC2"));
		cc3knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC3"));
		cc4knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC4"));
		cc5knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC5"));
		cc6knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC6"));
		cc7knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC7"));
		cc8knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC8"));
		cc9knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC9"));
		cc10knob = hardwareSurface.createAbsoluteHardwareKnob(createId("CC10"));

		ccKnobs = Arrays.asList(cc1knob, cc2knob, cc3knob, cc4knob, cc5knob, cc6knob, cc7knob, cc8knob, cc9knob,
				cc10knob);

		playButton = hardwareSurface.createHardwareButton(createId("PLAY"));
		stopButton = hardwareSurface.createHardwareButton(createId("STOP"));
		prevButton = hardwareSurface.createHardwareButton(createId("PREV"));
		nextButton = hardwareSurface.createHardwareButton(createId("NEXT"));

		initValueMatchers();

//		playButton.pressedAction().setBinding(transport.playAction());
////	stopButton.pressedAction().setBinding(transport.stopAction());
	}

	public HardwareActionBinding bindToPlayButton(HardwareBindable bindable) {
		return playButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToStopButton(HardwareBindable bindable) {
		return stopButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToNextButton(HardwareBindable bindable) {
		return nextButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToPrevButton(HardwareBindable bindable) {
		return prevButton.pressedAction().setBinding(bindable);
	}

	public AbsoluteHardwareControlBinding bindToCC1Knob(HardwareBindable param) {
		return cc1knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC2Knob(HardwareBindable param) {
		return cc2knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC3Knob(HardwareBindable param) {
		return cc3knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC4Knob(HardwareBindable param) {
		return cc4knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC5Knob(HardwareBindable param) {
		return cc5knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC6Knob(HardwareBindable param) {
		return cc6knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC7Knob(HardwareBindable param) {
		return cc7knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC8Knob(HardwareBindable param) {
		return cc8knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC9Knob(HardwareBindable param) {
		return cc9knob.setBinding(param);
	}

	public AbsoluteHardwareControlBinding bindToCC10Knob(HardwareBindable param) {
		return cc10knob.setBinding(param);
	}

	private void initValueMatchers() {
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

		playButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 48));
		stopButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 50));

		prevButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 52));
		nextButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 53));
	}

	private String createId(String name) {
		return String.format("OT_MIDI_%d_%d_%s", trackNumber, channel, name);
	}
}
