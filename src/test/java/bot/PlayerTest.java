package bot;

import javafx.scene.paint.Color;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlayerTest {

    @Test
    public void getBlackColor() {
        Player player = new Player(Color.BLACK, "abc", 2);
        Color color = player.getColor();
        assertEquals(Color.BLACK, color);
    }

    @Test
    public void getBlueColor() {
        Player player = new Player(Color.BLUE, "abc", 2);
        Color color = player.getColor();
        assertEquals(Color.BLUE, color);
    }

    @Test
    public void getSmallId() {
        Player player = new Player(Color.BLUE, "abc", 2);
        int id = player.getId();
        assertEquals(2, id);
    }

    @Test
    public void getBigId() {
        Player player = new Player(Color.BLUE, "abc", 400);
        int id = player.getId();
        assertEquals(400, id);
    }

    @Test
    public void getShortName() {
        Player player = new Player(Color.BLUE, "abc", 2);
        String name = player.getName();
        assertEquals("abc", name);
    }

    @Test
    public void getLongName() {
        Player player = new Player(Color.BLUE, "qwertyasdfghzxcvbn", 2);
        String name = player.getName();
        assertEquals("qwertyasdfghzxcvbn", name);
    }

    @Test
    public void getStartRemainingPoints() {
        Player player = new Player(Color.BLUE, "abc", 2);
        int points = player.getRemainingPoints();
        assertEquals(0, points);
    }

    @Test
    public void getStartPointsPerTurn() {
        Player player = new Player(Color.BLUE, "abc", 2);
        int points = player.getPointsPerTurn();
        assertEquals(0, points);
    }

    @Test
    public void getStartStrongholdsNumber() {
        Player player = new Player(Color.BLUE, "abc", 2);
        int strongholds = player.getStrongholdsNumber();
        assertEquals(0, strongholds);
    }

    @Test
    public void makeTurn() {
        Player player = new Player(Color.BLUE, "abc", 2);
        player.makeTurn();
    }

    @Test
    public void makeTurnWithStrategy() {
        Player player = new Player(Color.BLUE, "abc", 2);
        PlayerStrategy playerStrategy = mock(PlayerStrategy.class);
        InOrder inOrder = inOrder(playerStrategy);

        player.setPlayerStrategy(playerStrategy);
        player.makeTurn();

        inOrder.verify(playerStrategy).startTurn(player);
        inOrder.verify(playerStrategy).doTurn(player);
        inOrder.verify(playerStrategy).endTurn(player);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void getEmptyPlayerBoard() {
        Player player = new Player(Color.BLUE, "abc", 2);
        PlayerBoard playerBoard = player.getPlayerBoard();
        assertNull(playerBoard);
    }

    @Test
    public void getPlayerBoard() {
        Player player = new Player(Color.BLUE, "abc", 2);
        PlayerBoard playerBoard = mock(PlayerBoard.class);
        player.setPlayerBoard(playerBoard);
        assertSame(playerBoard,player.getPlayerBoard());
    }

    @Test
    public void addPoints(){
        Player player = new Player(Color.BLUE, "abc", 2);
        player.addPoints(10);
        int points = player.getRemainingPoints();
        assertEquals(10,points);
    }

    @Test
    public void subPoints() {
        Player player = new Player(Color.BLUE, "abc", 2);
        player.addPoints(10);
        player.subPoints(4);
        int points = player.getRemainingPoints();
        assertEquals(6,points);
    }

    @Test(expected = NotEnoughPointsException.class)
    public void subMorePoints() {
        Player player = new Player(Color.BLUE, "abc", 2);
        player.subPoints(4);
    }

    @Test
    public void addStronhold() {
        Player player = new Player(Color.BLUE, "abc", 2);
        player.addStronghold();
        player.addStronghold();
        int points = player.getPointsPerTurn();
        assertEquals(20,points);
    }

    @Test
    public void removeStronhold() {
        Player player = new Player(Color.BLUE, "abc", 2);
        player.addStronghold();
        player.addStronghold();
        player.removeStronghold();
        int points = player.getPointsPerTurn();
        assertEquals(10,points);
    }

    @Test(expected = PlayerHaveNoStrongholdsException.class)
    public void removeMoreStronhold() {
        Player player = new Player(Color.BLUE, "abc", 2);
        player.removeStronghold();
    }

    @Test
    public void addOrganism() {
        Player player = new Player(Color.BLUE, "abc", 2);
        PlayerBoard playerBoard = mock(PlayerBoard.class);
        player.setPlayerBoard(playerBoard);
        player.addOrganism();
        player.addOrganism();
        int points = player.getPointsPerTurn();
        assertEquals(2,points);
    }

    @Test
    public void removeOrganism() {
        Player player = new Player(Color.BLUE, "abc", 2);
        PlayerBoard playerBoard = mock(PlayerBoard.class);
        player.setPlayerBoard(playerBoard);
        player.addOrganism();
        player.removeOrganism();
        int points = player.getPointsPerTurn();
        assertEquals(0,points);
    }

    @Test
    public void isAlive() {
        Player player = new Player(Color.BLUE, "abc", 2);
        player.addStronghold();
        assertTrue(player.isAlive());
    }

    @Test
    public void isDead() {
        Player player = new Player(Color.BLUE, "abc", 2);
        player.addStronghold();
        player.removeStronghold();
        assertFalse(player.isAlive());
    }

    @Test
    public void getVisibleRange() {
        Player player = new Player(Color.BLUE, "abc", 2);
        assertEquals(5,player.getVisibleRange());
    }

    // todo chceck notify observers from add/remove stronghold/organism/points
}