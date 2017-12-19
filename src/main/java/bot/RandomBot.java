package bot;

import board.Board;
import gui.BotGui;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public class RandomBot extends Bot {

    private Random randomGenerator;

    RandomBot(Color color, String name, int id, Board board, BotGui botGui, Random randomGenerator) {
        super(color, name, id, board, botGui);
        this.randomGenerator = randomGenerator;
    }

    public RandomBot(Color color, String name, int id, Board board, BotGui botGui) {
        this(color, name, id, board, botGui, new Random());
    }

    @Override
    protected void doTurn() {
        List<GuiTile> accessibleTiles = getPlayerBoard().getAccessibleTiles();
        while (!accessibleTiles.isEmpty()) {
            int index = randomGenerator.nextInt(accessibleTiles.size());
            GuiTile tile = accessibleTiles.get(index);
            tile.inhabit();
            accessibleTiles = getPlayerBoard().getAccessibleTiles();
        }
    }
}
