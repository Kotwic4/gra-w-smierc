package bot;

import board.Board;
import gui.TurnCommunicator;
import javafx.scene.paint.Color;

public abstract class HeadlessPlayer extends Player {

    private TurnCommunicator turnCommunicator;

    HeadlessPlayer(Color color, String name, int id, PlayerBoard board, TurnCommunicator turnCommunicator) {
        super(color, name, id, board);
        this.turnCommunicator = turnCommunicator;
    }

    HeadlessPlayer(Color color, String name, int id, Board board, TurnCommunicator turnCommunicator) {
        super(color, name, id, board);
        this.turnCommunicator = turnCommunicator;
    }
    
    @Override
    protected void startTurn() {
        super.startTurn();
        turnCommunicator.startHeadlessTurn(this);
    }

    @Override
    protected void endTurn() {
        super.endTurn();
        turnCommunicator.endHeadlessTurn(this);
    }
}
