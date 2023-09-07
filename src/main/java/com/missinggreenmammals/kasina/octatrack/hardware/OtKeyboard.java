package com.missinggreenmammals.kasina.octatrack.hardware;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MidiIn;

/**
 * Represents the chromatic keyboard of the Octatrack with support for shift functions.
 *
 */
public class OtKeyboard extends OtHardwareElement implements Shiftable {
	
	// Individual buttons for the specific keys
//	private final OtTrigKey recordKey;
//	private final OtTrigKey playKey;
//	private final OtTrigKey stopKey;
//	private final OtTrigKey prevKey;
//	private final OtTrigKey nextKey;
//	private final OtTrigKey selectTrackKey;
//	private final OtTrigKey remotePagePrevKey;
//	private final OtTrigKey remotePageNextKey;
//	private final OtTrigKey cursorDevicePrevKey;
//	private final OtTrigKey cursorDeviceNextKey;
//	private final OtTrigKey remoteModeKey;
//	private final OtTrigKey toggleMetronomeKey;
//	private final OtTrigKey toggleTransportLoopKey;
//
//	private final OtTrigKey trackMuteKey;
//	private final OtTrigKey trackSoloKey;
//	private final OtTrigKey trackRecordEnableKey;
	
	// Collection of all the keys representing the whole keyboard
	private final OtTrigKey[] keys;
	
	public OtKeyboard(final int midiNote, final int channel, final int otTrack, final ControllerHost host, final HardwareSurface hardwareSurface) {
		super(channel, otTrack);
		
		MidiIn midiIn = host.getMidiInPort(0);
		keys = new OtTrigKey[128];
		
		
		
		// Instantiate buttons for the main keys 'main' octave (i.e., 3) keys 
		/*
		remotePagePrevButton = hardwareSurface.createHardwareButton(createId("SUB_PREV1"));
		remotePageNextButton = hardwareSurface.createHardwareButton(createId("SUB_NEXT1"));
		cursorDevicePrevButton = hardwareSurface.createHardwareButton(createId("SUB_PREV2"));
		cursorDeviceNextButton = hardwareSurface.createHardwareButton(createId("SUB_NEXT2"));
		remoteModeButton = hardwareSurface.createHardwareButton(createId("REMOTE_MODE"));
		selectTrackButton = hardwareSurface.createHardwareButton(createId("TRACK_SELECT"));
		trackRecordEnableButton = hardwareSurface.createHardwareButton(createId("REC_ENABLE"));
		trackSoloButton = hardwareSurface.createHardwareButton(createId("SOLO"));
		trackMuteButton = hardwareSurface.createHardwareButton(createId("MUTE"));
		toggleMetronomeButton = hardwareSurface.createHardwareButton(createId("METRO"));
		toggleTransportLoopButton = hardwareSurface.createHardwareButton(createId("LOOP"));
		recordButton = hardwareSurface.createHardwareButton(createId("RECORD"));
		playButton = hardwareSurface.createHardwareButton(createId("PLAY"));
		stopButton = hardwareSurface.createHardwareButton(createId("STOP"));
		prevButton = hardwareSurface.createHardwareButton(createId("PREV"));
		nextButton = hardwareSurface.createHardwareButton(createId("NEXT"));
		
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
		*/
		
		String[] ids = new String[] {
				"REM_PAGE_PREV", "REM_PAGE_NEXT", "DEV_PAGE_PREV", "DEV_PAGE_NEXT", "REMOTE_MODE",
				"TRACK_SELECT", "REC_ENABLE", "SOLO", "MUTE", "METRO", "LOOP", "RECORD",
				"PLAY", "STOP", "TRACK_PREV", "TRACK_NEXT"
		};
		
//		recordKey = new OtTrigKey(48, channel, otTrack, "SUB_PREV1", host, hardwareSurface);
	}

	@Override
	public void enableShiftMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableShiftMode() {
		// TODO Auto-generated method stub
		
	}
	

}
