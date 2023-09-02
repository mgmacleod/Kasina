package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.missinggreenmammals.octatrack.layout.OTMainTrackLayout;

public class OTPanLayout extends OTMainTrackLayout {

	public OTPanLayout(ControllerHost host) {
		super(host);
	}

	@Override
	protected HardwareBindable getItemAt(int index) {
		return trackBank.getItemAt(index).pan();
	}

}
