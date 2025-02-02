package components.manslice;

/*
 * Enhanced interface for Manslice
 */
public interface Manslice extends MansliceKernel {
    /*
     * A chord (notes) and its duration in song, beats
     */
    abstract class Slices extends MansliceKernel.Slices {
        /*
         * Switch positions of Slice at pos1 with Slice at pos2
         */
        abstract void move(int pos1, int pos2);

        /*
         * Edit beats and notes of Slice at pos
         */
        abstract void edit(int pos, int beats, int... notes);

        /*
         * Copy Slice at pos to clipboard
         */
        abstract void yank(int pos);

        /*
         * Paste Slice in clipboard to pos
         */
        abstract void put(int pos);

        /*
         * Cloness entry at pos
         */
        abstract void clone(int pos);

        /*
         * Adds Slice containing transformed values of Slice 
         * at pos using beats and int... notes
         */
        abstract void transform(int pos, int beats, int... notes);
    }

    /*
     * A pattern in song (the order the chords are played)
     */
    abstract class Flips extends MansliceKernel.Flips {
        /*
         * Switch Flip at pos1 with Flip at pos2
         */
        abstract void move(int pos1, int pos2);

        /*
         * Edit Flip at pos with int... pattern
         */
        abstract void edit(int pos, int... pattern);

        /*
         * Copy Flip at pos to clipboard
         */
        abstract void yank(int pos);

        /*
         * Paste Flip in clipboard to pos
         */
        abstract void put(int pos);

        /*
         * Clones entry at pos
         */
        abstract void clone(int pos);

        /*
         * Merges patterns of Flip(s) at positions into one Flip entry
         */
        abstract void meta(int... positions);

        /*
         * Order in which to play the Flips (orders the Flips)
         */
        abstract void order(int... positions);
    }

    /*
     * Revert to latest state in undo history
     */
    void undo();

    /*
     * Revert to latest state in redo history
     */
    void redo();

    /*
     * Play Midi song
     */
    void play();

    /*
     * Stop Midi song
     */
    void stop();
}
