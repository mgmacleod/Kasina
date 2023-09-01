package com.missinggreenmammals.octatrack;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.HardwareSurface;

public class OTVolumes extends OTMidiTrack {

	public OTVolumes(ControllerHost host, HardwareSurface hardwareSurface) {
		this("VOLUMES", 8, 1, host, hardwareSurface);
	}

	private OTVolumes(String name, int channel, int trackNumber, ControllerHost host, HardwareSurface hardwareSurface) {
		super(name, channel, trackNumber, host, hardwareSurface);
	}

	@Override
	protected void setBindings(ControllerHost host) {

		pbKnob.setBinding(trackBank.getItemAt(0).volume());
		cc1knob.setBinding(trackBank.getItemAt(1).volume());
		cc2knob.setBinding(trackBank.getItemAt(2).volume());
		cc3knob.setBinding(trackBank.getItemAt(3).volume());
		cc4knob.setBinding(trackBank.getItemAt(4).volume());
		cc5knob.setBinding(trackBank.getItemAt(5).volume());
		cc6knob.setBinding(trackBank.getItemAt(6).volume());
		cc7knob.setBinding(trackBank.getItemAt(7).volume());
		cc8knob.setBinding(trackBank.getItemAt(8).volume());
		cc9knob.setBinding(trackBank.getItemAt(9).volume());
		cc10knob.setBinding(trackBank.getItemAt(10).volume());

		trackBank.getItemAt(1).volume().markInterested();
		cc1knob.isUpdatingTargetValue().markInterested();
	}

	@Override
	protected void updateCCs() {
//		Parameter volume = trackBank.getItemAt(1).volume();
//		int val = (int) Math.round(volume.get() * 127);
//		if (ccCache[1] == val) {
//			host.println("not updating");
//			midiOut.sendMidi(0xB8, CC1, val);
//
//			return;
//		}
//		ccCache[1] = val;
//		host.println("value is " + val);

	}

}
