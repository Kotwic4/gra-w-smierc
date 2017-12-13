package bot;

import board.IBoard;
import gui.IPlayerGui;

import java.awt.*;

public class GuiPlayer extends Player {


    public GuiPlayer(Color color, String name, int id, IBoard board) {
        super(color, name, id, board);
    }

    @Override
    public void makeTurn(IPlayerGui gui) {
        startTurn(gui);
        gui.makeGuiTurn(this);
        endTurn(gui);
    }
}
