package components.manslice;

import components.standard.Standard;

/*
 * Arranges chords by patterns (chord progressions) like how a DJ or 
 * Hip-hop producer would "slice", "chop" and "flip" samples.
 */
public interface MansliceKernel extends Standard<Manslice> {

    /*
     * Reports length of `Sequence<Slice>` (length of slices)
     *
     * @return
     * Length of `Sequence<Slice>`
     */
    int lengthSlice();

    /*
     * Reports length of `Sequence<Flip>` (length of flips)
     *
     * @return
     * Length of `Sequence<Flip>`
     */
    int lengthFlip();

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

}
