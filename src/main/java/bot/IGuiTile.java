package bot;

import board.ITile;

import java.util.Optional;

public interface IGuiTile {
    boolean isAccessible();
    boolean isVisible();
    void inhabit();
    Optional<? extends ITile> getTile();
}
