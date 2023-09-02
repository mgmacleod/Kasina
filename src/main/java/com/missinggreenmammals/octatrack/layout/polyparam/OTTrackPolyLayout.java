package com.missinggreenmammals.octatrack.layout.polyparam;

import java.util.concurrent.atomic.AtomicBoolean;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorDevice;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.HardwareActionBindable;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public class OTTrackPolyLayout extends OTPolyParamLayout {

	protected Track track;
	protected final CursorTrack cursorTrack;
	private HardwareActionBindable remoteModeChangeAction;
	private HardwareBindable trackRemotePagePrevAction;
	private HardwareBindable trackRemotePageNextAction;
	private HardwareBindable deviceRemotePagePrevAction;
	private HardwareBindable deviceRemotePageNextAction;
	private HardwareBindable cursorDevicePagePrevAction;
	private HardwareBindable cursorDevicePageNextAction;

	private AtomicBoolean trackRemoteMode;
	private final CursorDevice cursorDevice;
	private final CursorRemoteControlsPage trackRemotesPage;
	private final CursorRemoteControlsPage deviceRemotesPage;
	private final OTMidiHardwareControls controls;

	public OTTrackPolyLayout(ControllerHost host, TrackBank trackBank, CursorTrack cursorTrack,
			OTMidiHardwareControls controls) {
		super(host, trackBank);

		this.controls = controls;
		this.cursorTrack = cursorTrack;
		track = trackBank.getItemAt(controls.getTrackNumber() - 1);
		remoteModeChangeAction = host.createAction(this::handleRemoteModeChange, this::remoteModeChangeDescription);

		trackRemoteMode = new AtomicBoolean(true);
		cursorDevice = track.createCursorDevice("Primary");
		trackRemotesPage = track.createCursorRemoteControlsPage("track-remotes-" + (controls.getTrackNumber() - 1), 6,
				null);

		deviceRemotesPage = cursorDevice
				.createCursorRemoteControlsPage("device-remotes-" + (controls.getTrackNumber() - 1), 6,
				null);

		// get binding targets
		trackRemotePagePrevAction = trackRemotesPage.selectPreviousAction();
		trackRemotePageNextAction = trackRemotesPage.selectNextAction();
		deviceRemotePagePrevAction = deviceRemotesPage.selectPreviousAction();
		deviceRemotePageNextAction = deviceRemotesPage.selectNextAction();
		cursorDevicePagePrevAction = cursorDevice.selectPreviousAction();
		cursorDevicePageNextAction = cursorDevice.selectNextAction();
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
		SendBank sendBank = track.sendBank();
		controls.getCcKnobs()[1].setBinding(sendBank.getItemAt(0));
		controls.getCcKnobs()[2].setBinding(sendBank.getItemAt(1));

		// pan
		controls.getCcKnobs()[3].setBinding(track.pan());

		// remotes
		controls.bindToRemoteModeButton(remoteModeChangeAction);

		// default to track mode
		initForTrackRemotes(trackRemotesPage, trackRemotePagePrevAction, trackRemotePageNextAction);
	}


	private void initForTrackRemotes(final CursorRemoteControlsPage controlsPage,
			final HardwareBindable selectPrevAction, final HardwareBindable selectNextAction) {
		controls.getCcKnobs()[4].setBinding(controlsPage.getParameter(0));
		controls.getCcKnobs()[5].setBinding(controlsPage.getParameter(1));
		controls.getCcKnobs()[6].setBinding(controlsPage.getParameter(2));
		controls.getCcKnobs()[7].setBinding(controlsPage.getParameter(3));
		controls.getCcKnobs()[8].setBinding(controlsPage.getParameter(4));
		controls.getCcKnobs()[9].setBinding(controlsPage.getParameter(5));

		controls.bindToSubPrev1Button(selectPrevAction);
		controls.bindToSubNext1Button(selectNextAction);
	}

	private void initForDeviceRemotes(final CursorRemoteControlsPage controlsPage,
			CursorDevice cursorDevice) {

		initForTrackRemotes(controlsPage, deviceRemotePagePrevAction, deviceRemotePageNextAction);

		controls.bindToSubPrev2Button(cursorDevicePagePrevAction);
		controls.bindToSubNext2Button(cursorDevicePageNextAction);
	}
}
