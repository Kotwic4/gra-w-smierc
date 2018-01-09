package bot;

import gui.TurnCommunicator;

public abstract class HeadlessPlayer extends PlayerStrategy {

    private TurnCommunicator turnCommunicator;

    public HeadlessPlayer(TurnCommunicator turnCommunicator) {
        this.turnCommunicator = turnCommunicator;
    }

    @Override
    protected final void startTurn(Player player) {
        turnCommunicator.startHeadlessTurn(player);
        super.startTurn(player);
    }

    @Override
    protected final void endTurn(Player player) {
        super.endTurn(player);
        turnCommunicator.endHeadlessTurn(player);
    }

}
