package bot;

import gui.TurnCommunicator;

public class CheatBot extends SimpleBot {

    public CheatBot(TurnCommunicator turnCommunicator) {
        super(turnCommunicator);
    }

    @Override
    protected void doTurn(Player player) {
        player.addPoints(player.getPointsPerTurn() * 10);
        super.doTurn(player);
    }
}
