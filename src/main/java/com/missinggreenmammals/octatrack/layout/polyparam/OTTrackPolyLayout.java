package com.missinggreenmammals.octatrack.layout.polyparam;

import java.util.concurrent.atomic.AtomicBoolean;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorDevice;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public class OTTrackPolyLayout extends OTPolyParamLayout {
	public static final int REMOTE_PAGE_SIZE = 8;

	protected final Track track;
	protected final CursorTrack cursorTrack;
	private final HardwareBindable remoteModeChangeAction;
	private final HardwareBindable selectTrackAction;
	private final HardwareBindable trackRemotePagePrevAction;
	private final HardwareBindable trackRemotePageNextAction;
	private final HardwareBindable deviceRemotePagePrevAction;
	private final HardwareBindable deviceRemotePageNextAction;
	private final HardwareBindable cursorDevicePagePrevAction;
	private final HardwareBindable cursorDevicePageNextAction;

	private AtomicBoolean trackRemoteMode;
	private final CursorDevice cursorDevice;
	private final CursorRemoteControlsPage trackRemotesPage;
	private final CursorRemoteControlsPage deviceRemotesPage;
	private final OTMidiHardwareControls controls;

	public OTTrackPolyLayout(ControllerHost host, TrackBank trackBank, Track track, CursorTrack cursorTrack,
			OTMidiHardwareControls controls) {

		super(host, trackBank);
		preinitialize(host, trackBank, track, cursorTrack, controls);

		this.controls = controls;
		this.cursorTrack = cursorTrack;
		this.track = track;
		remoteModeChangeAction = host.createAction(this::handleRemoteModeChange, this::remoteModeChangeDescription);
		selectTrackAction = host.createAction((value) -> track.selectInMixer(), () -> "selectInMixer");

		trackRemoteMode = new AtomicBoolean(true);
		cursorDevice = track.createCursorDevice("Primary");
		

		deviceRemotesPage = cursorDevice
				.createCursorRemoteControlsPage("device-remotes-" + (controls.getTrackNumber() - 1), REMOTE_PAGE_SIZE,
				null);
		
		trackRemotesPage = createRemotesPage(controls);

		// get binding targets
		trackRemotePagePrevAction = trackRemotesPage.selectPreviousAction();
		trackRemotePageNextAction = trackRemotesPage.selectNextAction();
		deviceRemotePagePrevAction = deviceRemotesPage.selectPreviousAction();
		deviceRemotePageNextAction = deviceRemotesPage.selectNextAction();
		cursorDevicePagePrevAction = cursorDevice.selectPreviousAction();
		cursorDevicePageNextAction = cursorDevice.selectNextAction();
		
	}

	protected void preinitialize(ControllerHost host, TrackBank trackBank, Track track, CursorTrack cursorTrack,
			OTMidiHardwareControls controls) {
		return;
	}

	protected CursorRemoteControlsPage createRemotesPage(OTMidiHardwareControls controls) {
		return track.createCursorRemoteControlsPage("track-remotes-" + (controls.getTrackNumber() - 1), REMOTE_PAGE_SIZE,
				null);
	}

	private String remoteModeChangeDescription() {
		return "Toggle remote control mode";
	}

	private void handleRemoteModeChange(double value) {
		boolean oldMode = trackRemoteMode.get();
		trackRemoteMode.set(!oldMode);

		if (oldMode) {
			// track remote mode becoming device mode, so switch to device
			initForDeviceRemotes(deviceRemotesPage, cursorDevice);
		} else {
			// device remote mode becoming track, so switch to track
			initForTrackRemotes(trackRemotesPage, trackRemotePagePrevAction, trackRemotePageNextAction);
		}

	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		// volume
		controls.getCcKnobs()[0].setBinding(track.volume());

		// Sends
		bindSends(controls);

		// pan
		controls.getCcKnobs()[3].setBinding(track.pan());

		// remotes
		controls.bindToRemoteModeButton(remoteModeChangeAction);

		// default to track remote mode
		initForTrackRemotes(trackRemotesPage, trackRemotePagePrevAction, trackRemotePageNextAction);
		
		// select track
		controls.bindToSelectTrackButton(selectTrackAction);
	}

	protected void bindSends(OTMidiHardwareControls controls) {
		SendBank sendBank = track.sendBank();
		controls.getPbKnob().setBinding(sendBank.getItemAt(0));
		controls.getAtKnob().setBinding(sendBank.getItemAt(1));
	}


	private void initForTrackRemotes(final CursorRemoteControlsPage controlsPage,
			final HardwareBindable selectPrevAction, final HardwareBindable selectNextAction) {
		
		controls.getCcKnobs()[1].setBinding(controlsPage.getParameter(0));
		controls.getCcKnobs()[2].setBinding(controlsPage.getParameter(1));
		controls.getCcKnobs()[4].setBinding(controlsPage.getParameter(2));
		controls.getCcKnobs()[5].setBinding(controlsPage.getParameter(3));
		controls.getCcKnobs()[6].setBinding(controlsPage.getParameter(4));
		controls.getCcKnobs()[7].setBinding(controlsPage.getParameter(5));
		controls.getCcKnobs()[8].setBinding(controlsPage.getParameter(6));
		controls.getCcKnobs()[9].setBinding(controlsPage.getParameter(7));

		controls.bindToRemotePagePrevButton(selectPrevAction);
		controls.bindToRemotePageNextButton(selectNextAction);
		controls.clearCursorDeviceButtonBindings(); // no devices to switch between in track mode
	}

	private void initForDeviceRemotes(final CursorRemoteControlsPage controlsPage,
			CursorDevice cursorDevice) {

		initForTrackRemotes(controlsPage, deviceRemotePagePrevAction, deviceRemotePageNextAction);

		controls.bindToCursorDevicePrevButton(cursorDevicePagePrevAction);
		controls.bindToCursorDeviceNextButton(cursorDevicePageNextAction);
	}
}
