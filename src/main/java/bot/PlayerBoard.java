package bot;

import board.Board;
import board.Coordinates;
import util.BoardHelper;

import java.util.*;
import java.util.stream.Collectors;


public class PlayerBoard {

    private PlayerTile[][] playerTiles;
    private int width;
    private int height;

    static public PlayerBoard createPlayerBoard(Board board, Player player, BoardHelper<PlayerTile> boardHelper) {
        int width = board.getWidth();
        int height = board.getHeight();
        PlayerTile[][] playerTiles = new PlayerTile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                playerTiles[x][y] = new PlayerTile(board.getTile(new Coordinates(x, y)), player, board);
                playerTiles[x][y].updateTileInformation();
            }
        }
        boardHelper.setNeighbours(playerTiles, PlayerTile::setNeighbours);
        return new PlayerBoard(playerTiles);
    }

    PlayerBoard(PlayerTile[][] playerTiles) {
        this.playerTiles = playerTiles;
        width = playerTiles.length;
        height = playerTiles[0].length;
    }

    private boolean checkCoords(Coordinates cords) {
        return cords.getX() < width && cords.getY() < height && cords.getX() >= 0 && cords.getY() >= 0;
    }

    public Optional<PlayerTile> getPlayerTile(Coordinates cords) {
        if (checkCoords(cords)) {
            return Optional.of(playerTiles[cords.getX()][cords.getY()]);
        } else {
            return Optional.empty();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<PlayerTile> getTiles(){
        List<PlayerTile> tiles = new ArrayList<>();
        for (PlayerTile[] array : playerTiles) {
            tiles.addAll(Arrays.asList(array));
        }
        return tiles;
    }

    public List<PlayerTile> getAccessibleTiles() {
        List<PlayerTile> tiles = getTiles();
        return tiles.stream()
                .filter(PlayerTile::isAccessible)
                .collect(Collectors.toList());
    }

}
