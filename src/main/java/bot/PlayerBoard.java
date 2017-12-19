package bot;

import board.Coordinates;

import java.util.List;

public interface PlayerBoard {
    GuiTile getGuiTile(Coordinates cords);
    GuiTile[][] getGuiTiles();
    List<GuiTile> getAccessibleTiles();
    int getWidth();
    int getHeight();
}
