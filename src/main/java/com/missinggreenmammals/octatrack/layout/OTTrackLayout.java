package com.missinggreenmammals.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;
import com.missinggreenmammals.octatrack.layout.monoparam.OTMonoParamLayout;

public abstract class OTTrackLayout extends OTMonoParamLayout {
	protected final TrackBank trackBank;

	public OTTrackLayout(ControllerHost host) {
		super(host);
		trackBank = createTrackBank(host);
	}

	protected abstract TrackBank createTrackBank(ControllerHost host);

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		controls.bindToPrevButton(trackBank.scrollPageBackwardsAction());
		controls.bindToNextButton(trackBank.scrollPageForwardsAction());
	}
}
