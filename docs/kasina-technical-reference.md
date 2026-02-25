# Kasina Technical Reference

## What Is Kasina?

Kasina is a Bitwig Studio controller extension (Java, Bitwig Controller API v18) that turns the Elektron Octatrack's MIDI capabilities into a control surface for Bitwig Studio. It does two things:

1. **Standard control surface features**: track volume, pan, sends, arm/mute/solo, transport, remote control page navigation, and device chain navigation — one OT track per Bitwig track.
2. **OT scene/crossfader integration into Bitwig's modulation system**: the Octatrack's scene select + crossfader messages are intercepted, transformed, and injected into Bitwig via a virtual MIDI port, driving project-level CC modulators that can modulate any parameter.

The extension is authored under the vendor name "Missing Green Mammals" and lives in the Java package `com.github.mgmacleod.kasina`.

---

## Part 1: Control Surface Mapping

### Track Layout

| OT Track | MIDI Channel | Bitwig Target | Notes |
|----------|-------------|---------------|-------|
| 1 | 8 | Regular track 1 | Volume, pan, 2 sends, track/device remotes |
| 2 | 9 | Regular track 2 | Same |
| 3 | 10 | Regular track 3 | Same |
| 4 | 11 | Regular track 4 | Same |
| 5 | 12 | Regular track 5 | Same |
| 6 | 13 | Regular track 6 | Same |
| 7 | 14 | Regular track 7 | Same |
| 8 | 15 | Master track | Volume, pan, **project-level** remotes, no sends |

The 7 regular tracks are presented through a `TrackBank` of size 8 that can be scrolled page-wise through the project.

### Encoders Per Track

Each OT track exposes 10 CC encoders, a pitch bend encoder, and a channel aftertouch encoder:

| Encoder | MIDI Source | Bitwig Binding (Regular) | Bitwig Binding (Master) |
|---------|-----------|--------------------------|-------------------------|
| CC 7 | CC encoder | Track volume | Master volume |
| CC 10 | CC encoder | Track pan | Master pan |
| CC 1 | CC encoder | Track remote param 0 | Project remote param 0 |
| CC 2 | CC encoder | Track remote param 1 | Project remote param 1 |
| CC 71-76 | CC encoders | Track remote params 2-7 | Project remote params 2-7 |
| Pitch bend | 14-bit PB | Send A (Send 1) | Unbound |
| Channel aftertouch | Channel pressure | Send B (Send 2) | Unbound |

### Keys Per Track (MIDI Notes 48-64)

| Note | Function | Shift Function |
|------|----------|----------------|
| 48 | Remote page previous | — |
| 49 | Remote page next | — |
| 50 | Previous device in chain | — |
| 51 | Next device in chain | Enter device slot (Shift) |
| 52 | Toggle track/device remote mode | — |
| 53 | Select track in mixer | — |
| 54 | Toggle record arm | — |
| 55 | Toggle solo | — |
| 56 | Toggle mute | — |
| 57 | Toggle metronome | — |
| 58 | Toggle arranger loop | — |
| 59 | Record | — |
| 60 | Play | — |
| 61 | Stop | — |
| 62 | Track bank scroll backward | Exit group / select parent (Shift) |
| 63 | Track bank scroll forward | Enter group / select child (Shift) |
| 64 | PAGE key (shift modifier) | — |

The shift system uses the OT's PAGE key as a modifier. Pressing PAGE activates alternative bindings on certain keys; releasing it reverts them.

### Remote Control Modes

Each regular track has two remote control modes toggled by note 52:

- **Track remote mode** (default): encoders CC 1, CC 2, CC 71-76 control the track's remote control page. Notes 48/49 navigate pages.
- **Device remote mode**: same encoders control the selected device's remote page. Notes 50/51 navigate devices in the chain. Shift+51 enters device slots.

The master track uses project-level remote controls instead of track-level ones.

---

## Part 2: Scene/Crossfader → Bitwig Modulation Integration

This is the novel and architecturally interesting part of the extension.

### The Problem

The Octatrack has a powerful scene system: 16 scenes can be assigned to two slots (A and B), and a physical crossfader morphs between them. The OT transmits this state as three MIDI CCs on channel 0:

- **CC 55 (Scene A select)**: value 0-15, which scene is loaded into slot A
- **CC 56 (Scene B select)**: value 0-15, which scene is loaded into slot B
- **CC 48 (Crossfader position / CFP)**: value 0-127, where 0 = fully A side, 127 = fully B side

The challenge: Bitwig has no native concept of "scenes that morph via crossfader." The goal was to bring this interaction paradigm into Bitwig's modulation system so that the crossfader could drive arbitrary parameter modulation in Bitwig, dynamically re-routed when scene assignments change.

### The Solution

#### Bitwig Side (Manual Setup)

16 CC modulators are created at the **project level** in Bitwig:

- Each modulator listens for **CC 0** on a unique MIDI channel (0-15)
- Each modulator corresponds to one of the 16 OT scenes
- The user assigns whatever Bitwig parameters they want to each modulator

#### Extension Side (Automatic)

The extension creates a **virtual MIDI input** (`NoteInput` named `"OT_CFP_DATA"`) and intercepts the three scene/CFP CCs via a raw MIDI callback. It then synthesizes and injects new CC messages into the virtual port:

**When the crossfader moves (CC 48):**
```
→ Send to channel [Scene A slot number]: CC 0, value = 127 - cfp
→ Send to channel [Scene B slot number]: CC 0, value = cfp
```

Scene A's value is inverted so it's at maximum when the crossfader is fully on the A side (where cfp = 0).

**When a scene selection changes (CC 55 or 56):**
```
→ Send to channel [OLD scene number]: CC 0, value = 0    (clear old modulator)
→ Send to channel [NEW scene number]: CC 0, value = [current cfp-derived value]
```

This prevents "stuck" modulators when scenes are swapped.

**On initialization:**
Both scene channels receive their current CFP-derived values so the system starts in a consistent state.

### The Virtual Port Trick

The `NoteInput` is created with the mask `"AF0000"`, which matches **poly aftertouch** — a message type the Octatrack never sends. This means:

- The auto-passthrough filter matches nothing from the real MIDI stream (no unintended leakage into Bitwig)
- But the extension can still manually inject arbitrary MIDI via `sendRawMidiEvent()` into this port
- It's effectively a private, extension-controlled channel into Bitwig's MIDI input system

### Why This Works Elegantly

- OT scene slot values (0-15) map directly to MIDI channels (0-15) — no translation needed
- The inversion (`127 - cfp`) turns a unidirectional fader into a proper A/B morph
- Clearing old modulators on scene change prevents stuck modulation values
- Thread safety is handled via `AtomicInteger` for `asChannel`, `bsChannel`, and `cfp`

### Signal Flow Diagram

```
Octatrack Hardware (Channel 0)
  ├── CC 55 (Scene A select, value 0-15) ──→ handleRawMidi()
  ├── CC 56 (Scene B select, value 0-15) ──→ handleRawMidi()
  └── CC 48 (Crossfader position, 0-127) ──→ handleRawMidi()
                                                    │
                                            ┌───────┴────────┐
                                            │  Extension      │
                                            │  transforms &   │
                                            │  routes to      │
                                            │  dynamic channel│
                                            └───────┬────────┘
                                                    │
                                    Virtual NoteInput "OT_CFP_DATA"
                                                    │
                                    ┌───────────────┴───────────────┐
                                    │  Bitwig Project-Level          │
                                    │  CC Modulators (16 total)      │
                                    │  Ch 0 = Scene 1 modulator      │
                                    │  Ch 1 = Scene 2 modulator      │
                                    │  ...                           │
                                    │  Ch 15 = Scene 16 modulator    │
                                    └───────────────┬───────────────┘
                                                    │
                                    Modulates any assigned parameters
                                    in native or plugin devices
```

---

## Architecture Overview

### Class Structure

```
OctatrackExtension          Entry point (init/exit/flush lifecycle)
  └── Octatrack             Orchestration wrapper, creates HardwareSurface
      └── OtDefaultConfiguration    Main configuration, scene integration
          ├── OtMidiTrack[]         8 tracks (7 regular + 1 master)
          │   ├── OtMidiHardwareControls   Hardware surface elements per track
          │   │   ├── OtControlChangeEncoder[]   10 CC knobs
          │   │   ├── OtPitchBendEncoder         14-bit pitch bend
          │   │   ├── OtAftertouchEncoder        Channel pressure
          │   │   └── OtKeyboard                 16 keys + shift key
          │   └── OtDefaultTrackLayout           Bitwig binding strategy
          │       ├── OtRegularTrackLayout       For tracks 1-7
          │       └── OtMasterTrackLayout        For track 8/master
          └── NoteInput ("OT_CFP_DATA")   Virtual port for scene modulation
```

### MIDI Port Configuration

- **1 physical MIDI input** (port 0): receives all OT MIDI data
- **1 physical MIDI output** (port 0): unused in current implementation
- **1 virtual NoteInput** ("OT_CFP_DATA"): private channel for injecting scene/CFP modulation data into Bitwig

### Key Constants

```
Track MIDI channels:     8-14 (tracks 1-7), 15 (master/track 8)
Scene/CFP MIDI channel:  0
CFP CC number:           48
Scene A select CC:       55
Scene B select CC:       56
Track bank size:         8
Sends per track:         2
API version:             18
```
