package bot;

import board.ITile;

import java.util.Optional;

public interface IGuiTile {
    boolean isAccesible();
    boolean isVisible();
    void inhabit();
    Optional<ITile> getTile();
}
