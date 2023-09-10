# Shift mode design

## Basic requirements
- when the presses a key regularly, the primary function is executed
- when the user holds the shift key and presses some other key, the secondary function is executed
  - if there is no secondary function assigned, nothing happens
- that's about it, I guess. It's a shift key!

## Implementation plan
- this will likely take the same basic approach as for switching between device and track remote control modes
  - there, we just swapped out the bindings in a fairly inelegant way
  - this is an opportunity to revisit that and put something better in place
    - Indeed, the shift function will ultimately extend to all the keys, so this will need to be thought out more clearly
- create
  - objects to represent the hardware elements more directly
    - a `Key` hierarchy to represent trig keys and shift key, and organized into a `Keyboard` object
      - a subclass here can be the shift key, which is like a regular key in some ways
        - the shift key needs additional bindings, as well need to respond to note on and note off events
      - trig keys should contain two bindings, one for regular and one for shift modes
      - the keyboard should manage switching between shift modes for all keys
        - of course, there will be a keyboard per track
    - an `Encoder` hierarchy and an appropriate container