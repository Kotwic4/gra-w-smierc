package bot;

import board.Board;
import gui.PlayerController;
import javafx.scene.paint.Color;

public class GuiPlayer extends Player {

    private PlayerController gui;

    public GuiPlayer(Color color, String name, int id, Board board, PlayerController gui) {
        super(color, name, id, board);
        this.gui = gui;
    }

    public GuiPlayer(Color color, String name, int id, PlayerBoard board, PlayerController gui) {
        super(color, name, id, board);
        this.gui = gui;
    }

    @Override
    public void doTurn() {
        gui.doGuiTurn(this);
    }

}
