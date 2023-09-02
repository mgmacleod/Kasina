package com.missinggreenmammals.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.PinnableCursorDevice;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public class OTPolyParamLayout extends OTTransportLayout {

	protected final TrackBank trackBank;
	protected final CursorTrack cursorTrack;
	protected final PinnableCursorDevice cursorDevice;

	public OTPolyParamLayout(ControllerHost host, TrackBank trackBank, final CursorTrack cursorTrack,
			final PinnableCursorDevice cursorDevice) {

		super(host);
		this.trackBank = trackBank;
		this.cursorTrack = cursorTrack;
		this.cursorDevice = cursorDevice;

	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);
		
		// volume
		controls.getCcKnobs()[0].setBinding(trackBank.getItemAt(controls.getTrackNumber() - 1).volume());

		// Sends
		SendBank sendBank = trackBank.getItemAt(controls.getTrackNumber() - 1).sendBank();
		controls.getCcKnobs()[1].setBinding(sendBank.getItemAt(0));
		controls.getCcKnobs()[2].setBinding(sendBank.getItemAt(1));

		// pan
		controls.getCcKnobs()[3].setBinding(trackBank.getItemAt(controls.getTrackNumber() - 1).pan());

	}

}
