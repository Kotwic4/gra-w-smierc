package board;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import bot.Player;

public class BoardBuilderTest {
    private Board.BoardBuilder boardBuilder;
    private final int boardWidth = 5;
    private final int boardHeight = 6;

    @Before
    public void setUp() {
        boardBuilder = new Board.BoardBuilder(boardWidth, boardHeight);
    }

    @Test
    public void markAsStronghold() {
        Coordinates strongholdCoord = new Coordinates(0, 1);
        boardBuilder.markAsStronghold(strongholdCoord);
        Board board = boardBuilder.build();
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Coordinates coords = new Coordinates(x, y);
                Tile tile = board.getTile(coords);
                if (coords.equals(strongholdCoord)) {
                    assertTrue(tile.isStronghold());
                } else {
                    assertFalse(tile.isStronghold());
                }
            }
        }
    }

    @Test
    public void setInhabitant() {
        Coordinates coords = new Coordinates(4, 2);
        try {
            boardBuilder.setInhabitant(coords, new Player(null, "", 0));
        } catch (InvalidTileCoordsException | TileAlreadyInhabitedException e) {
            fail();
        }
        try {
            boardBuilder.setInhabitant(coords, new Player(null, "", 1));
            fail();
        } catch (InvalidTileCoordsException e) {
            fail();
        } catch (TileAlreadyInhabitedException e) {
            //OK to be there
        }
    }

    @Test
    public void setTileCost() {
        Coordinates coords = new Coordinates(4, 2);
        try {
            boardBuilder.setTileCost(5, coords);
        } catch (CostAlreadyAssignedException e) {
            fail();
        }
        try {
            boardBuilder.setTileCost(6, coords);
            fail();
        } catch (CostAlreadyAssignedException e) {
            //OK to be there
        }
    }

    @Test
    public void setMaximumNeighbouringFriendsCount() {
        int maximumNeighbouringFriendsCount = 8;

        try {
            boardBuilder.setMaximumNeighbouringFriendsCount(maximumNeighbouringFriendsCount);
            for (int x = 0; x < boardWidth; x++) {
                for (int y = 0; y < boardHeight; y++) {
                    Coordinates coords = new Coordinates(x, y);
                    TileImplementation tile = boardBuilder.getTileImplementation(coords);
                    assertEquals(maximumNeighbouringFriendsCount, tile.getMaximumNeighbouringFriendsCount());
                }
            }
        } catch (MaximumNeighbouringFriendsCountAssignedException e) {
            fail();
        }
        try {
            boardBuilder.setMaximumNeighbouringFriendsCount(maximumNeighbouringFriendsCount + 1);
            fail();
        } catch (MaximumNeighbouringFriendsCountAssignedException e) {
            //OK to be there
        }
    }
}
