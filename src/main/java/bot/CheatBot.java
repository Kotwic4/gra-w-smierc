package bot;

import board.Board;
import gui.BotGui;
import gui.PlayerGui;
import javafx.scene.paint.Color;

import java.awt.*;

public class CheatBot extends Bot {

    public CheatBot(Color color, String name, int id, Board board, BotGui botGui) {
        super(color, name, id, board, botGui);
    }

    @Override
    protected void doTurn() {
        //todo
    }
}