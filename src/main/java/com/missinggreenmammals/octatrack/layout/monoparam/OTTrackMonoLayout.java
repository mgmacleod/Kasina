package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public abstract class OTTrackMonoLayout extends OTMonoParamLayout {
	protected final TrackBank trackBank;

	public OTTrackMonoLayout(ControllerHost host) {
		super(host);
		trackBank = createTrackBank(host);
	}

	protected abstract TrackBank createTrackBank(ControllerHost host);

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		controls.bindToPrevButton(trackBank.scrollPageBackwardsAction());
		controls.bindToNextButton(trackBank.scrollPageForwardsAction());

//		SendBank sendBank = trackBank.getItemAt(0).sendBank();
//		sendBank.exists();

	}
}
