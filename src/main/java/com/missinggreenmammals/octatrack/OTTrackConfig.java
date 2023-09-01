package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;

public abstract class OTTrackConfig extends OTConfiguration {
	protected final TrackBank trackBank;

	public OTTrackConfig(ControllerHost host) {
		super(host);
		trackBank = createTrackBank(host);
	}

	protected abstract TrackBank createTrackBank(ControllerHost host);
}
