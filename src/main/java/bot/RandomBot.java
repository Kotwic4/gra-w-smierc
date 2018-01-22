package bot;

import gui.TurnCommunicator;

import java.util.List;
import java.util.Random;

public class RandomBot extends BotStrategy {

    private Random randomGenerator;

    public RandomBot(TurnCommunicator turnCommunicator, Random randomGenerator) {
        super(turnCommunicator);
        this.randomGenerator = randomGenerator;
    }

    @Override
    protected PlayerTile chooseTile(List<PlayerTile> accessibleTiles) {
        int index = randomGenerator.nextInt(accessibleTiles.size());
        return accessibleTiles.get(index);
    }

}
