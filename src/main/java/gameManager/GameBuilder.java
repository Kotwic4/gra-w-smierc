package gameManager;

import board.Board;
import bot.Player;
import bot.PlayerBoard;
import bot.PlayerStrategy;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


public class GameBuilder {
    private Board board;
    private List<Player> players;
    public Board.BoardBuilder boardBuilder;
    private int nextPlayerId = 0;

    public GameBuilder(int boardWidth, int boardHeight) {
        players = new ArrayList<>();
        boardBuilder = new Board.BoardBuilder(boardWidth,boardHeight);
    }

    public GameBuilder addPlayer(Player player) {
        if(players.stream().noneMatch(player1 -> player1.getId()==player.getId()))
            players.add(player);
        return this;
    }

    public GameBuilder createBoard() {
        board = boardBuilder.build();
        return this;
    }

    public Game getGameInstance() {
        for(Player player: players)
            player.setPlayerBoard(PlayerBoard.createPlayerBoard(board,player));
        return new Game(players, board);
    }

}
