# Navigating multiple device chains
- the approach so far only considers the first nested chain on a device. How do we deal with multiple chains?
- I think the most natural approach would be simply to iterate over the array of slot names from `CursorDevice.slotNames()` and navigate each in turn
  - we'll need to make use of the `hasNext()` and `hasPrevious()` methods to know when we should switch to the next/previous slot
  - will also need to track the slot names of the current device and which one we're currently in (probably by index rather than name)
- but where do we do the check if we should move to the next/previous slot?
  - it would have to be in the action that moves the cursor device 
    - so far, we're using `cursorDevice.selectNextAction()` and `cursorDevice.selectPreviousAction()` for this so we might need to move to a different approach, such as the `selectNext()` and `selectPrevious()` methods and wrapping them in a larger function that does the rest of the checks
  - did that and it's all working