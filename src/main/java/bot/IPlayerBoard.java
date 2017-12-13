package bot;

import java.awt.*;
import java.util.Collection;

public interface IPlayerBoard {
    IGuiTile getGuiTile(Point cords);
    Collection<? extends IGuiTile> getGuiTiles(Point cords);
}
