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
        - turns out this isn't strictly necessary; the way I implemented it, just pressing prev_device will return you to the parent, so there's no need for a shift function to leave a chain
          - which probably means I should change the binding to a single key, maybe the remote mode change key

## Navigating device chains
[detailed notes](./navigating-device-chains.md)

### multiple device chains
[detailed note](./navigating-multiple-device-chains.md)

### Is it possible to show current nested chain on the screen via the API?
- basic navigation of the device chains now works, but it's quite difficult to tell where you are if the chain is not expanded/visible
- there's `CursorDeviceSlot` which extends `DeviceChain` and has a `selectSlot(String slot)` method
  - however, it doesn't seem to be returned from anything else in the API
    - no other extensions seem to use it
    - it is also not the type returned from `CursorDevice.deviceChain()` when in a slot/nested chain
- I haven't been about to find anything else of use so far in my spelunking of the API
  - might ask on KVR


## Other similar forms of navigation (layers, selectors, drum pads)
- `Device` has similar methods to the above for working with layers, selectors, drum pads and the like (e.g., `hasLayers()`, `hasDrumPads()`), although it seems there isn't an equivalent to `slotNames()` for these and you have to navigate them by banks
  - however, `CursorDevice` does have similar `selectFirstIn*(..)` methods 


## Shift mode design
[detailed notes](shift-mode-design.md)