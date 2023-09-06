package com.missinggreenmammals.kasina.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Transport;
import com.missinggreenmammals.kasina.octatrack.OTMidiHardwareControls;

public abstract class OTTransportLayout extends OTMidiTrackLayout {

	protected final Transport transport;

	public OTTransportLayout(ControllerHost host) {
		transport = host.createTransport();
	}

	public void applyTo(OTMidiHardwareControls controls) {
		controls.bindToPlayButton(transport.playAction());
		controls.bindToStopButton(transport.stopAction());
		controls.bindToRecordButton(transport.recordAction());
		controls.bindToToggleMetronomeButton(transport.isMetronomeEnabled());
		controls.bindToToggleTransportLoopButton(transport.isArrangerLoopEnabled());
	}
	
}
