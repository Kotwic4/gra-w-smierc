package gui;

import board.Coordinates;
import bot.Player;
import bot.PlayerBoard;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import gameManager.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class
GameController  implements PlayerController,TurnCommunicator{

    private final Lock guiTurnLock = new ReentrantLock();
    private int BUTTONS_PER_LINE = 20;
    private int NUM_BUTTON_LINES = 20;
    private PositionedButton[][] buttons = new PositionedButton[NUM_BUTTON_LINES][BUTTONS_PER_LINE];

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

        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);

        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                PositionedButton button = new PositionedButton(new Coordinates(r,c)," ");
                buttons[r][c] = button;
                button.setOnAction(event -> {
                    Button innerbutton = ((Button) event.getSource());
                    innerbutton.setStyle("-fx-background-color: #"+Color.BLUE.toString().substring(2,8));
                    System.out.println(Color.BLUE.toString().substring(2,8));
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

    public void assignButtonsToPlayerTiles(PlayerBoard playerBoard){
        for(int i=0;i<NUM_BUTTON_LINES;i++)
            for(int j =0;j<BUTTONS_PER_LINE;j++)
                if(playerBoard.getPlayerTile(new Coordinates(i,j)).isPresent()) {
                    buttons[i][j].setStyle("-fx-background-color: #" + playerBoard.getPlayerTile(new Coordinates(i, j)).get().getColor().toString().substring(2, 8));
                    buttons[i][j].setOnAction(event -> {
                        playerBoard.getPlayerTile(((PositionedButton)event.getSource()).coordinates).get().inhabit();
                    });
                }
                else
                    buttons[i][j].setStyle("-fx-background-color: #000000");

    }

    @Override
    public void doGuiTurn(Player player) {
        Platform.runLater(()->{
            assignButtonsToPlayerTiles(player.getPlayerBoard());
        });
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

