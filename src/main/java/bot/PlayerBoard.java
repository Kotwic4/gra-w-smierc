package bot;

import board.Board;
import board.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PlayerBoard {

    private PlayerTile[][] playerTiles;
    private int width;
    private int height;

    static public PlayerBoard createPlayerBoard(Board board, Player player) {
        int width = board.getWidth();
        int height = board.getHeight();
        PlayerTile[][] playerTiles = new PlayerTile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                playerTiles[x][y] = new PlayerTile(board.getTile(new Coordinates(x, y)), player);
            }
        }
        //todo set tiles neighbours
        return new PlayerBoard(playerTiles);
    }

    private boolean checkCoords(Coordinates cords) {
        return cords.getX() < width && cords.getY() < height;
    }

    public PlayerBoard(PlayerTile[][] playerTiles) {
        this.playerTiles = playerTiles;
        width = playerTiles.length;
        height = playerTiles[0].length;
    }

    public Optional<PlayerTile> getPlayerTile(Coordinates cords) {
        if (!checkCoords(cords)) return Optional.empty();
        else return Optional.of(playerTiles[cords.getX()][cords.getY()]);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<PlayerTile> getAccessibleTiles() {
        List<PlayerTile> list = new ArrayList<>();
        for (PlayerTile[] array : playerTiles) {
            list.addAll(Arrays.asList(array));
        }
        return list.stream()
                .filter(PlayerTile::isAccessible)
                .collect(Collectors.toList());
    }

    void update() {
        //todo
    }


}
