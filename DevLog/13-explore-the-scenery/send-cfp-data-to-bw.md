# Send the CFP data (and only that) into BW 

## Attempt 1
- initial plan was to 
  - create a `HardwareBindable` that wraps a function call using `host.createAction(..)` as elsewhere here
  - bind this action to an `AbsoluteHardwareKnob` matching on the CC data for the CFP
  - in the action code, send the appropriate data to the note input
- however
  - I couldn't get the binding to work:

```java
HardwareBindable cfpChangeAction = host.createAction((value) -> host.println(""+value), () -> "handleCrossfader");
AbsoluteHardwareKnob cfpKnob = hardwareSurface.createAbsoluteHardwareKnob("OT_MIDI_CFP_KNOB");

cfpKnob.setAdjustValueMatcher(midiIn.createAbsoluteCCValueMatcher(CFP_CHANNEL, CFP_CC_NUMBER));
cfpKnob.setBinding(cfpChangeAction);
```


Dies with:

```
Cannot bind to this target
DX: Cannot bind to this target
    at ucX.rl(SourceFile:99)
    at ucX.addBinding(SourceFile:44)
    at NDx.setBinding(SourceFile:151)
    at com.missinggreenmammals.octatrack.config.OTPolyParamConfig.<init>(OTPolyParamConfig.java:51)
    at com.missinggreenmammals.octatrack.Octatrack.<init>(Octatrack.java:18)
    at com.missinggreenmammals.octatrack.OctatrackExtension.init(OctatrackExtension.java:20)
    [...]
```

## Attempt 2: old-school MIDI handler
- idea is to define a basic MIDI callback handler
  - intercept the appropriate CC values and send them through on the `NoteInput`

Note input config (a bit odd, but working):

```java
final String inputMask = "AF0000";
noteInput = midiIn.createNoteInput("OT_CFP_DATA", inputMask);
```

(Note that we're actually matching on what we _don't_ want so that no unintended signals get sent into Bitwig; fortunately, the masks only apply to data that should be automatically caught from the MIDI stream and sent into bitwig, not on data manually sent to the note input )

MIDI Callback:

```java
public void handleMidi(final int statusByte, final int data1, final int data2) {
    final ShortMidiMessage msg = new ShortMidiMessage(statusByte, data1, data2);

    if (msg.isControlChange() && msg.getChannel() == CFP_CHANNEL && msg.getData1() == CFP_CC_NUMBER)
        noteInput.sendRawMidiEvent(191, CFP_CC_NUMBER, data2);
}
```

### Remaining issues

1. what to do when a scene change occurs while the crossfader is moving (which will happen all the time)?
   1. probably don't want to leave the value of the old modulator where it was when the selection change came in, as this could potentially lead to a bunch of 'stuck' scenes
   2. snapping back to zero is probably the best thing 