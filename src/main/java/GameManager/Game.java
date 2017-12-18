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
}
