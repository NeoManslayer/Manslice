# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2024.09.17

### Added

- Designed a `ManslayerProg` component
- Designed a `PresidentActions` component
- Designed a `Manslice` component

## 2024.10.05

### Added

- Designed a proof of concept for `Manslice` component

### Updated

- Changed design to include one internal class `Cell`
    - `Cell` represents a musical phrase of length `beats` with two chords: `openChord`, `closeChord`; a melody: `openMelody`, `closeMelody`; a rhythm: `openRhythm`, `closeRhythm`; and a bassline: `openBass`, `closeBass`
- Changed design to be implemented using `Queue` component (OSU Java Component)
- Changed design by removing the use of `openMode` in favor of an `openCell` function that will just override the current open cell if there is one (you can call `openCell` if there is a cell that hasn't been closed, it just means you will be making a new open cell and replacing the one that hasn't been closed)
