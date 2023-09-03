package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.Track;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public class OTProjectRemoteMonoLayout extends OTProjectMonoLayout {

	protected final Track rootTrackGroup;
	protected final CursorRemoteControlsPage controlsPage;

	public OTProjectRemoteMonoLayout(ControllerHost host) {
		super(host);

		rootTrackGroup = project.getRootTrackGroup();
		controlsPage = rootTrackGroup.createCursorRemoteControlsPage("project-remotes", PAGE_SIZE, null);
	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		controls.bindToPrevButton(controlsPage.selectPreviousAction());
		controls.bindToNextButton(controlsPage.selectNextAction());
	}

	@Override
	protected HardwareBindable getItemAt(int index) {
		return controlsPage.getParameter(index);
	}

}
