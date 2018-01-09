package bot;

import gui.TurnCommunicator;

import java.util.List;

public class SimpleBot extends HeadlessPlayer {

    private static final int NEIGHBOUR_PLAYER = 10;
    private static final int NEIGHBOUR_STRONGHOLD = 100;
    private static final int STRONGHOLD = 1000;
    private static final double NEIGHBOUR_MULTIPLY = 0.1;

    public SimpleBot(TurnCommunicator turnCommunicator) {
        super(turnCommunicator);
    }

    @Override
    protected void doTurn(Player player) {
        PlayerBoard playerBoard = player.getPlayerBoard();
        List<PlayerTile> accessibleTiles = playerBoard.getAccessibleTiles();
        while (!accessibleTiles.isEmpty()) {
            PlayerTile chosenTile = null;
            double max = 0;
            for(PlayerTile playerTile: accessibleTiles){
                double value = 0;
                for(PlayerTile neighbour: playerTile.getNeighbours()){
                    int nvalue = 0;
                    if(neighbour.isVisible()){
                        if(neighbour.getPlayer().isPresent()){
                            nvalue += NEIGHBOUR_PLAYER;
                        }
                        if(neighbour.isStronghold().get()){
                            nvalue += NEIGHBOUR_STRONGHOLD;
                        }
                        nvalue -= neighbour.getCost().get();
                    }
                    value += nvalue * NEIGHBOUR_MULTIPLY;
                }
                if(playerTile.isStronghold().get()){
                    value += STRONGHOLD;
                }
                value -= playerTile.getCost().get();
                if(value > max){
                    chosenTile = playerTile;
                    max = value;
                }
            }
            if(chosenTile == null) break;
            chosenTile.inhabit();
            accessibleTiles = playerBoard.getAccessibleTiles();
        }
    }
}
