package board;

import bot.Player;

import java.util.List;
import java.util.Optional;

public interface Tile {
    boolean isInhabitated();

    Optional<? extends Player> getInhabitant();

    void inhabit(Player player);

    boolean canInhabit(Player player);

    boolean isStronghold();

    int getCost();

    List<? extends Tile> getNeighbours();
}
