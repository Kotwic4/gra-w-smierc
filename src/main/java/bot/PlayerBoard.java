package bot;

import board.IBoard;
import board.ITile;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

public class PlayerBoard implements IPlayerBoard {

    private HashMap<Point,GuiTile> guiBoard;

    public PlayerBoard(IBoard board, Player player) {
        guiBoard = new HashMap<>();
        Collection<? extends ITile> tiles = board.getTiles();
        for(ITile tile : tiles){
            GuiTile guiTile = new GuiTile(tile,player);
            guiBoard.put(tile.getCords(),guiTile);
        }
    }

    @Override
    public IGuiTile getGuiTile(Point cords) {
        return guiBoard.get(cords);
    }

    @Override
    public Collection<? extends IGuiTile> getGuiTiles(Point cords) {
        return guiBoard.values();
    }
}
