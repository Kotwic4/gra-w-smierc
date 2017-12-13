package bot;

import board.ITile;

import java.util.Optional;

public class GuiTile implements IGuiTile {

    private ITile tile;
    private boolean visible;
    private Player player;

    GuiTile(ITile tile, Player player) {
        this.tile = tile;
        this.player = player;
        visible = false;
    }

    @Override
    public boolean isAccessible() {
        return visible && tile.canInhabit(player) && tile.getCost() < player.getRemainingPoints();
    }

    @Override
    public boolean isVisible() {
        //todo
        return true;
    }

    @Override
    public void inhabit() {
        if(isAccessible()){
            player.subPoints(tile.getCost());
            tile.inhabit(player);
        }
    }

    @Override
    public Optional<? extends ITile> getTile() {
        return visible ? Optional.of(tile) : Optional.empty();
    }
}
