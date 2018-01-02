package bot;

import board.Tile;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;

public class PlayerTile {

    private Tile tile;
    private boolean visible;
    private Player player;
    private List<PlayerTile> neighbours;

    PlayerTile(Tile tile, Player player) {
        this.tile = tile;
        this.player = player;
        visible = false;
    }

    public boolean isAccessible() {
        return visible && tile.canInhabit(player) && tile.getCost() <= player.getRemainingPoints();
    }

    public boolean isVisible() {
        return visible;
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void inhabit() {
        if (isAccessible()) {
            player.subPoints(tile.getCost());
            tile.inhabit(player);
        }
    }

    public Optional<Color> getColor() {
        return visible ? tile.getInhabitant().map(Player::getColor) : Optional.empty();
    }

    public Optional<Boolean> isStronghold() {
        return visible ? Optional.of(tile.isStronghold()) : Optional.empty();
    }

    public Optional<Integer> getCost() {
        return visible ? Optional.of(tile.getCost()) : Optional.empty();
    }

    public List<PlayerTile> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<PlayerTile> neighbours) {
        this.neighbours = neighbours;
    }
}
