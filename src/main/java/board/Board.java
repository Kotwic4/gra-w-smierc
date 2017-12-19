package board;

import java.util.List;

public interface Board {
    Tile getTile(Coordinates coords);

    Tile[][] getTiles();

    List<Tile> getStrongholdList();

    int getWidth();

    int getHeight();
}
