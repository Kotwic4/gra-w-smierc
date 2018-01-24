package util;

import board.Coordinates;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class BoardHelperTest {
    private BoardHelper<SimpleTile> boardHelper;
    private SimpleTile[][] simpleTiles;
    private final int X = 3;
    private final int Y = 4;

    @Before
    public void setUp() {
        boardHelper = new BoardHelper<>();
        simpleTiles = new SimpleTile[X][Y];
        for(int i = 0; i < X; i++) {
            for(int j = 0; j < Y; j++) {
                simpleTiles[i][j] = new SimpleTile();
            }
        }
    }

    @Test
    public void setNeighbours() {
        boardHelper.setNeighbours(simpleTiles, SimpleTile::setNeighbours);

        List<SimpleTile> upperLeftsNeighboursExpected = new LinkedList<SimpleTile>();
        upperLeftsNeighboursExpected.add(simpleTiles[1][Y-1]);
        upperLeftsNeighboursExpected.add(simpleTiles[1][Y-2]);
        upperLeftsNeighboursExpected.add(simpleTiles[0][Y-2]);

        List<SimpleTile> upperLeftsNeighbours = simpleTiles[0][Y-1].neighbours;
        if(upperLeftsNeighbours.size() != upperLeftsNeighboursExpected.size()) fail();
        for(SimpleTile simpleTile: upperLeftsNeighboursExpected) {
            if(!upperLeftsNeighbours.contains(simpleTile)){
                fail();
            }
        }
    }

    @Test
    public void getNeighbours() throws Exception {
        List<SimpleTile> upperLeftsNeighboursExpected = new LinkedList<SimpleTile>();
        upperLeftsNeighboursExpected.add(simpleTiles[1][Y-1]);
        upperLeftsNeighboursExpected.add(simpleTiles[1][Y-2]);
        upperLeftsNeighboursExpected.add(simpleTiles[0][Y-2]);

        List<SimpleTile> upperLeftsNeighbours = boardHelper.getNeighbours(simpleTiles, new Coordinates(0,Y-1));
        if(upperLeftsNeighbours.size() != upperLeftsNeighboursExpected.size()) fail();
        for(SimpleTile simpleTile: upperLeftsNeighboursExpected) {
            if(!upperLeftsNeighbours.contains(simpleTile)){
                fail();
            }
        }

        List<SimpleTile> innersNeighboursExpected = new LinkedList<SimpleTile>();
        // Neighbours of (1,2)
        int posX = 1;
        int posY = 2;
        innersNeighboursExpected.add(simpleTiles[posX][posY+1]);
        innersNeighboursExpected.add(simpleTiles[posX+1][posY+1]);
        innersNeighboursExpected.add(simpleTiles[posX+1][posY]);
        innersNeighboursExpected.add(simpleTiles[posX+1][posY-1]);
        innersNeighboursExpected.add(simpleTiles[posX][posY-1]);
        innersNeighboursExpected.add(simpleTiles[posX-1][posY-1]);
        innersNeighboursExpected.add(simpleTiles[posX-1][posY]);
        innersNeighboursExpected.add(simpleTiles[posX-1][posY+1]);

        List<SimpleTile> innersNeighbours = boardHelper.getNeighbours(simpleTiles, new Coordinates(posX, posY));
        if(innersNeighbours.size() != innersNeighboursExpected.size()) fail();
        for(SimpleTile simpleTile: innersNeighboursExpected) {
            if(!innersNeighbours.contains(simpleTile)){
                fail();
            }
        }
    }

    @Test
    public void getTile() throws Exception {
        // Invalid:
        Optional<SimpleTile> upperLeft = boardHelper.getTile(simpleTiles, new Coordinates(-1,Y));
        assertEquals(Optional.empty(), upperLeft);
        Optional<SimpleTile> upperRight = boardHelper.getTile(simpleTiles, new Coordinates(X, Y));
        assertEquals(Optional.empty(), upperRight);
        Optional<SimpleTile> lowerRight = boardHelper.getTile(simpleTiles, new Coordinates(X, -1));
        assertEquals(Optional.empty(), lowerRight);
        Optional<SimpleTile> lowerLeft = boardHelper.getTile(simpleTiles, new Coordinates(-1,-1));
        assertEquals(Optional.empty(), lowerLeft);
        Optional<SimpleTile> top = boardHelper.getTile(simpleTiles, new Coordinates(X-1,Y));
        assertEquals(Optional.empty(), top);
        Optional<SimpleTile> right = boardHelper.getTile(simpleTiles, new Coordinates(X,Y-1));
        assertEquals(Optional.empty(), right);
        Optional<SimpleTile> bottom = boardHelper.getTile(simpleTiles, new Coordinates(X-1,-1));
        assertEquals(Optional.empty(), bottom);
        Optional<SimpleTile> left = boardHelper.getTile(simpleTiles, new Coordinates(-1,Y-1));
        assertEquals(Optional.empty(), left);

        // Valid:
        Optional<SimpleTile> correct = boardHelper.getTile(simpleTiles, new Coordinates(X-1,Y-1));
        assertEquals(Optional.of(simpleTiles[X-1][Y-1]), correct);

    }

    class SimpleTile {
        private List<SimpleTile> neighbours;

        public void setNeighbours(List<SimpleTile> neighbours) {
            this.neighbours = neighbours;
        }
    }
}
