package bot;

import gui.TurnCommunicator;

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
        PlayerBoard playerBoard = player.getPlayerBoard();
        List<PlayerTile> accessibleTiles = playerBoard.getAccessibleTiles();
        while (!accessibleTiles.isEmpty()) {
            int index = randomGenerator.nextInt(accessibleTiles.size());
            PlayerTile tile = accessibleTiles.get(index);
            tile.inhabit();
            accessibleTiles = playerBoard.getAccessibleTiles();
        }
    }

}
