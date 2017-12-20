package GameManager;

import board.Board;
import board.Tile;

public class FlatBoardBuider implements IBoardBuilder {

    public void fillBoard(Board board) {
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < board.getWidth(); i++)
            for (int j = 0; j < board.getHeight(); j++){
                Tile tile = tiles[i][j];
                tile.setCost(1);
        }
    }
}
