package com.missinggreenmammals.kasina.octatrack.layout;

import java.util.concurrent.atomic.AtomicBoolean;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorDevice;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.StringArrayValue;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.kasina.octatrack.hardware.OtMidiHardwareControls;

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
	private final HardwareBindable leaveChainAction;

	protected final Track track;
	protected final CursorTrack cursorTrack;

	private final CursorDevice cursorDevice;
	private final CursorRemoteControlsPage trackRemotesPage;
	private final CursorRemoteControlsPage deviceRemotesPage;

	private final OtMidiHardwareControls controls;

	private final AtomicBoolean trackRemoteMode;

	public OtRegularTrackLayout(final ControllerHost host, final TrackBank trackBank, final Track track, final CursorTrack cursorTrack,
			final OtMidiHardwareControls controls) {

		super(host, trackBank);
		preinitialize(host, trackBank, track, cursorTrack, controls);

		this.controls = controls;
		this.cursorTrack = cursorTrack;
		this.track = track;

		// Create actions
		remoteModeChangeAction = host.createAction(this::handleRemoteModeChange, this::remoteModeChangeDescription);
		selectTrackAction = host.createAction((value) -> track.selectInMixer(), () -> "selectInMixer");
		enterChainAction = host.createAction(this::enterDeviceChain, () -> "enterDeviceChain");
		leaveChainAction = host.createAction(this::leaveDeviceChain, () -> "leaveDeviceChain");
		
		trackRemoteMode = new AtomicBoolean(true);

		// Configure CursorDevice
		cursorDevice = track.createCursorDevice("Primary");
		cursorDevice.hasSlots().markInterested();
		cursorDevice.slotNames().markInterested();
		cursorDevice.name().markInterested();
		cursorDevice.hasNext().markInterested();
		cursorDevice.hasPrevious().markInterested();
		cursorDevice.isNested().markInterested();

		deviceRemotesPage = cursorDevice
				.createCursorRemoteControlsPage("device-remotes-" + (controls.getOtTrack() - 1), REMOTE_PAGE_SIZE,
				null);
		
		trackRemotesPage = createRemotesPage(controls);

		// get binding targets
		trackRemotePagePrevAction = trackRemotesPage.selectPreviousAction();
		trackRemotePageNextAction = trackRemotesPage.selectNextAction();
		deviceRemotePagePrevAction = deviceRemotesPage.selectPreviousAction();
		deviceRemotePageNextAction = deviceRemotesPage.selectNextAction();
		cursorDevicePagePrevAction = cursorDevice.selectPreviousAction();
		cursorDevicePageNextAction = cursorDevice.selectNextAction();
		

		initShiftBindings();
		
	}

	protected void preinitialize(final ControllerHost host, final TrackBank trackBank, final Track track, final CursorTrack cursorTrack,
			final OtMidiHardwareControls controls) {
		return;
	}

	protected CursorRemoteControlsPage createRemotesPage(final OtMidiHardwareControls controls) {
		return track.createCursorRemoteControlsPage("track-remotes-" + (controls.getOtTrack() - 1), REMOTE_PAGE_SIZE,
				null);
	}
	
	private void initShiftBindings() {
		controls.getKeyboard().bindToCursorDeviceNextKeyShift(enterChainAction);
		controls.getKeyboard().bindToCursorDevicePrevKeyShift(leaveChainAction);
	}
	
	private void enterDeviceChain(final double value) {
		if (cursorDevice.hasSlots().get()) {
//			previousDevice = deviceBank.getItemAt(0);
			final StringArrayValue slotNames = cursorDevice.slotNames();
			final String[] slots = slotNames.get();
			final String slot = slots[0];
			cursorDevice.selectFirstInSlot(slot);
		}
	}

	private void leaveDeviceChain(final double value) {
		// I still have no idea how to do this! :(
		cursorDevice.selectParent();
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

	protected void bindSends(final OtMidiHardwareControls controls) {
		final SendBank sendBank = track.sendBank();
		controls.getPbEncoder().setBinding(sendBank.getItemAt(0));
		controls.getAtEncoder().setBinding(sendBank.getItemAt(1));
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
