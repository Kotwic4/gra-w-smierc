package bot;

import board.Board;
import gui.TurnCommunicator;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public class RandomBot extends HeadlessPlayer {

    private Random randomGenerator;

    RandomBot(Color color, String name, int id, PlayerBoard board, TurnCommunicator turnCommunicator,
              Random randomGenerator) {
        super(color, name, id, board, turnCommunicator);
        this.randomGenerator = randomGenerator;
    }

    public RandomBot(Color color, String name, int id, Board board, TurnCommunicator turnCommunicator) {
        super(color, name, id, board, turnCommunicator);
        this.randomGenerator = new Random();
    }

    @Override
    protected void doTurn() {
        List<PlayerTile> accessibleTiles = getPlayerBoard().getAccessibleTiles();
        while (!accessibleTiles.isEmpty()) {
            int index = randomGenerator.nextInt(accessibleTiles.size());
            PlayerTile tile = accessibleTiles.get(index);
            tile.inhabit();
            accessibleTiles = getPlayerBoard().getAccessibleTiles();
        }
    }

}
