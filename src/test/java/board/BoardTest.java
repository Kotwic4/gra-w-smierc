package board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Paweł Taborowski on 18.12.17.
 */
public class BoardTest {
    Board board;

    @Before
    public void setUp() throws Exception {
      board = new Board(5,6);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void markAsStronghold() {
      Coordinates coords = new Coordinates(2,3);
      board.markAsStronghold(coords);
      List<Tile> tileList = board.getStrongholdList();
      assertEquals(coords.getX(), tileList.get(0).getCoords().getX());
      assertEquals(coords.getY(), tileList.get(0).getCoords().getY());
      assertEquals(tileList.get(0).isStronghold(), true);

      Coordinates coords2 = new Coordinates(1,2);
      board.markAsStronghold(coords2);
      tileList = board.getStrongholdList();
      assertEquals(coords2.getX(), tileList.get(1).getCoords().getX());
      assertEquals(coords2.getY(), tileList.get(1).getCoords().getY());
      assertEquals(tileList.get(1).isStronghold(), true);
    }

    @Test
    public void markAndDelete() {
    }
}
