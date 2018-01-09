package gui;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private void sceneHandler() throws IOException {
        System.out.println("Scene changing...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Parent root = fxmlLoader.load();
        GameController gameController = fxmlLoader.getController();
        GameBuilder gameBuilder = new GameBuilder(20,20);
        Game game = gameBuilder.addPlayer(new GuiPlayer(gameController),"Ty").createBoard().
                addPlayer(new GuiPlayer(gameController),"Nie Ty").getGameInstance();
        Stage window=(Stage)newGameButton.getScene().getWindow();
        window.setScene(new Scene(root));
        Thread thread = new Thread(game);
        thread.start();
    }

    @FXML
    private void exitButtonHandler() {
        Stage stage =(Stage)newGameButton.getScene().getWindow();
        stage.close();
    }

}
