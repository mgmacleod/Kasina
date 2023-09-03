package com.missinggreenmammals.octatrack.config;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.missinggreenmammals.octatrack.track.monoparam.OTPans;
import com.missinggreenmammals.octatrack.track.monoparam.OTProjectRemotes;
import com.missinggreenmammals.octatrack.track.monoparam.OTVolumes;

public class OTMonoParamConfig extends OTMidiConfiguration {



	public OTMonoParamConfig(ControllerHost host, HardwareSurface hardwareSurface) {
		// volume on track 1 (0)
		tracks[0] = new OTVolumes(host, hardwareSurface);

		// Pan on track 2(1)
		tracks[1] = new OTPans(host, hardwareSurface);

		// project remotes on track 6 (5)
		tracks[5] = new OTProjectRemotes(host, hardwareSurface);
	}

}
