package com.missinggreenmammals.kasina.octatrack.hardware.keys;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.kasina.octatrack.hardware.OtHardwareElement;
import com.missinggreenmammals.kasina.octatrack.hardware.Shiftable;

/**
 * Represents the chromatic keyboard of the Octatrack with support for shift functions.
 *
 */
public class OtKeyboard extends OtHardwareElement implements Shiftable {
	
	private final OtShiftKey shiftKey;
	// Individual buttons for the specific keys
	private final OtTrigKey recordKey;
	private final OtTrigKey playKey;
	private final OtTrigKey stopKey;
	private final OtTrigKey prevTrackBankKey;
	private final OtTrigKey nextTrackBankKey;
	private final OtTrigKey selectTrackKey;
	private final OtTrigKey remotePagePrevKey;
	private final OtTrigKey remotePageNextKey;
	private final OtTrigKey cursorDevicePrevKey;
	private final OtTrigKey cursorDeviceNextKey;
	private final OtTrigKey remoteModeKey;
	private final OtTrigKey toggleMetronomeKey;
	private final OtTrigKey toggleTransportLoopKey;

	private final OtTrigKey trackMuteKey;
	private final OtTrigKey trackSoloKey;
	private final OtTrigKey trackRecordEnableKey;
	
	// Collection of all the keys representing the whole keyboard
	private final OtTrigKey[] keys;
	
	public OtKeyboard(final int midiNote, final int channel, final int otTrack, final ControllerHost host, final HardwareSurface hardwareSurface) {
		super(channel, otTrack);
		
		keys = new OtTrigKey[128];
		
		String[] ids = new String[] {
				"REM_PAGE_PREV", "REM_PAGE_NEXT", "DEV_PAGE_PREV", "DEV_PAGE_NEXT", "REMOTE_MODE",
				"TRACK_SELECT", "REC_ENABLE", "SOLO", "MUTE", "METRO", "LOOP", "RECORD",
				"PLAY", "STOP", "TRACK_PREV", "TRACK_NEXT"
		};
		
		// Instantiate keys for the 'main' octave (i.e., 3) of the chromatic keyboard
		remotePagePrevKey = new OtTrigKey(48, channel, otTrack, "REM_PAGE_PREV", host, hardwareSurface);
		remotePageNextKey = new OtTrigKey(49, channel, otTrack, "REM_PAGE_NEXT", host, hardwareSurface);
		cursorDevicePrevKey = new OtTrigKey(50, channel, otTrack, "DEV_PAGE_PREV", host, hardwareSurface);
		cursorDeviceNextKey = new OtTrigKey(51, channel, otTrack, "DEV_PAGE_PREV", host, hardwareSurface);
		remoteModeKey = new OtTrigKey(52, channel, otTrack, "REMOTE_MODE", host, hardwareSurface);
		selectTrackKey = new OtTrigKey(53, channel, otTrack, "TRACK_SELECT", host, hardwareSurface);
		trackRecordEnableKey = new OtTrigKey(54, channel, otTrack, "REC_ENABLE", host, hardwareSurface);
		trackSoloKey = new OtTrigKey(55, channel, otTrack, "SOLO", host, hardwareSurface);
		trackMuteKey = new OtTrigKey(56, channel, otTrack, "MUTE", host, hardwareSurface);
		toggleMetronomeKey = new OtTrigKey(57, channel, otTrack, "METRO", host, hardwareSurface);
		toggleTransportLoopKey = new OtTrigKey(58, channel, otTrack, "LOOP", host, hardwareSurface);
		recordKey = new OtTrigKey(59, channel, otTrack, "RECORD", host, hardwareSurface);
		playKey = new OtTrigKey(60, channel, otTrack, "PLAY", host, hardwareSurface);
		stopKey = new OtTrigKey(61, channel, otTrack, "STOP", host, hardwareSurface);
		prevTrackBankKey = new OtTrigKey(62, channel, otTrack, "TRACK_PREV", host, hardwareSurface);
		nextTrackBankKey = new OtTrigKey(63, channel, otTrack, "TRACK_NEXT", host, hardwareSurface);

		// Shift key
		shiftKey = new OtShiftKey(64, channel, otTrack, "SHIFT", host, hardwareSurface);

		addKeysToArray();
	}

	private void addKeysToArray() {
		keys[remotePagePrevKey.getMidiNote()] = remotePagePrevKey;
		keys[remotePageNextKey.getMidiNote()] = remotePageNextKey;
		keys[cursorDevicePrevKey.getMidiNote()] = cursorDevicePrevKey;
		keys[cursorDeviceNextKey.getMidiNote()] = cursorDeviceNextKey;
		keys[remoteModeKey.getMidiNote()] = remoteModeKey;
		keys[selectTrackKey.getMidiNote()] = selectTrackKey;
		keys[trackRecordEnableKey.getMidiNote()] = trackRecordEnableKey;
		keys[trackSoloKey.getMidiNote()] = trackSoloKey;
		keys[trackMuteKey.getMidiNote()] = trackMuteKey;
		keys[toggleMetronomeKey.getMidiNote()] = toggleMetronomeKey;
		keys[toggleTransportLoopKey.getMidiNote()] = toggleTransportLoopKey;
		keys[recordKey.getMidiNote()] = recordKey;
		keys[playKey.getMidiNote()] = playKey;
		keys[stopKey.getMidiNote()] = stopKey;
		keys[prevTrackBankKey.getMidiNote()] = prevTrackBankKey;
		keys[nextTrackBankKey.getMidiNote()] = nextTrackBankKey;
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
