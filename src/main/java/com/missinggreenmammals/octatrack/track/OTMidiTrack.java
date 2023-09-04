package com.missinggreenmammals.octatrack.track;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;
import com.missinggreenmammals.octatrack.layout.OTMidiTrackLayout;

public abstract class OTMidiTrack {

	protected final String name;
	protected final HardwareSurface hardwareSurface;
	protected final ControllerHost host;
	protected final OTMidiHardwareControls controls;
	protected final OTMidiTrackLayout layout;

	public OTMidiTrack(final String name, OTMidiHardwareControls controls, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		this.controls = controls;
		this.host = host;
		this.name = name;
		this.hardwareSurface = hardwareSurface;

		layout = createLayout(host);
		layout.applyTo(controls);
	}

	protected abstract OTMidiTrackLayout createLayout(ControllerHost host);

}
