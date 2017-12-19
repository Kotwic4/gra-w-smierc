package bot;

import board.Coordinates;
import gui.PlayerGui;
import javafx.scene.paint.Color;

public interface Player {
    Color getColor();
    int getId();
    String getName();
    void makeTurn();
    PlayerBoard getPlayerBoard();
    int getRemainingPoints();
    void addStronhold(Coordinates coordinates);
    void removeStronhold(Coordinates coordinates);
    void addOrganism();
    void removeOrganism();
    boolean isAlive();
}
