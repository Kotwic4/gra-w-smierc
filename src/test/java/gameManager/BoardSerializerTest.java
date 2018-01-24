package gameManager;

import board.Board;
import board.Coordinates;
import board.Tile;
import org.junit.Test;
import org.mockito.Mockito;
import util.BoardHelper;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Michał Pawłowicz on 06.01.18.
 */
public class BoardSerializerTest {
    private final static String[] board1 = {
            "width=5",
            "height=5",
            "[1,1] [2,0] [3,0] [4,0] [5,0]",
            "[6,0] [7,0] [8,0] [9,0] [10,0]",
            "[11,0] [12,0] [13,0] [14,0] [15,0]",
            "[16,0] [17,0] [18,0] [19,0] [20,0]",
            "[21,0] [22,0] [23,0] [24,0] [25,0]",
            null
    };

    @Test
    public void loadTest(){
        BufferedReader br;
        br = mock(BufferedReader.class);
        try {
            Mockito.when(br.readLine()).thenReturn(
                    board1[0], board1[1],
                    board1[2], board1[3],
                    board1[4], board1[5],
                    board1[6], board1[7]
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder(5,5,new BoardHelper<>());


        int tmp = 1;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                boardBuilder.setTileCost(tmp++, new Coordinates(i,j));
            }
        }
        boardBuilder.markAsStronghold(new Coordinates(0,0));
        Board boardExpected = boardBuilder.build();
        Board boardResult = null;
        try {
            boardResult = BoardSerializer.load(br).build();
        } catch (BoardSerializer.MalformedFileException e) {
            e.printStackTrace();
        }
        assertEquals(boardExpected,boardResult);
    }

    @Test
    public void loadTestIncorrect() {
        Board boardExpected = null;
        BufferedReader br = null;

        br = mock(BufferedReader.class);
        try {
            Mockito.when(br.readLine()).thenReturn(
                    board1[0], board1[1],
                    board1[2], board1[3],
                    board1[4], board1[5],
                    board1[6], board1[7]
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder(5,5, new BoardHelper<>());
        boardBuilder.markAsStronghold(new Coordinates(0,0));

        int tmp = 1;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                boardBuilder.setTileCost(tmp++, new Coordinates(i,j));
            }
        }
        boardExpected = boardBuilder.build();
        Board boardResult = null;
        try {
            boardResult = BoardSerializer.load(br).build();
        } catch (BoardSerializer.MalformedFileException e) {
            e.printStackTrace();
        }
        assertEquals(boardExpected,boardResult);
    }
}
