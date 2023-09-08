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
- we already have a `CursorDevice` per track
  - `CursorDevice` extends both `Cursor` and `Device`
    - `Device.getCursorSlot()` returns a `DeviceSlot`, which extends `DeviceChain` and "represent nested FX slots in devices"
    - `Device.deviceChain()` returns the chain containing the current device
- Given that, it seems like the following should work:
  - call `getCursorSlot()` on the cursorDevice to enter a chain
    - once inside, use `DeviceSlot.createDeviceBank(1)` to create a `DeviceBank` with a single device
    - use `DeviceBank.scrollPageDown()` and `DeviceBank.scrollPageUp()` to move back and forth
    - use `DeviceBank.getItemAt(0)` to get the current `Device`
      - should be able to select it with `Device.selectInEditor()`, though I feel like I tried that with the `CursorDevice` without luck, but we'll see
  - call `deviceChain()` on the current device in the chain to get back out
    - although this then lands you in a `DeviceChain`, rather than the previously selected device from before entering the chain
      - a few possibilities here
        - use the same approach as above and create a `DeviceBank` on the chain and move around that way
        - somehow store the start point and return to it when leaving the chain
          - but this assumes we can easily determine when it's appropriate to return to the starting point
            - if we have multiply nested devices, it will be tricky to keep track of where we are; so the former is probably the better approach
- There are some other methods of the `Device` interface that might be useful here
  - `isNested()` to determine if a device is part of a nested device chain in an FX slot
  - `hasSlots()` to determine if a device has FX slots to navigate
    - hmm, the JavaDocs here say "Use {@link #slotNames()} to get a list of available slot names, and navigate to devices in those slots using the {@link CursorDevice} interface."
      - not clear how that would work
        - ah, that would probably be methods like `CursorDevice.selectFirstInSlot(String)`
      - Still not quite sure how to get a `CursorDevice` in this context
        - oh, of course. We should already have it since we have one to navigate the devices in the 'main' chain on the track
          - so is just `cursorDevice.`

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
    - a `Key` hierarchy to represent trig keys and shift key, and organized into a `Keyboard` object
      - a subclass here can be the shift key, which is like a regular key in some ways
        - the shift key needs additional bindings, as well need to respond to note on and note off events
      - trig keys should contain two bindings, one for regular and one for shift modes
      - the keyboard should manage switching between shift modes for all keys
        - of course, there will be a keyboard per track
    - an `Encoder` hierarchy and an appropriate container
    - 