package components.manslice;

import components.standard.Standard;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import javax.sound.midi.*;

/*
 * MansliceKernel providing functionality for music making GUI
 */
public interface MansliceKernel extends Standard<Manslice> {
    /*
     * Translating user input to MidiNotes (1-127)
     */
    public enum MidiNote {
        C0(0), Csharp0(1), D0(2), Dsharp0(3), E0(4), F0(5), Fsharp0(6), G0(7), Gsharp0(8), A0(9), Asharp0(10), B0(11),
        C1(12), Csharp1(13), D1(14), Dsharp1(15), E1(16), F1(17), Fsharp1(18), G1(19), Gsharp1(20), A1(21), Asharp1(22),
        B1(23),
        C2(24), Csharp2(25), D2(26), Dsharp2(27), E2(28), F2(29), Fsharp2(30), G2(31), Gsharp2(32), A2(33), Asharp2(34),
        B2(35),
        C3(36), Csharp3(37), D3(38), Dsharp3(39), E3(40), F3(41), Fsharp3(42), G3(43), Gsharp3(44), A3(45), Asharp3(46),
        B3(47),
        C4(48), Csharp4(49), D4(50), Dsharp4(51), E4(52), F4(53), Fsharp4(54), G4(55), Gsharp4(56), A4(57), Asharp4(58),
        B4(59),
        C5(60), Csharp5(61), D5(62), Dsharp5(63), E5(64), F5(65), Fsharp5(66), G5(67), Gsharp5(68), A5(69), Asharp5(70),
        B5(71),
        C6(72), Csharp6(73), D6(74), Dsharp6(75), E6(76), F6(77), Fsharp6(78), G6(79), Gsharp6(80), A6(81), Asharp6(82),
        B6(83),
        C7(84), Csharp7(85), D7(86), Dsharp7(87), E7(88), F7(89), Fsharp7(90), G7(91), Gsharp7(92), A7(93), Asharp7(94),
        B7(95),
        C8(96), Csharp8(97), D8(98), Dsharp8(99), E8(100), F8(101), Fsharp8(102), G8(103), Gsharp8(104), A8(105),
        Asharp8(106), B8(107),
        C9(108), Csharp9(109), D9(110), Dsharp9(111), E9(112), F9(113), Fsharp9(114), G9(115), Gsharp9(116), A9(117),
        Asharp9(118), B9(119),
        C10(120), Csharp10(121), D10(122), Dsharp10(123), E10(124), F10(125), Fsharp10(126), G10(127);

        /*
         * Value of MidiNote
         */
        private final int value;

        /*
         * Constructor
         */
        MidiNote(int value) {
            this.value = value;
        }

        /*
         * Get MidiNote value
         */
        public int getValue() {
            return this.value;
        }

        /*
         * Check if int input is a valid MidiNote
         */
        public static boolean contains(int input) {
            return 0 <= input && 127 >= input;
        }

        /*
         * Check if String input is a valid MidiNote
         */
        public static boolean contains(String input) {
            if (input.contains("#")) {
                input = input.replace("#", "sharp");
            }

            try {
                valueOf(input);
                return true;
            }
            catch (IllegalArgumentException e) {
                return false;
            }
        }

        /*
         * Check if int... input is a valid MidiNote
         */
        public static boolean contains(int... input) {
            assert null != input;

            boolean contains = true;
            for (int i = 0; i < input.length && !contains; i++) {
                contains = contains(input[i]);
            }
            return contains;
        }

        /*
         * Check if String... input is a valid MidiNote
         */
        public static boolean contains(String... input) {
            assert null != input;

            boolean contains = true;
            for (int i = 0; i < input.length && !contains; i++) {
                contains = contains(input[i]);
            }
            return contains;
        }

        /*
         * Check if Sequence<Integer> input is a valid MidiNote
         */
        public static boolean contains(Sequence<Integer> input) {
            assert null != input;

            boolean contains = true;
            for (int i = 0; i < input.length() && !contains; i++) {
                contains = contains(input.entry(i));
            }
            return contains;
        }
    }

    /*
     * A chord (notes) and its duration in song, beats
     */
    class Slice {
        /*
         * Notes 
         */
        private final Sequence<Integer> notes;

        /*
         * Beats (4/4)
         */
        private int beats;

        /*
         * Default constructor
         */
        public Slice() {
            this.notes = new Sequence1L<>();
            this.beats = 0;
        }

        /*
         * Constructor from int beats and int... notes
         */
        public Slice(int beats, int... notes) {
            assert null != notes;
            assert MidiNote.contains(notes);
            assert 1 <= beats && 16 >= beats;

            this.notes = new Sequence1L<>();
            for (int note : notes) {
                this.notes.add(this.notes.length(), note);
            }
            this.beats = beats;
        }

        /*
         * Constructor from int beats and Sequence<Integer> notes
         */
        public Slice(int beats, Sequence<Integer> notes) {
            assert null != notes;
            assert MidiNote.contains(notes);
            assert 1 <= beats && 16 >= beats;

            this.notes = new Sequence1L<>();
            for (int note : notes) {
                this.notes.add(this.notes.length(), note);
            }
            this.beats = beats;
        }

        /*
         * Constructor from Slice
         */
        public Slice(Slice slice) {
            assert null != slice.notes;
            assert MidiNote.contains(slice.notes);
            assert 1 <= slice.beats && 16 >= slice.beats;

            this.notes = new Sequence1L<>();
            for (int note : slice.notes) {
                this.notes.add(this.notes.length(), note);
            }
            this.beats = slice.beats;
        }

        /*
         * Get notes
         */
        public Sequence<Integer> getNotes() {
            assert null != this.notes;
            assert MidiNote.contains(this.notes);

            return this.notes;
        }

        /*
         * Get beats
         */
        public int getBeats() {
            assert 1 <= beats && 16 >= beats;

            return this.beats;
        }

        /*
         * Set notes to int... notes
         */
        public void setNotes(int... notes) {
            assert null != notes;
            assert MidiNote.contains(notes);

            this.notes.clear();
            for (int note : notes) {
                this.notes.add(this.notes.length(), note);
            }
        }

        /*
         * Set notes to Sequence<Integer> notes
         */
        public void setNotes(Sequence<Integer> notes) {
            assert null != notes;
            assert MidiNote.contains(notes);

            this.notes.clear();
            for (int note : notes) {
                this.notes.add(this.notes.length(), note);
            }
        }

        /*
         * Set beats
         */
        public void setBeats(int beats) {
            assert 1 <= beats && 16 >= beats;

            this.beats = beats;
        }

        /*
         * Clone this
         */
        public Slice clone() {
            assert null != this.notes;
            assert MidiNote.contains(this.notes);
            assert 1 <= this.beats && 16 >= this.beats;

            return new Slice(this);
        }

        /*
         * Clone this and change beats
         */
        public Slice clone(int beats) {
            assert null != this.notes;
            assert MidiNote.contains(this.notes);
            assert 1 <= beats && 16 >= beats;

            return new Slice(beats, this.notes);
        }

        /*
         * A copy of this containing this.notes incremented/decremented by
         * respective entries in int... notes
         */
        public Slice transform(int... notes) {
            assert null != notes;
            assert 1 <= this.beats && 16 >= this.beats;
            assert notes.length <= this.notes.length();

            Slice transformed = this.clone();
            for (int i = 0; i < notes.length; i++) {
                int entry = transformed.getNotes().remove(i);
                entry += notes[i];
                transformed.getNotes().add(i, entry);
            }

            assert MidiNote.contains(transformed.getNotes());

            return new Slice(this.beats, transformed.notes);
        }

        /*
         * A copy of this containing this.notes incremented/decremented by
         * respective entries in Sequence<Integer> notes
         */
        public Slice transform(Sequence<Integer> notes) {
            assert null != notes;
            assert 1 <= this.beats && 16 >= this.beats;
            assert notes.length() <= this.notes.length();

            Slice transformed = this.clone();
            for (int i = 0; i < notes.length(); i++) {
                int entry = transformed.getNotes().remove(i);
                entry += notes.entry(i);
                transformed.getNotes().add(i, entry);
            }

            assert MidiNote.contains(transformed.getNotes());

            return new Slice(this.beats, transformed.notes);
        }

        /*
         * A copy of this containing this.notes incremented/decremented by
         * respective entries in int... notes with value beats
         */
        public Slice transform(int beats, int... notes) {
            assert null != notes;
            assert 1 <= beats && 16 >= beats;
            assert notes.length <= this.notes.length();

            Slice transformed = this.clone();
            for (int i = 0; i < notes.length; i++) {
                int entry = transformed.getNotes().remove(i);
                entry += notes[i];
                transformed.getNotes().add(i, entry);
            }

            assert MidiNote.contains(transformed.getNotes());

            return new Slice(beats, transformed.notes);
        }

        /*
         * A copy of this containing this.notes incremented/decremented by
         * respective entries in Sequence<Integer> notes with value beats
         */
        public Slice transform(int beats, Sequence<Integer> notes) {
            assert null != notes;
            assert 1 <= beats && 16 >= beats;
            assert notes.length() <= this.notes.length();

            Slice transformed = this.clone();
            for (int i = 0; i < notes.length(); i++) {
                int entry = transformed.getNotes().remove(i);
                entry += notes.entry(i);
                transformed.getNotes().add(i, entry);
            }

            assert MidiNote.contains(transformed.getNotes());

            return new Slice(beats, transformed.notes);
        }

        /*
         * this as String
         */
        @Override
        public String toString() {
            assert null != this.notes;
            assert MidiNote.contains(this.notes);
            assert 1 <= this.beats && 16 >= this.beats;

            return "Beats: " + this.beats + "\nSlice: " + this.notes;
        }
    }

    /*
     * A pattern in song (the order the chords are played)
     */
    class Flip {
        /*
         * Order in which chords are played
         */
        private final Sequence<Integer> pattern;

        /*
         * Default constructor
         */
        public Flip() {
            this.pattern = new Sequence1L<>();
        }

        /*
         * Constructor from int... pattern
         */
        public Flip(int... pattern) {
            assert null != pattern;

            this.pattern = new Sequence1L<>();
            for (int pos : pattern) {
                this.pattern.add(this.pattern.length(), pos);
            }
        }

        /*
         * Constructor from Flip
         */
        public Flip(Flip flip) {
            assert null != flip.pattern;

            this.pattern = new Sequence1L<>();
            for (int pos : pattern) {
                this.pattern.add(this.pattern.length(), pos);
            }
        }

        /*
         * Get pattern
         */
        public Sequence<Integer> getPattern() {
            assert null != this.pattern;

            return this.pattern;
        }

        /*
         * Set pattern to int... pattern
         */
        public void setPattern(int... pattern) {
            assert null != pattern;

            this.pattern.clear();
            for (int pos : pattern) {
                this.pattern.add(this.pattern.length(), pos);
            }
        }

        /*
         * Add int... pattern to this.pattern
         */
        public void addPattern(int... pattern) {
            assert null != pattern;

            for (int pos : pattern) {
                this.pattern.add(this.pattern.length(), pos);
            }
        }
        
        /*
         * Add Sequence<Integer> pattern to this.pattern
         */
        public void addPattern(Sequence<Integer> pattern) {
            assert null != pattern;

            for (int pos : pattern) {
                this.pattern.add(this.pattern.length(), pos);
            }
        }

        /*
         * Clone this
         */
        public Flip clone() {
            assert null != this.pattern;

            return new Flip(this);
        }

        /*
         * this as String
         */
        @Override
        public String toString() {
            assert null != this.pattern;

            return "Flip: " + this.pattern;
        }
    }

    /*
     * Sequence of chords (Slice(s))
     */
    abstract class Slices { 
        /*
         * Gets Slice in clipboard
         */
        abstract Slice getBuffer();

        /*
         * Sets clipboard to Slice x
         */
        abstract void setBuffer(Slice x);

        /*
         * Adds Slice to pos containing beats and int... notes
         */
        abstract void add(int pos, int beats, int... notes);

        /*
         * Adds Slice to pos containing Slice x
         */
        abstract void add(int pos, Slice x);

        /*
         * Removes Slice at pos
         */
        abstract Slice remove(int pos);

        /*
         * Replaces entry at pos with Slice x
         */
        abstract void replaceEntry(int pos, Slice x);

        /*
         * Gets entry at int pos
         */
        abstract Slice entry(int pos);

        /*
         * Number of Slice(s) in this
         */
        abstract int length();

    }

    /*
     * Sequence of orders in which chords are played (Flip(s))
     */
    abstract class Flips {
        /*
         * Gets Flip in buffer
         */
        abstract Flip getBuffer();

        /*
         * Sets buffer to Flip x
         */
        abstract void setBuffer(Flip x);

        /*
         * Adds Flip at int pos containing int... pattern
         */
        abstract void add(int pos, int... pattern);

        /*
         * Adds Flip at int pos containing Flip x
         */
        abstract void add(int pos, Flip x);

        /*
         * Removes Flip at int pos
         */
        abstract Flip remove(int pos);

        /*
         * Replaces entry at pos with Flip x
         */
        abstract void replaceEntry(int pos, Flip x);

        /*
         * Gets entry at int pos
         */
        abstract Flip entry(int pos);

        /*
         * Checks if pattern notes is valid
         */
        abstract boolean validPattern(int... notes);

        /*
         * Checks if pattern in Flip x is valid
         */
        abstract boolean validPattern(Flip x);

        /* 
         * Checks if positions are valid
         */
        abstract boolean validPositions(int... positions);

        /* 
         * Checks if positions are valid
         */
        abstract boolean validPositions(Sequence<Integer> positions);

        /*
         * Number of Flip(s) in this
         */
        abstract int length();

        /*
         * Clears this
         */
        abstract void clear();

        /*
         * Transfer from
         */
        abstract void transferFrom(Flips source);
    }

    /*
     * Store Manslice state in undo history
     */
    void pushHistory(Manslice state);

    /*
     * Store Manslice state in redo history
     */
    void pushRedoHistory(Manslice state);

    /*
     * Extract latest state in undo history
     */
    Manslice popHistory();

    /*
     * Extract latest state in redo history
     */
    Manslice popRedoHistory();

    /*
     * Gets current state
     */
    Manslice getState();

    /*
     * Sets state to Manslice x
     */
    void setState(Manslice x);

    /*
     * Get midi Sequencer
     */
    Sequencer getSequencer();

    /*
     * Get midi Sequence
     */
    javax.sound.midi.Sequence getSequence();

    /*
     * Get BPM
     */
    int getBPM();

    /*
     * Set BPM
     */
    void setBPM(int bpm);
}
