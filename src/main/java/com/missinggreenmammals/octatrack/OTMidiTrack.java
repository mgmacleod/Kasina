package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public abstract class OTMidiTrack {

	protected final String name;
	protected final HardwareSurface hardwareSurface;
	protected final ControllerHost host;
	protected final OTMidiHardwareControls controls;
	protected final OTTransportLayout config;

	public OTMidiTrack(final String name, final int channel, final int trackNumber, final ControllerHost host,
			final HardwareSurface hardwareSurface) {

		this.host = host;
		this.name = name;
		this.hardwareSurface = hardwareSurface;

		controls = new OTMidiHardwareControls(channel, trackNumber, host, hardwareSurface);
		config = createConfig(host);
		config.applyTo(controls);
	}

	protected abstract OTTransportLayout createConfig(ControllerHost host);

//	protected abstract void setBindings(ControllerHost host);

//	private void initButtons(final int channel, final ControllerHost host, HardwareSurface hardwareSurface) {
//		final HardwareButton playButton = hardwareSurface.createHardwareButton(createId("PLAY"));
//		final HardwareButton stopButton = hardwareSurface.createHardwareButton(createId("STOP"));
//		final Transport transport = host.createTransport();
//		playButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 48));
//		stopButton.pressedAction().setActionMatcher(midiIn.createNoteOnActionMatcher(channel, 50));
//		playButton.pressedAction().setBinding(transport.playAction());
//		stopButton.pressedAction().setBinding(transport.stopAction());
//
//	}
}
