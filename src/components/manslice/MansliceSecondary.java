package components.manslice;

import java.util.Arrays;

/*
 * Layered implementations of secondary methods for `Manslice`
 */
public abstract class MansliceSecondary implements Manslice {

    /*
     * Kernel methods
     *
     * int lengthSlice()
     * int lengthFlip()
     * void insertSlice(int pos, Slice x)
     * void insertFlip(int pos, Flip x)
     * Slice removeSlice(int pos)
     * Flip removeFlip(int pos)
     */

    /*
     * Represents collection of song phrases
     */
    public Slices<Slice> slices;

    /*
     * Represents collection of song patterns
     */
    public Flips<Flip> flips;

    /*
     * Constructor to initialize slices and flips
     */
    public MansliceSecondary(Slices<Slice> slices, Flips<Flip> flips) {
        assert slices != null;
        assert flips != null;
        this.slices = slices;
        this.flips = flips;
    }

    /*
     * Default constructor
     */
    public MansliceSecondary() {
    }

    /*
     * Returns slices followed by flips
     */
    public String toString() {
        String slices = this.slices.toString();
        String flips = this.flips.toString();
        return "Slices: " + slices + "\n\nFlips: " + flips;
    }

    /*
     * Replaces Slice at position with x
     * 
     * @param pos
     * Position in Sequence<Slice>
     * 
     * @param x
     * Slice that will replace a slice at pos
     * 
     * @ensures
     * Slice at pos is removed from this
     * then it is replaced with slice x at pos
     * 
     * @requires
     * Pos is in Manslice.Slices<Slice>
     */
    public void replaceSlice(int pos, Slice x) {
        assert slices != null;
        assert x != null;
        assert pos < lengthSlices();
        assert validChord(x.chord);

        insertSlice(pos, x);
        removeSlice(pos + 1);
    }

    /*
     * Replaces Flip at a position with x
     * 
     * @param pos
     * Position in Sequence<Flip>
     * 
     * @param x
     * Flip that will replace a flip at pos
     * 
     * @ensures
     * Flip at pos is removed from this
     * Then it is replaced with flip x at pos
     * 
     * @requires
     * Pos is in Manslice.Flips<Flip>
     */
    public void replaceFlip(int pos, Flip x) {
        assert flips != null;
        assert x != null;
        assert pos < lengthFlips();
        assert validFlip(x.flip);

        insertFlip(pos, x);
        removeFlip(pos + 1);
    }

    /*
     * Switches slice at position with slice at another position
     * 
     * @param pos1
     * 1st position
     * 
     * @param pos2
     * 2nd position
     * 
     * @ensures
     * Slice at pos1 is switched with slice at pos2
     *
     * @requires
     * There exists a slice at pos1 and pos2
     * Pos1 and pos2 are less than length of slices
     */
    public void switchSlice(int pos1, int pos2) {
        assert slices != null;
        assert pos1 < lengthSlices();
        assert pos2 < lengthSlices();

        if (pos1 == pos2) {
            return;
        }

        int one = pos1;
        int two = pos2;
        if (pos1 < pos2) {
            one = pos2;
            two = pos1;
        }
        Slice temp = removeSlice(one);
        insertSlice(one, removeSlice(two));
        insertSlice(two, temp);
    }

    /*
     * Switches flip at position with flip at another position
     * 
     * @param pos1
     * 1st position
     * 
     * @param pos2
     * 2nd position
     * 
     * @ensures
     * Flip at pos1 is switched with flip at pos2
     *
     * @requires
     * There exists a flip at pos1 and pos2
     * pos1 and pos2 are less than length of flips
     */
    public void switchFlip(int pos1, int pos2) {
        assert flips != null;
        assert pos1 < lengthFlips();
        assert pos2 < lengthFlips();

        if (pos1 == pos2) {
            return;
        }

        int one = pos1;
        int two = pos2;
        if (pos1 < pos2) {
            one = pos2;
            two = pos1;
        }
        Flip temp = removeFlip(one);
        insertFlip(one, removeFlip(two));
        insertFlip(two, temp);
    }

    /*
     * Appends a slice (chord that plays for a number of beats) to `this`
     *
     * @param beats
     * Number of beats (time duration of phrase)
     *
     * @param chord
     * Chord (collection of music notes)
     *
     * @ensures
     * Slice containing beats and chord is appended to `this`
     *
     * @requires
     * 1 <= beats <= 16
     * and chord belongs to set of chord names (maj7...)
     * or chord belongs to set of notes: A5, B7...
     * (or midi values: 30, 31...)
     */
    public void slice(int beats, String... chord) {
        assert slices != null;
        assert beats >= 1 && beats <= 16;
        assert validChord(chord);

        Slice x = new Slice();
        x.beats = beats;
        x.chord = chord;
        insertSlice(lengthSlices(), x);
    }

    /*
     * Appends a flip (phrase pattern) to `this`
     *
     * @param bpm
     * Beats per minute of flip
     * 
     * @param loop
     * How many times to repeat flip in song
     * 
     * @param flip
     * Phrase pattern: 1234343456 (corresponds to positions of slices)
     * 
     * @ensures
     * `this` is appended with a flip containing
     * bpm, loop, and pattern (flip)
     *
     * @requires
     * 80 <= bpm <= 120
     * 1 <= loop
     * Flip is numbers: "1234" + ... + "9842" + ...
     */
    public void flip(int bpm, int loop, String... flip) {
        assert flips != null;
        assert bpm >= 80 && bpm <= 120;
        assert validFlip(flip);

        Flip x = new Flip();
        x.bpm = bpm;
        x.loop = loop;
        x.flip = flip;
        insertFlip(lengthFlips(), x);
    }

    /*
     * Compiles flips sequence into one phrase pattern
     *
     * @param bpm
     * Beats per minute of compiled flips
     *
     * @param loop
     * Number of times compiled flips should be repeated in song
     *
     * @ensures
     * The flips sequence in `this`
     * (flip_1, flip_2, ..., flip_len-2, flip_len-1)
     * is compiled together into one flip (one pattern)
     * The updated flips sequence will have a new length of 1
     *
     * @requires
     * 80 <= bpm <= 120
     * 1 <= loop
     */
    public void meta(int bpm, int loop) {
        assert flips != null;
        assert bpm >= 80 && bpm <= 120;
        assert loop >= 1;

        if (lengthFlips() == 0) {
            return;
        }

        String compiled = "";
        while (lengthFlips() > 0) {
            compiled += Arrays.deepToString(removeFlip(0).flip);
        }
        Flip meta = new Flip();
        meta.bpm = bpm;
        meta.loop = loop;
        meta.flip = new String[] { compiled };
        insertFlip(0, meta);
    }

    // Enhanced method not implemented, left abstract
    /*
     * Generates gui interface that allows
     * playing/editing of chord progression
     *
     * @requires
     * Nick isn't lazy
     *
     * @ensures
     * Gui will open containing options to run above methods
     * through manual user input, including an option
     * to hear the chord progression (play the song).
     * Allows user to see flips/patterns/slices and edit them.
     */
    public abstract void song();
}
