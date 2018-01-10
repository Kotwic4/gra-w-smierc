package gui;

import board.Coordinates;
import bot.*;
import gameManager.Game;
import gameManager.GameBuilder;
import javafx.concurrent.Task;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Parent root = fxmlLoader.load();
        GameController gameController = fxmlLoader.getController();
        Player gamer = new Player(Color.RED,"Ty",1);
        gamer.setPlayerStrategy(new GuiPlayer(gameController));
        Player bot = new Player(Color.BLUE,"Bot",2);
        bot.setPlayerStrategy(new RandomBot(gameController,new Random()));
        GameBuilder gameBuilder = new GameBuilder(20,20);
        gameBuilder.boardBuilder.markAsStronghold(new Coordinates(0,0));
        gameBuilder.boardBuilder.markAsStronghold(new Coordinates(19,19));
        gameBuilder.boardBuilder.setInhabitant(new Coordinates(0,0),bot);
        gameBuilder.boardBuilder.setInhabitant(new Coordinates(19,19),gamer);
        gamer.addStronhold();
        bot.addStronhold();
        Game game = gameBuilder.addPlayer(gamer).createBoard().
                addPlayer(bot).getGameInstance();
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
