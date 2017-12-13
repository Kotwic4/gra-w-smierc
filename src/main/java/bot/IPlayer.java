package bot;

import java.awt.*;

public interface IPlayer {
    Color getColor();
    int getId();
    String getName();
    void makeTurn();
    IPlayerBoard getPlayerBoard();
    void addStronhold();
    void removeStronhold();
    void addOrganism();
    void removeOrganism();
}
