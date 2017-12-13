package board;

import java.awt.*;
import java.util.Collection;

public interface IBoard {
    ITile getTile(Point coordinates);
    Collection<ITile> getTiles();
}
