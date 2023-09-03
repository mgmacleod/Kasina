# MbGM1 - Basic Functionality for the Octatrack

## Conditional mapping for remote controls (dynamically switch between track and device remotes)
- I want to change the bindings of a set of CC knobs (Ctrl2) based on the state of a 'button' that sends note on/off events
  - ideally, this will work as a toggle: hit the button to go from one set of bindings to the other and hit it again to go back.
    - can live with using two buttons to select mode
- Need to understand:
  - how to bind the button pressed action to calling a Java Function
  - how to write the function so it can be called


### How to bind the button pressed action to calling a Java Function
- one way is to use `ControllerHost.createAction(DoubleConsumer actionPressureConsumer, Supplier< String > descriptionProvider)`, as in the below:
  - `host.createAction(this::handleRemoteModeChange, this::remoteModeChangeDescription);`


### How / where / when to define the function to be called
- need to be careful about accessing the `HardwareBindable` instances
  - they can only be accessed during initialization
  - current solution is to access and store them during init and then set them on the appropriate hardware controls when the above function is called (when it, in turn, is called from the hardware button press)

## Track Selection
- Is there a way to select