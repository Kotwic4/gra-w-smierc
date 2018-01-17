package gui;

import board.Coordinates;
import bot.*;
import gameManager.Game;
import gameManager.GameBuilder;
import javafx.concurrent.Task;
import bot.GuiPlayer;
import bot.SimpleBot;
import gameManager.Game;
import gameManager.GameBuilder;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainMenuController {

    @FXML
    private Button newGameButton;
    @FXML
    private Button boardEditorButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;

    @FXML
    private URL location;


    @FXML
    private ResourceBundle resources;
    public MainMenuController() {
    }


    @FXML
    private void newGameButtonHandler() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GameOptions.fxml"));
        Parent root = fxmlLoader.load();
        Stage window=(Stage)newGameButton.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    @FXML
    private void boardEditorButtonHandler() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Editor.fxml"));
        Parent root = fxmlLoader.load();
        Stage window=(Stage)newGameButton.getScene().getWindow();
        window.setScene(new Scene(root, 300, 275));
    }

    @FXML
    private void exitButtonHandler() {
        Stage stage =(Stage)newGameButton.getScene().getWindow();
        stage.close();
    }

}
