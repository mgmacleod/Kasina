package com.missinggreenmammals.kasina.octatrack.layout;

import java.util.concurrent.atomic.AtomicBoolean;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorDevice;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.kasina.octatrack.hardware.OtMidiHardwareControls;

/**
 * An {@link OtDefaultTrackLayout} to represent a regular track in Bitwig (i.e.,
 * not an FX track or the master track)
 */
public class OtRegularTrackLayout extends OtDefaultTrackLayout {
	public static final int REMOTE_PAGE_SIZE = 8;

	private final HardwareBindable remoteModeChangeAction;
	private final HardwareBindable selectTrackAction;
	private final HardwareBindable trackRemotePagePrevAction;
	private final HardwareBindable trackRemotePageNextAction;
	private final HardwareBindable deviceRemotePagePrevAction;
	private final HardwareBindable deviceRemotePageNextAction;
	private final HardwareBindable cursorDevicePagePrevAction;
	private final HardwareBindable cursorDevicePageNextAction;
	private final HardwareBindable enterChainAction;
	private final HardwareBindable enterGroupAction;
	private final HardwareBindable exitGroupAction;

	protected final ControllerHost host;
	protected final Track track;
	protected final CursorTrack cursorTrack;

	private final CursorDevice cursorDevice;
	private final CursorRemoteControlsPage trackRemotesPage;
	private final CursorRemoteControlsPage deviceRemotesPage;

	private final OtMidiHardwareControls controls;
	private final AtomicBoolean trackRemoteMode;

	private String[] currentDeviceSlotNames;
	private int currentDeviceSlotIndex;

	public OtRegularTrackLayout(final ControllerHost host, final TrackBank trackBank, final Track track, final CursorTrack cursorTrack,
			final OtMidiHardwareControls controls) {

		super(host, trackBank);
		preinitialize(host, trackBank, track, cursorTrack, controls);

		this.controls = controls;
		this.host = host;
		this.cursorTrack = cursorTrack;
		this.track = track;
		configureTrack();

		// Create actions
		remoteModeChangeAction = host.createAction(this::handleRemoteModeChange, this::remoteModeChangeDescription);
		selectTrackAction = host.createAction(this::handleTrackSelectionAction, () -> "selectInMixer");
		enterChainAction = host.createAction(this::enterDeviceChain, () -> "enterDeviceChain");
		enterGroupAction = host.createAction(this::enterGroup, () -> "enterGroup");
		exitGroupAction = host.createAction(this::exitGroup, () -> "exitGroup");
		
		trackRemoteMode = new AtomicBoolean(true);

		// Configure CursorDevice
		cursorDevice = track.createCursorDevice("Primary");
		configureCursorDevice();

		deviceRemotesPage = cursorDevice
				.createCursorRemoteControlsPage("device-remotes-" + (controls.getOtTrack() - 1), REMOTE_PAGE_SIZE,
				null);
		
		trackRemotesPage = createRemotesPage(controls);

		// get binding targets
		trackRemotePagePrevAction = trackRemotesPage.selectPreviousAction();
		trackRemotePageNextAction = trackRemotesPage.selectNextAction();
		deviceRemotePagePrevAction = deviceRemotesPage.selectPreviousAction();
		deviceRemotePageNextAction = deviceRemotesPage.selectNextAction();
		cursorDevicePagePrevAction = host.createAction(this::handleCursorDevicePreviousAction,
				() -> "handlePreviousAction");
		cursorDevicePageNextAction = host.createAction(this::handleCursorDeviceNextAction, () -> "handleNextAction");
		
		initCurrentDeviceSlotProperties();
		initShiftBindings();
		
	}

	@Override
	public void applyTo(final OtMidiHardwareControls controls) {
		super.applyTo(controls);

		// volume
		controls.getCcEncoders()[0].setBinding(track.volume());

		// Sends
		bindSends(controls);

		// pan
		controls.getCcEncoders()[3].setBinding(track.pan());

		// remotes
		controls.getKeyboard().bindToRemoteModeKeyRegular(remoteModeChangeAction);

		// default to track remote mode
		initForTrackRemotes(trackRemotesPage, trackRemotePagePrevAction, trackRemotePageNextAction);

		// track commands: select, record enable, mute, and solo
		controls.getKeyboard().bindToSelectTrackKeyRegular(selectTrackAction);
		controls.getKeyboard().bindToTrackRecordEnableKeyRegular(track.arm().toggleAction());
		controls.getKeyboard().bindToTrackMuteKeyRegular(track.mute().toggleAction());
		controls.getKeyboard().bindToTrackSoloKeyRegular(track.solo().toggleAction());
	}

	protected void preinitialize(final ControllerHost host, final TrackBank trackBank, final Track track, final CursorTrack cursorTrack,
			final OtMidiHardwareControls controls) {
		return;
	}

	protected CursorRemoteControlsPage createRemotesPage(final OtMidiHardwareControls controls) {
		return track.createCursorRemoteControlsPage("track-remotes-" + (controls.getOtTrack() - 1), REMOTE_PAGE_SIZE,
				null);
	}
	
	private void configureCursorDevice() {
		cursorDevice.hasSlots().markInterested();
		cursorDevice.slotNames().markInterested();
		cursorDevice.hasNext().markInterested();
		cursorDevice.hasPrevious().markInterested();
	}

	private void configureTrack() {
		track.isGroup().markInterested();
		track.isGroupExpanded().markInterested();
	}

	private void initShiftBindings() {
		controls.getKeyboard().bindToCursorDeviceNextKeyShift(enterChainAction);
		controls.getKeyboard().bindToNextKeyShift(enterGroupAction);
		controls.getKeyboard().bindToPrevKeyShift(exitGroupAction);
	}
	
	private void enterDeviceChain(final double value) {
		if (cursorDevice.hasSlots().get()) {
			currentDeviceSlotNames = cursorDevice.slotNames().get();
			final String slot = currentDeviceSlotNames[0];
			currentDeviceSlotIndex = 0;

			cursorDevice.selectFirstInSlot(slot);
		}
	}

	private void enterGroup(final double value) {
		// Don't enter a non-group
		if (!track.isGroup().get()) {
			return;
		}

		// First select the track
		track.selectInMixer();

		// Then wait a bit and enter
		host.scheduleTask(cursorTrack::selectFirstChild, 100);
	}

	private void exitGroup(final double value) {
		cursorTrack.selectParent();
	}

	private String remoteModeChangeDescription() {
		return "Toggle remote control mode";
	}

	private void handleRemoteModeChange(final double value) {
		final boolean oldMode = trackRemoteMode.get();
		trackRemoteMode.set(!oldMode);

		if (oldMode) {
			// track remote mode becoming device mode, so switch to device
			initForDeviceRemotes(deviceRemotesPage, cursorDevice);
		} else {
			// device remote mode becoming track, so switch to track
			initForTrackRemotes(trackRemotesPage, trackRemotePagePrevAction, trackRemotePageNextAction);
		}

		controls.getKeyboard().disableShiftMode();
	}

	private void handleCursorDevicePreviousAction(final double value) {
		if (cursorDevice.hasPrevious().get()) {
			cursorDevice.selectPrevious();
			return;
		}

		if (currentDeviceSlotNames != null && (currentDeviceSlotIndex - 1) >= 0) {
			final String slot = currentDeviceSlotNames[--currentDeviceSlotIndex];
			cursorDevice.selectParent();
			cursorDevice.selectLastInSlot(slot);

			return;
		}

		cursorDevice.selectParent();
		initCurrentDeviceSlotProperties();
	}

	private void initCurrentDeviceSlotProperties() {
		currentDeviceSlotIndex = -1;
		currentDeviceSlotNames = null;
	}

	private void handleCursorDeviceNextAction(final double value) {
		if (cursorDevice.hasNext().get()) {
			cursorDevice.selectNext();
			return;
		}

		if (currentDeviceSlotNames != null && (currentDeviceSlotIndex + 1) < currentDeviceSlotNames.length) {
			final String slot = currentDeviceSlotNames[++currentDeviceSlotIndex];
			cursorDevice.selectParent();
			cursorDevice.selectFirstInSlot(slot);

			return;
		}
	}

	protected void bindSends(final OtMidiHardwareControls controls) {
		final SendBank sendBank = track.sendBank();
		controls.getPbEncoder().setBinding(sendBank.getItemAt(0));
		controls.getAtEncoder().setBinding(sendBank.getItemAt(1));
	}

	private void handleTrackSelectionAction(final double value) {
		track.selectInMixer();
		trackBank.scrollIntoView(trackBank.cursorIndex().get());
	}

	private void initForTrackRemotes(final CursorRemoteControlsPage controlsPage,
			final HardwareBindable selectPrevAction, final HardwareBindable selectNextAction) {
		
		controls.getCcEncoders()[1].setBinding(controlsPage.getParameter(0));
		controls.getCcEncoders()[2].setBinding(controlsPage.getParameter(1));
		controls.getCcEncoders()[4].setBinding(controlsPage.getParameter(2));
		controls.getCcEncoders()[5].setBinding(controlsPage.getParameter(3));
		controls.getCcEncoders()[6].setBinding(controlsPage.getParameter(4));
		controls.getCcEncoders()[7].setBinding(controlsPage.getParameter(5));
		controls.getCcEncoders()[8].setBinding(controlsPage.getParameter(6));
		controls.getCcEncoders()[9].setBinding(controlsPage.getParameter(7));

		controls.getKeyboard().bindToRemotePagePrevKeyRegular(selectPrevAction);
		controls.getKeyboard().bindToRemotePageNextKeyRegular(selectNextAction);
		controls.getKeyboard().clearCursorDeviceKeyBindings(); // no devices to switch between in track mode
	}

	private void initForDeviceRemotes(final CursorRemoteControlsPage controlsPage,
			final CursorDevice cursorDevice) {

		initForTrackRemotes(controlsPage, deviceRemotePagePrevAction, deviceRemotePageNextAction);

		controls.getKeyboard().bindToCursorDevicePrevKeyRegular(cursorDevicePagePrevAction);
		controls.getKeyboard().bindToCursorDeviceNextKeyRegular(cursorDevicePageNextAction);
	}
}
