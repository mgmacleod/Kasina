package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Project;

public abstract class OTProjectLayout extends OTMonoParamLayout {

	protected final Project project;

	public OTProjectLayout(ControllerHost host) {
		super(host);
		project = host.getProject();
	}

}
