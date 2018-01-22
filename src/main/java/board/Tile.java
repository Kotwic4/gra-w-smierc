package board;

import bot.Player;

import java.util.Optional;

/**
 * Created by Paweł Taborowski on 28.12.17.
 */
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
