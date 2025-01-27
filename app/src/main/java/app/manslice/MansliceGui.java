package app.manslice;

import javax.sound.midi.*;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.map.Map;
import components.map.Map1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * brainstorming
 * methods:
 * switchSlices
 * cloneSlice // same chord but different beats
 * cloneFlip 
 * transformSlice
 * meta
 * addSlice (slice)
 * addFlip (flip)
 * removeSlice
 * removeFlip
 * order
 * gonna have a bpm value that is consistent for all flips (difficult to do otherwise)
 */

public class MansliceGui extends Application{

    /*
     * implementing class
     */
    public static Sequence<Map<Sequence<Integer>, Integer>> manSlices = new Sequence1L<>();

    /*
     * implementing class
     */
    public static Sequence<Sequence<Integer>> manFlips = new Sequence1L<>();

    /*
     * implementing class
     */
    public static Sequence<Integer> pattern = new Sequence1L<>();

    /*
     * implementing class
     *
     * 80 - 120
     */
    public static int BPM = 100;

    /*
     * implementing class
     */
    public static javax.sound.midi.Sequencer manSequencer;

    /*
     * kernel
     */
    public static void setPattern(int... args) {
        pattern.clear();
        for (int i = 0; i < args.length; i++) {
            pattern.add(pattern.length(), args[i]);
        }
    }

    /*
     * kernel
     */
    public static void addPattern(int... args) {
        for (int i = 0; i < args.length; i++) {
            pattern.add(pattern.length(), args[i]);
        }
    }

    /*
     * kernel
     */
    public static void removePattern(int pos) {
        pattern.remove(pos - 1);
    }

    /*
     * kernel
     */
    public static void switchPattern(int pos1, int pos2) {
        if (pos1 == pos2) {
            return;
        }

        int greater = pos1 - 1;
        int lesser = pos2 - 1;
        if (pos1 < pos2) {
            greater = pos2 - 1;
            lesser = pos1 - 1;
        }
        int temp = pattern.entry(greater);
        pattern.replaceEntry(greater, pattern.entry(lesser));
        pattern.replaceEntry(lesser, temp);
    }

    /*
     * kernel
     */
    public static int[] parsePattern(String... args) {
        // enumerations
        int[] parsed = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            parsed[i] = Integer.parseInt(args[i]) - 1;
        }
        return parsed;
    }

    /*
     * kernel
     */
    public static int[] parse(String... args) {
        int[] parsed = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            parsed[i] = Integer.parseInt(args[i]);
        }
        return parsed;
    }

    /*
     * kernel
     */
    public static Map<Sequence<Integer>, Integer> Slice(int beats, int... notes) {
        Map<Sequence<Integer>, Integer> slice = new Map1L<>();
        Sequence<Integer> noteSequence = new Sequence1L<>();
        // need enumeration
        for (int i = 0; i < notes.length; i++) {
            noteSequence.add(noteSequence.length(),
                    notes[i]);
        }
        slice.add(noteSequence, beats);
        return slice;
    }

    /*
     * kernel
     */
    public static Sequence<Integer> Flip(int loop, int... pattern) {
        Sequence<Integer> flip = new Sequence1L<>();
        // need enumeration
        for (int k = 0; k < loop + 1; k++) {
            for (int i = 0; i < pattern.length; i++) {
                flip.add(flip.length(),(pattern[i]));
            }
        }
        return flip;
    }

    /*
     * kernel
     */
    public static void addSlice(Map<Sequence<Integer>, Integer> slice) {
        manSlices.add(manSlices.length(), slice);
    }

    /*
     * kernel
     */
    public static void addFlip(Sequence<Integer> flip) {
        manFlips.add(manFlips.length(), flip);
    }

    /*
     * kernel
     */
    public static void setBPM(int bpm) {
        BPM = bpm;
    }

    /*
     * kernel
     */
    public static Sequence<Integer> getSliceChord(int pos) {
        Map.Pair<Sequence<Integer>, Integer> temp = manSlices.entry(pos).removeAny();
        Sequence<Integer> chord = temp.key();
        manSlices.entry(pos).add(temp.key(), temp.value());
        return chord;
    }

    /*
     * kernel
     */
    public static int getSliceBeats(int pos) {
        Map.Pair<Sequence<Integer>, Integer> temp = manSlices.entry(pos).removeAny();
        int beats = temp.value();
        manSlices.entry(pos).add(temp.key(), temp.value());
        return beats;
    }

    /*
     * kernel
     */
    public static Sequence<Integer> getFlipPattern(int pos) {
        return manFlips.entry(pos);
    }

    /*
     * kernel
     */
    public static boolean flipContains(Sequence<Integer> x) {
        boolean contains = false;
        for (int i = 0; i < manFlips.length() && !contains; i++) {
            contains = manFlips.entry(i) == x;
        }
        return contains;
    }

    /*
     * kernel (uses flipContains though)
     */
    public static void order() {
        Sequence<Sequence<Integer>> temp = new Sequence1L<>();
        temp.transferFrom(manFlips);
        final int ogLength = temp.length();
        for (int i = 0; i < temp.length(); i++) {
            int location = 0;
            if (i < pattern.length()) {
                location = pattern.entry(i);
            }
            Sequence<Integer> entryInPattern = temp.entry(location);
            Sequence<Integer> entryAtIndex = temp.entry(i);
            if (!flipContains(entryInPattern)) {
                manFlips.add(manFlips.length(), entryInPattern);
            }
            if (!flipContains(entryAtIndex)) {
                temp.add(temp.length(), entryAtIndex);
            }
            if (i >= ogLength && !flipContains(entryAtIndex)) {
                manFlips.add(manFlips.length(), temp.entry(i));
            }
        }
    }

    /*
     * kernel
     */
    public static void removeSlice(int pos) {
        manSlices.remove(pos);
    }

    /*
     * kernel
     */
    public static void removeFlip(int pos) {
        manFlips.remove(pos);
    }

    /*
     * kernel
     *
     * override this to allow user to meta only certain selected flips
     * allow user to choose whether to remove selected flips after creating meta
     */
    public static void metaFlips(int loop) {
        Sequence<Integer> meta = manFlips.entry(0);
        for (int i = 1; i < manSlices.length(); i++) {
            Sequence<Integer> pattern = manFlips.entry(i);
            for (int k = 0; k < pattern.length(); k++) {
                meta.add(meta.length(), manFlips.entry(i).entry(k));
            }
        }
        manFlips.add(manFlips.length(), meta);
    }

    /*
     * kernel
     */
    public static void transformSlice(int pos, int... transform) {
        Map<Sequence<Integer>, Integer> map = manSlices.entry(pos);
        Map.Pair<Sequence<Integer>, Integer> slice = map.removeAny();
        for (int i = 0; i < transform.length; i++) {
            int entry = slice.key().entry(i);
            entry += transform[i];
            slice.key().replaceEntry(i, entry);
        }
        map.add(slice.key(), slice.value());
        manSlices.add(manSlices.length(), map);
    }

    /*
     * kernel
     */
    public static void cloneSlice(int beats, int pos) {
        Map<Sequence<Integer>, Integer> map = manSlices.entry(pos - 1);
        Map.Pair<Sequence<Integer>, Integer> clone = map.removeAny();
        map.add(clone.key(), beats);
        manSlices.add(pos - 1, map);
    }

    /*
     * kernel
     */
    public static void cloneFlip(int loop, int pos) {
        Sequence<Integer> clone = manFlips.entry(pos);
        final Sequence<Integer> temp = manFlips.entry(pos);
        for (int i = 0; i < loop; i++) {
            for (int k = 0; k < temp.length(); k++) {
                clone.add(clone.length(), temp.entry(k));
            }
        }
        manFlips.add(manFlips.length(), clone);
    }

    /*
     * kernel
     */
    public static void switchSlices(int pos1, int pos2) {
        if (pos1 == pos2) {
            return;
        }

        int greater = pos1 - 1;
        int lesser = pos2 - 1;
        if (pos1 < pos2) {
            greater = pos2 - 1;
            lesser = pos1 - 1;
        }
        Map<Sequence<Integer>, Integer> temp = manSlices.entry(greater);
        manSlices.replaceEntry(greater, manSlices.entry(lesser));
        manSlices.replaceEntry(lesser, temp);
    }

    /*
     * kernel
     */
    public static void switchFlips(int pos1, int pos2) {
        if (pos1 == pos2) {
            return;
        }

        int greater = pos1 - 1;
        int lesser = pos2 - 1;
        if (pos1 < pos2) {
            greater = pos2 - 1;
            lesser = pos1 - 1;
        }
        Sequence<Integer> temp = manFlips.entry(greater);
        manFlips.replaceEntry(greater, manFlips.entry(lesser));
        manFlips.replaceEntry(lesser, temp);
    }

    /*
     * kernel
     *
     * updates then plays
     */
    public static void play() {
        if (manSequencer != null && manSequencer.isRunning()) {
            manSequencer.close();
        }
        try {
            manSequencer = MidiSystem.getSequencer();
            manSequencer.open();

            javax.sound.midi.Sequence manSequence = new javax.sound.midi.Sequence(javax.sound.midi.Sequence.PPQ, 4);
            javax.sound.midi.Track manTrack = manSequence.createTrack();

            int chordVoice = 0;
            ShortMessage changeProgram = new ShortMessage(
                    ShortMessage.PROGRAM_CHANGE, chordVoice, 0);
            manTrack.add(new MidiEvent(changeProgram, 0));

            int tick = 0;
            for (int m = 0; m < pattern.length(); m++) {
                Sequence<Integer> flip = getFlipPattern(pattern.entry(m));
                for (int i = 0; i < flip.length(); i++) {
                    Sequence<Integer> slice = getSliceChord(flip.entry(i));
                    for (int k = 0; k < slice.length(); k++) {
                        int note = slice.entry(k);
                        ShortMessage noteOn = new ShortMessage(ShortMessage.NOTE_ON, 0, note, 100);
                        ShortMessage noteOff = new ShortMessage(ShortMessage.NOTE_OFF, 0, note, 100);
                        manTrack.add(new MidiEvent(noteOn, tick));
                        manTrack.add(new MidiEvent(noteOff, tick + getSliceBeats(flip.entry(i)) * 2));
                    }
                    tick += getSliceBeats(flip.entry(i)) * 2;
                }
            }

            manSequencer.setSequence(manSequence);
            manSequencer.setTempoInBPM(BPM);
            manSequencer.setLoopCount(-1);

            manSequencer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * kernel
     */
    public static void pause() {
        if (manSequencer.isRunning()) {
            manSequencer.stop();
        }
    }

    /*
     * kernel
     *
     * don't work
     */
    public static void forward() {
        long tickLength = manSequencer.getTickLength();
        long newPosition = manSequencer.getTickPosition() + 32;
        if (newPosition > tickLength){
            long factor = newPosition / tickLength;
            newPosition = newPosition - (factor * tickLength);
        }
        manSequencer.setTickPosition(newPosition);
    }

    /*
     * kernel
     *
     * don't work
     */
    public static void backward() {
        long tickLength = manSequencer.getTickLength();
        long newPosition = manSequencer.getTickPosition() - 32;
        if (newPosition < 0){
            long factor = (Math.abs(newPosition) / tickLength);
            factor++;
            newPosition = newPosition + (factor * tickLength);
        }
        manSequencer.setTickPosition(newPosition);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

        String[][] chords = {
                { "55", "60", "64", "71" },
                { "54", "59", "62", "69" },
                { "52", "55", "60", "67" },
                { "50", "55", "59", "66" },
                { "55", "60", "64", "71" },
                { "54", "59", "62", "69" },
                { "52", "55", "60", "67" },
                { "50", "55", "59", "66" },
        };
        int[] chordBeats = {
                8,
                8,
                8,
                8,
                4,
                4,
                4,
                4,
        };
        for (int i = 0; i < chords.length; i++) {
            addSlice(Slice(chordBeats[i], parse(chords[i])));
        }

        String[][] flips = { { "1", "2" }, { "5", "6" }, { "7", "8"} };
        String[] patternInput = new String[] { "1", "1", "2", "3", "2", "3" };
        for (int i = 0; i < flips.length; i++) {
            addFlip(Flip(0, parsePattern(flips[i])));
        }

        setPattern(parsePattern(patternInput));

        BPM = 500;
        setBPM(BPM);

        play();
    }
}
