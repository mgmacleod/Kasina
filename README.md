# Kasina
Extensions for Bitwig Studio

# Devices (work in progress)

## Elektron Octatrack 

![Alt text](/images/ot-layout.png?raw=true "Octatrack Layout")

This extension uses the MIDI side of the Octatrack to turn it into a powerful controller for Bitwig Studio. It provides most the basic functionality you'd expect as well as a twist or two thrown in for good measure. Set the scene to crossfade into madness! :)


### Features
- Control basic transport functions
  - play
  - stop
  - record
- Adjust basic mix parameters of tracks in Bitwig Studio from 
  - Volume
  - Pan
  - Send 1 
  - Send 2
  - Select 
  - Arm
  - Solo
  - Mute
  - track types
    - tracks 1 - 7 control regular (audio/midi/hybrid) tracks 
    - track 8 controls the master track and project remote controls
  - trig buttons move the bank of 7 regular tracks around the project
- Adjust remote controls on tracks and devices
  - Navigate pages of remotes with trig buttons
  - trig keys toggle between track and device modes
    - in device mode, an additional set of trig keys scroll through the devices on the track
- Advanced features
  - This is a little less polished and requires a few hacks to make it work, but they are well worth the potential offered by the **integration of the Octatrack's scenes into Bitwig's modulation system**. Yes, that means what you think it means. With this extension, you can use modulators in bitwig to assign parameters from native and plugin devices to the scenes of your beloved Octatrack!
    - There are rough edges to some out, but it is currently workable. 
  - Many more things planned 
    - additional features for the MIDI side of the OT to act as a controller for Bitwig
    - what's to be gained by expanding the extension to incorporate elements of the Audio side?

## Korg Kaoss Pad KP3+ (not yet started)
- this SOB has a MIDI side too, ya know! 
  - Mapping it to XY FX is a no-brainer
  - more advanced and more like the OT above: map the XY params to 4 modulators and morph effects in the bitwig environment (again, but a bit different!)