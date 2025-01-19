package components.manslice;

import components.sequence.Sequence1L;

public class Manslice1 extends MansliceSecondary {

    /*
     * Slices
     */
    private final Slices<Slice> slices;

    /*
     * Flips
     */
    private final Flips<Flip> flips;

    /*
     * Default constructor
     */
    public Manslice1() {
        super();
        this.slices = new Slices1();
        this.flips = new Flips1();
    }

    /*
     * Reports length of `Sequence<Slice>` (length of slices)
     *
     * @return
     * Length of `Sequence<Slice>`
     */
    public int lengthSlices() {
        return slices.length();
    }

    /*
     * Reports length of `Sequence<Flip>` (length of flips)
     *
     * @return
     * Length of `Sequence<Flip>`
     */
    public int lengthFlips() {
        return flips.length();
    }

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
    public void insertSlice(int pos, Slice x) {
        slices.add(pos, x);
    }

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
    public void insertFlip(int pos, Flip x) {
        flips.add(pos, x);
    }

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
    public Slice removeSlice(int pos) {
        return slices.remove(pos);
    }

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
    public Flip removeFlip(int pos) {
        return flips.remove(pos);
    }

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
    public boolean validChord(String... chord) {
        return false;
    }

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
    public boolean validFlip(String... flip) {
        return false;
    }

    /*
     * transferfrom
     */
    public void transferFrom(Manslice x) {
        this.clear();
        slices.transferFrom(((Manslice1) x).slices);
        flips.transferFrom(((Manslice1) x).flips);
    }

    /*
     * clear
     */
    public void clear() {
        slices.clear();
        flips.clear();
    }

    /*
     * newinstance
     */
    public Manslice newInstance() {
        return (Manslice1) this;
    }

    /*
     * song
     */
    public void song() {
    }

    /*
     * classes
     */
    private static class Slices1 extends Sequence1L<Slice> implements Slices<Slice> {
    }
    private static class Flips1 extends Sequence1L<Flip> implements Flips<Flip> {
    }

}
