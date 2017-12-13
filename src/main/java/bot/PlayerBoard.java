package bot;

import board.IBoard;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PlayerBoard implements IPlayerBoard {

    private HashMap<Point,GuiTile> guiBoard;

    public PlayerBoard(IBoard board, Player player) {
        guiBoard = new HashMap<>();
        board.getTiles().forEach((key, value) -> {
            GuiTile guiTile = new GuiTile(value, player);
            guiBoard.put(key, guiTile);
        });
    }

    @Override
    public IGuiTile getGuiTile(Point cords) {
        return guiBoard.get(cords);
    }

    @Override
    public Map<Point,? extends IGuiTile> getGuiTiles() {
        return guiBoard;
    }


}
