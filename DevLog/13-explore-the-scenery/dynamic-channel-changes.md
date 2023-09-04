# Modify the channel of the CFP data _dynamically_ according to the ABS 

- This ended up being relatively easy. 
- I just added some extra conditions to the MIDI handler to catch those data as they arrive

```java
if (msg.isControlChange() && msg.getChannel() == CFP_CHANNEL && msg.getData1() == AS_CC_NUMBER) {
    asChannel.set(msg.getData2());
}

if (msg.isControlChange() && msg.getChannel() == CFP_CHANNEL && msg.getData1() == BS_CC_NUMBER) {
    bsChannel.set(msg.getData2());
}
```

- then use that to modify the channel in the status byte when sending the raw message
  - fortunately, ABS values are 0 to 15 exactly like channel number, so we can just add the ABS values to the base status of 176 to get the appropriate status byte to send:

```java
if (msg.isControlChange() && msg.getChannel() == CFP_CHANNEL && msg.getData1() == CFP_CC_NUMBER) {
    int asStatus = asChannel.get() + BASE_CFP_STATUS;
    int bsStatus = bsChannel.get() + BASE_CFP_STATUS;
    noteInput.sendRawMidiEvent(asStatus, 0, 127 - data2);
    noteInput.sendRawMidiEvent(bsStatus, 0, data2);
}
```

- values for scene A are 'inverted' so that its values are highest precisely when the crossfader is closest to the A side (when the CC value is in fact 0)
- this all seems to work pretty nicely, although there are still some rough edges
  - issues remain about what to do with abandoned and/or stuck modulators
  - there seems to be some weirdness that happens with very fast changes, which this will absolutely need to support
  - there's also the question of default values
    - when the extension loads, what channels should it use?
    - if the ABS values are part of the 'report all audio CCs' command, then we could poll the OT itself on boot