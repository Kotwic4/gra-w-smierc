package bot;

import board.Board;
import board.Coordinates;

import java.util.*;
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
                playerTiles[x][y] = new PlayerTile(board.getTile(new Coordinates(x, y)), player, board);
            }
        }

        PlayerBoard playerBoard = new PlayerBoard(playerTiles);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                List<PlayerTile> neighbours = new LinkedList<>();
                playerBoard.getPlayerTile(new Coordinates(x - 1, y - 1)).ifPresent(neighbours::add);
                playerBoard.getPlayerTile(new Coordinates(x - 1, y)).ifPresent(neighbours::add);
                playerBoard.getPlayerTile(new Coordinates(x - 1, y + 1)).ifPresent(neighbours::add);
                playerBoard.getPlayerTile(new Coordinates(x, y - 1)).ifPresent(neighbours::add);
                playerBoard.getPlayerTile(new Coordinates(x, y + 1)).ifPresent(neighbours::add);
                playerBoard.getPlayerTile(new Coordinates(x + 1, y - 1)).ifPresent(neighbours::add);
                playerBoard.getPlayerTile(new Coordinates(x + 1, y)).ifPresent(neighbours::add);
                playerBoard.getPlayerTile(new Coordinates(x + 1, y + 1)).ifPresent(neighbours::add);
                playerTiles[x][y].setNeighbours(neighbours);
            }
        }
        return new PlayerBoard(playerTiles);
    }

    private boolean checkCoords(Coordinates cords) {
        return cords.getX() < width && cords.getY() < height && cords.getX() >= 0 && cords.getY() >= 0;
    }

    PlayerBoard(PlayerTile[][] playerTiles) {
        this.playerTiles = playerTiles;
        width = playerTiles.length;
        height = playerTiles[0].length;
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

    public List<PlayerTile> getAccessibleTiles() {
        List<PlayerTile> list = new ArrayList<>();
        for (PlayerTile[] array : playerTiles) {
            list.addAll(Arrays.asList(array));
        }
        return list.stream()
                .filter(PlayerTile::isAccessible)
                .collect(Collectors.toList());
    }

    public void update() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                playerTiles[x][y].update();
            }
        }
    }


}
