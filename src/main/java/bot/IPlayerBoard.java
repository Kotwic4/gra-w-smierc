package bot;

import java.awt.*;
import java.util.Collection;

public interface IPlayerBoard {
    IGuiTile getGuiTile(Point cords);
    Collection<IGuiTile> getGuiTiles(Point cords);
}
