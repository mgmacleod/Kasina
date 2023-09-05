package com.missinggreenmammals.kasina.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.DocumentState;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.SettableRangedValue;
import com.missinggreenmammals.kasina.octatrack.config.OTDefaultParamConfig;
import com.missinggreenmammals.kasina.octatrack.config.OTMidiConfiguration;

public class Octatrack {

	private HardwareSurface hardwareSurface;
	@SuppressWarnings("unused")
	private OTDefaultParamConfig config;
	private SettableRangedValue sceneA;
	private SettableRangedValue sceneB;

	public Octatrack(ControllerHost host) {
		hardwareSurface = host.createHardwareSurface();
		
		final DocumentState documentState = host.getDocumentState ();
		sceneA = documentState.getNumberSetting("Scene A", "Scenes", 0, 128, 1, null, 0);
		sceneB = documentState.getNumberSetting("Scene B", "Scenes", 0, 128, 1, null, 8);
		
		int valSceneA = (int) sceneA.get();
		int valSceneB = (int) sceneB.get();
		
		config = new OTDefaultParamConfig(host, hardwareSurface, this, valSceneA, valSceneB);
	}
	
	public void doPersistence() {
		int valSceneA = config.getSceneSelectionA();
		int valSceneB = config.getSceneSelectionB();
		
		sceneA.set(valSceneA, 128);
		sceneB.set(valSceneB, 128);
	}

}
