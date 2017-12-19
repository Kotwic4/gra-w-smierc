package bot;

import board.Board;
import gui.TurnCommunicator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class HeadlessPlayerTest {

    @Mock
    private PlayerBoard playerBoard;
    @Mock
    private TurnCommunicator turnCommunicator;
    private HeadlessPlayer headlessPlayer;
    private InOrder inOrder;

    private class TestHeadlessPlayer extends HeadlessPlayer {

        TestHeadlessPlayer(PlayerBoard playerBoard, TurnCommunicator turnCommunicator) {
            super(null, null, 0, playerBoard, turnCommunicator);
        }

        @Override
        protected void doTurn() {
            playerBoard.getAccessibleTiles();
        }
    }

    @Before
    public void setUp() {
        playerBoard = mock(PlayerBoard.class);
        turnCommunicator = mock(TurnCommunicator.class);
        headlessPlayer = new TestHeadlessPlayer(playerBoard, turnCommunicator);
        inOrder = inOrder(playerBoard, turnCommunicator);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(playerBoard);
        verifyNoMoreInteractions(turnCommunicator);
    }

    @Test
    public void startTurn() {
        headlessPlayer.startTurn();
        inOrder.verify(playerBoard).updateVision();
        inOrder.verify(turnCommunicator).startHeadlessTurn(headlessPlayer);

    }

    @Test
    public void endTurn() {
        headlessPlayer.endTurn();
        verify(turnCommunicator).endHeadlessTurn(headlessPlayer);
    }

    @Test
    public void makeTurn() {
        headlessPlayer.makeTurn();
        inOrder.verify(playerBoard).updateVision();
        inOrder.verify(turnCommunicator).startHeadlessTurn(headlessPlayer);
        inOrder.verify(playerBoard).getAccessibleTiles();
        inOrder.verify(turnCommunicator).endHeadlessTurn(headlessPlayer);
    }

    @Test
    public void constructor() {
        Board board = mock(Board.class);
        when(board.getHeight()).thenReturn(0);
        when(board.getWidth()).thenReturn(0);
        new HeadlessPlayer(null, null, 0,board,null) {
            @Override
            protected void doTurn() {

            }
        };
        verify(board).getHeight();
        verify(board).getWidth();
        verifyNoMoreInteractions(board);
    }
}