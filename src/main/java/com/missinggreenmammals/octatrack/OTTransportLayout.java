package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.AbsoluteHardwareKnob;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.bitwig.extension.controller.api.Transport;

public abstract class OTTransportLayout extends OTMidiTrackLayout {

	protected final Transport transport;

	public OTTransportLayout(ControllerHost host) {
		transport = host.createTransport();
	}


	public void applyTo(OTMidiHardwareControls controls) {
		controls.bindToPlayButton(transport.playAction());
		controls.bindToStopButton(transport.stopAction());

		for (int i = 0; i < PAGE_SIZE; i++) {
			AbsoluteHardwareKnob knob = controls.ccKnobs.get(i);
			knob.setBinding(getItemAt(i));
		}
	}
	
	protected abstract HardwareBindable getItemAt(int index);

}
