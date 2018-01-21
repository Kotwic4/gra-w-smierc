package bot;

import gui.TurnCommunicator;

import java.util.List;

public class SimpleBot extends BotStrategy {

    private static final int NEIGHBOUR_PLAYER = 10;
    private static final int NEIGHBOUR_STRONGHOLD = 100;
    private static final int STRONGHOLD = 1000;
    private static final double NEIGHBOUR_MULTIPLY = 0.1;

    public SimpleBot(TurnCommunicator turnCommunicator) {
        super(turnCommunicator);
    }

    @Override
    protected PlayerTile chooseTile(List<PlayerTile> accessibleTiles){
        PlayerTile chosenTile = null;
        double max = 0;
        for (PlayerTile playerTile : accessibleTiles) {
            double value = getValue(playerTile);
            if (value > max) {
                chosenTile = playerTile;
                max = value;
            }
        }
        return chosenTile;
    }

    protected double getValue(PlayerTile playerTile){
        double value = 0;
        for (PlayerTile neighbour : playerTile.getNeighbours()) {
            int neighbourValue = 0;
            if (neighbour.isVisible()) {
                if (neighbour.getPlayer().isPresent()) {
                    neighbourValue += NEIGHBOUR_PLAYER;
                }
                if (neighbour.isStronghold().get()) {
                    neighbourValue += NEIGHBOUR_STRONGHOLD;
                }
                neighbourValue -= neighbour.getCost().get();
            }
            value += neighbourValue * NEIGHBOUR_MULTIPLY;
        }
        if (playerTile.isStronghold().get()) {
            value += STRONGHOLD;
        }
        value -= playerTile.getCost().get();
        return value;
    }
}
