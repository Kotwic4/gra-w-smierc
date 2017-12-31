package board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Paweł Taborowski on 18.12.17.
 */
public class BoardTest {
    Board board;
    Coordinates organismToRemove;

    @Before
    public void setUp() throws Exception {
      BoardBuilder boardBuilder = new BoardBuilder(5,6);
      Coordinates strongholdCoord = new Coordinates(0,1);
      boardBuilder.markAsStronghold(strongholdCoord);
      Organism strongholdOrganism = new Organism(0);
      boardBuilder.getTileImplementation(strongholdCoord).uncheckedSetIntabitant(strongholdOrganism);
      organismToRemove = new Coordinates(4,5);
      boardBuilder.getTileImplementation(organismToRemove).uncheckedSetIntabitant(new Organism(0));
      board = boardBuilder.generateBoard();

      Coordinates[] aliveTiles = {new Coordinates(1,1), new Coordinates(2,1), new Coordinates(2,2), new Coordinates(2,3)};
      for(int i = 0; i < aliveTiles.length; i++){
         try {
           board.getTile(aliveTiles[i]).setInhabitant(new Organism(0));
         } catch (InvalidOrganismPositionException | InvalidTileCoordsException e) {
           fail();
         }
      }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void markAndClear() {
        try {
            assertEquals(board.getTile(organismToRemove).getInhabitant(), null);
        } catch (InvalidTileCoordsException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTile() {
        try {
            Coordinates coords = new Coordinates(4, 3);
            TileImplementation tile = (TileImplementation) board.getTile(coords);
            assertEquals(coords.getX(), tile.getCoords().getX());
            assertEquals(coords.getY(), tile.getCoords().getY());

        }catch (InvalidTileCoordsException e){
            fail();
        }

        Coordinates invalidCoords = new Coordinates(5,5);
        try{
            TileImplementation tile = (TileImplementation) board.getTile(invalidCoords);
            fail();
        }catch(InvalidTileCoordsException e){
            // It's OK to be here.
        }
    }
}
