package board;

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

        public BoardBuilder setInhabitant(Coordinates coords, int nation) {
            try {
                tiles[coords.getX()][coords.getY()].uncheckedSetIntabitant(new Organism(nation));
            } catch (TileAlreadyInhabitedException e) {
                e.getMessage();
            }
            return this;
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

        public void setMaximumNeighbouringFriendsCount (int maximumNeighbouringFriendsCount) throws InvalidTileCoordsException{
            for (int x = 0; x < width; x++){
                for (int y=0; y < height; y++){
                    getTileImplementation(new Coordinates(x,y)).setMaximumNeighbouringFriendsCount(maximumNeighbouringFriendsCount);
                }
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

        if (getWidth() != board.getWidth()) return false;
        if (getHeight() != board.getHeight()) return false;
        //if (lastAppeal != board.lastAppeal) return false;
        if (!Arrays.deepEquals(getTiles(), board.getTiles())) return false;
        //return getStrongholdList() != null ? getStrongholdList().equals(board.getStrongholdList()) : board.getStrongholdList() == null;
        return true;
    }
}
