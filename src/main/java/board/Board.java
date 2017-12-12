package board;

import java.util.List;
import java.util.Map;

public class Board {
    private Map<Pair, Tile> tiles;
    private List<Tile> strongholdList;
    private int width;
    private int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        // TODO - generateBoard here ;
    }

    public Tile getTile(int x, int y){
        //TODO
        return null;
    }

    public void markAsStronghold(int x, int y){
        //TODO
    }

    public void markAndDelete(){

    }

    public Map<Pair, Tile> getTiles() {
        return tiles;
    }

    public List<Tile> getStrongholdList() {
        return strongholdList;
    }
}
