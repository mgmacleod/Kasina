package com.missinggreenmammals.kasina.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import com.missinggreenmammals.kasina.octatrack.hardware.OTMidiHardwareControls;

public abstract class OTDefaultTrackLayout {
	protected final Transport transport;
	protected final TrackBank trackBank;

	public OTDefaultTrackLayout(final ControllerHost host, final TrackBank trackBank) {
		transport = host.createTransport();
		this.trackBank = trackBank;

	}

	public void applyTo(final OTMidiHardwareControls controls) {

		controls.getKeyboard().bindToPrevKeyRegular(trackBank.scrollPageBackwardsAction());
		controls.getKeyboard().bindToNextKeyRegular(trackBank.scrollPageForwardsAction());
		controls.getKeyboard().bindToPlayKeyRegular(transport.playAction());
		controls.getKeyboard().bindToStopKeyRegular(transport.stopAction());
		controls.getKeyboard().bindToRecordKeyRegular(transport.recordAction());
		controls.getKeyboard().bindToToggleMetronomeKeyRegular(transport.isMetronomeEnabled());
		controls.getKeyboard().bindToToggleTransportLoopKeyRegular(transport.isArrangerLoopEnabled());
	}

}
