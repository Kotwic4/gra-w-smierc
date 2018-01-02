package board;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paweł Taborowski on 18.12.17.
 */
public class BoardTest {

    @Test
    public void markAndClear() throws Exception{
        // SETUP
        BoardBuilder boardBuilder = new BoardBuilder(5, 6);

        Coordinates strongholdCoord = new Coordinates(0, 1);
        boardBuilder.markAsStronghold(strongholdCoord);
        Organism strongholdOrganism = new Organism(0);
        boardBuilder.getTileImplementation(strongholdCoord).uncheckedSetIntabitant(strongholdOrganism);

        Coordinates organismToRemoveCoord = new Coordinates(4, 5);
        boardBuilder.getTileImplementation(organismToRemoveCoord).uncheckedSetIntabitant(new Organism(0));

        Coordinates[] wellPlacedTiles = {new Coordinates(1, 1), new Coordinates(2, 1), new Coordinates(2, 2), new Coordinates(2, 3)};

        for (Coordinates wellPlacedTile : wellPlacedTiles) {
            boardBuilder.getTileImplementation(wellPlacedTile).uncheckedSetIntabitant(new Organism(0));
        }

        Board board = boardBuilder.generateBoard();

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
        Board board = new BoardBuilder(5,6).generateBoard();
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
