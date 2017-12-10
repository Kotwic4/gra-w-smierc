package GameManager;

import java.util.ArrayList;
import java.util.List;


class GameBuilder {
    Board board;
    List<Player> players;
    IBoardBuilder boardBuilder;

    GameBuilder(int boardSize) {
        players = new ArrayList<Player>();
        board = new Board(boardSize);
        boardBuilder = new FlatBoardBuider();
    }

    void addPlayer(Player player) {
        players.add(player);
    }

    public void createBoard() {
        boardBuilder.fillBoard(board);
        ;
    }

    public void createBoard(IBoardBuilder boardBuilder) {
        boardBuilder.fillBoard(board);
    }

    public Game getGameInstance() {
        return new Game(players, board);
    }

}
