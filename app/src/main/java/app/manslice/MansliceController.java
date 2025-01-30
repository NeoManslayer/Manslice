package app.manslice;

import javafx.fxml.*;
import javafx.scene.input.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.event.*;
import java.util.Arrays;
import components.map.Map;
import components.sequence.Sequence;

public class MansliceController extends MansliceGui {

    public void template() {
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

        String[][] flips = { { "1", "2" }, { "5", "6" }, { "7", "8" }, { "1", "3" } };
        String[] patternInput = new String[] { "1", "1", "2", "3", "2", "3" };
        for (int i = 0; i < flips.length; i++) {
            addFlip(Flip(0, parse(flips[i])));
        }

        setPattern(parse(patternInput));

        BPM = 500;
        setBPM(BPM);
    }

    @FXML
    private Button sliceButton,
            flipButton,
            patternButton,
            midiButton,
            addSliceButton,
            removeSliceButton,
            addFlipButton,
            removeFlipButton,
            setPatternButton,
            playButton,
            stopButton;

    private Button[] mainButtons,
            sliceButtons,
            flipButtons,
            patternButtons,
            midiButtons;

    private int currentIndex = 0;

    private Button[] buttons;

    @FXML
    private VBox inputContainer;

    @FXML
    private HBox mainContainer,
            sliceContainer,
            flipContainer,
            patternContainer,
            midiContainer,
            manSlicesContainer,
            manFlipsContainer,
            manPatternContainer;

    @FXML
    private TextField inputField;

    String userInput;

    private Node lastFocusedNode;

    private void generateManButtons() {
        manSlicesContainer.getChildren().clear();
        manFlipsContainer.getChildren().clear();
        manPatternContainer.getChildren().clear();

        for (int i = 0; i < manSlices.length(); i++) {
            final int pos = i + 1;
            Button button = new Button(
                    "Entry: " + (pos) +
                            "\nBeats: " + getSliceBeats(pos) +
                            "\nSlice: " + getSliceChord(pos).toString());
            button.setOnAction(e -> handleManSlices(pos));
            manSlicesContainer.getChildren().add(button);
        }

        for (int i = 0; i < manFlips.length(); i++) {
            final int pos = i + 1;
            Button button = new Button(
                    "Entry: " + (pos) +
                            "\nFlip: " + getFlipPattern(pos).toString());
            button.setOnAction(e -> handleManFlips(pos));
            manFlipsContainer.getChildren().add(button);
        }

        for (int i = 0; i < pattern.length(); i++) {
            final int pos = i + 1;
            Button button = new Button(
                    "Entry: " + (pos) +
                            "\nPattern: " + pattern.entry(i).toString());
            button.setOnAction(e -> handleManPattern(pos));
            manPatternContainer.getChildren().add(button);
        }
    }

    @FXML
    public void initialize() {
        template();
        generateManButtons();

        mainButtons = new Button[] { sliceButton, flipButton, patternButton, midiButton };
        sliceButtons = new Button[] { addSliceButton, removeSliceButton };
        flipButtons = new Button[] { addFlipButton, removeFlipButton };
        patternButtons = new Button[] { setPatternButton };
        midiButtons = new Button[] { playButton, stopButton };

        // Ensure mainButtons are focusable
        for (Button button : mainButtons) {
            button.setFocusTraversable(false);
        }

        for (Button button : sliceButtons) {
            button.setFocusTraversable(false);
        }

        for (Button button : flipButtons) {
            button.setFocusTraversable(false);
        }

        for (Button button : patternButtons) {
            button.setFocusTraversable(false);
        }

        for (Button button : midiButtons) {
            button.setFocusTraversable(false);
        }

        // Set initial focus
        Platform.runLater(() -> {
            mainButtons[currentIndex].getScene().getRoot().requestFocus();
            mainButtons[currentIndex].requestFocus();
        });
    }

    @FXML
    protected void handleKeyPress(KeyEvent event) {
        if (mainContainer.isVisible()) {
            buttons = mainButtons;
        } else if (sliceContainer.isVisible()) {
            buttons = sliceButtons;
        } else if (flipContainer.isVisible()) {
            buttons = flipButtons;
        } else if (patternContainer.isVisible()) {
            buttons = patternButtons;
        } else if (midiContainer.isVisible()) {
            buttons = midiButtons;
        }
        switch (event.getCode()) {
            case H:
            case LEFT:
                moveFocus(currentIndex - 1); // Move left
                break;
            case L:
            case RIGHT:
                moveFocus(currentIndex + 1); // Move right
                break;
            case ESCAPE:
                if (!mainContainer.isVisible()) {
                    int index = 0;
                    if (sliceContainer.isVisible()) {
                        sliceContainer.setVisible(false);
                        manSlicesContainer.setVisible(false);
                        lastFocusedNode = mainContainer.getScene().getFocusOwner();
                    } else if (flipContainer.isVisible()) {
                        flipContainer.setVisible(false);
                        manFlipsContainer.setVisible(false);
                        index = 1;
                        lastFocusedNode = mainContainer.getScene().getFocusOwner();
                    } else if (patternContainer.isVisible()) {
                        patternContainer.setVisible(false);
                        manPatternContainer.setVisible(false);
                        index = 2;
                        lastFocusedNode = mainContainer.getScene().getFocusOwner();
                    } else if (midiContainer.isVisible()) {
                        midiContainer.setVisible(false);
                        index = 3;
                        lastFocusedNode = mainContainer.getScene().getFocusOwner();
                    } else if (inputContainer.isVisible()) {
                        closeDialog();
                        break;
                    }
                    mainContainer.setVisible(true);
                    mainContainer.requestFocus();
                    mainContainer.getChildren().get(index).requestFocus();
                }
                break;
            default:
                break;
        }
        generateManButtons();
    }

    private void moveFocus(int newIndex) {
        if (newIndex < 0) {
            newIndex = buttons.length - 1;
        } else if (newIndex >= buttons.length) {
            newIndex = 0;
        }

        currentIndex = newIndex;
        buttons[currentIndex].requestFocus();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == sliceButton) {
            mainContainer.setVisible(false);
            changeFocus(sliceContainer);
            manSlicesContainer.setVisible(true);
        }
        if (clickedButton == flipButton) {
            mainContainer.setVisible(false);
            changeFocus(flipContainer);
            manFlipsContainer.setVisible(true);
        }
        if (clickedButton == patternButton) {
            mainContainer.setVisible(false);
            changeFocus(patternContainer);
            manPatternContainer.setVisible(true);
        }
        if (clickedButton == midiButton) {
            mainContainer.setVisible(false);
            changeFocus(midiContainer);
        }
    }

    private void handleManSlices(int index) {
    }

    private void handleManFlips(int index) {
    }

    private void handleManPattern(int index) {
    }

    @FXML
    private void handleSliceButtons(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == addSliceButton) {
            sliceContainer.setVisible(false);
            showInput();
        }
        if (clickedButton == removeSliceButton) {
            sliceContainer.setVisible(false);
            showInput();
        }
    }

    @FXML
    private void handleFlipButtons(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == addFlipButton) {
            flipContainer.setVisible(false);
            showInput();
        }
        if (clickedButton == removeFlipButton) {
            flipContainer.setVisible(false);
            showInput();
        }
    }

    @FXML
    private void handlePatternButtons(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == setPatternButton) {
            patternContainer.setVisible(false);
            showInput();
        }
    }

    @FXML
    private void handleMidiButtons(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        if (clickedButton == playButton) {
            play();
        }
        if (clickedButton == stopButton) {
            pause();
        }
    }

    @FXML
    private void changeFocus(HBox container) {
        lastFocusedNode = container.getScene().getFocusOwner();
        container.setVisible(true);
        container.requestFocus();
        container.getChildren().getFirst().requestFocus();
    }

    @FXML
    private void changeFocus(VBox container) {
        lastFocusedNode = container.getScene().getFocusOwner();
        container.setVisible(true);
        container.requestFocus();
        container.getChildren().getFirst().requestFocus();
    }

    @FXML
    private void showInput() {
        lastFocusedNode = inputContainer.getScene().getFocusOwner();
        inputContainer.setVisible(true);
        inputField.clear();
        inputField.requestFocus();
    }

    @FXML
    private void handleInput(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                userInput = inputField.getText();
                String regex = "[,\\s]+";
                if (lastFocusedNode == addSliceButton) {
                    String[] args = userInput.trim().split(regex);
                    int beats = Integer.parseInt(args[0]);
                    String[] notes = new String[args.length - 1];
                    for (int i = 0, k = 1; i < notes.length; i++, k++) {
                        notes[i] = args[k];
                    }
                    addSlice(Slice(beats, parse(notes)));
                } else if (lastFocusedNode == removeSliceButton) {
                    int pos = Integer.parseInt(userInput.trim());
                    if (pos < manSlices.length() + 1) {
                        removeSlice(pos);
                    }
                } else if (lastFocusedNode == addFlipButton) {
                    String[] args = userInput.trim().split(regex);
                    int loop = Integer.parseInt(args[0]);
                    String[] notes = new String[args.length - 1];
                    for (int i = 0, k = 1; i < notes.length; i++, k++) {
                        notes[i] = args[k];
                    }
                    addFlip(Flip(loop, parse(notes)));
                } else if (lastFocusedNode == removeFlipButton) {
                    int pos = Integer.parseInt(userInput.trim());
                    if (pos < manFlips.length() + 1) {
                        removeFlip(pos);
                    }
                } else if (lastFocusedNode == setPatternButton) {
                    String[] pattern = userInput.trim().split(regex);
                    setPattern(parse(pattern));
                    closeDialog();
                }
                break;
            case ESCAPE:
                closeDialog();
                break;
            default:
                break;
        }
    }

    private void closeDialog() {
        manSlicesContainer.setVisible(false);
        manFlipsContainer.setVisible(false);
        manPatternContainer.setVisible(false);
        inputContainer.setVisible(false);
        mainContainer.setVisible(true);
        mainContainer.requestFocus();
    }
}
