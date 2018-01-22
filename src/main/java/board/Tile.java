package board;

import bot.Player;

import java.util.Observable;
import java.util.Optional;

public interface Tile {
    boolean isInhabited();

    void broadcastAppeal(int appeal);

    Organism getInhabitant();

    int getCost();

    Coordinates getCoords();

    boolean isStronghold();

    void checkAppealAndReact(int appeal);

    boolean canInhabit(Player player);

    void inhabit(Player player);

    Optional<Player> getPlayer();

    void registerObserver(TileObserver tileObserver);
    void removeObserver(TileObserver tileObserver);
}
