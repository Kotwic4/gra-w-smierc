package gameManager;

import board.Board;
import bot.Player;

import java.util.ArrayList;
import java.util.List;


class GameBuilder {
    Board board;
    List<Player> players;
    Board.BoardBuilder boardBuilder;

    GameBuilder(int boardWidth, int boardHeight) {
        players = new ArrayList<Player>();
        boardBuilder = new Board.BoardBuilder(boardWidth,boardHeight);
    }

    void addPlayer(Player player) {
        players.add(player);
    }

    public void createBoard() {
        board = boardBuilder.build();
    }

    public void createBoard(IBoardBuilder boardBuilder) {
        boardBuilder.fillBoard(board);
    }

    public Game getGameInstance() {
        return new Game(players, board);
    }

}
