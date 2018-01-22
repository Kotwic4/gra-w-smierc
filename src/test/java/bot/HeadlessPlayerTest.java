package bot;

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
    @Mock
    private Player player;

    private HeadlessPlayer headlessPlayer;
    private InOrder inOrder;

    @Before
    public void setUp() {
        playerBoard = mock(PlayerBoard.class);
        turnCommunicator = mock(TurnCommunicator.class);
        player = mock(Player.class);
        headlessPlayer = new HeadlessPlayer(turnCommunicator) {
            @Override
            protected void doTurn(Player player) {
                player.getPlayerBoard().getAccessibleTiles();
            }
        };
        inOrder = inOrder(playerBoard, turnCommunicator, player);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(playerBoard, turnCommunicator, player);
    }

    @Test
    public void startTurn() {
        when(player.getPointsPerTurn()).thenReturn(10);
        when(player.getPlayerBoard()).thenReturn(playerBoard);
        headlessPlayer.startTurn(player);
        inOrder.verify(turnCommunicator).startHeadlessTurn(player);
        inOrder.verify(player).getPointsPerTurn();
        inOrder.verify(player).addPoints(10);
    }

    @Test
    public void endTurn() {
        headlessPlayer.endTurn(player);
        verify(turnCommunicator).endHeadlessTurn(player);
    }


}