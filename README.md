# Kasina


Extensions for Bitwig Studio that try to look deeply into what makes a piece of gear great and then bring something of that to the Bitwig world.


***NOTE: This is a personal project and very much a work in progress. I hope it might be useful to others, but the intention isn't to create a general purpose extension that meets everyone's needs. That said, comments and suggestions are welcome. Create a issue if you have an idea or run into an problem.*** 

## Download

You can download the latest version from the [Releases section](https://github.com/mgmacleod/Kasina/releases).

# Devices

## Elektron Octatrack 

![Alt text](/images/ot-layout.png?raw=true "Octatrack Layout")

This extension uses the MIDI side of the Octatrack to turn it into a powerful controller for Bitwig Studio. It provides most the basic functionality you'd expect as well as a twist or two thrown in for good measure. Set the scene to crossfade into madness! :)


### Features
- Control basic transport functions (Arranger only for now)
  - play
  - stop
  - record
- Adjust basic mix parameters of tracks in Bitwig Studio
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
  - OT scenes integration
    - This requires a few hacks to make it work, but they are well worth the potential offered by the **integration of the Octatrack's scenes into Bitwig's modulation system**. With this extension, you can use modulators in bitwig to assign parameters from native and plugin devices to the scenes of the Octatrack!


More complete documentation in the works and will be available in a [separate repo](https://github.com/mgmacleod/Kasina-Documentation).

## Other devices and possible experimental subjects (not yet started)
- Korg Kaoss Pad KP3+ (MIDI side)
- Novation LaunchPad Pro MKI
- Novation LaunchControl XL
- Ableton Push 2
- NI Maschine MK3
- Arturia Beatstep Pro
- Keith McMillen SoftStep 2