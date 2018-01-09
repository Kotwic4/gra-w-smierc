package board;

/**
 * Created by Paweł Taborowski on 28.12.17.
 */
public interface Tile {
  boolean isInhabitated();
  void broadcastAppeal(int appeal);
  Organism getInhabitant();
  boolean checkIfInhabitable(Organism inhabitant);
  void setInhabitant(Organism inhabitant) throws InvalidOrganismPositionException;
  int getCost();
  Coordinates getCoords();
  boolean isStronghold();
  void checkAppealAndReact(int appeal);
}
