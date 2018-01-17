package gui;

import board.Board;
import bot.GuiPlayer;
import bot.Player;
import gameManager.BoardSerializer;
import gameManager.Game;
import gameManager.GameBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class
GameOptionsController {


    @FXML
    private Button loadBoard;

    @FXML
    private Button endTurnButton;

    Board.BoardBuilder boardBuilder;
    GameBuilder gameBuilder;
    List<Player> players = new ArrayList<>();

    public GameOptionsController() {
    }



    public void loadBoardButton(ActionEvent actionEvent)  {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Board");
        File file = fileChooser.showOpenDialog(new Stage());
        FileReader fr;
        if (file != null) {
            try {
                fr = new FileReader(file);
                boardBuilder = BoardSerializer.load(new BufferedReader(fr));
                gameBuilder = new GameBuilder(boardBuilder);
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BoardSerializer.MalformedFileException e) {
                e.printStackTrace();
            }
        }

    }

    public void play(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Parent root=null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = fxmlLoader.getController();
        Player gamer = new Player(Color.RED,"Ty",1);
        gamer.setPlayerStrategy(new GuiPlayer(gameController));
        Player bot = new Player(Color.BLUE,"Bot",2);
        bot.setPlayerStrategy(new GuiPlayer(gameController));
        GameBuilder gameBuilder = new GameBuilder(boardBuilder);
        Game game = gameBuilder.addPlayer(gamer).addPlayer(bot).getGameInstance();
        Stage window=(Stage)loadBoard.getScene().getWindow();
        window.setScene(new Scene(root, 300, 275));
        Thread thread = new Thread(game);
        thread.start();
    }
}