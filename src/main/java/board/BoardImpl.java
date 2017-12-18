package board;

import java.util.List;

/**
 * Created by Albert on 18.12.2017.
 */
public class BoardImpl implements Board {
    Tile[][] tiles;
    List<Tile> strongholdList;
    int width;
    int height;
    public BoardImpl(int width, int height){
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for(int i=0;i<width;i++)
            for(int j=0;j<height;j++)
                tiles[i][j]=new Tile();
        // TODO - generateBoard here ;
    }

    public Tile getTile(int x, int y){
        //TODO
        return tiles[x][y];
    }

    public void markAsStronghold(int x, int y){
        //TODO
    }

    public void markAndDelete(){

    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public List<Tile> getStrongholdList() {
        return strongholdList;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
