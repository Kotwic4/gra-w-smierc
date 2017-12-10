package GameManager;

public class FlatBoardBuider implements IBoardBuilder {

    public void fillBoard(Board board) {
        for (int i = 0; i < board.size; i++)
            for (int j = 0; j < board.size; j++)
                board.setTile(new Tile(1, false), i, j);
    }
}
