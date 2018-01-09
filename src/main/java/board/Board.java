package board;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;

/*
Use this as follows :
Board board = new Board.BoardBuilder(width, height).markAsStronghold(coords).setInhabitant(coords, nation).build();
*/

public class Board {
    private final Tile[][] tiles;
    private final List<? extends Tile> strongholdList;
    private final int width;
    private final int height;
    private final Random random;
    private int lastAppeal;

    public static class BoardBuilder {
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
            new connectionGenerator().makeConnectionsBetweenTiles();
        }

        private class connectionGenerator{
            private void makeConnectionsBetweenTiles(){
                for (int x=0; x<width; x++){
                    for (int y=0; y<height; y++){
                        if(isNotOnLeftBorder(x) && isNotOnBottomBorder(y))
                            tiles[x][y].addNeighbour(tiles[stepLeft(x)][stepDown(y)]);
                        if(isNotOnLeftBorder(x))
                            tiles[x][y].addNeighbour(tiles[stepLeft(x)][y]);
                        if(isNotOnLeftBorder(x) && isNotOnTopBorder(y))
                            tiles[x][y].addNeighbour(tiles[stepLeft(x)][stepUp(y)]);
                        if(isNotOnTopBorder(y))
                            tiles[x][y].addNeighbour(tiles[x][stepUp(y)]);
                        if(isNotOnRightBorder(x) && isNotOnTopBorder(y))
                            tiles[x][y].addNeighbour(tiles[stepRight(x)][stepUp(y)]);
                        if(isNotOnRightBorder(x))
                            tiles[x][y].addNeighbour(tiles[stepRight(x)][y]);
                        if(isNotOnRightBorder(x) && isNotOnBottomBorder(y))
                            tiles[x][y].addNeighbour(tiles[stepRight(x)][stepDown(y)]);
                        if(isNotOnBottomBorder(y))
                            tiles[x][y].addNeighbour(tiles[x][stepDown(y)]);
                    }
                }
            }

            private boolean isNotOnLeftBorder(int x){
                return x > 0;
            }

            private boolean isNotOnRightBorder(int x){
                return x < width-1;
            }

            private boolean isNotOnBottomBorder(int y){
                return y > 0;
            }

            private boolean isNotOnTopBorder(int y){
                return y < height-1;
            }

            private int stepLeft(int x){
                return x-1;
            }

            private int stepRight(int x){
                return x+1;
            }

            private int stepUp(int y){
                return y+1;
            }

            private int stepDown(int y){
                return y-1;
            }
        }

        public BoardBuilder markAsStronghold(Coordinates coords) throws InvalidTileCoordsException{
            TileImplementation tile = getTileImplementation(coords);
            tile.setStronghold();
            strongholdList.add(tile);
            return this;
        }

        public TileImplementation getTileImplementation(Coordinates coords) throws InvalidTileCoordsException{
            try {
                return tiles[coords.getX()][coords.getY()];
            }catch(ArrayIndexOutOfBoundsException e){
                throw new InvalidTileCoordsException(coords);
            }
        }

        public BoardBuilder setInhabitant(Coordinates coords, int nation) throws TileAlreadyInhabitedException{
            tiles[coords.getX()][coords.getY()].uncheckedSetIntabitant(new Organism(nation));
            return this;
        }

        public void setTileCost(int cost, Coordinates coordinates){
            TileImplementation tileImplementation = getTileImplementation(coordinates);
            tileImplementation.setCost(cost);
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
            } catch(InvalidTileCoordsException e){
                e.getMessage();
            }

            return maximumNeighbouringFriendsCount;
        }

        public void setMaximumNeighbouringFriendsCount (int maximumNeighbouringFriendsCount) {
            try {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        getTileImplementation(new Coordinates(x, y)).setMaximumNeighbouringFriendsCount(maximumNeighbouringFriendsCount);
                    }
                }
            } catch(InvalidTileCoordsException e){
                    // Can't happen.
            }
        }

        public Board build(){
            return new Board(this);
        }
    }

    private Board(BoardBuilder builder){
      this.tiles = builder.tiles;
      this.strongholdList = builder.strongholdList;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (width != board.width) return false;
        if (height != board.height) return false;
        if (lastAppeal != board.lastAppeal) return false;
        if (!Arrays.deepEquals(tiles, board.tiles)) return false;
        if (strongholdList != null ? !strongholdList.equals(board.strongholdList) : board.strongholdList != null)
            return false;
        return random != null ? random.equals(board.random) : board.random == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(tiles);
        result = 31 * result + (strongholdList != null ? strongholdList.hashCode() : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + (random != null ? random.hashCode() : 0);
        result = 31 * result + lastAppeal;
        return result;
    }
}
