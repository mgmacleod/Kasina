package com.missinggreenmammals.kasina.octatrack.track;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.kasina.octatrack.hardware.OTMidiHardwareControls;
import com.missinggreenmammals.kasina.octatrack.layout.OTDefaultTrackLayout;

public abstract class OTMidiTrack {

	protected final String name;
	protected final HardwareSurface hardwareSurface;
	protected final ControllerHost host;
	protected final OTMidiHardwareControls controls;
	protected final OTDefaultTrackLayout layout;

	public OTMidiTrack(final String name, final OTMidiHardwareControls controls, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		this.controls = controls;
		this.host = host;
		this.name = name;
		this.hardwareSurface = hardwareSurface;

		layout = createLayout(host);
		layout.applyTo(controls);

		controls.getKeyboard().disableShiftMode();
	}

	protected abstract OTDefaultTrackLayout createLayout(ControllerHost host);

}
