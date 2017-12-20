package GameManager;

import board.Board;
import board.BoardImpl;

import java.util.ArrayList;
import java.util.List;


class GameBuilder {
    Board board;
    List<Player> players;
    IBoardBuilder boardBuilder;

    GameBuilder(int boardWidth, int boardHeight) {
        players = new ArrayList<Player>();
        board = new BoardImpl(boardWidth, boardHeight);
        boardBuilder = new FlatBoardBuider();
    }

    void addPlayer(Player player) {
        players.add(player);
    }

    public void createBoard() {
        boardBuilder.fillBoard(board);
    }

    public void createBoard(IBoardBuilder boardBuilder) {
        boardBuilder.fillBoard(board);
    }

    public Game getGameInstance() {
        return new Game(players, board);
    }

}
