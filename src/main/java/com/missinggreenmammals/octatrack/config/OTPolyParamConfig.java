package com.missinggreenmammals.octatrack.config;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiDataReceivedCallback;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MasterTrack;
import com.bitwig.extension.controller.api.MidiIn;
import com.bitwig.extension.controller.api.NoteInput;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;
import com.missinggreenmammals.octatrack.layout.OTMidiTrackLayout;
import com.missinggreenmammals.octatrack.layout.polyparam.OTMasterPolyLayout;
import com.missinggreenmammals.octatrack.layout.polyparam.OTTrackPolyLayout;
import com.missinggreenmammals.octatrack.track.OTMidiTrack;

public class OTPolyParamConfig extends OTMidiConfiguration {

	private final int CFP_CHANNEL = 0;
	private final int CFP_CC_NUMBER = 48;

	private final ShortMidiDataReceivedCallback midiCallback;
	protected TrackBank trackBank;
	protected CursorTrack cursorTrack;
	protected MasterTrack masterTrack;
	private final NoteInput noteInput;
	private ControllerHost host;

	public OTPolyParamConfig(ControllerHost host, HardwareSurface hardwareSurface) {
		this.host = host;

		trackBank = host.createMainTrackBank(7, 2, 0);
		cursorTrack = host.createCursorTrack("OT_CURSOR_TRACK", "Cursor track", 2, 0, true);
		masterTrack = host.createMasterTrack(0);

		trackBank.followCursorTrack(cursorTrack);

		MidiIn midiIn = host.getMidiInPort(0);

		midiIn.setMidiCallback(this::handleMidi);

		/*
		 * match on poly AT, of which none will be sent from the device and none can
		 * cause issues if piped into BWS. It's a little weird to match on something you
		 * specifically don't want, but it works in this context
		 */
		final String inputMask = "AF0000";
		noteInput = midiIn.createNoteInput("OT_CFP_DATA", inputMask);

		// main tracks
		int track = 1;
		int channel = 8;

		for (int i = 0; i < tracks.length - 1; i++) {
			OTMidiHardwareControls controls = new OTMidiHardwareControls(channel, track, host, hardwareSurface);

			tracks[i] = new OTMidiTrack("TRACK" + track, controls, host, hardwareSurface) {
				@Override
				protected OTMidiTrackLayout createLayout(ControllerHost host) {
					return new OTTrackPolyLayout(host, trackBank, cursorTrack, controls);
				}
			};

			track++;
			channel++;
		}

		// master track
		track = 8;
		channel = 15;
		OTMidiHardwareControls controls = new OTMidiHardwareControls(channel, track, host, hardwareSurface);

		tracks[7] = new OTMidiTrack("MasterTrack", controls, host, hardwareSurface) {
			@Override
			protected OTMidiTrackLayout createLayout(ControllerHost host) {
				return new OTMasterPolyLayout(host, trackBank, masterTrack);
			}
		};

		midiCallback = this::handleMidi;
	}

	public void handleMidi(final int statusByte, final int data1, final int data2) {
		final ShortMidiMessage msg = new ShortMidiMessage(statusByte, data1, data2);

		if (msg.isControlChange() && msg.getChannel() == CFP_CHANNEL && msg.getData1() == CFP_CC_NUMBER)
			noteInput.sendRawMidiEvent(191, CFP_CC_NUMBER, data2);
	}

	public ShortMidiDataReceivedCallback getMidiCallback() {
		return midiCallback;
	}

}
