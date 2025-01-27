# Portfolio Part 1: Component Brainstorming

- **Name**: Nick Monaco
- **Dot Number**: monaco.60
- **Due Date**: 9/16 @ 4:10 PM EST

## Assignment Overview

The overall goal of the portfolio project is to have you design and implement
your own OSU component. There are no limits to what you choose to design and
implement, but your component must fit within the constraints of our software
sequence discipline. In other words, the component must extend from Standard and
must include both a kernel and a secondary interface.

Because this is a daunting project, we will be providing you with a series of
activities to aid in your design decisions. For example, the point of this
assignment is to help you brainstorm a few possible components and get some
feedback. For each of these components, you will need to specify the high-level
design in terms of the software sequence discipline. In other words, you will
describe a component, select a few kernel methods for your component, and select
a few secondary methods to layer on top of your kernel methods.

You are not required to specify contracts at this time. However, you are welcome
to be as detailed as you'd like. More detail means you will be able to get more
detailed feedback, which may help you decide which component to ultimately
implement.

## Assignment Checklist

To be sure you have completed everything on this assignment, we have littered
this document with TODO comments. You can browse all of them in VSCode by
opening the TODOs window from the sidebar. The icon looks like a tree and will
likely have a large number next to it indicating the number of TODOS. You'll
chip away at that number over the course of the semester. However, if you'd
like to remove this number, you can disable it by removing the following
line from the `settings.json` file:

```json
"todo-tree.general.showActivityBarBadge": true,
```

Which is not to be confused with the following setting that adds the counts
to the tree diagram (you may remove this one as well):

```json
"todo-tree.tree.showCountsInTree": true,
```

## Assignment Learning Objectives

Without learning objectives, there really is no clear reason why a particular
assessment or activity exists. Therefore, to be completely transparent, here is
what we're hoping you will learn through this particular aspect of the portfolio
project. Specifically, students should be able to:

1. Integrate their areas of interest in their personal lives and/or careers with
   their knowledge of software design
2. Determine the achievablility of a software design given time constraints
3. Design high-level software components following the software sequence
   discipline

## Assignment Rubric: 10 Points

Again, to be completely transparent, most of the portfolio project, except the
final submission, is designed as a formative assessment. Formative assessments
are meant to provide ongoing feedback in the learning process. Therefore,
the rubric is designed to assess the learning objectives *directly* in a way
that is low stakes—meaning you shouldn't have to worry about the grade. Just
do good work.

1. (3 points) Each design must align with your personal values and long-term
   goals. Because the goal of this project is to help your build out a
   portfolio, you really ought to care about what you're designing. We'll give
   you a chance to share your personal values, interests, and long-term goals
   below.
2. (3 points) Each design must be achievable over the course of a single
   semester. Don't be afraid to design something very small. There is no shame
   in keeping it simple.
3. (4 points) Each design must fit within the software sequence discipline. In
   other words, your design should expect to inherit from Standard, and it
   should contain both kernel and secondary methods. Also, null and aliasing
   must be avoided, when possible. The methods themselves must also be in
   justifiable locations, such as kernel or secondary.

## Pre-Assignment

> Before you jump in, we want you to take a moment to share your interests
> below. Use this space to talk about your career goals as well as your personal
> hobbies. These will help you clarify your values before you start
> brainstorming. Plus it helps us get to know you better! Feel free to share
> images in this section.

GOALS OF NICK "MANSLAYER" MONACO:
I am the NEXT KANYE WEST minus the anti-Semitism. I am a liberal online personality that makes music and argues and aphorizes about philosophy and politics. I am a master programmer that worships code as much as Torvalds and I will be the Dr. Gregory House of coding but my diagnostic fixations will be based on computers and programs instead of patients; I am already one drug closer to becoming Gregory House (who has a Vicodin addiction) now that I have been prescribed Vyvanse for my inattentive ADHD.

PERSONAL UNDERTAKINGS OF "MANSLAYER":
I am THE ONLY PERSON ALIVE that makes REAL MUSIC besides Kanye West and Nujabes (except Kanye West's music was only good before 2011 and Nujabes is dead). I make music in FL Studio which is inspired by Nujabes and Kanye West (beats); I "play" the guitar and piano to come up with chords and melodies. I am a liberal atheist that loves Joe Biden and Kamala Harris so I argue with religious student group staff members over lunch at OSU or I post tweets or snaps arguing with Republicans and Leftists online. I have a dark sense of humor and philosophical views inspired by among many people, Friedrich Nietzsche, that I share online.

TL;DR:
I want the populist rhetoric of Donald Trump, the political views of Joe Biden and the confidence/don't-care-mentality of Kanye West while being funnier than Dave Chapelle.

## Assignment

As previously stated, you are tasked with brainstorming 3 possible components.
To aid you in this process, we have provided [some example components][example-components]
that may help you in your brainstorming. All of these components were made at
some point by one of your peers, so you should feel confident that you can
accomplish any of them.

There is no requirement that you use any of the components listed above.
If you want to model something else, go for it! Very common early object
projects usually attempt to model real-world systems like banks, cars,
etc. Make of this whatever seems interesting to you, and keep in mind that
you're just brainstorming right now. You do not have to commit to anything.

### Example Component

To help you brainstorm a few components, we've provided an example below of a
component you already know well: NaturalNumber. We highly recommend that you
mirror the formatting as close as possible in your designs. By following this
format, we can be more confident that your designs will be possible.

- Example Component: `NaturalNumber`
  - **Description**:
    - The purpose of this component is to model a non-negative
      integer. Our intent with this design was to keep a simple kernel that
      provides the minimum functionality needed to represent a natural number.
      Then, we provide more complex mathematical operations in the secondary
      interface.
  - **Kernel Methods**:
    - `void multiplyBy10(int k)`: multiplies `this` by 10 and adds `k`
    - `int divideBy10()`: divides `this` by 10 and reports the remainder
    - `boolean isZero()`: reports whether `this` is zero
  - **Secondary Methods**:
    - `void add(NaturalNumber n)`: adds `n` to `this`
    - `void subtract(NaturalNumber n)`: subtracts `n` from `this`
    - `void multiply(NaturalNumber n)`: multiplies `this` by `n`
    - `NaturalNumber divide(NaturalNumber n)`: divides `this` by `n`, returning
      the remainder
    - ...
  - **Additional Considerations** (*note*: "I don't know" is an acceptable
    answer for each of the following questions):
    - Would this component be mutable? Answer and explain:
      - Yes, basically all OSU components have to be mutable as long as they
        inherit from Standard. `clear`, `newInstance`, and `transferFrom` all
        mutate `this`.
    - Would this component rely on any internal classes (e.g., `Map.Pair`)?
      Answer and explain:
      - No. All methods work with integers or other NaturalNumbers.
    - Would this component need any enums or constants (e.g.,
      `Program.Instruction`)? Answer and explain:
      - Yes. NaturalNumber is base 10, and we track that in a constant called
          `RADIX`.
    - Can you implement your secondary methods using your kernel methods?
      Answer, explain, and give at least one example:
      - Yes. The kernel methods `multiplyBy10` and `divideBy10` can be used to
        manipulate our natural number as needed. For example, to implement
        `increment`, we can trim the last digit off with `divideBy10`, add 1 to
        it, verify that the digit hasn't overflown, and multiply the digit back.
        If the digit overflows, we reset it to zero and recursively call
        `increment`.

Keep in mind that the general idea when putting together these layered designs
is to put the minimal implementation in the kernel. In this case, the kernel is
only responsible for manipulating a digit at a time in the number. The secondary
methods use these manipulations to perform more complex operations like
adding two numbers together.

Also, keep in mind that we don't know the underlying implementation. It would be
completely reasonable to create a `NaturalNumber1L` class which layers the
kernel on top of the existing `BigInteger` class in Java. It would also be
reasonable to implement `NaturalNumber2` on top of `String` as seen in
Project 2. Do not worry about your implementations at this time.

On top of everything above, there is no expectation that you have a perfect
design. Part of the goal of this project is to have you actually use your
component once it's implemented to do something interesting. At which point, you
will likely refine your design to make your implementation easier to use.

### Component Designs

> Please use this section to share your designs.

- Component Design #1: `ManslayerProg`
  - **Description**:
    - The purpose of this component is to model a musical chord progression for an entire song that would put a smile on the face of Manslayer (Nick Monaco). The intent is to keep a simple kernel that provides minimal functionality to represent a chord progression that Manslayer tolerates. Then, more complex musical operations in the secondary interface to put a smile on Manslayer's face.
  - **Kernel Methods**:
    - `int BPM()`: returns Beats Per Minute (BPM) of `this`
    - `void setBPM(int BPM)`: sets BPM of `this` to `BPM`
    - `boolean openMode()`: returns true if `this` has an open cell (`ChordCell` that only has one chord), returns false otherwise
    - `int cellCount()`: returns the number of `ChordCell`(s) in `this` (closed cells or cells that have two chords)
    - `void openCell(int cellNum, String openChord, int beats)`: if `cellNum` = 0 (or args are empty) AND `!openMode`, creates an open cell at the end of `this` with values `openChord` and `beats` (how much time two chords will evenly share); if `cellNum` > 0, replaces the first chord and beat values of `ManslayerProg(cellNum)` (`ChordCell` at `cellNum`) with `openChord` and `beats`
    - `void closeCell(int cellNum, String closeChord, int beats)`: if `cellNum` = 0 (or args are empty) AND `openMode`, closes the open cell in `this`, setting the second chord value to `closeChord` and setting beats to `beats`; if `cellNum` > 0, replaces the second chord and beat values of `ManslayerProg(cellNum)` with `closeChord` and `beats`
    - `void slice(String slice)`: sets the slicing (mixing) of `ManslayerProg` to `slice`; example of a `slice` would be `slice = "111222111222"` (plays `ChordCell` at `cellNum` 1 for three times, then `ChordCell` at `cellNum` 2 for three times...)
  - **Secondary Methods**:
    - `ManslayerProg subClone(int cellStart, int cellEnd)`: returns clone of `ChordCell`(s) from [cellStart, cellEnd] in `this`
    - `ChordCell cell(int cellNum)`: returns the `ChordCell` at `cellNum` in `this`
    - `void switch(int cellNum1, int cellNum2)`: switches position of `ChordCell`(s) at position `cellNum1` and `cellNum2`
    - `void insert(int cellNum, ChordCell x)`: inserts `ChordCell` `x` at position `cellNum`, moving each cell to the right of `x` one position to the right
    - `void remove(int cellNum)`: removes `ChordCell` at position `cellNum` in `this`
  - **Additional Considerations** (*note*: "I don't know" is an acceptable
    answer for each of the following questions):
    - Would this component be mutable? Answer and explain:
      - Yes, this component would be mutable because methods change the value of `this`; For example, `void remove(int cellNum)` would modify `this` by erasing `ManslayerProg(cellNum)` (`ChordCell` at `cellNum`) from `this`
    - Would this component rely on any internal classes (e.g., `Map.Pair`)?
      Answer and explain:
      - Yes, this component would rely on `ChordCell` that has attributes `openChord` (first chord), `closeChord` (second chord) and `beats` (limited to factors of 8); This class exists in order to model what "good" music sounds like by restricting the amount of chords allowed per entry in `ManslayerProg` to 2 and limiting the amount of beats to only factors of 8 (Manslayer enjoys music with a handful of chords repeated throughout a song)
    - Would this component need any enums or constants (e.g.,
      `Program.Instruction`)? Answer and explain:
      - Yes, this component needs constants for the initial beat value (8) for each `ChordCell`, the initial BPM (100) and constants for the range of acceptable BPM (60-120) because "good" music is within 60-120 BPM with sequences of chords that change up (aren't repeated for 600 beats, but instead only 8 or less)
    - Can you implement your secondary methods using your kernel methods?
      Answer, explain, and give at least one example:
      - No, I forgot what the difference between Kernel and Secondary methods were so I just placed the cooler methods in Kernel and the less cool ones in Secondary; In the future I will do something like have `remove`, `insert` and `cell` be Kernel methods and then use those in the implementation of for example: `openCell`, where I will insert `ChordCell` at `cellNum` provided with `openChord` and `beat` values

- Component Design #2: `PresidentActions`
  - **Description**:
    - An organized model of the actions (acts, bills...) that a president (and their administration) has done including important information like whether it was bipartisan, where money is going to, what issues do the actions deal with (abortion, infastructure, immigration...).
  - **Kernel Methods**:
    - `int length()`: returns number of actions in `this`
    - `void addAction(String type, String issues, String parties, String president)`: adds entry of type `Action` to `this` with attributes `type`, `issues`, `parties` and `president` (who was in office)`
    - `Action removeAction(int index)`: removes action from `this` and returns it
  - **Secondary Methods**:
    - `void sortByPartisanship(String order)`: sorts `this` by `order` (for example "RDB" would sort by republican actions, then democrat actions, then bipartisan actions in left to right order in `this`)
    - `PresidentActions getBipartisan(PresidentActions x)`: returns object of type `PresidentActions` containing only the bipartisan `Actions` in `this`
    - `PresidentActions getRepublican... (same as above but for republican actions)`
    - `PresidentActions getDemocrat... (same as above but for democrat actions)`
    - `String whoIsMoreBipartisan(PresidentActions a, PresidentActions b...)`: returns string from left to right (bipartisan to least partisan) containing names of presidents gathered from comparing actions in `a` `b`... in order to declare who is most to least bipartisan (for example: "Kamala, Biden, Trump").
    - `String whoCaresMoreAbout(String issue, PresidentActions a, PresidentActions b...)`: returns string with names from left to right of presidents (care most about `issue` to care least about `issue`) based off `a`, `b`...
  - **Additional Considerations** (*note*: "I don't know" is an acceptable
    answer for each of the following questions):
    - Would this component be mutable? Answer and explain:
      - Yes, this component allows for the adding and removing of entries (changes `this`).
    - Would this component rely on any internal classes (e.g., `Map.Pair`)?
      Answer and explain:
      - Yes, it relies on `Actions` with attributes such as `type`, `issues`, `parties`, `president in order to describe actions that a president has taken
    - Would this component need any enums or constants (e.g.,
      `Program.Instruction`)? Answer and explain:
      - No
    - Can you implement your secondary methods using your kernel methods?
      Answer, explain, and give at least one example:
      - Not all of them or at least it would be difficult for the last couple of methods.

- Component Design #3: `Manslice`
  - **Description**:
    - A representation for music that Manslayer likes that includes rhythm, melody and chord progression (or more) which can be exported to Fl Studio through MIDI-Scripting (<https://youtu.be/JYslZkc90GI?t=954>); An image of what an Fl Studio song looks like: ![alt text](<FL Studio song 23 image.png>)
  - **Kernel Methods**:
    - `void addTrack(int trackIndex, K trackEntry)`: adds an empty track to `this` at location `trackIndex` that only allows type `K` entries (i.e. `ChordCells`, `MelodicCells`, `DrumCells`)
    - `void removeTrack(int trackIndex)`: removes track from `this` corresponding to `trackIndex`
    - `int trackCount()`: returns the number of tracks in `this`
    - `int cellCount(int trackIndex)`: returns the number of closed cells in a track in `this` corresponding to `trackIndex`
    - `boolean openMode(int trackIndex)`: returns true if there is an open cell in the track of `this` corresponding to `trackIndex`, false otherwise
    - `void add(int cellNum)`: adds and replaces `entry` to `this` corresponding to the track that accepts type `K` entries to position `cellNum`
    - `K remove(int cellNum, K entry)`: removes `entry` at `cellNum` in `track` corresponding to type `K` entries and returns it
    - `int findTrack(K entry)`: finds the track in `this` containing `K` entries and returns its track index
    - `int BPM()`: returns BPM of `this`
    - `void setBPM(int BPM)`: sets BPM of `this` to `BPM`, a value within 60-120
    - `K getEntry(int trackNum, int cellNum)` gets entry in `this` located in `track` corresponding to `trackIndex` at position `cellNum`
    - `Track getTrack(int trackNum)`: gets track located at `trackNum` in `this`
    - `void addTrack(Track x, int trackIndex)`: overloading of first method in Kernel; adds `x` to `this` at `trackIndex`
    - `String voice(int trackIndex)`: gets instrument of `Track` at `trackIndex` in `this`
    - `void setVoice(int trackIndex, String voice)`: sets instrument of `Track` at `trackIndex` in `this`
  - **Secondary Methods**
    - `void openCell(int cellNum, String firstPhrase, int beats)`: (For future Nick: `beats` is unnecessary) if `cellNum` == 0 and `!openMode` for track containing `K` entries (where `K` corresponds to contents of `firstPhrase`), adds an open cell to `this` with values for `firstPhrase` and `beats`; if `cellNum` > 0, replaces values of `K` entry at `cellNum` with `firstPhrase` and `beats`
    - `void closeCell(int cellNum, String secondPhrase, int beats)`: if `cellNum` == 0 and `openMode`, closes the open cell in the track of `this` with values `secondPhrase` and `beats`; if `cellNum` > 0, replaces values of entry `K` at `cellNum` with `secondPhrase` and `beats`
    - `Manslice subClone(int trackStart, int trackEnd, int cellNum1, int cellNum2)`: creates a clone of `this` containing tracks [trackStart, trackEnd] and the cells [cellNum1, cellNum2] in each selected track
    - `void switchCell(int trackIndex, int cellNum1, int cellNum2)`: switches position of cells at `cellNum1` and `cellNum2` in `Track` designated by `trackIndex`
    - `void insertCell(int cellNum, K entry)`: inserts `K` entry into `Track` of `this` with `K` entries at `cellNum`
    - `void exportSong(SimpleWriter output)`: exports `this` to a file `output` after which its contents will ideally be interpreted and converted to MIDI file which could then be opened in FL Studio
    - `void exportTrack(int trackNum, SimpleWriter output)`: exports contents of `Track` at `trackNum` in `this` to a file `output` after which can be converted to MIDI and opened in FL Studio
  - **Additional Considerations** (*note*: "I don't know" is an acceptable
    answer for each of the following questions):
    - Would this component be mutable? Answer and explain:
      - Yes, methods can change its value. For example, `removeTrack` will remove a track from `this`
    - Would this component rely on any internal classes (e.g., `Map.Pair`)?
      Answer and explain:
      - Yes, this component would rely on `Track`, with attributes for the instrument of the track and the type of entries in track (`MelodicCells`, `BeatCells`, `ChordCell`) to model the format of an FL Studio song ![image]("C:\Users\xxnin\Downloads\images\FL Studio song 23 image.png"); It also relies on `MelodicCell` for melody track, `BeatCell` for drum track, `ChordCell` for chord track, each containing an attribute for `firstPart` (i.e. `firstChord` for `ChordCell`), `secondPart` and `beats` (NOTE: `beats` may be removed in the future due to redundancy). However, these classes could be consolidated into one class `Cells` which would allow for users to create even more `Cells` like a `BassCell`, `StringCell`...
    - Would this component need any enums or constants (e.g.,
      `Program.Instruction`)? Answer and explain:
      - Yes, this component would need constants for allowed BPM (60-120), for initial BPM (100), for initial `beat` value (8), an enum for chord names ("Gmaj7"...), an enum for note values ("A7", "C4"...) an enum for instruments ("Kick", "Snare", "Piano", "Violin"), and a constant for the maximum subbeats (16th notes) in a 4 beat phrase (4 *2* 2 = 16); We need note values and chord names to know what notes are used in the song and we need constants for range of BPM and beat values in order to make the song sound "good" (crazy fast or crazy slow BPM is not only harder to interpret, it also doesn't sound good) AND we don't want a huge number of subbeats or an infinite number of subbeats because that would be either hard or impossible to interpret (which is why we limit the fastest note to 16th notes, the slowest note to whole note)
    - Can you implement your secondary methods using your kernel methods?
      Answer, explain, and give at least one example:
      - Yes, for most methods I believe they can be implemented using `addTrack, removeTrack, add, remove, openMode, getTrack...`; For example, the implementation of `openCell` could involve `openMode` to check if we can add an open cell or not (checks if there's already an open cell in `this`) and then use `add` to add the open cell, otherwise if `cellNum` > 0, it uses `add` to replace an entry.

## Post-Assignment

The following sections detail everything that you should do once you've
completed the assignment.

### Changelog

At the end of every assignment, you should update the
[CHANGELOG.md](../../CHANGELOG.md) file found in the root of the project folder.
Since this is likely the first time you've done this, we would recommend
browsing the existing file. It includes all of the changes made to the portfolio
project template. When you're ready, you should delete this file and start your
own. Here's what I would expect to see at the minimum:

```markdown
# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## YYYY.MM.DD

### Added

- Designed a <!-- insert name of component 1 here --> component
- Designed a <!-- insert name of component 2 here --> component
- Designed a <!-- insert name of component 3 here --> component
```

Here `YYYY.MM.DD` would be the date of your submission, such as 2024.04.21.

You may notice that things are nicely linked in the root CHANGELOG. If you'd
like to accomplish that, you will need to make GitHub releases after each pull
request merge (or at least tag your commits). This is not required.

In the future, the CHANGELOG will be used to document changes in your
designs, so we can gauge your progress. Please keep it updated at each stage
of development.

### Submission

If you have completed the assignment using this template, we recommend that
you convert it to a PDF before submission. If you're not sure how, check out
this [Markdown to PDF guide][markdown-to-pdf-guide]. However, PDFs should be
created for you automatically every time you save, so just double check that
all your work is there before submitting. For future assignments, you will
just be submitting a link to a pull request. This will be the only time
you have to submit any PDFs.

### Peer Review

Following the completion of this assignment, you will be assigned three
students' component brainstorming assignments for review. Your job during the
peer review process is to help your peers flesh out their designs. Specifically,
you should be helping them determine which of their designs would be most
practical to complete this semester. When reviewing your peers' assignments,
please treat them with respect. Note also that we can see your comments, which
could help your case if you're looking to become a grader. Ultimately, we
recommend using the following feedback rubric to ensure that your feedback is
both helpful and respectful (you may want to render the markdown as HTML or a
PDF to read this rubric as a table).

| Criteria of Constructive Feedback | Missing                                                                                                                           | Developing                                                                                                                                                                                                                                | Meeting                                                                                                                                                               |
| --------------------------------- | --------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Specific                          | All feedback is general (not specific)                                                                                            | Some (but not all) feedback is specific and some examples may be provided.                                                                                                                                                                | All feedback is specific, with examples provided where possible                                                                                                       |
| Actionable                        | None of the feedback provides actionable items or suggestions for improvement                                                     | Some feedback provides suggestions for improvement, while some do not                                                                                                                                                                     | All (or nearly all) feedback is actionable; most criticisms are followed by suggestions for improvement                                                               |
| Prioritized                       | Feedback provides only major or minor concerns, but not both. Major and minar concerns are not labeled or feedback is unorganized | Feedback provides both major and minor concerns, but it is not clear which is which and/or the feedback is not as well organized as it could be                                                                                           | Feedback clearly labels major and minor concerns. Feedback is organized in a way that allows the reader to easily understand which points to prioritize in a revision |
| Balanced                          | Feedback describes either strengths or areas of improvement, but not both                                                         | Feedback describes both strengths and areas for improvement, but it is more heavily weighted towards one or the other, and/or descusses both but does not clearly identify which part of the feedback is a strength/area for improvement  | Feedback provides balanced discussion of the document's strengths and areas for improvement. It is clear which piece of feedback is which                             |
| Tactful                           | Overall tone and language are not appropriate (e.g., not considerate, could be interpreted as personal criticism or attack)       | Overall feedback tone and language are general positive, tactul, and non-threatening, but one or more feedback comments could be interpretted as not tactful and/or feedback leans toward personal criticism, not focused on the document | Feedback tone and language are positive, tactful, and non-threatening. Feedback addesses the document, not the writer                                                 |

### Assignment Feedback

If you'd like to give feedback for this assignment (or any assignment, really),
make use of [this survey][survey]. Your feedback helps make assignments
better for future students.

[example-components]: https://therenegadecoder.com/code/the-never-ending-list-of-small-programming-project-ideas/
[markdown-to-pdf-guide]: https://therenegadecoder.com/blog/how-to-convert-markdown-to-a-pdf-3-quick-solutions/
[survey]: https://forms.gle/dumXHo6A4Enucdkq9
