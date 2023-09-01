package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;

public class OTPanConfig extends OTMainTrackConfig {

	public OTPanConfig(ControllerHost host) {
		super(host);
	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		controls.bindToCC1Knob(trackBank.getItemAt(0).pan());
		controls.bindToCC2Knob(trackBank.getItemAt(1).pan());
		controls.bindToCC3Knob(trackBank.getItemAt(2).pan());
		controls.bindToCC4Knob(trackBank.getItemAt(3).pan());
		controls.bindToCC5Knob(trackBank.getItemAt(4).pan());
		controls.bindToCC6Knob(trackBank.getItemAt(5).pan());
		controls.bindToCC7Knob(trackBank.getItemAt(6).pan());
		controls.bindToCC8Knob(trackBank.getItemAt(7).pan());
		controls.bindToCC9Knob(trackBank.getItemAt(8).pan());
		controls.bindToCC10Knob(trackBank.getItemAt(9).pan());

		controls.bindToPrevButton(trackBank.scrollPageBackwardsAction());
		controls.bindToNextButton(trackBank.scrollPageForwardsAction());

	}

}
