# Look into bulk upload and download of CC values

The idea here is to look into tightening the integration between BW and the OT by keeping the software and hardware parameter values synchronized as much as possible. There's the OT CC command that will have it send out all of its current CC values, but only for the audio side and therefore of little use for our usecase here. What is potentially of more interest is to push the state of the software to the hardware.

## Initial thoughts
- It would be great if we could get things to a point where the encoders are always at the same value as their corresponding software controls
  - This is likely not entirely possible, as I don't think its feasible to output the value of software controls to the OT in realtime; I did some experiments with this early on and ran into nasty MIDI feedback issues
    - I'm not sure if it's possible to output the MIDI data *only* when BW itself or some other control is changing a value and *not* when the same controls are changed using the OT
      - *if* that's possible, then we might have something
- likely the more reasonable and achievable goal is something like pushing the software state when selections change
  - track bank page change
    - all encoders on all tracks
  - remote controls page change or device selection change
    - the RC-related encoders (4 and 5 on FX1 and all of them on FX2) of the currently control track on the OT

## After a little experimentation
- I'm thinking this is more trouble than it's worth
  - so many edge cases to handle
    - would need to be observing for all selection events anywhere, not just on the OT
      - otherwise, we might be able to keep things in sync if you only use the OT for everything, but as soon as you touch something in the UI or some other controller, we'd be back to unsynchronized controls
        - I suspect it would be more annoying to have sync work sporadically than not at all
  - hence, I'm pulling the plug on this for now
    - I don't really find catch up more to be all that problematic and there's always relative scaling to make things feel a little nice.