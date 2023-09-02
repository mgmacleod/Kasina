package com.missinggreenmammals.octatrack.layout.polyparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorDevice;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public class OTTrackPolyLayout extends OTPolyParamLayout {

	protected Track track;
	protected final CursorTrack cursorTrack;

	public OTTrackPolyLayout(ControllerHost host, TrackBank trackBank, CursorTrack cursorTrack) {

		super(host, trackBank);
		this.cursorTrack = cursorTrack;
	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);
		track = trackBank.getItemAt(controls.getTrackNumber() - 1);

		// volume
		controls.getCcKnobs()[0].setBinding(track.volume());

		// Sends
		SendBank sendBank = track.sendBank();
		controls.getCcKnobs()[1].setBinding(sendBank.getItemAt(0));
		controls.getCcKnobs()[2].setBinding(sendBank.getItemAt(1));

		// pan
		controls.getCcKnobs()[3].setBinding(track.pan());

		// remotes
		final CursorDevice cursorDevice = track.createCursorDevice("Primary");
		final CursorRemoteControlsPage trackRemotesPage = track
				.createCursorRemoteControlsPage("track-remotes-" + (controls.getTrackNumber() - 1), 6, null);

		final CursorRemoteControlsPage deviceRemotesPage = cursorDevice
				.createCursorRemoteControlsPage("device-remotes-" + (controls.getTrackNumber() - 1), 6, null);

		initForDeviceRemotes(controls, deviceRemotesPage, cursorDevice);
	}

	private void initForTrackRemotes(OTMidiHardwareControls controls, final CursorRemoteControlsPage controlsPage) {
		controls.getCcKnobs()[4].setBinding(controlsPage.getParameter(0));
		controls.getCcKnobs()[5].setBinding(controlsPage.getParameter(1));
		controls.getCcKnobs()[6].setBinding(controlsPage.getParameter(2));
		controls.getCcKnobs()[7].setBinding(controlsPage.getParameter(3));
		controls.getCcKnobs()[8].setBinding(controlsPage.getParameter(4));
		controls.getCcKnobs()[9].setBinding(controlsPage.getParameter(5));

		controls.bindToSubPrev1Button(controlsPage.selectPreviousAction());
		controls.bindToSubNext1Button(controlsPage.selectNextAction());
	}

	private void initForDeviceRemotes(OTMidiHardwareControls controls, final CursorRemoteControlsPage controlsPage,
			CursorDevice cursorDevice) {

		initForTrackRemotes(controls, controlsPage);

		controls.bindToSubPrev2Button(cursorDevice.selectPreviousAction());
		controls.bindToSubNext2Button(cursorDevice.selectNextAction());
	}
}
