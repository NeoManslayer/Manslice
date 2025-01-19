package components.manslice;

import components.standard.Standard;
import components.sequence.Sequence;

/*
 * Arranges chords by patterns (chord progressions) like how a DJ or 
 * Hip-hop producer would "slice", "chop" and "flip" samples.
 */
public interface MansliceKernel extends Standard<Manslice> {

    /*
     * Represents collection of song phrases
     */
    interface Slices<T extends Slice> extends Sequence<T> {
    }

    /*
     * Represents collection of song patterns
     */
    interface Flips<T extends Flip> extends Sequence<T> {
    }

    /*
     * Reports length of `Sequence<Slice>` (length of slices)
     *
     * @return
     * Length of `Sequence<Slice>`
     */
    int lengthSlices();

    /*
     * Reports length of `Sequence<Flip>` (length of flips)
     *
     * @return
     * Length of `Sequence<Flip>`
     */
    int lengthFlips();

    /*
     * Inserts a slice at a position
     *
     * @param pos
     * Position
     *
     * @param x
     * Slice to be inserted
     *
     * @ensures
     * `Sequence<Slice>` has x inserted at position
     *
     * @requires
     * Pos < length of slices
     */
    void insertSlice(int pos, Slice x);

    /*
     * Inserts a flip at a position
     *
     * @param pos
     * Position
     *
     * @param x
     * Flip to be inserted
     *
     * @ensures
     * `Sequence<Flip>` has x inserted at position
     *
     * @requires
     * Pos < length of flips
     */
    void insertFlip(int pos, Flip x);

    /*
     * Removes and returns a slice at position
     *
     * @param pos
     * Position
     *
     * @ensures
     * Slice at position is no longer with us (in slices)
     *
     * @requires
     * Pos < length of slices
     *
     * @return
     * Slice at position
     */
    Slice removeSlice(int pos);

    /*
     * Removes and returns a flip at position
     *
     * @param pos
     * Position
     *
     * @ensures
     * Flip at position is no longer with us (in slices)
     *
     * @requires
     * Pos < length of flips
     *
     * @return
     * Flip at position
     */
    Flip removeFlip(int pos);

    /*
     * Checks if chord is valid input
     *
     * @param chord
     * musical notes (chord)
     *
     * @requires
     *
     * @return
     * Whether chord is valid {@code true}
     * Or invalid {@code false}
     */
    boolean validChord(String... chord);

    /*
     * Checks if flip is valid input
     *
     * @param flip
     * Flip containing song pattern info.
     *
     * @requires
     * Sequence of positive integer digits (0 to Slices.length())
     * Each digit is less than length of {@code Slices}
     *
     * @return
     * Whether pattern is valid {@code true}
     * Or invalid {@code false}
     */
    boolean validFlip(String... flip);

}
