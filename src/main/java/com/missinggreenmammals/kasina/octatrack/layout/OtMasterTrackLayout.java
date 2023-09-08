package com.missinggreenmammals.kasina.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.kasina.octatrack.hardware.OTMidiHardwareControls;

public class OtMasterTrackLayout extends OtRegularTrackLayout {

	protected Track rootTrackGroup;

	public OtMasterTrackLayout(ControllerHost host, TrackBank trackBank, Track track, CursorTrack cursorTrack,
			OTMidiHardwareControls controls) {

		super(host, trackBank, track, cursorTrack, controls);

	}

	@Override
	protected void preinitialize(ControllerHost host, TrackBank trackBank, Track track, CursorTrack cursorTrack,
			OTMidiHardwareControls controls) {

		rootTrackGroup = host.getProject().getRootTrackGroup();
	}


	@Override
	protected CursorRemoteControlsPage createRemotesPage(OTMidiHardwareControls controls) {
		return rootTrackGroup.createCursorRemoteControlsPage("project-remotes", REMOTE_PAGE_SIZE, null);
	}

	protected void bindSends(OTMidiHardwareControls controls) {
		// override to do nothing, as there are no sends on the master track in BW
	}

}
