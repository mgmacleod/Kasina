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
    - it would be nice to 'wrap' some hardware API around the handler function to fake that it's coming directly from a controls