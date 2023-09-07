package com.missinggreenmammals.kasina.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.kasina.octatrack.hardware.OTMidiHardwareControls;

public abstract class OTDefaultTrackLayout extends OTTransportLayout {
	protected final TrackBank trackBank;

	public OTDefaultTrackLayout(ControllerHost host, TrackBank trackBank) {
		super(host);
		this.trackBank = trackBank;

	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		controls.bindToPrevButton(trackBank.scrollPageBackwardsAction());
		controls.bindToNextButton(trackBank.scrollPageForwardsAction());
	}

}
