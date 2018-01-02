package Gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.PopupBuilder;

import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;

public class
GameController {

    @FXML
    private GridPane grid;

    @FXML
    private Label livingOrganisms;

    @FXML
    private Label newOrganisms;

    @FXML
    private Button surrenderButton;

    @FXML
    private Button endTurnButton;

    public GameController() {

    }

    @FXML
    public void initialize(){
        int BUTTON_PADDING =0 ;
        int BUTTONS_PER_LINE = 20;
        int NUM_BUTTON_LINES = 20;
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);

        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                Button button = new Button(" ");
                button.setOnAction(event -> {Button innerbutton = ((Button)event.getSource());
                    innerbutton.setStyle("-fx-background-color: #ff0000");});
                grid.add(button, c, r);
            }
        }

        newOrganisms.setText("10");
        livingOrganisms.setText("3");

    }

    private void surrenderButtonHandler() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("SURRENDER");
        alert.setHeaderText("GIT GUD");
        alert.setContentText("SCRUB");
        alert.showAndWait();
    }

        private void endTurnButtonHandler() {
        System.out.println("Zakonczono ture.");
    }


}

