package board;

import java.util.List;
import java.util.Random;
import java.util.LinkedList;

public class Board {
    private final Tile[][] tiles;
    private final List<? extends Tile> strongholdList;
    private final int width;
    private final int height;
    private final Random random;
    private int lastAppeal;

    Board(Tile[][] tiles, List<? extends Tile> strongholdList){
      this.tiles = tiles;
      this.strongholdList = strongholdList;
      this.width = tiles.length;
      this.height = tiles[0].length;
      random = new Random();
    }

    public Tile getTile(Coordinates coords) throws InvalidTileCoordsException{
        try {
            return tiles[coords.getX()][coords.getY()];
        }catch(ArrayIndexOutOfBoundsException e){
            throw new InvalidTileCoordsException(coords);
        }
    }

    public void markAndClear(){
      int appeal = lastAppeal;
      while (appeal == lastAppeal){
        appeal = random.nextInt(10000);
      }
      lastAppeal = appeal;

      for (Tile stronghold: strongholdList){
        if(stronghold.isInhabitated()){
          stronghold.getInhabitant().setAppeal(appeal);
          stronghold.broadcastAppeal(appeal);
        }
      }

      for (int i=0; i<width; i++){
        for (int j=0; j<height; j++){
          if(tiles[i][j].isInhabitated()){
            tiles[i][j].checkAppealAndReact(appeal);
          }
        }
      }
    }

    public int getWidth(){
      return width;
    }

    public int getHeight(){
      return height;
    }

}
