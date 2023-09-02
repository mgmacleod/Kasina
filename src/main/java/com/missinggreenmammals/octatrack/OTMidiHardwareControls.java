package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.AbsoluteHardwareKnob;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareActionBinding;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.HardwareButton;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MidiIn;
import com.bitwig.extension.controller.api.MidiOut;
import com.missinggreenmammals.octatrack.layout.OTMidiTrackLayout;

public class OTMidiHardwareControls {
	private static final int[] CC_NUMS = { 7, 1, 2, 10, 71, 72, 73, 74, 75, 76 };
//	private static final int[] BUTTON_NUMS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58 };

	protected final AbsoluteHardwareKnob pbKnob;
	protected AbsoluteHardwareKnob[] ccKnobs;

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
		ccKnobs = new AbsoluteHardwareKnob[OTMidiTrackLayout.PAGE_SIZE];

		for (int i = 0; i < OTMidiTrackLayout.PAGE_SIZE; i++) {
			ccKnobs[i] = hardwareSurface.createAbsoluteHardwareKnob(createId("CC" + (i + 1)));
		}

		playButton = hardwareSurface.createHardwareButton(createId("PLAY"));
		stopButton = hardwareSurface.createHardwareButton(createId("STOP"));
		prevButton = hardwareSurface.createHardwareButton(createId("PREV"));
		nextButton = hardwareSurface.createHardwareButton(createId("NEXT"));

		initValueMatchers();

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

	private void initValueMatchers() {
		pbKnob.setAdjustValueMatcher(midiIn.createAbsolutePitchBendValueMatcher(channel));

		for (int i = 0; i < ccKnobs.length; i++) {
			ccKnobs[i].setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC_NUMS[i]));
		}

		playButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 48));
		stopButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 49));
		prevButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 50));
		nextButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 51));
	}

	private String createId(String name) {
		return String.format("OT_MIDI_%d_%d_%s", trackNumber, channel, name);
	}

	public AbsoluteHardwareKnob[] getCcKnobs() {
		return ccKnobs;
	}

	public int getChannel() {
		return channel;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

}
