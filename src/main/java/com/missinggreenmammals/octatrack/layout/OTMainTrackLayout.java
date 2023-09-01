package com.missinggreenmammals.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;

public abstract class OTMainTrackLayout extends OTTrackLayout {

	public OTMainTrackLayout(ControllerHost host) {
		super(host);
	}

	@Override
	protected TrackBank createTrackBank(ControllerHost host) {
		return host.createMainTrackBank(PAGE_SIZE, 0, 0);
	}

}
