package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Project;

public abstract class OTProjectConfig extends OTConfiguration {

	protected final Project project;

	public OTProjectConfig(ControllerHost host) {
		super(host);
		project = host.getProject();
	}

}
