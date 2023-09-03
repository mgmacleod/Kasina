package com.missinggreenmammals.octatrack.config;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MasterTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;
import com.missinggreenmammals.octatrack.layout.OTMidiTrackLayout;
import com.missinggreenmammals.octatrack.layout.polyparam.OTMasterPolyLayout;
import com.missinggreenmammals.octatrack.layout.polyparam.OTTrackPolyLayout;
import com.missinggreenmammals.octatrack.track.OTMidiTrack;

public class OTPolyParamConfig extends OTMidiConfiguration {

	protected TrackBank trackBank;
	protected CursorTrack cursorTrack;
	protected MasterTrack masterTrack;

	public OTPolyParamConfig(ControllerHost host, HardwareSurface hardwareSurface) {
		trackBank = host.createMainTrackBank(7, 2, 0);
		cursorTrack = host.createCursorTrack("OT_CURSOR_TRACK", "Cursor track", 2, 0, true);
		masterTrack = host.createMasterTrack(0);

		trackBank.followCursorTrack(cursorTrack);

		// main tracks
		int track = 1;
		int channel = 8;

		for (int i = 0; i < tracks.length - 1; i++) {
			OTMidiHardwareControls controls = new OTMidiHardwareControls(channel, track, host, hardwareSurface);

			tracks[i] = new OTMidiTrack("TRACK" + track, controls, host, hardwareSurface) {
				@Override
				protected OTMidiTrackLayout createLayout(ControllerHost host) {
					return new OTTrackPolyLayout(host, trackBank, cursorTrack, controls);
				}
			};

			track++;
			channel++;
		}

		// master track
		track = 8;
		channel = 15;
		OTMidiHardwareControls controls = new OTMidiHardwareControls(channel, track, host, hardwareSurface);

		tracks[7] = new OTMidiTrack("MasterTrack", controls, host, hardwareSurface) {
			@Override
			protected OTMidiTrackLayout createLayout(ControllerHost host) {
				return new OTMasterPolyLayout(host, trackBank, masterTrack);
			}
		};
	}

}
