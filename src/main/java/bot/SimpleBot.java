package bot;

import board.Board;
import gui.TurnCommunicator;
import javafx.scene.paint.Color;

public class SimpleBot extends Bot {

    public SimpleBot(Color color, String name, int id, Board board, TurnCommunicator turnCommunicator) {
        super(color, name, id, board, turnCommunicator);
    }

    @Override
    protected void doTurn() {
        //todo
    }
}
