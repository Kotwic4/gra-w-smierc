package bot;

import board.Board;
import board.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PlayerBoard {

    private PlayerTile[][] guiTiles;
    private int width;
    private int height;

    PlayerBoard(Board board, Player player) {
        width = board.getWidth();
        height = board.getHeight();
        guiTiles = new PlayerTile[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                guiTiles[x][y] = new PlayerTile(board.getTile(new Coordinates(x,y)), player);
            }
        }
    }

    public PlayerTile getGuiTile(Coordinates cords) {
        return guiTiles[cords.getX()][cords.getY()];
    }

    public PlayerTile[][] getGuiTiles() {
        return guiTiles;
    }

    private List<PlayerTile> getGuiTilesList() {
        List<PlayerTile> list = new ArrayList<>();
        for(PlayerTile[] array : guiTiles){
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<PlayerTile> getAccessibleTiles(){
        return getGuiTilesList().stream()
                .filter(PlayerTile::isAccessible)
                .collect(Collectors.toList());
    }


}
