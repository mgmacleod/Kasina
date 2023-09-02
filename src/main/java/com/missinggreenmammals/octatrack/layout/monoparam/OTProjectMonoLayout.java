package com.missinggreenmammals.octatrack.layout.monoparam;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Project;

public abstract class OTProjectMonoLayout extends OTMonoParamLayout {

	protected final Project project;

	public OTProjectMonoLayout(ControllerHost host) {
		super(host);
		project = host.getProject();
	}

}
