package bot;

import board.Board;
import gui.BotGui;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Bot extends PlayerImpl {

    private BotGui botGui;

    Bot(Color color, String name, int id, Board board, BotGui botGui) {
        super(color, name, id, board);
        this.botGui = botGui;
    }

    @Override
    protected void startTurn() {
        super.startTurn();
        botGui.startNoGuiTurn(this);
    }

    @Override
    protected void endTurn() {
        super.endTurn();
        botGui.endNoGuiTurn(this);
    }
}
