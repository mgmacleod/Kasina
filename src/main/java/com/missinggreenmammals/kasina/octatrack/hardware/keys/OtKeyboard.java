package com.missinggreenmammals.kasina.octatrack.hardware.keys;

import java.util.LinkedList;
import java.util.List;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
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
	private final OtTrigKey trackBankPrevKey;
	private final OtTrigKey trackBankNextKey;
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
	private final List<OtTrigKey> trigKeys;
	
	public OtKeyboard(final int channel, final int otTrack, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		super(channel, otTrack);
		
		trigKeys = new LinkedList<>();
		
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
		trackBankPrevKey = new OtTrigKey(62, channel, otTrack, "TRACK_PREV", host, hardwareSurface);
		trackBankNextKey = new OtTrigKey(63, channel, otTrack, "TRACK_NEXT", host, hardwareSurface);

		// Shift key
		shiftKey = new OtShiftKey(64, channel, otTrack, "SHIFT", host, hardwareSurface);

		addKeysToList();
	}

	private void addKeysToList() {
		trigKeys.add(remotePagePrevKey);
		trigKeys.add(remotePageNextKey);
		trigKeys.add(cursorDevicePrevKey);
		trigKeys.add(cursorDeviceNextKey);
		trigKeys.add(remoteModeKey);
		trigKeys.add(selectTrackKey);
		trigKeys.add(trackRecordEnableKey);
		trigKeys.add(trackSoloKey);
		trigKeys.add(trackMuteKey);
		trigKeys.add(toggleMetronomeKey);
		trigKeys.add(toggleTransportLoopKey);
		trigKeys.add(recordKey);
		trigKeys.add(playKey);
		trigKeys.add(stopKey);
		trigKeys.add(trackBankPrevKey);
		trigKeys.add(trackBankNextKey);
	}

	@Override
	public void enableShiftMode() {
		trigKeys.forEach((key) -> key.enableShiftMode());
	}

	@Override
	public void disableShiftMode() {
		trigKeys.forEach((key) -> key.disableShiftMode());
	}
	
	public void bindToRecordKeyRegular(final HardwareBindable bindable) {
		recordKey.setRegularBinding(bindable);
	}

	public void bindToPlayKeyRegular(final HardwareBindable bindable) {
		playKey.setRegularBinding(bindable);
	}

	public void bindToStopKeyRegular(final HardwareBindable bindable) {
		stopKey.setRegularBinding(bindable);
	}

	public void bindToNextKeyRegular(final HardwareBindable bindable) {
		trackBankNextKey.setRegularBinding(bindable);
	}

	public void bindToPrevKeyRegular(final HardwareBindable bindable) {
		trackBankPrevKey.setRegularBinding(bindable);
	}

	public void bindToToggleTransportLoopKeyRegular(final HardwareBindable bindable) {
		toggleTransportLoopKey.setRegularBinding(bindable);
	}

	public void bindToToggleMetronomeKeyRegular(final HardwareBindable bindable) {
		toggleMetronomeKey.setRegularBinding(bindable);
	}

	public void bindToRemotePageNextKeyRegular(final HardwareBindable bindable) {
		remotePageNextKey.setRegularBinding(bindable);
	}

	public void bindToRemotePagePrevKeyRegular(final HardwareBindable bindable) {
		remotePagePrevKey.setRegularBinding(bindable);
	}

	public void bindToCursorDeviceNextKeyRegular(final HardwareBindable bindable) {
		cursorDeviceNextKey.setRegularBinding(bindable);
	}

	public void bindToCursorDevicePrevKeyRegular(final HardwareBindable bindable) {
		cursorDevicePrevKey.setRegularBinding(bindable);
	}

	public void bindToRemoteModeKeyRegular(final HardwareBindable bindable) {
		remoteModeKey.setRegularBinding(bindable);
	}

	public void bindToSelectTrackKeyRegular(final HardwareBindable bindable) {
		selectTrackKey.setRegularBinding(bindable);
	}

	public void bindToTrackMuteKeyRegular(final HardwareBindable bindable) {
		trackMuteKey.setRegularBinding(bindable);
	}

	public void bindToTrackSoloKeyRegular(final HardwareBindable bindable) {
		trackSoloKey.setRegularBinding(bindable);
	}

	public void bindToTrackRecordEnableKeyRegular(final HardwareBindable bindable) {
		trackRecordEnableKey.setRegularBinding(bindable);
	}

	public void clearCursorDeviceKeyBindings() {
		cursorDeviceNextKey.clearBindings();
		cursorDevicePrevKey.clearBindings();
	}
	
	public void bindToRecordKeyShift(final HardwareBindable bindable) {
		recordKey.setShiftBinding(bindable);
	}

	public void bindToPlayKeyShift(final HardwareBindable bindable) {
		playKey.setShiftBinding(bindable);
	}

	public void bindToStopKeyShift(final HardwareBindable bindable) {
		stopKey.setShiftBinding(bindable);
	}

	public void bindToNextKeyShift(final HardwareBindable bindable) {
		trackBankNextKey.setShiftBinding(bindable);
	}

	public void bindToPrevKeyShift(final HardwareBindable bindable) {
		trackBankPrevKey.setShiftBinding(bindable);
	}

	public void bindToToggleTransportLoopKeyShift(final HardwareBindable bindable) {
		toggleTransportLoopKey.setShiftBinding(bindable);
	}

	public void bindToToggleMetronomeKeyShift(final HardwareBindable bindable) {
		toggleMetronomeKey.setShiftBinding(bindable);
	}

	public void bindToRemotePageNextKeyShift(final HardwareBindable bindable) {
		remotePageNextKey.setShiftBinding(bindable);
	}

	public void bindToRemotePagePrevKeyShift(final HardwareBindable bindable) {
		remotePagePrevKey.setShiftBinding(bindable);
	}

	public void bindToCursorDeviceNextKeyShift(final HardwareBindable bindable) {
		cursorDeviceNextKey.setShiftBinding(bindable);
	}

	public void bindToCursorDevicePrevKeyShift(final HardwareBindable bindable) {
		cursorDevicePrevKey.setShiftBinding(bindable);
	}

	public void bindToRemoteModeKeyShift(final HardwareBindable bindable) {
		remoteModeKey.setShiftBinding(bindable);
	}

	public void bindToSelectTrackKeyShift(final HardwareBindable bindable) {
		selectTrackKey.setShiftBinding(bindable);
	}

	public void bindToTrackMuteKeyShift(final HardwareBindable bindable) {
		trackMuteKey.setShiftBinding(bindable);
	}

	public void bindToTrackSoloKeyShift(final HardwareBindable bindable) {
		trackSoloKey.setShiftBinding(bindable);
	}

	public void bindToTrackRecordEnableKeyShift(final HardwareBindable bindable) {
		trackRecordEnableKey.setShiftBinding(bindable);
	}

	public void bindToShiftKeyNoteOn(final HardwareBindable bindable) {
		shiftKey.setNoteOnBinding(bindable);
	}

	public void bindToShiftKeyNoteOff(final HardwareBindable bindable) {
		shiftKey.setNoteOffBinding(bindable);
	}
}
