package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Project;

public abstract class OTProjectLayout extends OTTransportLayout {

	protected final Project project;

	public OTProjectLayout(ControllerHost host) {
		super(host);
		project = host.getProject();
	}

}
