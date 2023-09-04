# 13: Explore the scenery

Things are about to happen!! 

## Approach

Basic plan is outlined [here](../9-brainstorm-uses-for-remaining-controls/ot-scenes-for-bitwig.md). It probably makes sense to do the 'easy' one first and build from that if successful.

## Things that need to happen

### In Java land
1. capture CC data for A/B selection (ABS) and crossfader position (CFP)
   1. this should be straightforward: set up bindings as usual
2. Send the CFP data (and only that) into BW 
   1. might be a bit more involved, but should be a matter of 
      1. creating a `NoteInput` with the appropriate filters
      2. writing CFP data to it (like was done in a tutorial for note data)
   2. yep, was [more involved](./send-cfp-data-to-bw.md)
3. Modify the channel of the CFP data _dynamically_ according to the ABS value(s)
   1. this one will require [more thought](dynamic-channel-changes.md)! 

### In Bitwig
1. define the MIDI modulators at the project level for simplicity
   1. one modulator per scene and one channel per modulator
   2. modulate some things with each scene modulator
2. stand back and keep watch for explosion (implosion or some other catastrophic event)
