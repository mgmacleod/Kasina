# Kasina Context Primer

> Use this document to bootstrap conversations about a music production setup that features the Kasina extension. It provides enough context for an LLM to reason about capabilities, limitations, and integration points without needing to read the source code.

---

## One-Paragraph Summary

Kasina is a Bitwig Studio controller extension that turns the Elektron Octatrack into a control surface. It provides standard mixer/transport/remote-control features (one OT track per Bitwig track, 7 regular + 1 master), and — more uniquely — it bridges the Octatrack's scene/crossfader system into Bitwig's modulation system. The crossfader physically morphs between two scenes on the OT; the extension intercepts those MIDI messages, transforms them, and injects them into Bitwig via a virtual MIDI port, where project-level CC modulators pick them up. This lets you assign any Bitwig parameter (native or plugin) to an OT scene and crossfade between parameter states using the OT's hardware crossfader.

---

## What the OT Controls in Bitwig

### Per Track (7 regular tracks + master)
- **Volume** (CC 7)
- **Pan** (CC 10)
- **Send A** (pitch bend) and **Send B** (aftertouch) — regular tracks only
- **8 remote control parameters** (CC 1, 2, 71-76) — track remotes on regular tracks, project remotes on master
- **Track select, arm, mute, solo** (trig keys)
- **Transport**: play, stop, record, metronome toggle, loop toggle (trig keys, shared across tracks)
- **Navigation**: track bank scrolling (page-wise, 8 tracks per page), remote page prev/next, device chain prev/next, enter/exit device slots, enter/exit groups (trig keys + shift)

### Track/Device Remote Mode Toggle
Each track can switch between controlling the track's own remote page and the selected device's remote page. In device mode, additional keys scroll through the device chain.

---

## Scene/Crossfader Integration: How It Works

### The Concept
The Octatrack has 16 scenes. Two can be loaded into slots A and B at any time. A physical crossfader morphs between them. The extension makes this interaction available in Bitwig by:

1. Intercepting scene select (CC 55/56) and crossfader position (CC 48) from the OT
2. Dynamically routing the crossfader value to the MIDI channels corresponding to the active scenes
3. Injecting these as CC messages into a virtual MIDI port that Bitwig's CC modulators receive

### The Mapping
- 16 scenes map to 16 MIDI channels (scene 1 = channel 0, ..., scene 16 = channel 15)
- Scene A receives the **inverted** crossfader value (max when fader is on the A side)
- Scene B receives the **direct** crossfader value (max when fader is on the B side)
- When a scene slot assignment changes, the old modulator is zeroed out and the new one receives the current value

### What the User Sets Up in Bitwig
- 16 CC modulators at the project level, each on a unique MIDI channel (0-15), all listening for CC 0
- Parameters from any device assigned to the desired scene modulators
- This is a one-time setup per project (or saveable as a template)

### The Result
Moving the crossfader smoothly morphs between whatever parameters are assigned to the two active scenes' modulators. Changing scene assignments on the OT dynamically re-routes the crossfader to different modulators. It's a real-time parameter morph system that combines the OT's physical UI with Bitwig's deep modulation capabilities.

---

## What the Extension Does NOT Do

- It does not control the OT's audio engine — the OT remains an independent audio device
- It does not handle OT sequencer sync (that's a separate MIDI clock/transport concern)
- It does not auto-create the CC modulators in Bitwig — that's manual setup
- It does not provide visual feedback from Bitwig back to the OT (the MIDI output port is unused)
- The arranger-only transport control means clip launcher transport is not addressed
- There is no per-track EQ or insert effect control beyond what's exposed through remote control pages

---

## Key Architectural Details (For Technical Discussions)

- **Language/Framework**: Java, Bitwig Controller API v18
- **MIDI channels**: OT tracks use channels 8-15; scene/CFP data arrives on channel 0
- **Virtual port**: A `NoteInput` named "OT_CFP_DATA" with a poly-aftertouch mask that matches nothing from the real MIDI stream, used solely for extension-injected modulation data
- **Thread safety**: Scene selection and crossfader values stored as `AtomicInteger` due to concurrent MIDI callback access
- **Track bank**: 8 tracks, page-scrollable, with a cursor track for selection tracking

---

## Integration Points and Expansion Possibilities

Things to be aware of when planning around this extension:

1. **Remote control pages** are the primary way to control device parameters from the OT. Any parameter you want to tweak from the OT needs to be on a remote page (track or device level).
2. **Project-level remotes** on the master track (OT track 8) are a good place for global parameters.
3. **The scene system** is independent of the remote control system — scenes use the modulation path, remotes use direct parameter binding. They complement each other.
4. **Send A and Send B** are hardwired to pitch bend and aftertouch respectively. These are 7-bit (aftertouch) and 14-bit (pitch bend) resolution.
5. **The track bank is 8 wide** and scrolls in pages of 8. If your Bitwig project has more than 8 tracks, you navigate between banks with trig keys.
6. **Group navigation** is supported via shift bindings — you can enter and exit groups.
7. **The scene modulator setup** (16 CC modulators at project level) is manual but could be templated in a Bitwig project template.
