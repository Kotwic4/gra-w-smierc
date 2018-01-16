package board;

import bot.Player;

import java.util.Observable;
import java.util.Optional;

/**
 * Created by Paweł Taborowski on 28.12.17.
 */
public abstract class Tile extends Observable{
  abstract public boolean isInhabited();
  abstract void broadcastAppeal(int appeal);
  abstract public Organism getInhabitant();
  abstract public int getCost();
  abstract public Coordinates getCoords();
  abstract public boolean isStronghold();
  abstract void checkAppealAndReact(int appeal);
  abstract public boolean canInhabit(Player player);
  abstract public void inhabit(Player player);
  abstract public Optional<Player> getPlayer();
}
