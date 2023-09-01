package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.Track;

public class OTProjectRemoteConfig extends OTProjectConfig {

	private final Track rootTrackGroup;
	private final CursorRemoteControlsPage controlsPage;

	public OTProjectRemoteConfig(ControllerHost host) {
		super(host);

		rootTrackGroup = project.getRootTrackGroup();
		controlsPage = rootTrackGroup.createCursorRemoteControlsPage("project-remotes", PAGE_SIZE, null);

	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		controls.cc1knob.setBinding(controlsPage.getParameter(0));
		controls.cc2knob.setBinding(controlsPage.getParameter(1));
		controls.cc3knob.setBinding(controlsPage.getParameter(2));
		controls.cc4knob.setBinding(controlsPage.getParameter(3));
		controls.cc5knob.setBinding(controlsPage.getParameter(4));
		controls.cc6knob.setBinding(controlsPage.getParameter(5));
		controls.cc7knob.setBinding(controlsPage.getParameter(6));
		controls.cc8knob.setBinding(controlsPage.getParameter(7));
		controls.cc9knob.setBinding(controlsPage.getParameter(8));
		controls.cc10knob.setBinding(controlsPage.getParameter(9));

		controls.nextButton.pressedAction().setBinding(controlsPage.selectNextAction());
		controls.prevButton.pressedAction().setBinding(controlsPage.selectPreviousAction());

	}

}
