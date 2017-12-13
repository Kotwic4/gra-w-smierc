package bot;

import board.IBoard;
import gui.IPlayerGui;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomBot extends Bot{

    private Random randomGenerator;

    public RandomBot(Color color, String name, int id, IBoard board) {
        super(color, name, id, board);
    }

    @Override
    public void makeTurn(IPlayerGui gui) {
        startTurn(gui);
        List<IGuiTile> tiles = getAccessibleTiles();
        while(!tiles.isEmpty()){
            int index = randomGenerator.nextInt(tiles.size());
            IGuiTile tile = tiles.get(index);
            tile.inhabit();
            tiles = getAccessibleTiles();
        }
        endTurn(gui);
    }
}
