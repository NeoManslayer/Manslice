package components.manslice;

/*
 * Allows extended editing of chord progression and
 * potential gui interface with song playability
 */
public interface Manslice extends MansliceKernel {

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
     * Pos < Manslice.Slices<Slice>.length()
     */
    void replaceSlice(int pos, Slice x);

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
     * Pos < Manslice.Flips<Flip>.length()
     */
    void replaceFlip(int pos, Flip x);

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
    void switchSlice(int pos1, int pos2);

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
    void switchFlip(int pos1, int pos2);

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
    void slice(int beats, String... chord);

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
    void flip(int bpm, int loop, String... flip);

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
    void meta(int bpm, int loop);

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
    void song();
}
