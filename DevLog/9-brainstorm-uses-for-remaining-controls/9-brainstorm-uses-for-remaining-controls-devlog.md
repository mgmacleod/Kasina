# 9-brainstorm-uses-for-remaining-controls Dev Log

Controls not currently used:

- encoders (PB and AT)
  - PB binds just fine, but there aren't / don't seem to be ready-made matchers for CAT 
  - Need a lower-level approach from the Hardware API (HAPI) or an old-school raw MIDI handler if all else fails
    - the former: see [Channel Pressure](./channel-pressure.md)
    - resolved in the code
- a few trig keys
  - 6 remaining 
    - 3 per side 
  - some other options
    - mute and solo 
    - further context-sensitive actions 
      - select current thing
      - deactivate current thing?
- the page key
  - save this for a shift key!
    - question then becomes, how should shift functions work?
- the yes and no buttons transmit MIDI
  - but they probably can't be used without impacting functionality on the audio side
- the scene select keys and the crossfader transmit and receive on channel 1
  - something scene-like should be possible with the Hardware API and CC modulators: [further exploration](ot-scenes-for-bitwig.md)
    - won't be pretty, but might be workable
