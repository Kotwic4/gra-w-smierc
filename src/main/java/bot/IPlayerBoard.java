package bot;

import java.awt.*;
import java.util.Map;

public interface IPlayerBoard {
    IGuiTile getGuiTile(Point cords);
    Map<Point,? extends IGuiTile> getGuiTiles();
}
