package bot;

import board.Board;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RandomBotTest {

    @Mock
    private PlayerBoard playerBoard;
    @Mock
    private Random random;
    private RandomBot randomBot;

    @Before
    public void setUp() {
        playerBoard = mock(PlayerBoard.class);
        random = mock(Random.class);
        randomBot = new RandomBot(null,null,0,playerBoard,null,random);
    }

    @After
    public void tearDown(){
        verifyNoMoreInteractions(random,playerBoard);
    }

    @Test
    public void doTurnEmpty() {
        when(playerBoard.getAccessibleTiles()).thenReturn(Collections.emptyList());
        randomBot.doTurn();
        verify(playerBoard).getAccessibleTiles();
    }

    @Test
    public void doTurnSimple() {
        PlayerTile aTile = mock(PlayerTile.class);
        PlayerTile bTile = mock(PlayerTile.class);
        when(playerBoard.getAccessibleTiles()).thenReturn(Arrays.asList(aTile,bTile)).
                thenReturn(Collections.emptyList());
        when(random.nextInt(2)).thenReturn(0);
        randomBot.doTurn();
        verify(playerBoard,times(2)).getAccessibleTiles();
        verify(random).nextInt(2);
        verify(aTile).inhabit();
        verifyNoMoreInteractions(aTile,bTile);
    }

    @Test
    public void doTurnMoreComplex() {
        PlayerTile aTile = mock(PlayerTile.class);
        PlayerTile bTile = mock(PlayerTile.class);
        PlayerTile cTile = mock(PlayerTile.class);
        InOrder inOrder = inOrder(playerBoard,random,aTile,bTile,cTile);
        when(playerBoard.getAccessibleTiles())
                .thenReturn(Arrays.asList(aTile,bTile))
                .thenReturn(Arrays.asList(aTile,bTile,cTile))
                .thenReturn(Collections.singletonList(bTile))
                .thenReturn(Collections.emptyList());
        when(random.nextInt(2)).thenReturn(0);
        when(random.nextInt(3)).thenReturn(1);
        when(random.nextInt(1)).thenReturn(0);
        randomBot.doTurn();
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
        verifyNoMoreInteractions(aTile,bTile,cTile);
    }

    @Test
    public void constructor() {
        Board board = mock(Board.class);
        when(board.getHeight()).thenReturn(0);
        when(board.getWidth()).thenReturn(0);
        new RandomBot(null,null,0,board,null);
        verify(board).getHeight();
        verify(board).getWidth();
        verifyNoMoreInteractions(board);
    }
}