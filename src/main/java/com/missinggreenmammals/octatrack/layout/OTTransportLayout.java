package com.missinggreenmammals.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Transport;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public abstract class OTTransportLayout extends OTMidiTrackLayout {

	protected final Transport transport;

	public OTTransportLayout(ControllerHost host) {
		transport = host.createTransport();
	}

	public void applyTo(OTMidiHardwareControls controls) {
		controls.bindToPlayButton(transport.playAction());
		controls.bindToStopButton(transport.stopAction());
		controls.bindToRecordButton(transport.recordAction());
	}
	
}
