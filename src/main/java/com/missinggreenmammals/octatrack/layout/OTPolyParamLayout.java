package com.missinggreenmammals.octatrack.layout;

import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SendBank;
import com.bitwig.extension.controller.api.TrackBank;
import com.missinggreenmammals.octatrack.OTMidiHardwareControls;

public class OTPolyParamLayout extends OTTransportLayout {

	protected final TrackBank trackBank;
	protected final CursorTrack cursorTrack;

	public OTPolyParamLayout(ControllerHost host, TrackBank trackBank, final CursorTrack cursorTrack) {
		super(host);
		this.trackBank = trackBank;
		this.cursorTrack = cursorTrack;

		trackBank.followCursorTrack(cursorTrack);
	}

	@Override
	public void applyTo(OTMidiHardwareControls controls) {
		super.applyTo(controls);
		
		// volume
		controls.getCcKnobs()[0].setBinding(trackBank.getItemAt(controls.getTrackNumber() - 1).volume());

		// pan
		controls.getCcKnobs()[3].setBinding(trackBank.getItemAt(controls.getTrackNumber() - 1).pan());

		SendBank sendBank = trackBank.getItemAt(0).sendBank();
		sendBank.exists();
	}

}
