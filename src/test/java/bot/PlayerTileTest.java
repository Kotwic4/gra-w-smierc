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
        verify(player).addObserver(playerTile);
        verify(tile).registerObserver(playerTile);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(player);
        verifyNoMoreInteractions(tile);
        verifyNoMoreInteractions(board);
    }

    @Test
    public void isAccessibleInvisible() {
        //todo
//        playerTile.setVisibleRange(false);
//        assertFalse(playerTile.isAccessible());
    }

    @Test
    public void isAccessibleUninhabitable() {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.canInhabit(player)).thenReturn(false);
//        assertFalse(playerTile.isAccessible());
//        verify(tile).canInhabit(player);
    }

    @Test
    public void isAccessibleToExpensive() {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.canInhabit(player)).thenReturn(true);
//        when(tile.getCost()).thenReturn(10);
//        when(player.getRemainingPoints()).thenReturn(5);
//        assertFalse(playerTile.isAccessible());
//        verify(tile).canInhabit(player);
//        verify(tile).getCost();
//        verify(player).getRemainingPoints();
    }

    @Test
    public void isAccessibleJustInEnough() {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.canInhabit(player)).thenReturn(true);
//        when(tile.getCost()).thenReturn(10);
//        when(player.getRemainingPoints()).thenReturn(10);
//        assertTrue(playerTile.isAccessible());
//        verify(tile).canInhabit(player);
//        verify(tile).getCost();
//        verify(player).getRemainingPoints();
    }

    @Test
    public void isAccessibleMoreThenEnough() {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.canInhabit(player)).thenReturn(true);
//        when(tile.getCost()).thenReturn(10);
//        when(player.getRemainingPoints()).thenReturn(20);
//        assertTrue(playerTile.isAccessible());
//        verify(tile).canInhabit(player);
//        verify(tile).getCost();
//        verify(player).getRemainingPoints();
    }

    @Test
    public void isVisible() {
        //todo
//        playerTile.setVisibleRange(true);
//        assertTrue(playerTile.isVisible());
    }

    @Test
    public void isInVisible() {
        //todo
//        playerTile.setVisibleRange(false);
//        assertFalse(playerTile.isVisible());
    }

    @Test
    public void inhabitInvisible() {
        //todo
//        playerTile.setVisibleRange(false);
//        playerTile.inhabit();
    }

    @Test
    public void inhabitUnAccessible() {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.canInhabit(player)).thenReturn(false);
//        playerTile.inhabit();
//        verify(tile).canInhabit(player);
    }

    @Test
    public void inhabitAccessible() {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.canInhabit(player)).thenReturn(true);
//        when(tile.getCost()).thenReturn(10);
//        when(player.getRemainingPoints()).thenReturn(20);
//        playerTile.inhabit();
//        verify(tile).canInhabit(player);
//        verify(tile, times(2)).getCost();
//        verify(player).getRemainingPoints();
//        verify(tile).inhabit(player);
//        verify(player).subPoints(10);
    }

    @Test
    public void getColorInvisible() {
        //todo
//        playerTile.setVisibleRange(false);
//        assertFalse(playerTile.getColor().isPresent());
    }

    @Test
    public void getColorVisibleUninhabited() {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.getPlayer()).thenReturn(Optional.empty());
//        assertFalse(playerTile.getColor().isPresent());
//        verify(tile).getPlayer();
    }

    @Test
    public void getColorVisibleBlackInhabited() throws Exception {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.getPlayer()).thenReturn(Optional.of(player));
//        when(player.getColor()).thenReturn(Color.BLACK);
//        assertEquals(playerTile.getColor().orElseThrow(Exception::new), Color.BLACK);
//        verify(tile).getPlayer();
//        verify(player).getColor();
    }

    @Test
    public void isStrongholdInvisible() {
        //todo
//        playerTile.setVisibleRange(false);
//        assertFalse(playerTile.isStronghold().isPresent());
    }

    @Test
    public void isStrongholdVisible() throws Exception {
        //todo
//        playerTile.setVisibleRange(true);
//        when(tile.isStronghold()).thenReturn(true);
//        assertTrue(playerTile.isStronghold().orElseThrow(Exception::new));
//        verify(tile).isStronghold();
    }

    @Test
    public void getCostInvisible() {
        //todo
//        playerTile.setVisibleRange(false);
//        assertFalse(playerTile.getCost().isPresent());
    }

    @Test
    public void getCostVisible() throws Exception {
        //todo
//        playerTile.setVisibleRange(true);
//        Integer cost = 10;
//        when(tile.getCost()).thenReturn(cost);
//        assertEquals(playerTile.getCost().orElseThrow(Exception::new), cost);
//        verify(tile).getCost();
    }

    @Test
    public void getPlayer() throws Exception {
        //todo
    }
    @Test
    public void setNeighbours() throws Exception {
        //todo
    }

    @Test
    public void updateTileInformation() throws Exception {
        when(tile.getPlayer()).thenReturn(Optional.of(player));
        when(player.getVisibleRange()).thenReturn(5);
        when(tile.isStronghold()).thenReturn(true);
        when(tile.getCost()).thenReturn(10);
        when(tile.canInhabit(player)).thenReturn(false);
        playerTile.updateTileInformation();
        verify(tile).getPlayer();
        verify(player).getVisibleRange();
        verify(tile).isStronghold();
        verify(tile).getCost();
        verify(tile).canInhabit(player);
        assertTrue(playerTile.isVisible());
        assertTrue(playerTile.isStronghold().get());
        assertFalse(playerTile.isAccessible());
        assertEquals(5,playerTile.getVisibleRange());
        assertEquals(player, playerTile.getPlayer().get());
        assertEquals(10, playerTile.getCost().get().intValue());
    }

    @Test
    public void updateTile() throws Exception {
        //todo
    }

    @Test
    public void updatePlayer() throws Exception {
        //todo
    }

}