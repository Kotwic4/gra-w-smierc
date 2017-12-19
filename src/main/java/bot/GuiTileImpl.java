package bot;

import board.Tile;
import javafx.scene.paint.Color;

import java.util.Optional;

public class GuiTileImpl implements GuiTile {

    private Tile tile;
    private boolean visible;
    private PlayerImpl playerImpl;

    GuiTileImpl(Tile tile, PlayerImpl playerImpl) {
        this.tile = tile;
        this.playerImpl = playerImpl;
        visible = false;
    }

    @Override
    public boolean isAccessible() {
        return visible && tile.canInhabit(playerImpl) && tile.getCost() <= playerImpl.getRemainingPoints();
    }

    @Override
    public boolean isVisible() {
        //todo
        return true;
    }

    @Override
    public void inhabit() {
        if(isAccessible()){
            playerImpl.subPoints(tile.getCost());
            tile.inhabit(playerImpl);
        }
    }

    @Override
    public Optional<Color> getColor() {
        return visible ? tile.getInhabitant().map(Player::getColor) : Optional.empty();
    }

    @Override
    public Optional<Boolean> isStronghold() {
        return visible ? Optional.of(tile.isStronghold()) : Optional.empty();
    }

    @Override
    public Optional<Integer> getCost() {
        return visible ? Optional.of(tile.getCost()) : Optional.empty();
    }
}
