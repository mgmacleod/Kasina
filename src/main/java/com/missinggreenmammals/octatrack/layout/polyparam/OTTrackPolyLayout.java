package com.missinggreenmammals.octatrack.layout.polyparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.PinnableCursorDevice;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public class OTTrackPolyLayout extends OTPolyParamLayout {

	protected final CursorTrack cursorTrack;
	protected final PinnableCursorDevice cursorDevice;

	public OTTrackPolyLayout(ControllerHost host, TrackBank trackBank, CursorTrack cursorTrack,
			PinnableCursorDevice cursorDevice) {

		super(host, trackBank);
		this.cursorTrack = cursorTrack;
		this.cursorDevice = cursorDevice;
	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		// volume
		controls.getCcKnobs()[0].setBinding(trackBank.getItemAt(controls.getTrackNumber() - 1).volume());

		// Sends
		SendBank sendBank = trackBank.getItemAt(controls.getTrackNumber() - 1).sendBank();
		controls.getCcKnobs()[1].setBinding(sendBank.getItemAt(0));
		controls.getCcKnobs()[2].setBinding(sendBank.getItemAt(1));

		// pan
		controls.getCcKnobs()[3].setBinding(trackBank.getItemAt(controls.getTrackNumber() - 1).pan());

		// remotes
		final CursorRemoteControlsPage controlsPage = trackBank.getItemAt(controls.getTrackNumber() - 1)
				.createCursorRemoteControlsPage("track-remotes-" + (controls.getTrackNumber() - 1), 6, null);
		controls.getCcKnobs()[4].setBinding(controlsPage.getParameter(0));
		controls.getCcKnobs()[5].setBinding(controlsPage.getParameter(1));
		controls.getCcKnobs()[6].setBinding(controlsPage.getParameter(2));
		controls.getCcKnobs()[7].setBinding(controlsPage.getParameter(3));
		controls.getCcKnobs()[8].setBinding(controlsPage.getParameter(4));
		controls.getCcKnobs()[9].setBinding(controlsPage.getParameter(5));

		controls.bindToSubPrev1Button(controlsPage.selectPreviousAction());
		controls.bindToSubNext1Button(controlsPage.selectNextAction());
	}
}
