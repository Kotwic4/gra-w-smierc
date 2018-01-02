package bot;

import board.Board;
import gui.TurnCommunicator;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class PlayerBoardTest {


    @Test
    public void createPlayerBoard() {
        //todo
//        Player player = new Player(Color.BLUE, "abc", 2);
//        Board board = mock(Board.class);
//        when(board.getWidth()).thenReturn(2);
//        when(board.getHeight()).thenReturn(3);
//        when(board.getTile(any())).thenReturn(null);
//        InOrder inOrder = inOrder(board);
//
//        player.createPlayerBoard(board);
//        PlayerBoard playerBoard = player.getPlayerBoard();
//
//        assertNotNull(playerBoard);
//        assertEquals(2, playerBoard.getWidth());
//        assertEquals(3, playerBoard.getHeight());
//        inOrder.verify(board).getWidth();
//        inOrder.verify(board).getHeight();
//        inOrder.verify(board, times(2 * 3)).getTile(any());
//        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void getGuiTile() {
        //todo
    }

    @Test
    public void getGuiTiles() {
        //todo
    }

    @Test
    public void getWidth() {
        //todo
    }

    @Test
    public void getHeight() {
        //todo
    }

    @Test
    public void getAccessibleTiles() {
        //todo
    }

    @Test
    public void updateVision() {
        //todo
    }
}