package gui;

import board.Board;
import board.Coordinates;
import gameManager.BoardSerializer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.regex.Pattern;

import static javafx.scene.layout.GridPane.getRowIndex;

public class BoardEditController {

    @FXML
    private GridPane grid;

    @FXML
    private Button saveBoardButton;

    @FXML
    public void initialize() {
        int BUTTON_PADDING = 0;
        int BUTTONS_PER_LINE = 20;
        int NUM_BUTTON_LINES = 20;
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);

        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                Button button = new Button("  ");
                button.setOnMouseClicked(event -> {
                    MouseButton button1 = event.getButton();
                    if (button1 == MouseButton.PRIMARY) {
                        leftMouseButtonAction(button);
                    } else if (button1 == MouseButton.SECONDARY) {
                        rightMouseButtonAction(button);
                    }
                });

                grid.add(button, c, r);
            }
        }


    }

    private void leftMouseButtonAction(Button button) {
        if (button.getText().equals("S")) {
            button.setText("  ");
        } else {
            button.setText("S");
        }

        System.out.println("PRIMARY button clicked on button");
    }

    private void rightMouseButtonAction(Button button) {
        final Stage dialog = new Stage();
        final Button submitButton = new Button("Potwierdz");
        submitButton.setDefaultButton(true);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                dialog.close();
            }
        });
        TextField textField = new TextField();
        textField.setMinHeight(TextField.USE_PREF_SIZE);
        final VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER_RIGHT);
        layout.setStyle("-fx-background-color: darkkhaki; -fx-padding: 10;");
        layout.getChildren().setAll(
                textField,
                submitButton
        );
        dialog.setTitle("Ustaw koszt");
        dialog.setScene(new Scene(layout));
        dialog.showAndWait();

        String result = textField.getText();
        if(!result.isEmpty() && !Pattern.matches("[a-zA-Z]+", result)) {
            button.setText(result);
        }

        System.out.println("SECONDARY button clicked on button");
    }

    @FXML
    private void saveBoardButtonHandler() throws IOException {
        ObservableList<Node> list = grid.getChildren();
        Board.BoardBuilder board = new Board.BoardBuilder(20,20);
        for(Node node : list) {
            Integer row = GridPane.getRowIndex(node);
            Integer column = GridPane.getColumnIndex(node);

            Coordinates coords = new Coordinates(column, row);
            if (node instanceof Button){
                String s = ((Button) node).getText();
                if(s.equals("S")) {
                    board.markAsStronghold(coords);
                } else if(Pattern.matches("[1-9]+", s)){
                    board.setTileCost(Integer.parseInt(s), coords);
                }
            }
        }
       // BoardSerializer.save(board, new BufferedWriter(new FileWriter("newBoardSave.txt")));


    }

}
