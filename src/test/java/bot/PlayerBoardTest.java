package bot;

import board.Board;
import board.Coordinates;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlayerBoardTest {

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

    @Test
    public void constructor() {
        Board board = mock(Board.class);
        Player player = mock(Player.class);
        when(board.getWidth()).thenReturn(5);
        when(board.getHeight()).thenReturn(4);
        when(board.getTile(any(Coordinates.class))).thenReturn(null);
        new PlayerBoard(board,player);
        verify(board).getWidth();
        verify(board).getHeight();
        verify(board,times(20)).getTile(any(Coordinates.class));
        verifyNoMoreInteractions(board,player);
    }
}