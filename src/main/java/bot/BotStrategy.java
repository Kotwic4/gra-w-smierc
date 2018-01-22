package bot;

import gui.TurnCommunicator;

import java.util.List;

public abstract class BotStrategy extends HeadlessPlayer{

    BotStrategy(TurnCommunicator turnCommunicator) {
        super(turnCommunicator);
    }

    @Override
    protected void doTurn(Player player) {
        PlayerBoard playerBoard = player.getPlayerBoard();
        List<PlayerTile> accessibleTiles = playerBoard.getAccessibleTiles();
        while (!accessibleTiles.isEmpty()) {
            PlayerTile chosenTile = chooseTile(accessibleTiles);
            if (chosenTile == null) break;
            chosenTile.inhabit();
            accessibleTiles = playerBoard.getAccessibleTiles();
        }
    }

    protected abstract PlayerTile chooseTile(List<PlayerTile> accessibleTiles);

}
