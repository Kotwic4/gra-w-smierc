package bot;

import gui.TurnCommunicator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class HeadlessPlayerTest {

    private PlayerBoard playerBoard;
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
    public void setUp() throws Exception {
        playerBoard = mock(PlayerBoard.class);
        turnCommunicator = mock(TurnCommunicator.class);
        headlessPlayer = new TestHeadlessPlayer(playerBoard, turnCommunicator);
        inOrder = inOrder(playerBoard, turnCommunicator);
    }


    @Test
    public void startTurn() {
        headlessPlayer.startTurn();
        inOrder.verify(playerBoard, times(1)).updateVision();
        inOrder.verify(turnCommunicator, times(1)).startHeadlessTurn(headlessPlayer);
        verifyNoMoreInteractions(playerBoard);
        verifyNoMoreInteractions(turnCommunicator);
    }

    @Test
    public void endTurn() {
        headlessPlayer.endTurn();
        verify(turnCommunicator, times(1)).endHeadlessTurn(headlessPlayer);
        verifyNoMoreInteractions(playerBoard);
        verifyNoMoreInteractions(turnCommunicator);
    }

    @Test
    public void makeTurn() {
        headlessPlayer.makeTurn();
        inOrder.verify(playerBoard, times(1)).updateVision();
        inOrder.verify(turnCommunicator, times(1)).startHeadlessTurn(headlessPlayer);
        inOrder.verify(playerBoard, times(1)).getAccessibleTiles();
        inOrder.verify(turnCommunicator, times(1)).endHeadlessTurn(headlessPlayer);
        verifyNoMoreInteractions(playerBoard);
        verifyNoMoreInteractions(turnCommunicator);
    }
}