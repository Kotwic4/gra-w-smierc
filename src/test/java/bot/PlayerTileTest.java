package bot;

import board.Board;
import board.Tile;
import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerTileTest {

    @Mock
    private Player player;
    @Mock
    private Tile tile;
    @Mock
    private Board board;

    private PlayerTile playerTile;

    @Before
    public void setUp() {
        player = mock(Player.class);
        tile = mock(Tile.class);
        board = mock(Board.class);
        playerTile = new PlayerTile(tile, player, board);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(player);
        verifyNoMoreInteractions(tile);
    }

    @Test
    public void isAccessibleInvisible() {
        playerTile.setVisible(false);
        assertFalse(playerTile.isAccessible());
    }

    @Test
    public void isAccessibleUninhabitable() {
        playerTile.setVisible(true);
        when(tile.canInhabit(player)).thenReturn(false);
        assertFalse(playerTile.isAccessible());
        verify(tile).canInhabit(player);
    }

    @Test
    public void isAccessibleToExpensive() {
        playerTile.setVisible(true);
        when(tile.canInhabit(player)).thenReturn(true);
        when(tile.getCost()).thenReturn(10);
        when(player.getRemainingPoints()).thenReturn(5);
        assertFalse(playerTile.isAccessible());
        verify(tile).canInhabit(player);
        verify(tile).getCost();
        verify(player).getRemainingPoints();
    }

    @Test
    public void isAccessibleJustInEnough() {
        playerTile.setVisible(true);
        when(tile.canInhabit(player)).thenReturn(true);
        when(tile.getCost()).thenReturn(10);
        when(player.getRemainingPoints()).thenReturn(10);
        assertTrue(playerTile.isAccessible());
        verify(tile).canInhabit(player);
        verify(tile).getCost();
        verify(player).getRemainingPoints();
    }

    @Test
    public void isAccessibleMoreThenEnough() {
        playerTile.setVisible(true);
        when(tile.canInhabit(player)).thenReturn(true);
        when(tile.getCost()).thenReturn(10);
        when(player.getRemainingPoints()).thenReturn(20);
        assertTrue(playerTile.isAccessible());
        verify(tile).canInhabit(player);
        verify(tile).getCost();
        verify(player).getRemainingPoints();
    }

    @Test
    public void isVisible() {
        playerTile.setVisible(true);
        assertTrue(playerTile.isVisible());
    }

    @Test
    public void isInVisible() {
        playerTile.setVisible(false);
        assertFalse(playerTile.isVisible());
    }

    @Test
    public void inhabitInvisible() {
        playerTile.setVisible(false);
        playerTile.inhabit();
    }

    @Test
    public void inhabitUnAccessible() {
        playerTile.setVisible(true);
        when(tile.canInhabit(player)).thenReturn(false);
        playerTile.inhabit();
        verify(tile).canInhabit(player);
    }

    @Test
    public void inhabitAccessible() {
        playerTile.setVisible(true);
        when(tile.canInhabit(player)).thenReturn(true);
        when(tile.getCost()).thenReturn(10);
        when(player.getRemainingPoints()).thenReturn(20);
        playerTile.inhabit();
        verify(tile).canInhabit(player);
        verify(tile, times(2)).getCost();
        verify(player).getRemainingPoints();
        verify(tile).inhabit(player);
        verify(player).subPoints(10);
    }

    @Test
    public void getColorInvisible() {
        playerTile.setVisible(false);
        assertFalse(playerTile.getColor().isPresent());
    }

    @Test
    public void getColorVisibleUninhabited() {
        playerTile.setVisible(true);
        when(tile.getPlayer()).thenReturn(Optional.empty());
        assertFalse(playerTile.getColor().isPresent());
        verify(tile).getPlayer();
    }

    @Test
    public void getColorVisibleBlackInhabited() throws Exception {
        playerTile.setVisible(true);
        when(tile.getPlayer()).thenReturn(Optional.of(player));
        when(player.getColor()).thenReturn(Color.BLACK);
        assertEquals(playerTile.getColor().orElseThrow(Exception::new), Color.BLACK);
        verify(tile).getPlayer();
        verify(player).getColor();
    }

    @Test
    public void isStrongholdInvisible() {
        playerTile.setVisible(false);
        assertFalse(playerTile.isStronghold().isPresent());
    }

    @Test
    public void isStrongholdVisible() throws Exception {
        playerTile.setVisible(true);
        when(tile.isStronghold()).thenReturn(true);
        assertTrue(playerTile.isStronghold().orElseThrow(Exception::new));
        verify(tile).isStronghold();
    }

    @Test
    public void getCostInvisible() {
        playerTile.setVisible(false);
        assertFalse(playerTile.getCost().isPresent());
    }

    @Test
    public void getCostVisible() throws Exception {
        playerTile.setVisible(true);
        Integer cost = 10;
        when(tile.getCost()).thenReturn(cost);
        assertEquals(playerTile.getCost().orElseThrow(Exception::new), cost);
        verify(tile).getCost();
    }

}