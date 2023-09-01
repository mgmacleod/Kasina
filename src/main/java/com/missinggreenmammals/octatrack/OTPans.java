package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class OTPans extends OTMidiTrack {

	public OTPans(ControllerHost host, HardwareSurface hardwareSurface) {
		this("PANS", 9, 2, host, hardwareSurface);
	}

	private OTPans(String name, int channel, int trackNumber, ControllerHost host, HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected void setBindings(ControllerHost host) {
		pbKnob.setBinding(trackBank.getItemAt(0).pan());
		cc1knob.setBinding(trackBank.getItemAt(1).pan());
		cc2knob.setBinding(trackBank.getItemAt(2).pan());
		cc3knob.setBinding(trackBank.getItemAt(3).pan());
		cc4knob.setBinding(trackBank.getItemAt(4).pan());
		cc5knob.setBinding(trackBank.getItemAt(5).pan());
		cc6knob.setBinding(trackBank.getItemAt(6).pan());
		cc7knob.setBinding(trackBank.getItemAt(7).pan());
		cc8knob.setBinding(trackBank.getItemAt(8).pan());
		cc9knob.setBinding(trackBank.getItemAt(9).pan());
		cc10knob.setBinding(trackBank.getItemAt(10).pan());

	}

	@Override
	protected void updateCCs() {
		// TODO Auto-generated method stub

	}

}
