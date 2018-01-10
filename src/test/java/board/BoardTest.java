package board;

import bot.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void markAndClear() throws Exception{
        // SETUP
        Coordinates strongholdCoord = new Coordinates(0, 1);
        Coordinates organismToRemoveCoord = new Coordinates(4, 5);
        Coordinates[] wellPlacedTiles = {new Coordinates(1, 1), new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3)};
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder(5,6 ).markAsStronghold(strongholdCoord).setInhabitant(strongholdCoord, new Player(null, "", 0)).setInhabitant(organismToRemoveCoord, new Player(null, "", 0));

        for (Coordinates wellPlacedTile : wellPlacedTiles) {
            boardBuilder.setInhabitant(wellPlacedTile, new Player(null, "", 0));
        }

        Board board = boardBuilder.build();

        // EXECUTE
        board.markAndClear();

        // VERIFY
        assertNull(board.getTile(organismToRemoveCoord).getInhabitant());
        for(Coordinates wellPlacedTile: wellPlacedTiles){
            assertNotNull(board.getTile(wellPlacedTile));
        }
    }

    @Test
    public void getTile() {
        Board board = new Board.BoardBuilder(5,6).build();
        try {
            Coordinates coords = new Coordinates(4, 3);
            TileImplementation tile = (TileImplementation) board.getTile(coords);
            assertEquals(coords.getX(), tile.getCoords().getX());
            assertEquals(coords.getY(), tile.getCoords().getY());

        } catch (InvalidTileCoordsException e) {
            fail();
        }

        Coordinates invalidCoords = new Coordinates(5, 5);
        try {
            TileImplementation tile = (TileImplementation) board.getTile(invalidCoords);
            fail();
        } catch (InvalidTileCoordsException e) {
            // It's OK to be here.
        }
    }
}
