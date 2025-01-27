package app.manslice;

import javax.sound.midi.*;

public class AruarianDance {

    // Helper method to create a "Note On" event for MIDI
    private static MidiEvent createNoteOnEvent(int channel, int note, int velocity, int tick) throws InvalidMidiDataException {
        // has constructor too
        ShortMessage message = new ShortMessage();  
        // channel, note, velocity
        message.setMessage(ShortMessage.NOTE_ON, channel, note, velocity);
        return new MidiEvent(message, tick);
    }

    // Helper method to create a "Note Off" event for MIDI
    private static MidiEvent createNoteOffEvent(int channel, int note, int velocity, int tick) throws InvalidMidiDataException {
        // has constructor too
        ShortMessage message = new ShortMessage();
        // channel, note, velocity
        message.setMessage(ShortMessage.NOTE_OFF, channel, note, velocity);
        return new MidiEvent(message, tick);
    }

    public static void main(String[] args) {
        try {
            // create sequencer (accesses software/hardware to play sequences)
            Sequencer sequencer = MidiSystem.getSequencer();
            // "opens up for use" whatever that means
            sequencer.open(); 

            // create a sequence (notes and timing information, holds one or more tracks)
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            // set channel 0 (chords) to strings instrument
            int chordInstrument = 1;
            // 2 bytes of data
            ShortMessage programChangeMessage = new ShortMessage();
            // last 2 args are for placing information in the 2 bytes
            programChangeMessage.setMessage(ShortMessage.PROGRAM_CHANGE, 0, chordInstrument, 0);
            // last arg tells you at what tick (time) the message is put into place (what time instrument is changed)
            MidiEvent programChangeEvent = new MidiEvent(programChangeMessage, 0);
            // add event (instrument change) to track
            track.add(programChangeEvent);

            // initialize chords (channel 0)
            int[][] chords = {{55, 60, 64, 71}, {54, 59, 62, 69}, 
                {55, 60, 64, 71}, {54, 59, 62, 69},
            {55, 60, 64, 71}, {54, 59, 62, 69},
            {52, 55, 60, 67}, {50, 55, 59, 66}
            };  
            int[] chordDurations = {16, 16, 16, 16, 8, 8, 8, 8};

            // add chords to track
            int tick = 1;
            for (int i = 0; i < chords.length; i++) {
                int[] notes = chords[i];
                for (int k = 0; k < notes.length; k++) {
                    // see helper methods
                    track.add(createNoteOnEvent(0, notes[k], 70, tick));
                    track.add(createNoteOffEvent(0, notes[k], 70, tick + chordDurations[i]));
                }
                tick += chordDurations[i];
            }

            // set channel 1 (melody) to piano instrument
            int melodyInstrument = 0;
            // 2 bytes of data
            programChangeMessage = new ShortMessage();
            // last 2 args are for placing information in the 2 bytes
            programChangeMessage.setMessage(ShortMessage.PROGRAM_CHANGE, 1, melodyInstrument, 0);
            // last arg tells you at what tick (time) the message is put into place (what time instrument is changed)
            programChangeEvent = new MidiEvent(programChangeMessage, 0);
            // add event (instrument change) to track
            track.add(programChangeEvent);
            
            // initialize melody (channel 1)
            int[][] melody = { {71}, {55}, {60}, {60, 64, 71}, {64}, {60}, {55}, {54, 54}, {69}, {74}, {71},
            {71}, {55}, {60}, {60, 64, 71}, {64}, {60}, {55}, {54, 54}, {69}, {74}, {71},
            {55}, {60}, {64}, {71}, {55}, {60}, {64}, {71},
            {54}, {59}, {62}, {69}, {54}, {59}, {62}, {69},
            {52}, {55}, {60}, {67}, {52}, {55}, {60}, {67},
            {50}, {55}, {59}, {66}, {64}, {76}, 
            {64}, {57}, {59}, {64}, {69}, {70, 82}, {71, 83}, {72, 84}, {74, 86},
            };
            int[] melodyDurations = {2, 2, 2, 4, 2, 2, 2, 4, 4, 4, 4,
            2, 2, 2, 4, 2, 2, 2, 4, 4, 4, 4,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 4, 1,
            6, 2, 2, 2, 2, 2, 
            4, 4, 4, 4
            };
    
            // add melody to track
            tick = 1;
            for (int i = 0; i < melody.length; i++) {
                int[] notes = melody[i];
                for (int k = 0; k < notes.length; k++) {
                    // see helper methods
                    track.add(createNoteOnEvent(1, notes[k], 100, tick));
                    track.add(createNoteOffEvent(1, notes[k], 100, tick + melodyDurations[i]));
                }
                tick += melodyDurations[i];
            }

            // set sequence to sequencer
            sequencer.setSequence(sequence);

            // set tempo
            sequencer.setTempoInBPM(100);

            // loop forever
            sequencer.setLoopCount(-1);

            // start sequencer to play song
            sequencer.start();

            // wait until song is finished
            while (sequencer.isRunning()) {
                Thread.sleep(10000);
            }
            // restart / move forwards or backwards functionality
            sequencer.stop();
            sequencer.setTickPosition(0);
            sequencer.setTempoInBPM(300);
            sequencer.start();

            while (sequencer.isRunning()) {

            }

            sequencer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
