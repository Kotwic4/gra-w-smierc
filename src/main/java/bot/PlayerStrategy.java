package bot;

public abstract class PlayerStrategy {

    protected void startTurn(Player player) {
        player.addPoints(player.getPointsPerTurn());
        player.getPlayerBoard().update();
    }

    protected abstract void doTurn(Player player);

    protected void endTurn(Player player) {

    }
}
