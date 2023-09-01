package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.Track;

public class OTProjectRemoteLayout extends OTProjectLayout {

	protected final Track rootTrackGroup;
	protected final CursorRemoteControlsPage controlsPage;

	public OTProjectRemoteLayout(ControllerHost host) {
		super(host);

		rootTrackGroup = project.getRootTrackGroup();
		controlsPage = rootTrackGroup.createCursorRemoteControlsPage("project-remotes", PAGE_SIZE, null);

	}

	@Override
	protected HardwareBindable getItemAt(int index) {
		return controlsPage.getParameter(index);
	}


}
