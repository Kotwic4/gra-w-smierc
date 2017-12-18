package board;

import java.util.List;
import java.util.Map;

public interface Board {

    public Tile getTile(int x, int y);

    public void markAsStronghold(int x, int y);

    public void markAndDelete();

    public Tile[][] getTiles() ;

    public List<Tile> getStrongholdList();

    public int getHeight();

    public int getWidth();
}
