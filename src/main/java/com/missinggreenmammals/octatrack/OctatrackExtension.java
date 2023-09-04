package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

public class OctatrackExtension extends ControllerExtension {
	@SuppressWarnings("unused")
	private Octatrack octatrack;

	protected OctatrackExtension(ControllerExtensionDefinition definition, ControllerHost host) {
		super(definition, host);
	}

	@Override
	public void init() {
		final ControllerHost host = getHost();
		host.showPopupNotification("Octatrack Initialized");

//		host.getMidiInPort(0).setMidiCallback(this::handleMidi);

		octatrack = new Octatrack(host);
	}

	@Override
	public void exit() {
		getHost().showPopupNotification("Octatrack Exited");
	}

	@Override
	public void flush() {
	}

//	public void handleMidi(final int statusByte, final int data1, final int data2) {
//		final ShortMidiMessage msg = new ShortMidiMessage(statusByte, data1, data2);
//		getHost().println(msg.toString());
//	}

}
