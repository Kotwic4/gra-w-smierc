package board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Taborowski on 18.12.17.
 */
public class TileTest {
    Board board;
    Coordinates[] coordinates;
    Tile[] tiles;
    int LENGTH = 6;

    @Before
    public void setUp() throws Exception {
      board = new Board(5,6);
      coordinates = new Coordinates[LENGTH];
      coordinates[0] = new Coordinates(3,2);
      coordinates[1] = new Coordinates(2,2);
      coordinates[2] = new Coordinates(1,2);
      coordinates[3] = new Coordinates(1,3);
      coordinates[4] = new Coordinates(1,4);
      coordinates[5] = new Coordinates(4,4);

      for (int i=0; i<LENGTH; i++){
         board.getTile(coordinates[i]).uncheckedSetIntabitant(new Organism(1));
      }
      board.markAsStronghold(coordinates[0]);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isInhabitated() {
    }

    @Test
    public void broadcastAppeal() {
      Tile startingTile = board.getTile(new Coordinates(2,2));
      int mockAppeal = 5;
      startingTile.broadcastAppeal(mockAppeal);

      for (int i = 0; i < LENGTH; i++){
        if (i == 5) assertNotEquals(mockAppeal, board.getTile(coordinates[i]).getInhabitant().getAppeal());
        else assertEquals(mockAppeal, board.getTile(coordinates[i]).getInhabitant().getAppeal());
      }
    }

    @Test
    public void getInhabitant() {
    }

    @Test
    public void setInhabitant() {
    }

    @Test
    public void getCost() {
    }

    @Test
    public void setCost() {
    }

    @Test
    public void getCoords() {
    }

    @Test
    public void addNeighbour() {
    }

    @Test
    public void setStronghold() {
    }

    @Test
    public void getStronghold() {
    }
}
