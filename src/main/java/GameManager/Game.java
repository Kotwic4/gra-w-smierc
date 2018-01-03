package GameManager;

import board.Board;

import java.util.List;


class Game {

    List<Player> players;
    Board board;

    public Game(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (players != null ? !players.equals(game.players) : game.players != null) return false;
        return board != null ? board.equals(game.board) : game.board == null;
    }
}
