package com.missinggreenmammals.kasina.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import com.missinggreenmammals.kasina.octatrack.hardware.OtMidiHardwareControls;

public abstract class OtDefaultTrackLayout {
	protected final Transport transport;
	protected final TrackBank trackBank;

	public OtDefaultTrackLayout(final ControllerHost host, final TrackBank trackBank) {
		transport = host.createTransport();
		this.trackBank = trackBank;

	}

	public void applyTo(final OtMidiHardwareControls controls) {

		controls.getKeyboard().bindToPrevKeyRegular(trackBank.scrollPageBackwardsAction());
		controls.getKeyboard().bindToNextKeyRegular(trackBank.scrollPageForwardsAction());
		controls.getKeyboard().bindToPlayKeyRegular(transport.playAction());
		controls.getKeyboard().bindToStopKeyRegular(transport.stopAction());
		controls.getKeyboard().bindToRecordKeyRegular(transport.recordAction());
		controls.getKeyboard().bindToToggleMetronomeKeyRegular(transport.isMetronomeEnabled());
		controls.getKeyboard().bindToToggleTransportLoopKeyRegular(transport.isArrangerLoopEnabled());
	}

}