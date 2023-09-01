package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.HardwareBindable;
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
	protected HardwareBindable getItemAt(int index) {
		return controlsPage.getParameter(index);
	}


}
