# Kernel

- add(int pos, int beats, int... notes)
- add(int pos, int... pattern)
- Slice removeSlice(int pos)
- Flip removeFlip(int pos)

<--TODO-->
- ?
- ??

# Enhanced (Minimum functionality I want, harder to implement as functionality gets higher)

- edit(int pos, int beats, int... notes)
- switch(int pos1, int pos2)
- yank(int pos, int beats)
- transform(int pos, int beats, int... notes)
- put(int pos)
- meta(int... positions)
- order(int... positions)
- undo()
- redo()

- play()
- stop()


# General

- Two nested interfaces, Slices and Flips
