package bot;

import board.Board;
import gui.TurnCommunicator;
import javafx.scene.paint.Color;

public class CheatBot extends HeadlessPlayer {

    CheatBot(Color color, String name, int id, PlayerBoard board, TurnCommunicator turnCommunicator) {
        super(color, name, id, board, turnCommunicator);
    }

    public CheatBot(Color color, String name, int id, Board board, TurnCommunicator turnCommunicator) {
        super(color, name, id, board, turnCommunicator);
    }

    @Override
    protected void doTurn() {
        //todo Implement CheatBot behaviour
    }
}
