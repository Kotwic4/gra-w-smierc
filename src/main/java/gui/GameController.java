package gui;

import bot.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class
GameController implements PlayerController,TurnCommunicator{

    private final Lock guiTurnLock = new ReentrantLock();

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
    public void initialize() {
        int BUTTON_PADDING = 0;
        int BUTTONS_PER_LINE = 20;
        int NUM_BUTTON_LINES = 20;
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);

        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                Button button = new Button(" ");
                button.setOnAction(event -> {
                    Button innerbutton = ((Button) event.getSource());
                    innerbutton.setStyle("-fx-background-color: #ff0000");
                });
                grid.add(button, c, r);
            }
        }


        newOrganisms.setText("10");
        livingOrganisms.setText("3");

    }

    @FXML
    private void surrenderButtonHandler() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("SURRENDER");
        alert.setHeaderText("GIT GUD");
        alert.setContentText("SCRUB");
        alert.showAndWait();
    }

    @FXML
    private void endTurnButtonHandler() {
        synchronized (guiTurnLock){
            guiTurnLock.notifyAll();
        }
    }


    @Override
    public void doGuiTurn(Player player) {
        synchronized (guiTurnLock){
            try {
                guiTurnLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void startHeadlessTurn(Player player) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Bot start");
            alert.showAndWait();
            alert.hide();
        });

    }

    @Override
    public void endHeadlessTurn(Player player) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Bot stop");
            alert.showAndWait();
        });
    }
}

