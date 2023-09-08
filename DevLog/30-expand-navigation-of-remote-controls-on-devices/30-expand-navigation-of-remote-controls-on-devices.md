# GH-30: Expand navigation of remote controls on devices

## Background
The idea here is that we want to be able to navigate into and out of device chains. For this, a few things need to happen:

- First, I'll need to figure out how to move between device chains from an API perspective
- The larger issue is that I don't really have any more keys to use on the default octave of the keyboard to map to the functions
  - I don't want to get into potentially using other octaves at this point, as that's a whole other can of worms
  - a slightly (hopefully!) smaller can of worms is to introduce shift functions using the [PAGE] key
    - then we can hold shift and select one of the keys already in use
      - it probably makes sense to use the device navigation keys: 
      - shift + next_device = enter device chain
      - shift + prev_device = leave device chain

## Navigating device chains
- 

## Shift mode design

### Basic requirements
- when the presses a key regularly, the primary function is executed
- when the user holds the shift key and presses some other key, the secondary function is executed
  - if there is no secondary function assigned, nothing happens
- that's about it, I guess. It's a shift key!

### Implementation plan
- this will likely take the same basic approach as for switching between device and track remote control modes
  - there, we just swapped out the bindings in a fairly inelegant way
  - this is an opportunity to revisit that and put something better in place
    - Indeed, the shift function will ultimately extend to all the keys, so this will need to be thought out more clearly
- create
  - objects to represent the hardware elements more directly
    - a `Key` hierarchy to represent trig keys, and organized into a `Keyboard` object
      - a subclass here can be the shift key, which is like a regular key in some ways
      - trig keys should contain two bindings, one for regular and one for shift modes
      - the keyboard should manage switching between shift modes for all keys
        - of course, there will be a keyboard per track
    - an `Encoder` hierarchy and an appropriate container
    - 