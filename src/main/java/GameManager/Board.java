package GameManager;

public class Board {
    private Tile[][] board;
    public int size;

    Board(int boardSize) {
        board = new Tile[boardSize][boardSize];
        this.size = boardSize;
    }

    Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size)
            return null;
        return board[x][y];
    }

    void setTile(Tile newTile, int x, int y) {
        newTile.setENeighbour(getTile(x - 1, y));
        newTile.setNNeighbour(getTile(x, y - 1));
        newTile.setWNeighbour(getTile(x + 1, y));
        newTile.setSNeighbour(getTile(x, y + 1));
        newTile.setNENeighbour(getTile(x - 1, y - 1));
        newTile.setSENeighbour(getTile(x - 1, y + 1));
        newTile.setNWNeighbour(getTile(x + 1, y - 1));
        newTile.setSWNeighbour(getTile(x + 1, y + 1));
        board[x][y] = newTile;
    }
}
