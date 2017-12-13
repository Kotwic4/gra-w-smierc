package board;

import bot.IPlayer;

import java.awt.*;

public interface ITile {
    boolean isInhabitated();
    IOrganism getInhabitant();
    void inhabit(IPlayer player);
    boolean canInhabit(IPlayer player);
    boolean isStronghold();
    int getCost();
    Point getCoords();

}
