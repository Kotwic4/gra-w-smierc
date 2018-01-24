package gameManager;

import board.Board;
import board.Coordinates;
import bot.Player;
import bot.PlayerBoard;
import bot.PlayerStrategy;
import javafx.scene.paint.Color;
import util.BoardHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


public class GameBuilder {
    private Board board;
    private List<Player> players;
    public Board.BoardBuilder boardBuilder;
    private int nextPlayerId = 0;
    private boolean isBoardInitialized = false;

    public GameBuilder(int boardWidth, int boardHeight) {
        players = new ArrayList<>();
        boardBuilder = new Board.BoardBuilder(boardWidth,boardHeight, new BoardHelper<>());
    }

    public GameBuilder addPlayer(Player player) {
        players.add(player);
        return this;
    }

    public GameBuilder addPlayer(PlayerStrategy playerStrategy, String name) {
        Random random = new Random();
        nextPlayerId++;
        Player player = new Player(Color.color(random.nextDouble(),random.nextDouble(), random.nextDouble()), name, nextPlayerId);
        player.setPlayerStrategy(playerStrategy);
        players.add(player);
        return this;
    }

    public GameBuilder(Board.BoardBuilder boardBuilder){
        players = new ArrayList<>();
        this.boardBuilder = boardBuilder;
    }

    public Game getGameInstance() {
        for(Player player: players){
            boardBuilder.setStrongholdInhabitant(player);
        }
        Board board = boardBuilder.build();
        for(Player player: players) {
            player.setPlayerBoard(PlayerBoard.createPlayerBoard(board, player, new BoardHelper<>()));
        }
            return new Game(players, board);
    }

}
