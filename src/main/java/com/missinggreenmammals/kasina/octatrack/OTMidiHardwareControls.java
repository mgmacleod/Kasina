package com.missinggreenmammals.kasina.octatrack;

import com.bitwig.extension.controller.api.AbsoluteHardwareKnob;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareActionBinding;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.HardwareButton;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MidiIn;
import com.bitwig.extension.controller.api.MidiOut;
import com.missinggreenmammals.kasina.octatrack.layout.OTMidiTrackLayout;

public class OTMidiHardwareControls {
	private static final int[] CC_NUMS = { 7, 1, 2, 10, 71, 72, 73, 74, 75, 76 };
//	private static final int[] BUTTON_NUMS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64 };

	private final AbsoluteHardwareKnob pbKnob;
	private final AbsoluteHardwareKnob atKnob;
	private AbsoluteHardwareKnob[] ccKnobs;

	private final HardwareButton recordButton;
	private final HardwareButton playButton;
	private final HardwareButton stopButton;
	private final HardwareButton prevButton;
	private final HardwareButton nextButton;
	private final HardwareButton selectTrackButton;
	private final HardwareButton remotePagePrevButton;
	private final HardwareButton remotePageNextButton;
	private final HardwareButton cursorDevicePrevButton;
	private final HardwareButton cursorDeviceNextButton;
	private final HardwareButton remoteModeButton;
	private final HardwareButton toggleMetronomeButton;
	private final HardwareButton toggleTransportLoopButton;

	private final HardwareButton trackMuteButton;
	private final HardwareButton trackSoloButton;
	private final HardwareButton trackRecordEnableButton;

	private final MidiIn midiIn;
	private final MidiOut midiOut;

	private final int channel;
	private final int trackNumber;

	public OTMidiHardwareControls(final int channel, final int trackNumber, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		midiIn = host.getMidiInPort(0);
		midiOut = host.getMidiOutPort(0);
		this.channel = channel;
		this.trackNumber = trackNumber;

		pbKnob = hardwareSurface.createAbsoluteHardwareKnob(createId("PB"));
		atKnob = hardwareSurface.createAbsoluteHardwareKnob(createId("AT"));
		ccKnobs = new AbsoluteHardwareKnob[OTMidiTrackLayout.PAGE_SIZE];

		for (int i = 0; i < OTMidiTrackLayout.PAGE_SIZE; i++) {
			ccKnobs[i] = hardwareSurface.createAbsoluteHardwareKnob(createId("CC" + (i + 1)));
		}

		recordButton = hardwareSurface.createHardwareButton(createId("RECORD"));
		playButton = hardwareSurface.createHardwareButton(createId("PLAY"));
		stopButton = hardwareSurface.createHardwareButton(createId("STOP"));
		prevButton = hardwareSurface.createHardwareButton(createId("PREV"));
		nextButton = hardwareSurface.createHardwareButton(createId("NEXT"));
		toggleMetronomeButton = hardwareSurface.createHardwareButton(createId("METRO"));
		toggleTransportLoopButton = hardwareSurface.createHardwareButton(createId("LOOP"));

		selectTrackButton = hardwareSurface.createHardwareButton(createId("TRACK_SELECT"));
		remotePagePrevButton = hardwareSurface.createHardwareButton(createId("SUB_PREV1"));
		remotePageNextButton = hardwareSurface.createHardwareButton(createId("SUB_NEXT1"));
		cursorDevicePrevButton = hardwareSurface.createHardwareButton(createId("SUB_PREV2"));
		cursorDeviceNextButton = hardwareSurface.createHardwareButton(createId("SUB_NEXT2"));
		remoteModeButton = hardwareSurface.createHardwareButton(createId("REMOTE_MODE"));

		trackMuteButton = hardwareSurface.createHardwareButton(createId("MUTE"));
		trackSoloButton = hardwareSurface.createHardwareButton(createId("SOLO"));
		trackRecordEnableButton = hardwareSurface.createHardwareButton(createId("REC_ENABLE"));

		initValueMatchers();

	}

	public HardwareActionBinding bindToRecordButton(HardwareBindable bindable) {
		return recordButton.pressedAction().setBinding(bindable);
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

	public HardwareActionBinding bindToToggleTransportLoopButton(HardwareBindable bindable) {
		return toggleTransportLoopButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToToggleMetronomeButton(HardwareBindable bindable) {
		return toggleMetronomeButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToRemotePageNextButton(HardwareBindable bindable) {
		return remotePageNextButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToRemotePagePrevButton(HardwareBindable bindable) {
		return remotePagePrevButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToCursorDeviceNextButton(HardwareBindable bindable) {
		return cursorDeviceNextButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToCursorDevicePrevButton(HardwareBindable bindable) {
		return cursorDevicePrevButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToRemoteModeButton(HardwareBindable bindable) {
		return remoteModeButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToSelectTrackButton(HardwareBindable bindable) {
		return selectTrackButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToTrackMuteButton(HardwareBindable bindable) {
		return trackMuteButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToTrackSoloButton(HardwareBindable bindable) {
		return trackSoloButton.pressedAction().setBinding(bindable);
	}

	public HardwareActionBinding bindToTrackRecordEnableButton(HardwareBindable bindable) {
		return trackRecordEnableButton.pressedAction().setBinding(bindable);
	}

	public void clearCursorDeviceButtonBindings() {
		cursorDeviceNextButton.pressedAction().clearBindings();
		cursorDevicePrevButton.pressedAction().clearBindings();
	}

	private void initValueMatchers() {
		final String expression = createAftertouchExpression();
		pbKnob.setAdjustValueMatcher(midiIn.createAbsolutePitchBendValueMatcher(channel));
		atKnob.setAdjustValueMatcher(midiIn.createAbsoluteValueMatcher(expression, "data1", 7));

		for (int i = 0; i < ccKnobs.length; i++) {
			ccKnobs[i].setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(channel, CC_NUMS[i]));
		}


		remotePagePrevButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 48));
		remotePageNextButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 49));
		cursorDevicePrevButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 50));
		cursorDeviceNextButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 51));
		remoteModeButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 52));
		selectTrackButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 53));
		trackRecordEnableButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 54));
		trackSoloButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 55));
		trackMuteButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 56));
		toggleMetronomeButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 57));
		toggleTransportLoopButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 58));
		recordButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 59));
		playButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 60));
		stopButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 61));
		prevButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 62));
		nextButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 63));
	}

	private String createId(String name) {
		return String.format("OT_MIDI_%d_%d_%s", trackNumber, channel, name);
	}
	
	private String createAftertouchExpression() {
		return String.format("status == 0xD%s", Integer.toHexString(channel));
	}

	public AbsoluteHardwareKnob[] getCcKnobs() {
		return ccKnobs;
	}
	
	public AbsoluteHardwareKnob getAtKnob() {
		return atKnob;
	}
	
	public AbsoluteHardwareKnob getPbKnob() {
		return pbKnob;
	}

	public int getChannel() {
		return channel;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

}
