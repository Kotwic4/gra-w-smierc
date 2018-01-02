package bot;

import gui.TurnCommunicator;

public class CheatBot extends HeadlessPlayer {

    public CheatBot(TurnCommunicator turnCommunicator) {
        super(turnCommunicator);
    }

    @Override
    protected void doTurn(Player player) {
        //todo Implement CheatBot behaviour
        super.doTurn(player);
    }
}
