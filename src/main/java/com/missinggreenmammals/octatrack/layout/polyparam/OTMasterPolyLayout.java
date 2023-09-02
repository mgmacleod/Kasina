package com.missinggreenmammals.octatrack.layout.polyparam;

import com.bitwig.extension.controller.api.AbsoluteHardwareKnob;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.MasterTrack;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;
import com.missinggreenmammals.octatrack.layout.OTMidiTrackLayout;

public class OTMasterPolyLayout extends OTPolyParamLayout {

	private static final int NUM_REMOTES = 8;

	protected final MasterTrack masterTrack;
	protected final Track rootTrackGroup;
	protected final CursorRemoteControlsPage controlsPage;

	public OTMasterPolyLayout(ControllerHost host, TrackBank trackBank, MasterTrack masterTrack) {
		super(host, trackBank);
		this.masterTrack = masterTrack;

		rootTrackGroup = host.getProject().getRootTrackGroup();
		controlsPage = rootTrackGroup.createCursorRemoteControlsPage("project-remotes", NUM_REMOTES, null);
	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);
		
		int remote = 0;
		for (int i = 0; i < OTMidiTrackLayout.PAGE_SIZE; i++) {
			AbsoluteHardwareKnob knob = controls.getCcKnobs()[i];
			HardwareBindable bindable;

			if (i == 0) {
				bindable = masterTrack.volume();
			} else if (i == 3) {
				bindable = masterTrack.pan();
			} else {
				bindable = controlsPage.getParameter(remote);
				remote++;
			}

			knob.setBinding(bindable);
		}

		controls.bindToSubPrev1Button(controlsPage.selectPreviousAction());
		controls.bindToSubNext1Button(controlsPage.selectNextAction());
	}

}
