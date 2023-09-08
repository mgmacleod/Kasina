package com.missinggreenmammals.kasina.octatrack.track;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.kasina.octatrack.hardware.OtMidiHardwareControls;
import com.missinggreenmammals.kasina.octatrack.layout.OtDefaultTrackLayout;

public abstract class OtMidiTrack {

	protected final String name;
	protected final HardwareSurface hardwareSurface;
	protected final ControllerHost host;
	protected final OtMidiHardwareControls controls;
	protected final OtDefaultTrackLayout layout;

	public OtMidiTrack(final String name, final OtMidiHardwareControls controls, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		this.controls = controls;
		this.host = host;
		this.name = name;
		this.hardwareSurface = hardwareSurface;

		layout = createLayout(host);
		layout.applyTo(controls);

		controls.getKeyboard().disableShiftMode();
	}

	protected abstract OtDefaultTrackLayout createLayout(ControllerHost host);

}
