package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;

public class OTPanMonoLayout extends OTMainTrackMonoLayout {

	public OTPanMonoLayout(ControllerHost host) {
		super(host);
	}

	@Override
	protected HardwareBindable getItemAt(int index) {
		return trackBank.getItemAt(index).pan();
	}

}
