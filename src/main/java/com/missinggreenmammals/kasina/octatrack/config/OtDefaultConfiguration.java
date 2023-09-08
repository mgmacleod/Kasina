package com.missinggreenmammals.kasina.octatrack.config;

import java.util.concurrent.atomic.AtomicInteger;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.HardwareSurface;
import com.bitwig.extension.controller.api.MasterTrack;
import com.bitwig.extension.controller.api.MidiIn;
import com.bitwig.extension.controller.api.NoteInput;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.kasina.octatrack.hardware.OTMidiHardwareControls;
import com.missinggreenmammals.kasina.octatrack.layout.OtDefaultTrackLayout;
import com.missinggreenmammals.kasina.octatrack.layout.OtMasterTrackLayout;
import com.missinggreenmammals.kasina.octatrack.layout.OtRegularTrackLayout;
import com.missinggreenmammals.kasina.octatrack.track.OtMidiTrack;

public class OtDefaultConfiguration extends OtMidiConfiguration {

	private static final int CFP_CHANNEL = 0;
	private static final int CFP_CC_NUMBER = 48;
	private static final int AS_CC_NUMBER = 55;
	private static final int BS_CC_NUMBER = 56;
	private static final int BASE_CFP_STATUS = 176;

	protected TrackBank trackBank;
	protected CursorTrack cursorTrack;
	private final NoteInput noteInput;

	private AtomicInteger asChannel;
	private AtomicInteger bsChannel;
	private AtomicInteger cfp;

	public OtDefaultConfiguration(final ControllerHost host, final HardwareSurface hardwareSurface) {
		asChannel = new AtomicInteger(0);
		bsChannel = new AtomicInteger(8);
		cfp = new AtomicInteger(0);

		trackBank = host.createMainTrackBank(7, 2, 0);
		cursorTrack = host.createCursorTrack("OT_CURSOR_TRACK", "Cursor track", 2, 0, true);

		trackBank.followCursorTrack(cursorTrack);

		final MidiIn midiIn = host.getMidiInPort(0);

		midiIn.setMidiCallback(this::handleRawMidi);

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
			final OTMidiHardwareControls controls = new OTMidiHardwareControls(channel, track, host, hardwareSurface);
			final Track bwTrack = trackBank.getItemAt(track - 1);
			tracks[i] = new OtMidiTrack("TRACK" + track, controls, host, hardwareSurface) {
				@Override
				protected OtDefaultTrackLayout createLayout(final ControllerHost host) {
					return new OtRegularTrackLayout(host, trackBank, bwTrack, cursorTrack, controls);
				}
			};

			track++;
			channel++;
		}

		// master track
		track = 8;
		channel = 15;
		final OTMidiHardwareControls controls = new OTMidiHardwareControls(channel, track, host, hardwareSurface);
		final MasterTrack masterTrack = host.createMasterTrack(0);

		tracks[7] = new OtMidiTrack("MasterTrack", controls, host, hardwareSurface) {
			@Override
			protected OtDefaultTrackLayout createLayout(final ControllerHost host) {
				return new OtMasterTrackLayout(host, trackBank, masterTrack, cursorTrack, controls);
			}
		};

		initializeScenes();
	}

	public void handleRawMidi(final int statusByte, final int data1, final int data2) {
		final ShortMidiMessage msg = new ShortMidiMessage(statusByte, data1, data2);

		if (!msg.isControlChange() || msg.getChannel() != CFP_CHANNEL) {
			return;
		}

		if (data1 == AS_CC_NUMBER) {
			handleSceneSelectionChange(data2, asChannel, true);
		}

		if (data1 == BS_CC_NUMBER) {
			handleSceneSelectionChange(data2, bsChannel, false);
		}

		if (data1 == CFP_CC_NUMBER) {
			cfp.set(data2);
			noteInput.sendRawMidiEvent(asChannel.get() + BASE_CFP_STATUS, 0, 127 - data2);
			noteInput.sendRawMidiEvent(bsChannel.get() + BASE_CFP_STATUS, 0, data2);

		}
	}
	
	private void handleSceneSelectionChange(final int data2, final AtomicInteger channel, final boolean isSceneA) {
		final int oldChannel = channel.getAndSet(data2);
		noteInput.sendRawMidiEvent(oldChannel + BASE_CFP_STATUS, 0, 0);
		final int newCfp = isSceneA ? 127 - cfp.get() : cfp.get();
		noteInput.sendRawMidiEvent(channel.get() + BASE_CFP_STATUS, 0, newCfp);
	}

	private void initializeScenes() {
		noteInput.sendRawMidiEvent(asChannel.get() + BASE_CFP_STATUS, 0, 127 - cfp.get());
		noteInput.sendRawMidiEvent(bsChannel.get() + BASE_CFP_STATUS, 0, cfp.get());
	}

}
