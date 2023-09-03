# 9-brainstorm-uses-for-remaining-controls Dev Log

## Channel pressure

So Far, I haven't found a Hardware API-based solution for capturing the channel aftertouch / pressure .

### Option1: use an old-school MIDI handler 

e.g.: 
```java
public void handleRawMidi(final int statusByte, final int data1, final int data2) {
    final ShortMidiMessage msg = new ShortMidiMessage(statusByte, data1, data2);
    if (msg.isChannelPressure() && msg.getChannel() == channel) {
        // todo a thing
    }
}
```

- this seems to work pretty well for just getting the raw value
  - doesn't interfere with the rest of the controls using the newer hardware API
    - it would be nice to 'wrap' some hardware API code around the handler function to fake that it's coming directly from a control


### Option 2: figure out how to use expressions in MidiIn.createAbsoluteValueMatcher

Basic idea:
- There's a `MidiIn` function that might work: `com.bitwig.extension.controller.api.MidiIn.createAbsoluteValueMatcher(String, String, int)`
- the Strings are expressions but I can't find any documentation on how to structure them
  - however, it seems like you can just run an expression on the raw data and pull out what you need, which should be pretty straightforward 
- DrivenByMoss to the rescue:

```java
case NOTE:
    // Handle MPE channels 1-15
    if (channel == -1)
    {
        final String v = Integer.toString (control);
        final String pressedExpression = "status > 0x90 && status <= 0x9F && data1 == " + v + " && data2 > 0";
        final String releasedExpression = "data1 == " + v + " && ((status > 0x90 && status <= 0x9F && data2 == 0) || (status > 0x80 && status <= 0x8F))";
        pressedMatcher = this.port.createAbsoluteValueMatcher (pressedExpression, "data2", 7);
        releasedMatcher = this.port.createActionMatcher (releasedExpression);
    }
```
(`de.mossgrabers.bitwig.framework.midi.MidiInputImpl`: lines 124-133)

- and that's it. just needed to match on `status == 0xD<channel>` and do the usual wiring. 