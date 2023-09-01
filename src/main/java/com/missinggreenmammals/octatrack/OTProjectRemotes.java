package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.Project;
import com.bitwig.extension.controller.api.Track;

public class OTProjectRemotes extends OTMidiTrack {

	public OTProjectRemotes(ControllerHost host, HardwareSurface hardwareSurface) {
		this("PROJECT_REMOTES", 13, 6, host, hardwareSurface);
	}

	private OTProjectRemotes(String name, int channel, int trackNumber, ControllerHost host,
			HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected void setBindings(ControllerHost host) {
		Project project = host.getProject();
		Track rootTrackGroup = project.getRootTrackGroup();
		CursorRemoteControlsPage controlsPage = rootTrackGroup.createCursorRemoteControlsPage("project-remotes", 11,
				null);

		pbKnob.setBinding(controlsPage.getParameter(0));
		cc1knob.setBinding(controlsPage.getParameter(1));
		cc2knob.setBinding(controlsPage.getParameter(2));
		cc3knob.setBinding(controlsPage.getParameter(3));
		cc4knob.setBinding(controlsPage.getParameter(4));
		cc5knob.setBinding(controlsPage.getParameter(5));
		cc6knob.setBinding(controlsPage.getParameter(6));
		cc7knob.setBinding(controlsPage.getParameter(7));
		cc8knob.setBinding(controlsPage.getParameter(8));
		cc9knob.setBinding(controlsPage.getParameter(9));
		cc10knob.setBinding(controlsPage.getParameter(10));
	}

	@Override
	protected void updateCCs() {
		// TODO Auto-generated method stub

	}

}
