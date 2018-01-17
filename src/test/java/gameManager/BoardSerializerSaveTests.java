package gameManager;

import board.Board;
import board.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by michal on 06.01.18.
 */
public class BoardSerializerSaveTests {

    private static final String resultString = "width=5\n" +
            "height=5\n" +
            "[1,1] [2,0] [3,0] [4,0] [5,0]\n" +
            "[6,0] [7,0] [8,0] [9,0] [10,0]\n" +
            "[11,0] [12,0] [13,0] [14,0] [15,0]\n" +
            "[16,0] [17,0] [18,0] [19,0] [20,0]\n" +
            "[21,0] [22,0] [23,0] [24,0] [25,0]\n";

    @Test
    public void saveTest() {
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder(5,5);;
        int tmp = 1;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                boardBuilder.setTileCost(tmp++,new Coordinates(i,j));
            }
        }
        boardBuilder.markAsStronghold(new Coordinates(0,0));
        Board board = boardBuilder.build();
        String result = BoardSerializer.getSerializedString(board);
        assertEquals(resultString.equals(result), true);
    }
}
