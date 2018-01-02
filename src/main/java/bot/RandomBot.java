package bot;

import board.Board;
import gui.TurnCommunicator;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public class RandomBot extends HeadlessPlayer {

    private Random randomGenerator;

    public RandomBot(TurnCommunicator turnCommunicator, Random randomGenerator) {
        super(turnCommunicator);
        this.randomGenerator = randomGenerator;
    }

    @Override
    protected void doTurn(Player player) {
        List<PlayerTile> accessibleTiles = player.getPlayerBoard().getAccessibleTiles();
        while (!accessibleTiles.isEmpty()) {
            int index = randomGenerator.nextInt(accessibleTiles.size());
            PlayerTile tile = accessibleTiles.get(index);
            tile.inhabit();
            accessibleTiles = player.getPlayerBoard().getAccessibleTiles();
        }
        super.doTurn(player);
    }

}
