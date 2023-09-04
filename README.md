# Kasina


Extensions for Bitwig Studio that try to look deeply into what makes a piece of gear great and then bring something of that to the Bitwig world.


***WARNING: This project is very much a work in progress. Expect breakage, changage, rearrangeage, and any other -age that might happen during active development. I'm trying to make things I can use, which means I'm definitely not trying to break anything and make every effort to avoid posting code that does so (I don't want to hurt my very expensive OT any more than you do!). That said, there can be lots of crazy data flying around all over the place in these kinds of setups and there's no way I can anticipant everything that might happen. All that to say: I believe everything here is safe, but I can neither prove nor guarantee it. Proceed at your own risk.*** 


# Devices

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
    - tracks 1 - 7 on the Octatrack control regular (audio/midi/hybrid) tracks in Bitwig Studio
    - track 8 on the Octatrack controls Bitwig's master track and the project remote controls
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

## Other devices and possible experimental subjects
- Novation LaunchPad Pro MKI
- Novation LaunchControl XL
- Ableton Push 2
- NI Maschine MK3
- Arturia Beatstep Pro
- Keith McMillen SoftStep 2
- Various other odds and ends