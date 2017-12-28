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
    public void markAndClear() {
    }

    @Test
    public void getTile() {
        try {
            Coordinates coords = new Coordinates(4, 3);
            Tile tile = board.getTile(coords);
            assertEquals(coords.getX(), tile.getCoords().getX());
            assertEquals(coords.getY(), tile.getCoords().getY());

        }catch (InvalidTileCoordsException e){
            fail();
        }

        Coordinates invalidCoords = new Coordinates(5,5);
        try{
            Tile tile = board.getTile(invalidCoords);
            fail();
        }catch(InvalidTileCoordsException e){
            // It's OK to be here.
        }
    }

    @Test
    public void markAsStronghold() {
        try {
            Coordinates coords = new Coordinates(2, 3);
            board.markAsStronghold(coords);
            assertTrue(board.getTile(coords).isStronghold());

            Coordinates coords2 = new Coordinates(1, 2);
            board.markAsStronghold(coords2);
            assertTrue(board.getTile(coords2).isStronghold());
        }catch (InvalidTileCoordsException e){
            fail();
        }
    }
}
