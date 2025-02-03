package components.manslice;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.stack.Stack;
import components.stack.Stack1L;
import javax.sound.midi.*;

/*
 * Manslice1 class implementation of Manslice
 */
public class Manslice1 extends MansliceSecondary {
    /*
     * Slices 
     */
    public class Slices extends MansliceSecondary.Slices {
        /*
         * Private members
         */

        /*
         * Rep of flips
         */
        private Sequence<Slice> slices;

        /*
         * Rep of copy/paste buffer
         */
        private Slice buffer;

        /*
         * Creates initial representation of flips
         */
        public void createNewRep() {
            this.slices = new Sequence1L<>();
        }

        /*
         * Constructors ---------------------------------------------------
         */

        public Slices() {
            this.createNewRep(); 
        }

        /*
         * Kernel methods -------------------------------------------------
         */

        /*
         * Clears this
         */
        public void clear() {
            this.slices.clear();
        }

        /*
         * Transfer from
         */
        public void transferFrom(Sequence<Slice> source) {
            this.slices.transferFrom(source);
            source.clear();
        }

        /*
         * Gets Slice in clipboard
         */
        public Slice getBuffer() {
            assert null != this.buffer;

            return this.buffer;
        }

        /*
         * Sets clipboard to Slice x
         */
        public void setBuffer(Slice x) {
            assert null != x;

            this.buffer = new Slice(x); 
        }

        /*
         * Adds Slice to pos containing beats and int... notes
         */
        public void add(int pos, int beats, int... notes) {
            assert this.slices.length() > pos - 1;
            assert 1 <= beats && 16 >= beats;
            assert notes != null;
            assert MidiNote.contains(notes);
            
            this.slices.add(pos - 1, new Slice(beats, notes));            
        }

        /*
         * Adds Slice to pos containing Slice x
         */
        public void add(int pos, Slice x) {
            assert this.slices.length() > pos - 1;
            assert 1 <= x.getBeats() && 16 >= x.getBeats();
            assert x.getNotes() != null;
            assert MidiNote.contains(x.getNotes());

            this.slices.add(pos - 1, new Slice(x.getBeats(), x.getNotes()));
        }

        /*
         * Removes Slice at pos
         */
        public Slice remove(int pos) {
            assert this.slices.length() > pos - 1;

            return this.slices.remove(pos - 1);
        }

        /*
         * Replaces entry at pos with Slice x
         */
        public void replaceEntry(int pos, Slice x) {
            assert this.slices.length() > pos - 1;

            this.slices.replaceEntry(pos - 1, x);
        }

        /*
         * Gets entry at int pos
         */
        public Slice entry(int pos) {
            assert this.slices.length() > pos - 1;

            return this.slices.entry(pos - 1);
        }

        /*
         * Number of Slice(s) in this
         */
        public int length() {
            return this.slices.length();
        }
    }

    /*
     * Flips 
     */
    private class Flips extends MansliceSecondary.Flips {
        /*
         * Private members
         */

        /*
         * Rep of flips
         */
        private Sequence<Flip> flips;

        /*
         * Rep of copy/paste buffer
         */
        private Flip buffer;

        /*
         * Creates initial representation of flips
         */
        public void createNewRep() {
            this.flips = new Sequence1L<>();
        }

        /*
         * Constructors ---------------------------------------------------
         */

        public Flips() {
            this.createNewRep(); 
        }

        /*
         * Kernel methods -------------------------------------------------
         */

        /*
         * Clears this
         */
        public void clear() {
            this.flips.clear();
        }

        /*
         * Transfer from TODO
         */
        public void transferFrom(MansliceKernel.Flips source) {
            source.clear();
        }

        /*
         * Gets Flip in buffer
         */
        public Flip getBuffer() {
            return this.buffer;
        }

        /*
         * Sets buffer to Flip x
         */
        public void setBuffer(Flip x) {
            assert null != x;

            this.buffer = new Flip(x);
        }

        /*
         * Adds Flip at int pos containing int... pattern
         */
        public void add(int pos, int... pattern) {
            assert this.flips.length() > pos - 1;
            assert validPattern(pattern);

            this.flips.add(pos - 1, new Flip(pattern));
        }

        /*
         * Adds Flip at int pos containing Flip x
         */
        public void add(int pos, Flip x) {
            assert this.flips.length() > pos - 1;
            assert validPattern(x);

            this.flips.add(pos - 1, x);
        }

        /*
         * Removes Flip at int pos
         */
        public Flip remove(int pos) {
            assert this.flips.length() > pos - 1;

            return this.flips.remove(pos - 1);
        }

        /*
         * Replaces entry at pos with Flip x
         */
        public void replaceEntry(int pos, Flip x) {
            assert this.flips.length() > pos - 1;
            assert null != x;

            this.flips.replaceEntry(pos - 1, x);
        }

        /*
         * Gets entry at int pos
         */
        public Flip entry(int pos) {
            assert this.flips.length() > pos - 1;

            return this.flips.entry(pos - 1);
        }

        /*
         * Checks if pattern notes is valid
         */
        public boolean validPattern(int... pattern) {
            assert null != pattern;

            boolean valid = true;
            for (int entry : pattern) {
                if (entry - 1 > slices.length()) {
                    valid = false;
                }
            }
            return valid;
        }

        /*
         * Checks if pattern in Flip x is valid
         */
        public boolean validPattern(Flip x) {
            assert null != x.getPattern();

            boolean valid = true;
            for (int entry : x.getPattern()) {
                if (entry - 1 > slices.length()) {
                    valid = false;
                }
            }
            return valid;
        }

        /* 
         * Checks if positions are valid
         */
        public boolean validPositions(int... positions) {
            assert null != positions;

            boolean valid = true;
            for (int entry : positions) {
                if (entry - 1 > slices.length()) {
                    valid = false;
                }
            }
            return valid;
        }

        /* 
         * Checks if positions are valid
         */
        public boolean validPositions(Sequence<Integer> positions) {
            assert null != positions;

            boolean valid = true;
            for (int entry : positions) {
                if (entry - 1 > slices.length()) {
                    valid = false;
                }
            }
            return valid;
        }

        /*
         * Number of Flip(s) in this
         */
        public int length() {
            return this.flips.length();
        }
    }

    /*
     * Private members ------------------------------------------------
     */

    /*
     *
     */
    private Slices slices;

    /*
     *
     */
    private Flips flips;

    /*
     * Rep of undo history
     */
    private Stack<Manslice> undoHistory;

    /*
     * Rep of redo history
     */
    private Stack<Manslice> redoHistory;

    /*
     * Midi Sequencer
     */
    private Sequencer sequencer;

    /*
     * Midi Sequence
     */
    private javax.sound.midi.Sequence sequence;

    /*
     * BPM of song
     */
    private int BPM;

    /*
     * Creator of initial representation
     */
    private void createNewRep() {
        this.slices = new Slices();
        this.flips = new Flips();
        this.undoHistory = new Stack1L<>();
        this.redoHistory = new Stack1L<>();
        try {
            this.sequencer = MidiSystem.getSequencer();
            this.sequence = new javax.sound.midi.Sequence(javax.sound.midi.Sequence.PPQ, 4);
        }
        catch (Exception e) {
        }
    }

    /*
     * Constructors ---------------------------------------------------
     */

    /*
     * No-argument constructor
     */
    public Manslice1() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------
     */

    @Override
    public final Manslice newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        }
        catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Manslice source) {
        assert source != null;
        assert source != this;
        assert source instanceof Manslice1;

        Manslice1 localSource = (Manslice1) source;
        // TODO
    }

    /*
     * Kernel methods ---------------------------------------------------
     */

    /*
     * Create slices
     */
    public Slices createSlices() {
        return new Slices();
    }

    /*
     * Create flips
     */
    public Flips createFlips() {
        return new Flips();
    }

    /*
     * Store Manslice state in undo history
     */
    public void pushHistory(Manslice state) {
        undoHistory.push(state);
    }

    /*
     * Store Manslice state in redo history
     */
    public void pushRedoHistory(Manslice state) {
        redoHistory.push(state);
    }

    /*
     * Extract latest state in undo history
     */
    public Manslice popHistory() {
        assert 0 < this.undoHistory.length();

        // history of 20
        if (this.undoHistory.length() <= 20) {
            return undoHistory.pop();
        }
        return this;
    }

    /*
     * Extract latest state in redo history
     */
    public Manslice popRedoHistory() {
        assert 0 < this.redoHistory.length();

        // history of 20
        if (this.redoHistory.length() <= 20) {
            return redoHistory.pop();
        }
        return this;
    }

    /*
     * Gets current state
     */
    public Manslice getState() {
        return this;
    }

    /*
     * Sets state to Manslice x
     */
    public void setState(Manslice x) {
        this.transferFrom(x);
    }

    /*
     * Get midi Sequencer
     */
    public Sequencer getSequencer() {
        return this.sequencer;
    }

    /*
     * Get midi Sequence
     */
    public javax.sound.midi.Sequence getSequence() {
        return this.sequence;
    }

    /*
     * Get BPM
     */
    public int getBPM() {
        return this.BPM;
    }

    /*
     * Set BPM
     */
    public void setBPM(int bpm) {
        this.BPM = bpm;
    }
}
