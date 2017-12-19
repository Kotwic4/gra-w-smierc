package bot;

import javafx.scene.paint.Color;

import java.util.Optional;

public interface GuiTile {
    boolean isAccessible();
    boolean isVisible();
    void inhabit();
    Optional<Color> getColor();
    Optional<Boolean> isStronghold();
    Optional<Integer> getCost();
}
