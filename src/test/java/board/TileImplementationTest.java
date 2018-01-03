package board;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Paweł Taborowski on 18.12.17.
 */
public class TileImplementationTest {
    private BoardBuilder boardBuilder;
    private List<TileImplementation> connectedTiles;
    private TileImplementation notConnectedTile;
    private int LENGTH = 5;

    @Before
    public void setUp() throws Exception {
      boardBuilder = new BoardBuilder(5,6);
      connectedTiles = new LinkedList<>();
      connectedTiles.add(boardBuilder.getTileImplementation(new Coordinates(3,2)));
      connectedTiles.add(boardBuilder.getTileImplementation(new Coordinates(2,2)));
      connectedTiles.add(boardBuilder.getTileImplementation(new Coordinates(1,2)));
      connectedTiles.add(boardBuilder.getTileImplementation(new Coordinates(1,3)));
      connectedTiles.add(boardBuilder.getTileImplementation(new Coordinates(1,4)));
      notConnectedTile = boardBuilder.getTileImplementation(new Coordinates(4,4));
      /*
      Board being generated:
      _____
      _#__#
      _#___
      _###_
      _____
      _____
       */

      for (TileImplementation tile: connectedTiles){
         tile.uncheckedSetIntabitant(new Organism(1));
      }
      notConnectedTile.uncheckedSetIntabitant(new Organism(1));
    }

    @Test
    public void broadcastAppeal() {
      try {
          Tile startingTile = boardBuilder.getTileImplementation(new Coordinates(2, 2));
          int fakeAppeal = 5;
          startingTile.broadcastAppeal(fakeAppeal);

          for (Tile connectedTile: connectedTiles) {
              assertEquals(fakeAppeal, connectedTile.getInhabitant().getAppeal());
          }

          assertNotEquals(fakeAppeal, notConnectedTile.getInhabitant().getAppeal());
      }catch (InvalidTileCoordsException e){
          fail();
      }
    }

    @Test
    public void setInhabitant() {
        // TODO: implement
    }

    // TODO: Implement test for setting neighbours
}
