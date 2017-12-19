package bot;

import board.Board;
import board.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PlayerBoardImpl implements PlayerBoard {

    private GuiTileImpl[][] guiTiles;
    private int width;
    private int height;

    PlayerBoardImpl(Board board, PlayerImpl playerImpl) {
        width = board.getWidth();
        height = board.getHeight();
        guiTiles = new GuiTileImpl[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                guiTiles[x][y] = new GuiTileImpl(board.getTile(new Coordinates(x,y)),playerImpl);
            }
        }
    }

    @Override
    public GuiTile getGuiTile(Coordinates cords) {
        return guiTiles[cords.getX()][cords.getY()];
    }

    @Override
    public GuiTile[][] getGuiTiles() {
        return guiTiles;
    }

    private List<GuiTile> getGuiTilesList() {
        List<GuiTile> list = new ArrayList<>();
        for(GuiTileImpl[] array : guiTiles){
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public List<GuiTile> getAccessibleTiles(){
        return getGuiTilesList().stream()
                .filter(GuiTile::isAccessible)
                .collect(Collectors.toList());
    }


}
