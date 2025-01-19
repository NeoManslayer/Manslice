# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2025.01.19

### Added

- Designed kernel implementation (`Manslice1`) for `Manslice` component

### Updated

- Changed `MansliceKernel` to allow nested interfaces `Slices` and `Flips` to
  extend `Sequence`

### Issues and future todos

- Design is complicated (rushed) and there is more optimizations (best
  practice) necessary to implement this component and to design these
  interfaces.
    - `super()` usage in `Manslice1` for initializing two sequences
      (representation)
    - missing convention and correspondence
    - empty implementation of `song()`
    - need to figure out how to use MIDI and javafx libraries

## 2025.01.18

### Added

- Designed abstract class for `Manslice` component

### Updated

- Changed design of `MansliceKernel` to include two interfaces `Slices<T
  extends Slice>` and `Flips<T extends Flip>` to serve as internal classes for
  storing of song pattern and chords
- Kept `song()` as an abstract method
    - Due to requiring unique libraries like javafx for GUI/MIDI support, along
      with being more difficult to implement than other methods, the
      implementation of `song()` will be left to non-abstract classes.
    - In future revisions, `song()` may be replaced with multiple methods
      implemented within the abstract class to help with class implementation.

## 2025.01.18

### Added

- Designed kernel and enhanced interfaces for `Manslice` component

### Updated

- Changed design to include `Slice` and `Flip` classes in order to have a
  component that will be implemented with two sequences (one sequence,
  `Sequence<Slice>` represents the chords of the song... the other,
  `Sequence<Flip>` represents the way in which those will be arranged in time
  within a song)
    - `Slice` represents the chords
    - `Flip` represents pattern of chords in context of song (arrangement,
      tempo, bpm)
- Changed design to potentially include a GUI and MIDI functionality that
  detects manual user input for editing of song slices and allows for playing
  of chord progression

## 2024.10.05

### Added

- Designed a proof of concept for `Manslice` component

### Updated

- Changed design to include one internal class `Cell`
    - `Cell` represents a musical phrase of length `beats` with two chords:
      `openChord`, `closeChord`; a melody: `openMelody`, `closeMelody`; a
      rhythm: `openRhythm`, `closeRhythm`; and a bassline: `openBass`,
      `closeBass`
- Changed design to be implemented using `Queue` component (OSU Java Component)
    - Changed design by removing the use of `openMode` in favor of an
      `openCell` function that will just override the current open cell if
      there is one (you can call `openCell` if there is a cell that hasn't been
      closed, it just means you will be making a new open cell and replacing
      the one that hasn't been closed)

## 2024.09.17

### Added

- Designed a `ManslayerProg` component
- Designed a `PresidentActions` component
- Designed a `Manslice` component

