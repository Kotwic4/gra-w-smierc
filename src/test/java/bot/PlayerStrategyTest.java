package bot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class PlayerStrategyTest {

    @Mock
    private Player player;
    @Mock
    private PlayerBoard playerBoard;

    private PlayerStrategy playerStrategy;
    private InOrder inOrder;

    @Before
    public void setUp() {
        player = mock(Player.class);
        playerBoard = mock(PlayerBoard.class);
        playerStrategy = new PlayerStrategy() {
            @Override
            protected void doTurn(Player player) {
            }
        };
        inOrder = inOrder(playerBoard, player);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(playerBoard, player);
    }

    @Test
    public void startTurn() {
        when(player.getPointsPerTurn()).thenReturn(10);
        when(player.getPlayerBoard()).thenReturn(playerBoard);
        playerStrategy.startTurn(player);
        inOrder.verify(player).getPointsPerTurn();
        inOrder.verify(player).addPoints(10);
    }

    @Test
    public void endTurn() {
        playerStrategy.endTurn(player);
    }
}