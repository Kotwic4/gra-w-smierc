package board;

import java.util.List;
import java.util.Map;

public class Board {
    private Tile[][] tiles;
    private List<Tile> strongholdList;
    private final int width;
    private final int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        for (int i=0; i<width; i++){
          for (int j=0; j<height; j++){
            tiles[i][j] = new Tile(new Coordinates(i, j));
          }
        }
        for (int i=0; i<width; i++){
          for (int j=0; j<height; j++){
            if(i > 0 && j > 0) tiles[i][j].addNeighbour(tiles[i-1][j-1]);
            if(i > 0) tiles[i][j].addNeighbour(tiles[i-1][j]);
            if(i > 0 && j < height-1) tiles[i][j].addNeighbour(tiles[i-1][j+1]);
            if(j < height-1) tiles[i][j].addNeighbour(tiles[i][j+1]);
            if(i < width-1 && j < height-1) tiles[i][j].addNeighbour(tiles[i+1][j+1]);
            if(i < width-1) tiles[i][j].addNeighbour(tiles[i+1][j]);
            if(i < width-1 && j > 0) tiles[i][j].addNeighbour(tiles[i+1][j-1]);
            if(j > 0) tiles[i][j].addNeighbour(tiles[i][j-1]);
          }
        }
    }

    public Tile getTile(Coordinates coords){
        return tiles[coords.getX()][coords.getY()];
    }

    public void markAsStronghold(Coordinates coords){
        Tile tile = tiles[coords.getX()][coords.getY()];
        tile.setStronghold();
        strongholdList.append(tile);
    }

    public void markAndDelete(){
      for (int i=0; i<width; i++){
        for (int j=0; j<height; j++){
          if(!tiles[i][j].getInhabitant())){
            
          }
        }
      }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public List<Tile> getStrongholdList() {
        return strongholdList;
    }

    public int getWidth(){
      return width;
    }

    public int getHeight(){
      return height;
    }
}
