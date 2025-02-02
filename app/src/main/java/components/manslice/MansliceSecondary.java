package components.manslice;

import javax.sound.midi.*;
import components.sequence.Sequence;

/*
 * Abstract class for Manslice that implements 
 * enhanced methods using kernel methods
 */
public abstract class MansliceSecondary implements Manslice {
    /*
     * Create new Slices
     */
    protected abstract Slices createSlices();

    /*
     * Create new Flips
     */
    protected abstract Flips createFlips();

    /*
     * A chord (notes) and its duration in song, beats
     */
    public abstract class Slices extends Manslice.Slices {
        /*
         * Switch positions of Slice at pos1 with Slice at pos2
         */
        void move(int pos1, int pos2) {
            assert this.length() > pos1 - 1 && this.length() > pos2 - 1;
            assert null != this.entry(pos1) && null != this.entry(pos2);

            pushHistory(getState());
            if (pos1 == pos2) {
                return;
            }
            int greater = pos1 - 1;
            int lesser = pos2 - 1;
            if (pos1 < pos2) {
                greater = pos2 - 1;
                lesser = pos1 - 1;
            }
            Slice temp = this.entry(greater);
            this.replaceEntry(greater, this.entry(lesser));
            this.replaceEntry(lesser, temp);
        }

        /*
         * Edit beats and notes of Slice at pos
         */
        void edit(int pos, int beats, int... notes) {
            assert this.length() > pos - 1;
            assert 1 <= beats && 16 >= beats;
            assert null != notes;
            assert MidiNote.contains(notes);

            pushHistory(getState());
            Slice slice = this.entry(pos);
            slice.setBeats(beats);
            slice.setNotes(notes);
            this.replaceEntry(pos, slice);
        }

        /*
         * Edit beats and notes of Slice at pos
         */
        void edit(int pos, int beats, Sequence<Integer> notes) {
            assert this.length() > pos - 1;
            assert 1 <= beats && 16 >= beats;
            assert null != notes;
            assert MidiNote.contains(notes);

            pushHistory(getState());
            Slice slice = this.entry(pos);
            slice.setBeats(beats);
            slice.setNotes(notes);
            this.replaceEntry(pos, slice);
        }

        /*
         * Edit beats and notes of Slice at pos
         */
        void edit(int pos, Slice x) {
            assert this.length() > pos - 1;
            assert 1 <= x.getBeats() && 16 >= x.getBeats();
            assert null != x.getNotes();
            assert MidiNote.contains(x.getNotes());

            pushHistory(getState());
            Slice slice = this.entry(pos);
            slice.setBeats(x.getBeats());
            slice.setNotes(x.getNotes());
            this.replaceEntry(pos, slice);
        }

        /*
         * Edit beats of Slice at pos
         */
        void edit(int pos, int beats) {
            assert this.length() > pos - 1;
            assert 1 <= beats && 16 >= beats;

            pushHistory(getState());
            Slice slice = this.entry(pos);
            slice.setBeats(beats);
            this.replaceEntry(pos, slice);
        }

        /*
         * Edit notes of Slice at pos
         */
        void edit(int pos, int... notes) {
            assert this.length() > pos - 1;
            assert null != notes;
            assert MidiNote.contains(notes);

            pushHistory(getState());
            Slice slice = this.entry(pos);
            slice.setNotes(notes);
            this.replaceEntry(pos, slice);
        }

        /*
         * Edit notes of Slice at pos
         */
        void edit(int pos, Sequence<Integer> notes) {
            assert this.length() > pos - 1;
            assert null != notes;
            assert MidiNote.contains(notes);

            pushHistory(getState());
            Slice slice = this.entry(pos);
            slice.setNotes(notes);
            this.replaceEntry(pos, slice);
        }

        /*
         * Copy Slice at pos to clipboard
         */
        void yank(int pos) {
            assert this.length() > pos - 1;

            setBuffer(this.entry(pos));
        }

        /*
         * Paste Slice in clipboard to pos
         */
        void put(int pos) {
            assert this.length() > pos - 1;

            pushHistory(getState());
            this.add(pos, getBuffer());
        }

        /*
         * Clones entry at pos
         */
        void clone(int pos) {
            assert this.length() > pos - 1;

            pushHistory(getState());
            Slice clone = this.entry(pos).clone();
            this.add(pos, clone);
        }

        /*
         * Clones entry at pos with value beats
         */
        void clone(int pos, int beats) {
            assert this.length() > pos - 1;
            assert 1 <= beats && 16 >= beats;

            pushHistory(getState());
            Slice clone = this.entry(pos).clone(beats);
            this.add(pos, clone);
        }

        /*
         * Adds Slice containing transformed values of Slice
         * at pos using beats and int... notes
         */
        void transform(int pos, int beats, int... notes) {
            assert this.length() > pos - 1;
            assert 1 <= beats && 16 >= beats;
            assert null != notes;
            assert MidiNote.contains(notes);

            pushHistory(getState());
            Slice transformed = this.entry(pos).transform(beats, notes);
            this.add(pos, transformed);
        }

        /*
         * Adds Slice containing transformed values of Slice
         * at pos using beats and Sequence<Integer> notes
         */
        void transform(int pos, int beats, Sequence<Integer> notes) {
            assert this.length() > pos - 1;
            assert 1 <= beats && 16 >= beats;
            assert null != notes;
            assert MidiNote.contains(notes);

            pushHistory(getState());
            Slice transformed = this.entry(pos).transform(beats, notes);
            this.add(pos, transformed);
        }

        /*
         * Adds Slice containing transformed values of Slice
         * at pos using int... notes
         */
        void transform(int pos, int... notes) {
            assert this.length() > pos - 1;
            assert null != notes;
            assert MidiNote.contains(notes);

            pushHistory(getState());
            Slice transformed = this.entry(pos).transform(notes);
            this.add(pos, transformed);
        }

        /*
         * Adds Slice containing transformed values of Slice
         * at pos using int... notes
         */
        void transform(int pos, Sequence<Integer> notes) {
            assert this.length() > pos - 1;
            assert null != notes;
            assert MidiNote.contains(notes);

            pushHistory(getState());
            Slice transformed = this.entry(pos).transform(notes);
            this.add(pos, transformed);
        }
    }

    /*
     * A pattern in song (the order the chords are played)
     */
    public abstract class Flips extends Manslice.Flips {
        /*
         * Switch Flip at pos1 with Flip at pos2
         */
        void move(int pos1, int pos2) {
            assert this.length() > pos1 && this.length() > pos2;
            assert null != this.entry(pos1) && null != this.entry(pos2);

            pushHistory(getState());
            if (pos1 == pos2) {
                return;
            }
            int greater = pos1 - 1;
            int lesser = pos2 - 1;
            if (pos1 < pos2) {
                greater = pos2 - 1;
                lesser = pos1 - 1;
            }
            Flip temp = this.entry(greater);
            this.replaceEntry(greater, this.entry(lesser));
            this.replaceEntry(lesser, temp);
        }

        /*
         * Edit Flip at pos with int... pattern
         */
        void edit(int pos, int... pattern) {
            assert this.length() > pos - 1;
            assert validPattern(pattern);

            pushHistory(getState());
            this.entry(pos).setPattern(pattern);
        }

        /*
         * Clones entry at pos
         */
        void clone(int pos) {
            assert this.length() > pos - 1;

            pushHistory(getState());
            Flip clone = this.entry(pos).clone();
            this.add(pos, clone);
        }

        /*
         * Copy Flip at pos to clipboard
         */
        void yank(int pos) {
            assert this.length() > pos - 1;

            setBuffer(this.entry(pos));
        }

        /*
         * Paste Flip in clipboard to pos
         */
        void put(int pos) {
            assert this.length() > pos - 1;

            pushHistory(getState());
            this.add(pos, getBuffer());
        }

        /*
         * Merges patterns of Flip(s) at positions into one Flip entry
         */
        void meta(int... positions) {
            assert validPositions(positions);

            pushHistory(getState());
            Flip meta = this.entry(positions[0] - 1);
            for (int i = 1; i < positions.length; i++) {
                meta.addPattern(this.remove(positions[i] - 1).getPattern());
            }
            this.replaceEntry(positions[0] - 1, meta);
        }

        /*
         * Merges patterns of Flip(s) at positions into one Flip entry
         */
        void meta(Sequence<Integer> positions) {
            assert validPositions(positions);

            pushHistory(getState());
            Flip meta = this.entry(positions.entry(0));
            for (int i = 1; i < positions.length(); i++) {
                meta.addPattern(this.remove(positions.entry(i) - 1).getPattern());
            }
            this.replaceEntry(positions.entry(0) - 1, meta);
        }

        /*
         * Order the way Flips are played
         */
        void order(int... positions) {
            assert validPositions(positions);

            pushHistory(getState());
            Flips temp = createFlips();
            temp.transferFrom(this);
            for (int i = 0; i < temp.length(); i++) {
                if (i > positions.length - 1) {
                    this.add(this.length(), temp.remove(0));
                }
                else {
                    this.add(this.length(), temp.remove(positions[i] - 1)); 
                }
            }
        }

        /*
         * Order the way Flips are played
         */
        void order(Sequence<Integer> positions) {
            assert validPositions(positions);

            pushHistory(getState());
            Flips temp = createFlips();
            temp.transferFrom(this);
            for (int i = 0; i < temp.length(); i++) {
                if (i > positions.length() - 1) {
                    this.add(this.length(), temp.remove(0));
                }
                else {
                    this.add(this.length(), temp.remove(positions.entry(i) - 1)); 
                }
            } 
        }
    }

    /*
     * Revert to latest state in undo history
     */
    public void undo() {
        assert null != this;

        pushRedoHistory(getState());
        setState(popHistory());
    }

    /*
     * Revert to latest state in redo history
     */
    public void redo() {
        assert null != this;

        pushHistory(getState());
        setState(popRedoHistory());
    }

    /*
     * Play Midi song
     */
    public void play() {
        assert null != getSequencer();

        Sequencer sequencer = getSequencer();
        if (sequencer.isRunning()) {
            sequencer.close();
        }
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            javax.sound.midi.Sequence sequence = getSequence();

            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(getBPM());
            sequencer.setLoopCount(-1);

            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Stop Midi song
     */
    public void stop() {
        assert null != getSequencer();

        Sequencer sequencer = getSequencer();
        try {
            if (getSequencer().isRunning()) {
                sequencer.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
