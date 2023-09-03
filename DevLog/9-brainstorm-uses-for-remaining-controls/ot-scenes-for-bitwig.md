# OT Scenes in Bitwig

The basic idea here is to use the MIDI messages the OT can TX and RX about (1) the current position of the crossfader and (2) which scene was selected when a slot assignment changes in conjunction with some nasty hacks involving CC modulators on the project level (potentially also some Note Grid action and maybe even a CLAP plugin for good measure) to bring something like scenes into the Bitwig modulation system. Certainly this can be aided by the API, but it's still not likely to be pretty (maybe you could tuck it way somewhere on a group track or something?)


## A fairly direct mapping for something like on the A4/AR MKII
- crossfader transmits CC 0x30 (48)
- scene select
  - scene A select: CC 0x37 (55) 
  - scene b select: CC 0x38 (56)
  - in both cases, a value of 0 would map to scene slot 1 and 15 to slot 16, etc.
- so we should be able to tell:
  - when a scene is selected/assigned to either slot and which scene was selected
  - the current position of the crossfader
- putting them together, it should be possible to:
  - create an array of CC modulators to represent scenes
    - 16 modulators: one per scene, each on a separate channel
    - assign things to the modulators as normal
  - in code, we monitor the scene selection for slot B, in which case we:
    - modify the channel on which the CC messages are sent to target specific CC modulators
      - [what happens when the channel changes and the crossfader is in the middle? stay where is, snap back to zero, slide back if possible? ]
  - back in BW, the crossfader motion drives the amount for modulator X, which drives whatever changes have been assigned to the modulator in BW
    - change B to scene Y and the crossfader will drive the modulator associated with that channel 


### Options to bring slot A into the mix?
