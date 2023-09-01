package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class Octatrack {

	private ControllerHost host;
	private HardwareSurface hardwareSurface;
	private OTVolumes volumes;
	private OTPans pans;
	private OTProjectRemotes remotes;

	public Octatrack(ControllerHost host) {
		this.host = host;
		hardwareSurface = host.createHardwareSurface();
		volumes = new OTVolumes(host, hardwareSurface);
		pans = new OTPans(host, hardwareSurface);
		remotes = new OTProjectRemotes(host, hardwareSurface);

	}

	public void updateCCs() {
//		volumes.updateCCs();
//		pans.updateCCs();
//		remotes.updateCCs();
	}

}
