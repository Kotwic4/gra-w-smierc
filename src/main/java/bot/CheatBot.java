package bot;

import board.IBoard;
import gui.IPlayerGui;

import java.awt.*;

public class CheatBot extends Bot {

    public CheatBot(Color color, String name, int id, IBoard board) {
        super(color, name, id, board);
    }

    @Override
    public void makeTurn(IPlayerGui gui) {
        //todo
    }
}
