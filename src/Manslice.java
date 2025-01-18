import java.util.Arrays;

import components.queue.Queue;
import components.queue.Queue2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class Manslice {

    private String chordVoice, melodyVoice, rhythmVoice, bassVoice;

    private int BPM;

    private String slice;

    private class Cell {

        @Override
        public Cell clone() {
            Cell clone = new Cell();
            clone.beats = this.beats;
            clone.openChordRoot = this.openChordRoot;
            clone.openChordType = this.openChordType;
            clone.closeChordRoot = this.closeChordRoot;
            clone.closeChordType = this.closeChordType;
            clone.openMelody = Arrays.copyOf(this.openMelody,
                    this.openMelody.length);
            clone.closeMelody = Arrays.copyOf(this.closeMelody,
                    this.closeMelody.length);
            clone.openRhythm = Arrays.copyOf(this.openRhythm,
                    this.openRhythm.length);
            clone.closeRhythm = Arrays.copyOf(this.closeRhythm,
                    this.closeRhythm.length);
            clone.openBass = Arrays.copyOf(this.openBass, this.openBass.length);
            clone.closeBass = Arrays.copyOf(this.closeBass,
                    this.closeBass.length);
            return clone;
        }

        private int beats;

        private String openChordRoot, openChordType, closeChordRoot,
                closeChordType;
        private String[] openMelody, closeMelody;
        private String[] openRhythm, closeRhythm;
        private String[] openBass, closeBass;

    }

    private Queue<Cell> rep;

    private void createNewRep() {
        this.rep = new Queue2<Cell>();
    }

    public Manslice() {
        this.createNewRep();
        this.rep.enqueue(new Cell());
    }

    public void setBPM(int bpm) {
        this.BPM = bpm;
    }

    public void slice(String slice) {
        this.slice = slice;
    }

    public void chordVoice(String voice) {
        this.chordVoice = voice;
    }

    public void melodyVoice(String voice) {
        this.melodyVoice = voice;
    }

    public void rhythmVoice(String voice) {
        this.rhythmVoice = voice;
    }

    public void bassVoice(String voice) {
        this.bassVoice = voice;
    }

    public void openCell(int beats) {
        this.rep.front().beats = beats;
        this.rep.front().openMelody = new String[beats * 2];
        this.rep.front().closeMelody = new String[beats * 2];
        this.rep.front().openRhythm = new String[beats * 2];
        this.rep.front().closeRhythm = new String[beats * 2];
        this.rep.front().openBass = new String[beats * 2];
        this.rep.front().closeBass = new String[beats * 2];
    }

    public void openChord(String rootNote, String chordType) {
        this.rep.front().openChordRoot = rootNote;
        this.rep.front().openChordType = chordType;
    }

    public void closeChord(String rootNote, String chordType) {
        this.rep.front().closeChordRoot = rootNote;
        this.rep.front().closeChordType = chordType;
    }

    public void openMelody(String... melody) {
        this.rep.front().openMelody = melody;
    }

    public void closeMelody(String... melody) {
        this.rep.front().closeMelody = melody;
    }

    public void openRhythm(String... rhythm) {
        this.rep.front().openRhythm = rhythm;
    }

    public void closeRhythm(String... rhythm) {
        this.rep.front().closeRhythm = rhythm;
    }

    public void openBass(String... bass) {
        this.rep.front().openBass = bass;
    }

    public void closeBass(String... bass) {
        this.rep.front().closeBass = bass;
    }

    public void openClonedCell(int position, int beats) {
        this.rep.rotate(position);
        Cell x = this.rep.front().clone();
        int originalBeats = this.rep.front().beats;
        if (beats != 0 && beats != originalBeats) {
            x.beats = beats;
            x.openMelody = new String[beats * 2];
            x.closeMelody = new String[beats * 2];
            x.openRhythm = new String[beats * 2];
            x.closeRhythm = new String[beats * 2];
            x.openBass = new String[beats * 2];
            x.closeBass = new String[beats * 2];
        }
        this.rep.rotate(-position);
        this.rep.replaceFront(x);
    }

    public int cellNum() {
        return this.rep.length() - 1; // number of closed cells
    }

    @Override
    public String toString() {
        String manslice = "BPM: " + this.BPM + "\n";
        manslice += "Voices (Chord, Melody, Rhythm, Bass): " + this.chordVoice
                + ", " + this.melodyVoice + ", " + this.rhythmVoice + ", "
                + this.bassVoice + "\n";
        manslice += "Slice: " + this.slice + "\n";
        for (int i = 1; i < this.rep.length(); i++) {
            this.rep.rotate(1);
            Cell x = this.rep.front();
            if (x.beats != 0) {
                manslice += "Cell " + i + ":\n";
                manslice += "\tBeats: " + x.beats + "\n";
                manslice += "\tChords (open, close): " + x.openChordRoot + " "
                        + x.openChordType + ", " + x.closeChordRoot + " "
                        + x.closeChordType + "\n";
                manslice += "\tMelody (open, close): "
                        + Arrays.toString(x.openMelody) + ", "
                        + Arrays.toString(x.closeMelody) + "\n";
                manslice += "\tDrums (open, close): "
                        + Arrays.toString(x.openRhythm) + ", "
                        + Arrays.toString(x.closeRhythm) + "\n";
                manslice += "\tBass (open, close): "
                        + Arrays.toString(x.openBass) + ", "
                        + Arrays.toString(x.closeBass) + "\n";
            }
        }
        this.rep.rotate(-(this.cellNum()));
        return manslice;
    }

    public void closeCell(int position) {
        while (this.rep.length() <= position) {
            this.rep.enqueue(new Cell());
            // so that when we enqueue it goes to position and not less than position
        }
        Cell front = this.rep.front();
        this.rep.replaceFront(new Cell());

        this.rep.rotate(position);
        this.rep.replaceFront(front);
        this.rep.rotate(-position);
    }

    public static void main(String[] args) {
        Manslice manslice = new Manslice();
        int beats = 8, bpm = 100;
        String openRoot = "60", openType = "maj7", closeRoot = "64",
                closeType = "min7";
        String[] openMelody = { ("10"), ("11"), ("12"), ("13"), ("14"), ("67"),
                ("68"), ("69"), ("69"), ("69"), ("69"), ("69"), ("76"), ("76"),
                ("76"), ("76") };
        String[] closeMelody = { ("21"), ("21"), ("21"), ("21"), ("42"), ("42"),
                ("42"), ("42"), ("42"), ("42"), ("42"), ("42"), ("42"),
                ("42,67,79"), ("30,33,36"), ("39,42,45") };
        String[] openRhythm = { ("S"), ("K,S"), ("S"), ("K,S"), ("S"), ("K,S"),
                ("H"), ("H"), ("H"), ("H"), ("S"), ("K,S"), ("S"), ("K,S"),
                ("S"), ("H") };
        String[] closeRhythm = { ("H"), ("H"), ("H"), ("H"), ("H"), ("H"),
                ("H"), ("H"), ("H"), ("S"), ("H"), ("K"), ("K"), ("K"), ("K"),
                ("H") };
        String[] openBass = { ("1"), ("2"), ("3"), ("4"), ("5"), ("6"), (""),
                ("7,8"), (""), ("10,11"), (""), ("13,14"), (""), ("15,16"),
                (""), ("1") };
        String[] closeBass = { (""), (""), ("3"), (""), (""), ("7"), (""),
                ("9"), (""), (""), ("12"), (""), (""), ("11"), (""), ("") };

        manslice.setBPM(bpm);
        manslice.slice = "1123411234112341123477777777777777";
        manslice.chordVoice = "Viola";
        manslice.melodyVoice = "Piano";
        manslice.rhythmVoice = "Jazz Kit";
        manslice.bassVoice = "Acoustic";

        manslice.openCell(beats);
        manslice.openChord(openRoot, openType);
        manslice.openMelody(openMelody);
        manslice.openRhythm(openRhythm);
        manslice.openBass(openBass);
        manslice.closeChord(closeRoot, closeType);
        manslice.closeMelody(closeMelody);
        manslice.closeRhythm(closeRhythm);
        manslice.closeBass(closeBass);

        manslice.closeCell(1);

        manslice.openClonedCell(1, 0);
        manslice.closeCell(2);

        String openRoot3 = "45", openType3 = "min9", closeRoot3 = "21",
                closeType3 = "maj9";
        String[] openMelody3 = { ("11"), ("1"), ("30"), ("33"), ("24"), ("70"),
                ("50"), ("51"), ("19"), ("35"), ("45"), ("50"), ("51"), ("60"),
                ("65"), ("69") };
        String[] closeMelody3 = { ("2"), ("22"), ("54"), ("31"), ("42"), ("42"),
                ("42"), ("42"), ("42"), ("42"), (""), (""), ("69"),
                ("69,69,69"), ("30"), ("69,45") };
        String[] openRhythm3 = { (""), ("S"), ("K"), ("H"), ("S"), ("K,S"),
                ("H"), ("H"), ("H"), ("H"), ("S"), ("H"), ("S"), ("K,S"), ("S"),
                ("S") };
        String[] closeRhythm3 = { ("S"), ("S"), ("H"), ("H"), ("H"), ("H"),
                ("H"), ("H"), ("H"), ("H"), ("H"), ("H"), ("S"), ("K"), ("K"),
                ("S") };
        String[] openBass3 = { ("5"), ("10"), ("15"), ("20,25"), ("30,35"),
                ("50"), (""), ("7,8"), (""), ("10,11"), ("69"), ("13,14"),
                ("69"), ("15,16"), ("2"), ("2") };
        String[] closeBass3 = { ("20"), ("22"), ("24,26"), ("29,30"), ("31"),
                ("31"), ("31"), ("3"), ("7"), ("10"), ("12"), ("15"), ("18"),
                ("21"), ("24"), ("27") };

        manslice.openCell(beats);
        manslice.openChord(openRoot3, openType3);
        manslice.openMelody(openMelody3);
        manslice.openRhythm(openRhythm3);
        manslice.openBass(openBass3);
        manslice.closeChord(closeRoot3, closeType3);
        manslice.closeMelody(closeMelody3);
        manslice.closeRhythm(closeRhythm3);
        manslice.closeBass(closeBass3);

        manslice.closeCell(3);

        manslice.openClonedCell(3, beats);

        String openRoot4 = "19", openType4 = "dim", closeRoot4 = "71",
                closeType4 = "maj";

        manslice.openChord(openRoot4, openType4);
        manslice.closeChord(closeRoot4, closeType4);

        manslice.closeCell(4);

        manslice.openClonedCell(4, 4);
        manslice.closeCell(7);

        SimpleWriter out = new SimpleWriter1L();
        out.println(manslice.toString());
        out.close();
    }
}
