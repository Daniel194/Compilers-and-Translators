### Model

# state

power = 'Off'
door = 'Closed'


# actions

# power switch

def on_enabled():
    return power == 'Off' and door == 'Closed'  # door for safety


def on():
    global power
    power = 'On'


def off_enabled():
    return power == 'On'


def off():
    global power
    power = 'Off'


# door

def open_enabled():
    return door == 'Closed'


def open():
    global power, door
    power = 'Off'  # for safety
    door = 'Open'


def close_enabled():
    return door == 'Open'


def close():
    global door
    door = 'Closed'


## Metadata

state = ('door', 'power')

actions = {on, off, open, close}
enablers = {on: (on_enabled,), off: (off_enabled,),
            open: (open_enabled,), close: (close_enabled,)}


def state_invariant():
    """
    Safety requirement: power == 'On' => door == 'Closed'
    """
    return power == 'Off' or door == 'Closed'


def accepting():
    return power == 'Off' and door == 'Closed'