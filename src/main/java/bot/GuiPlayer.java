package bot;

import board.Board;
import gui.PlayerGui;
import javafx.scene.paint.Color;

public class GuiPlayer extends PlayerImpl {

    private PlayerGui gui;

    public GuiPlayer(Color color, String name, int id, Board board, PlayerGui gui) {
        super(color, name, id, board);
        this.gui = gui;
    }

    @Override
    public void doTurn() {
        gui.doGuiTurn(this);
    }
}
