package bot;

import gui.TurnCommunicator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.mockito.Mockito.*;

public class RandomBotTest {

    @Mock
    private PlayerBoard playerBoard;
    @Mock
    private Random random;
    @Mock
    private Player player;
    @Mock
    private TurnCommunicator turnCommunicator;

    private RandomBot randomBot;

    @Before
    public void setUp() {
        playerBoard = mock(PlayerBoard.class);
        random = mock(Random.class);
        player = mock(Player.class);
        turnCommunicator = mock(TurnCommunicator.class);
        randomBot = new RandomBot(turnCommunicator, random);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(playerBoard, random, player, turnCommunicator);
    }

    @Test
    public void doTurnEmpty() {
        when(player.getPlayerBoard()).thenReturn(playerBoard);
        when(playerBoard.getAccessibleTiles()).thenReturn(Collections.emptyList());
        randomBot.doTurn(player);
        verify(player).getPlayerBoard();
        verify(playerBoard).getAccessibleTiles();
    }

    @Test
    public void doTurnSimple() {
        PlayerTile aTile = mock(PlayerTile.class);
        PlayerTile bTile = mock(PlayerTile.class);
        when(player.getPlayerBoard()).thenReturn(playerBoard);
        when(playerBoard.getAccessibleTiles()).thenReturn(Arrays.asList(aTile, bTile)).
                thenReturn(Collections.emptyList());
        when(random.nextInt(2)).thenReturn(0);
        randomBot.doTurn(player);
        verify(player).getPlayerBoard();
        verify(playerBoard, times(2)).getAccessibleTiles();
        verify(random).nextInt(2);
        verify(aTile).inhabit();
        verifyNoMoreInteractions(aTile, bTile);
    }

    @Test
    public void doTurnMoreComplex() {
        PlayerTile aTile = mock(PlayerTile.class);
        PlayerTile bTile = mock(PlayerTile.class);
        PlayerTile cTile = mock(PlayerTile.class);
        InOrder inOrder = inOrder(playerBoard, random, player, turnCommunicator, aTile, bTile, cTile);
        when(player.getPlayerBoard()).thenReturn(playerBoard);
        when(playerBoard.getAccessibleTiles())
                .thenReturn(Arrays.asList(aTile, bTile))
                .thenReturn(Arrays.asList(aTile, bTile, cTile))
                .thenReturn(Collections.singletonList(bTile))
                .thenReturn(Collections.emptyList());
        when(random.nextInt(2)).thenReturn(0);
        when(random.nextInt(3)).thenReturn(1);
        when(random.nextInt(1)).thenReturn(0);
        randomBot.doTurn(player);
        inOrder.verify(player).getPlayerBoard();
        inOrder.verify(playerBoard).getAccessibleTiles();
        inOrder.verify(random).nextInt(2);
        inOrder.verify(aTile).inhabit();
        inOrder.verify(playerBoard).getAccessibleTiles();
        inOrder.verify(random).nextInt(3);
        inOrder.verify(bTile).inhabit();
        inOrder.verify(playerBoard).getAccessibleTiles();
        inOrder.verify(random).nextInt(1);
        inOrder.verify(bTile).inhabit();
        inOrder.verify(playerBoard).getAccessibleTiles();
        verifyNoMoreInteractions(aTile, bTile, cTile);
    }

}