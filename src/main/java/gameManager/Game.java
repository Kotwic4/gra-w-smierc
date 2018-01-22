package gameManager;

import board.Board;
import bot.Player;
import javafx.concurrent.Task;

import java.util.List;


public class Game implements Runnable {

    private List<Player> players;
    Board board;

    Game(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return (players != null ? players.equals(game.players) : game.players == null) && (board != null ? board.equals(game.board) : game.board == null);
    }
  
    @Override
    public void run() {
        while (true)
        for(Player player:players){
            System.out.println("new Player");
            player.makeTurn();
        }
    }
}

