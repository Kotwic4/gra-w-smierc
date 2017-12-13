package bot;

import gui.IPlayerGui;

import java.awt.*;

public interface IPlayer {
    Color getColor();
    int getId();
    String getName();
    void makeTurn(IPlayerGui gui);
    IPlayerBoard getPlayerBoard();
    int getRemainingPoints();
    void addStronhold();
    void removeStronhold();
    void addOrganism();
    void removeOrganism();
    boolean isAlive();
}
