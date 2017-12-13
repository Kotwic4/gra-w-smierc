package bot;

import board.IBoard;
import gui.IPlayerGui;

import java.awt.*;

public abstract class Bot extends Player {

    public Bot(Color color, String name, int id, IBoard board) {
        super(color, name, id, board);
    }

    @Override
    void startTurn(IPlayerGui gui) {
        super.startTurn(gui);
        gui.startNoGuiTurn(this);
    }

    @Override
    void endTurn(IPlayerGui gui) {
        super.endTurn(gui);
        gui.endNoGuiTurn(this);
    }
}
