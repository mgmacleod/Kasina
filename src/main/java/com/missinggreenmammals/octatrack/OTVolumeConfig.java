package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;

public class OTVolumeConfig extends OTMainTrackConfig {

	public OTVolumeConfig(ControllerHost host) {
		super(host);
	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		controls.bindToCC1Knob(trackBank.getItemAt(0).volume());
		controls.bindToCC2Knob(trackBank.getItemAt(1).volume());
		controls.bindToCC3Knob(trackBank.getItemAt(2).volume());
		controls.bindToCC4Knob(trackBank.getItemAt(3).volume());
		controls.bindToCC5Knob(trackBank.getItemAt(4).volume());
		controls.bindToCC6Knob(trackBank.getItemAt(5).volume());
		controls.bindToCC7Knob(trackBank.getItemAt(6).volume());
		controls.bindToCC8Knob(trackBank.getItemAt(7).volume());
		controls.bindToCC9Knob(trackBank.getItemAt(8).volume());
		controls.bindToCC10Knob(trackBank.getItemAt(9).volume());

		controls.bindToPrevButton(trackBank.scrollPageBackwardsAction());
		controls.bindToNextButton(trackBank.scrollPageForwardsAction());

	}

}
