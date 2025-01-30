package app.manslice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FocusableGridWithSwap extends Application {

    // Custom class
    static class CustomObject {
        int number;
        String text;

        CustomObject(int number, String text) {
            this.number = number;
            this.text = text;
        }
    }

    // Array of objects
    private CustomObject[] objects = {
        new CustomObject(1, "One"),
        new CustomObject(2, "Two"),
        new CustomObject(3, "Three"),
        new CustomObject(4, "Four"),
        new CustomObject(5, "Five")
    };

    // Tracks the first selected box for swapping
    private StackPane firstSelectedBox = null;

    @Override
    public void start(Stage primaryStage) {
        // Create the GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap between boxes

        // Populate the GridPane with boxes
    for (int i = 0; i < objects.length; i++) {
        final int index = i; // Create a final copy of i
        CustomObject obj = objects[i];
    
        // Create a VBox for the box content
        VBox boxContent = new VBox(5); // Vertical gap between elements in the box
        boxContent.setStyle("-fx-alignment: center;");
    
        // Add a rectangle for the box background
        Rectangle background = new Rectangle(80, 50, Color.LIGHTBLUE);
        background.setArcWidth(10);
        background.setArcHeight(10);
    
        // Add labels for the int and string fields
        Label numberLabel = new Label("Number: " + obj.number);
        Label textLabel = new Label("Text: " + obj.text);
    
        // Stack the rectangle and labels
        StackPane box = new StackPane(background, boxContent);
        boxContent.getChildren().addAll(numberLabel, textLabel);
    
        // Make the box focusable and add keyboard interaction
        box.setFocusTraversable(true);
        box.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    handleEnterPress(box, index); // Use the final index variable here
                    break;
                default:
                    break;
            }
        });
    
        // Add the box to the GridPane
        gridPane.add(box, i, 0); // (column, row)
    }

        // Create and set up the scene
        Scene scene = new Scene(gridPane, 600, 150);
        primaryStage.setTitle("Focusable Grid with Swapping");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Handles the Enter key press for a box.
     */
    private void handleEnterPress(StackPane box, int index) {
        if (firstSelectedBox == null) {
            // First selection: Highlight the box
            firstSelectedBox = box;
            box.setStyle("-fx-border-color: red; -fx-border-width: 3;");
        } else {
            // Second selection: Swap the objects
            int firstIndex = GridPane.getColumnIndex(firstSelectedBox);
            swapObjects(firstIndex, index);

            // Update the display content
            updateBoxContent(firstSelectedBox, objects[firstIndex]);
            updateBoxContent(box, objects[index]);

            // Reset the first selected box
            firstSelectedBox.setStyle(""); // Remove highlight
            firstSelectedBox = null;
        }
    }

    /**
     * Swaps two objects in the array.
     */
    private void swapObjects(int index1, int index2) {
        CustomObject temp = objects[index1];
        objects[index1] = objects[index2];
        objects[index2] = temp;
    }

    /**
     * Updates the content of a box to match its corresponding object.
     */
    private void updateBoxContent(StackPane box, CustomObject obj) {
        VBox boxContent = (VBox) box.getChildren().get(1);
        Label numberLabel = (Label) boxContent.getChildren().get(0);
        Label textLabel = (Label) boxContent.getChildren().get(1);

        numberLabel.setText("Number: " + obj.number);
        textLabel.setText("Text: " + obj.text);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

