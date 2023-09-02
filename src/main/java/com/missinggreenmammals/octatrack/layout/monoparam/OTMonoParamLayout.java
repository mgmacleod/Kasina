package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.AbsoluteHardwareKnob;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;
import com.missinggreenmammals.octatrack.layout.OTTransportLayout;

public abstract class OTMonoParamLayout extends OTTransportLayout {

	public OTMonoParamLayout(ControllerHost host) {
		super(host);
	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);

		for (int i = 0; i < PAGE_SIZE; i++) {
			AbsoluteHardwareKnob knob = controls.getCcKnobs().get(i);
			knob.setBinding(getItemAt(i));
		}
	}

	protected abstract HardwareBindable getItemAt(int index);

}
