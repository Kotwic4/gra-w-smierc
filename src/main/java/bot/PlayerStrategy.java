package bot;

public abstract class PlayerStrategy {

    protected void startTurn(Player player) {
        System.out.println("adding points for "+player.getName());
        player.addPoints(player.getPointsPerTurn());
        System.out.println("now "+player.getName()+" has "+Integer.toString(player.getRemainingPoints())+" remaining points");
        player.getPlayerBoard().update();
    }

    protected abstract void doTurn(Player player);

    protected void endTurn(Player player) {

    }
}
