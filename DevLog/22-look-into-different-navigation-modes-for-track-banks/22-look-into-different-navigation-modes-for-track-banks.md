# Look into different navigation modes for track banks

The goal here is to have the track bank not necessarily include all the tracks in nested groups. Naturally, if we don't include the nested tracks, we'll need a way to enter and exit groups

## Excluding tracks nested in a group
- it looks like the ticket is to use `Track.createMainTrackBank(int numTracks, int numSends, int numScenes, boolean hasFlatTrackList)`, with that last parameter being the key
  - when false, it doesn't include the tracks inside a group
- not exactly clear on which track to call this on
  - calling it on the cursor track doesn't work; there's effectively no track bank then (no indications of controlled tracks)
  - calling it on the root group does work at least from the perspective of excluding group tracks
    - however, it also seems to prevent the track group from moving into the group when the latter is entered
      - actually, it's not due to the use of the root group, as the same thing happens if we set `hasFlatTrackList` to true or use the previous `ControllerHost` method for creating the bank that doesn't take that parameter
        - in these cases, the bank of course still crosses the group boundaries, but it does not 
- it took some digging, but DrivenByMoss to the rescue again
  - `de.mossgrabers.bitwig.framework.daw.ModelImpl` line 124 has
    - `tb = this.bwCursorTrack.createSiblingsTrackBank (numTracks, numSends, numScenes, false, false);`
    - I have no idea what a sibling track is and the JavaDocs don't say, but let's see what happens!

## Entering and exiting group tracks
- this also looks to be straightforward
  - we need some methods of `Track`
    - `isGroup()`
    - `isGroupExpanded()` - might be useful, as we might want to expand it while entering if not expanded
  - the track needs to be selected before we can enter it
    - or does it? unclear
    - did some searching in DBM and found that `de.mossgrabers.bitwig.framework.daw.data.TrackImpl.enter()` seems to imply that the track should be selected first and that we should wait a bit before entering the group by using `ControllerHost.scheduleTask(Runnable callback, long delay)`
  - will need to use `CursorTrack.selectFirstChild()` to enter
    - this does select the track, but doesn't move the track bank inside along with it
  - use `cursorTrack.selectParent()` to exit

## key bindings
- I think it makes most sense to use shift functions on the track-back-next and track-bank-prev keys, such that the former enters and the latter exits