# 9-brainstorm-uses-for-remaining-controls Dev Log

Controls not currently used listed below. 

## encoders (PB and AT)
- PB binds just fine, but there aren't / don't seem to be ready-made matchers for CAT 
- Need a lower-level approach from the Hardware API (HAPI) or an old-school raw MIDI handler if all else fails
  - the former: see [Channel Pressure](./channel-pressure.md)
  - resolved in the code


## regular trig keys
- 6 remaining 
  - 3 per side 
- some other options
  - mute and solo 
  - further context-sensitive actions 
    - select current thing
    - deactivate/mute/disable current thing?


## the page key
- save this for a shift key!
  - question then becomes, how should shift functions work?


## the yes and no buttons 
- they do transmit MIDI
- but they probably can't be used without impacting functionality on the audio side
  - every time you arm or disarm anything, you get one of these messages


## the scene select keys and the crossfader transmit and receive on channel 1
- something scene-like should be possible with the Hardware API and CC modulators: [further exploration](ot-scenes-for-bitwig.md)
  - won't be pretty, but might be workable


## track mutes
- FUNC+T# sends MIDI (OT will RX the same)
- other ways of muting (e.g., quick mutes) do NOT TX 
  - so no good way to ensure the display of the lights on the device and the state of the thing it's supposed to represent in the software are aligned / synchronized
  - for this reason, the obvious mapping to Mute state of the current track works less well
- current thinking is to support 'turning off' a track so that its controls have no effect on anything in Bitwig
  - could be that the mappings are simply cleared and any data sent from the relevant controls is simply eaten by Bitwig
  - or could be that signals are passed through effectively outside of Bitwig, so you could still sequence other gear
    - or perhaps again you 


## Other octaves of the chromatic keyboard
- see [here](./other-keyboard-octaves.md)