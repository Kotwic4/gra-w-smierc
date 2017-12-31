package board;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paweł Taborowski on 28.12.17.
 */
public class BoardBuilder {
    private TileImplementation[][] tiles;
    private List<TileImplementation> strongholdList;
    private final int width;
    private final int height;

    public BoardBuilder(int width, int height){
      this.width = width;
      this.height = height;
      tiles = new TileImplementation[width][height];
      for (int i=0; i<width; i++){
        for (int j=0; j<height; j++){
          tiles[i][j] = new TileImplementation(new Coordinates(i, j));
        }
      }
      strongholdList = new LinkedList<>();
      makeConnectionsBetweenTiles();
    }

    private void makeConnectionsBetweenTiles(){
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

    public void markAsStronghold(Coordinates coords) throws InvalidTileCoordsException{
        TileImplementation tile = getTileImplementation(coords);
        tile.setStronghold();
        strongholdList.add(tile);
    }

    public TileImplementation getTileImplementation(Coordinates coords) throws InvalidTileCoordsException{
        try {
            return tiles[coords.getX()][coords.getY()];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new InvalidTileCoordsException(coords);
        }
    }

    public Board generateBoard(){
        Board board = new Board(tiles, strongholdList);
        return board;
    }

    public int getWidth(){
      return width;
    }

    public int getHeight(){
      return height;
    }

    public int getMaximumNeighbouringFriendsCount() {
        int maximumNeighbouringFriendsCount = 0;
        try {
            maximumNeighbouringFriendsCount = getTileImplementation(new Coordinates(0, 0)).getMaximumNeighbouringFriendsCount();
        } catch(InvalidTileCoordsException e){}

        return maximumNeighbouringFriendsCount;
    }

    public void setMaximumNeighbouringFriendsCount (int maximumNeighbouringFriendsCount) throws InvalidTileCoordsException{
        for (int x = 0; x < width; x++){
            for (int y=0; y < height; y++){
                getTileImplementation(new Coordinates(x,y)).setMaximumNeighbouringFriendsCount(maximumNeighbouringFriendsCount);
            }
        }
    }
}
