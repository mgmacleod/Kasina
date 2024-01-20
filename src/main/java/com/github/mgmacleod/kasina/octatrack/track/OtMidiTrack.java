package com.github.mgmacleod.kasina.octatrack.track;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.github.mgmacleod.kasina.octatrack.hardware.OtMidiHardwareControls;
import com.github.mgmacleod.kasina.octatrack.layout.OtDefaultTrackLayout;

/**
 * Represents a MIDI track on the OT. Links an {@link OtDefaultTrackLayout} with
 * an {@link OtMidiHardwareControls} instance to connect Bitwig functionality of
 * the former with hardware actions from the latter.
 */
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
