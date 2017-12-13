package board;

import java.awt.*;
import java.util.Map;

public interface IBoard {
    ITile getTile(Point coordinates);
    Map<Point, ITile> getTiles();
}
