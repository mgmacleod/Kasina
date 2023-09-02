package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.TrackBank;

public abstract class OTMainTrackMonoLayout extends OTTrackMonoLayout {

	public OTMainTrackMonoLayout(ControllerHost host) {
		super(host);
	}

	@Override
	protected TrackBank createTrackBank(ControllerHost host) {
		return host.createMainTrackBank(PAGE_SIZE, 0, 0);
	}

}
