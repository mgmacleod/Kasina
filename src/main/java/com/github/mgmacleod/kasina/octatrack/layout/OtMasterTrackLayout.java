package com.github.mgmacleod.kasina.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorRemoteControlsPage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.github.mgmacleod.kasina.octatrack.hardware.OtMidiHardwareControls;

/**
 * An {@link OtDefaultTrackLayout} to represent the master track in Bitwig.
 * Mostly the same as its parent class except that it doesn't have effect sends
 * and it has the project remotes instead of the track remotes.
 */
public class OtMasterTrackLayout extends OtRegularTrackLayout {

	protected Track rootTrackGroup;

	public OtMasterTrackLayout(final ControllerHost host, final TrackBank trackBank, final Track track,
			final CursorTrack cursorTrack,
			final OtMidiHardwareControls controls) {

		super(host, trackBank, track, cursorTrack, controls);

	}

	@Override
	protected void preinitialize(final ControllerHost host, final TrackBank trackBank, final Track track,
			final CursorTrack cursorTrack,
			final OtMidiHardwareControls controls) {

		rootTrackGroup = host.getProject().getRootTrackGroup();
	}

	@Override
	protected CursorRemoteControlsPage createRemotesPage(final OtMidiHardwareControls controls) {
		return rootTrackGroup.createCursorRemoteControlsPage("project-remotes", REMOTE_PAGE_SIZE, null);
	}

	@Override
	protected void bindSends(final OtMidiHardwareControls controls) {
		// override to do nothing, as there are no sends on the master track in BW
	}

}
