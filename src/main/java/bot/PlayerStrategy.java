package bot;

public abstract class PlayerStrategy {

    public void startTurn(Player player){
        player.addPoints(player.getPointsPerTurn());
        player.getPlayerBoard().update();
        doTurn(player);
    }

    protected abstract void doTurn(Player player);
}
