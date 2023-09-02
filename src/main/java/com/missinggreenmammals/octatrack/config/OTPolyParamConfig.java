package com.missinggreenmammals.octatrack.config;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorDeviceFollowMode;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MasterTrack;
import com.bitwig.extension.controller.api.PinnableCursorDevice;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.layout.OTMidiTrackLayout;
import com.missinggreenmammals.octatrack.layout.polyparam.OTMasterPolyLayout;
import com.missinggreenmammals.octatrack.layout.polyparam.OTTrackPolyLayout;
import com.missinggreenmammals.octatrack.track.OTMidiTrack;

public class OTPolyParamConfig extends OTMidiConfiguration {

	protected TrackBank trackBank;
	protected CursorTrack cursorTrack;
	protected PinnableCursorDevice cursorDevice;
	protected MasterTrack masterTrack;

	public OTPolyParamConfig(ControllerHost host, HardwareSurface hardwareSurface) {
		trackBank = host.createMainTrackBank(7, 2, 0);
		cursorTrack = host.createCursorTrack("OT_CURSOR_TRACK", "Cursor track", 2, 0, true);
		cursorDevice = cursorTrack.createCursorDevice("OT_CURSOR_DEVICE", "Cursor Device", 0,
				CursorDeviceFollowMode.FOLLOW_SELECTION);
		masterTrack = host.createMasterTrack(0);

		trackBank.followCursorTrack(cursorTrack);

		// main tracks
		int track = 1;
		int channel = 8;

		for (int i = 0; i < tracks.length - 1; i++) {
			tracks[i] = new OTMidiTrack("TRACK" + track, channel, track, host, hardwareSurface) {
				@Override
				protected OTMidiTrackLayout createLayout(ControllerHost host) {
					return new OTTrackPolyLayout(host, trackBank, cursorTrack, cursorDevice);
				}
			};

			track++;
			channel++;
		}

		// master track
		tracks[7] = new OTMidiTrack("MasterTrack", 15, 8, host, hardwareSurface) {
			@Override
			protected OTMidiTrackLayout createLayout(ControllerHost host) {
				return new OTMasterPolyLayout(host, trackBank, masterTrack);
			}
		};
	}

}
