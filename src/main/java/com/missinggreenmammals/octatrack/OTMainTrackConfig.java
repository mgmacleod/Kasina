package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;

public abstract class OTMainTrackConfig extends OTTrackConfig {

	public OTMainTrackConfig(ControllerHost host) {
		super(host);
	}

	@Override
	protected TrackBank createTrackBank(ControllerHost host) {
		return host.createMainTrackBank(PAGE_SIZE, 0, 0);
	}

}
