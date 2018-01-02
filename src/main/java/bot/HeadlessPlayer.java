package bot;

import gui.TurnCommunicator;

public abstract class HeadlessPlayer extends PlayerStrategy {

    private TurnCommunicator turnCommunicator;

    public HeadlessPlayer(TurnCommunicator turnCommunicator) {
        this.turnCommunicator = turnCommunicator;
    }

    @Override
    public void startTurn(Player player) {
        turnCommunicator.startHeadlessTurn(player);
        super.startTurn(player);
    }

    @Override
    protected void doTurn(Player player) {
        turnCommunicator.endHeadlessTurn(player);
    }

}
