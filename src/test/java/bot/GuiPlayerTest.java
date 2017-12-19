package bot;

import board.Board;
import gui.PlayerController;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class GuiPlayerTest {

    @Test
    public void doTurn() {
        PlayerController playerController = mock(PlayerController.class);
        GuiPlayer player = new GuiPlayer(null, null, 0, (PlayerBoard) null, playerController);
        player.doTurn();
        verify(playerController).doGuiTurn(player);
        verifyNoMoreInteractions(playerController);
    }

    @Test
    public void constructor() {
        Board board = mock(Board.class);
        when(board.getHeight()).thenReturn(0);
        when(board.getWidth()).thenReturn(0);
        new GuiPlayer(null, null, 0, board, null);
        verify(board).getHeight();
        verify(board).getWidth();
        verifyNoMoreInteractions(board);
    }
}