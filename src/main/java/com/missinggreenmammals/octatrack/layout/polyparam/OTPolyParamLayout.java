package com.missinggreenmammals.octatrack.layout.polyparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;
import com.missinggreenmammals.octatrack.layout.OTTransportLayout;

public abstract class OTPolyParamLayout extends OTTransportLayout {
	protected final TrackBank trackBank;

	public OTPolyParamLayout(ControllerHost host, TrackBank trackBank) {
		super(host);
		this.trackBank = trackBank;

	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

//		controls.bindToPrevButton(trackBank.scrollPageBackwardsAction());
//		controls.bindToNextButton(trackBank.scrollPageForwardsAction());

		controls.bindToPrevButton(trackBank.scrollBackwardsAction());
		controls.bindToNextButton(trackBank.scrollForwardsAction());
	}

}
