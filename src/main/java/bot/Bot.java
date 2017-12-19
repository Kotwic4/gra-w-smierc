package bot;

import board.Board;
import gui.TurnCommunicator;
import javafx.scene.paint.Color;

abstract class Bot extends HeadlessPlayer {

    Bot(Color color, String name, int id, Board board, TurnCommunicator turnCommunicator) {
        super(color, name, id, board, turnCommunicator);
    }

}
