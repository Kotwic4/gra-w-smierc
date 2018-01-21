package bot;

import board.Board;
import board.Tile;
import board.TileObserver;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class PlayerTile extends Observable implements TileObserver, Observer{

    private Tile tile;
    private Player player;
    private List<PlayerTile> neighbours;
    private Board board;

    private int visibleRange = 0;
    private Player tilePlayer = null;
    private Boolean isStronghold = false;
    private Boolean accessible = false;
    private Integer tileCost = 0;

    PlayerTile(Tile tile, Player player, Board board) {
        this.tile = tile;
        this.player = player;
        player.addObserver(this);
        this.board = board;
    }

    public void inhabit() {
        if (isAccessible()) {
            player.subPoints(tileCost);
            tile.inhabit(player);
            board.markAndClear();
        }
    }

    public boolean isVisible() {
        return visibleRange > 0;
    }

    public boolean isAccessible() {
        return accessible;
    }

    private int getVisibleRange() {
        return visibleRange;
    }

    public Optional<Player> getPlayer() {
        return isVisible() ? Optional.ofNullable(tilePlayer) : Optional.empty();
    }

    public Optional<Color> getColor() {
        return getPlayer().map(Player::getColor);
    }

    public Optional<Boolean> isStronghold() {
        return isVisible() ? Optional.of(isStronghold) : Optional.empty();
    }

    public Optional<Integer> getCost() {
        return isVisible() ? Optional.of(tileCost) : Optional.empty();
    }

    List<PlayerTile> getNeighbours() {
        return neighbours;
    }

    void setNeighbours(List<PlayerTile> neighbours) {
        this.neighbours = neighbours;
    }

    private boolean updateTilePlayer(Player newPlayer) {
        if (newPlayer != tilePlayer) {
            if(tilePlayer != player){
                tilePlayer.deleteObserver(this);
            }
            tilePlayer = newPlayer;
            if(tilePlayer != player){
                tilePlayer.addObserver(this);
            }
            return true;
        }
        return false;
    }

    private int getNewVisibleRange() {
        if (tilePlayer == player) {
            return player.getVisibleRange();
        }
        int newVisibleRange = 0;
        for (PlayerTile neighbour : neighbours) {
            if (newVisibleRange < neighbour.getVisibleRange()) {
                newVisibleRange = neighbour.getVisibleRange() - 1;
            }
        }
        return newVisibleRange;
    }

    private boolean updateVisible() {
        int newVisibleRange = getNewVisibleRange();
        if (visibleRange != newVisibleRange) {
            visibleRange = newVisibleRange;
            return true;
        }
        return false;
    }

    private boolean updateStronghold() {
        boolean newIsStronghold = tile.isStronghold();
        if (newIsStronghold != isStronghold) {
            isStronghold = newIsStronghold;
            return true;
        }
        return false;
    }

    private boolean updateTileCost() {
        int newCost = tile.getCost();
        if (newCost != tileCost) {
            tileCost = newCost;
            return true;
        }
        return false;
    }

    private boolean updateAccessible() {
        boolean newAccessible = isVisible() && tile.canInhabit(player) && tileCost <= player.getRemainingPoints();
        if (newAccessible != accessible) {
            accessible = newAccessible;
            return true;
        }
        return false;
    }

    void updateTileInformation() {
        Player newPlayer = tile.getPlayer().orElse(null);
        boolean updated = updateTilePlayer(newPlayer)
                | updateVisible()
                | updateStronghold()
                | updateTileCost()
                | updateAccessible();
        if (updated) {
            neighbours.forEach(PlayerTile::updateTileInformation);
            notifyObservers();
        }
    }

    @Override
    public void update(Tile tile) {
        updateTileInformation();
    }

    @Override
    public void update(Observable o, Object arg) {
        updateTileInformation();
    }
}
