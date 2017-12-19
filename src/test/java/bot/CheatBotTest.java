package bot;

import board.Board;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CheatBotTest {

    @Test
    public void doTurn() {
        CheatBot player = new CheatBot(null, null, 0, (PlayerBoard) null, null);
        player.doTurn();
    }

    @Test
    public void constructor() {
        Board board = mock(Board.class);
        when(board.getHeight()).thenReturn(0);
        when(board.getWidth()).thenReturn(0);
        new CheatBot(null, null, 0, board, null);
        verify(board).getHeight();
        verify(board).getWidth();
        verifyNoMoreInteractions(board);
    }

}