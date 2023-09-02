package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareBindable;
import com.missinggreenmammals.octatrack.layout.OTMainTrackLayout;

public class OTVolumeLayout extends OTMainTrackLayout {

	public OTVolumeLayout(ControllerHost host) {
		super(host);
	}

	@Override
	protected HardwareBindable getItemAt(int index) {
		return trackBank.getItemAt(index).volume();
	}

}
