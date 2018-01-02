package board;

import java.util.List;
import java.util.LinkedList;

class TileImplementation implements Tile{
    private Organism inhabitant;
    private int cost;
    private Coordinates coords;
    private List<TileImplementation> neighbours;
    private boolean stronghold;
    private int maximumNeighbouringFriendsCount = 4;

    public TileImplementation(Coordinates coords) {
      this.coords = coords;
      neighbours = new LinkedList<>();
    }

    public boolean isInhabitated(){
        return this.inhabitant != null;
    }

    public void broadcastAppeal(int appeal){
        for(TileImplementation neighbour: neighbours){
          if(neighbour.isInhabitated()){
            if (neighbour.getInhabitant().getAppeal() == appeal) continue;
            neighbour.getInhabitant().setAppeal(appeal);
            neighbour.broadcastAppeal(appeal);
          }
        }
    }

    public Organism getInhabitant() {
        return inhabitant;
    }

    public void setInhabitant(Organism inhabitant) throws InvalidOrganismPositionException{
        if(checkIfInhabitable(inhabitant)){
            this.inhabitant = inhabitant;
        } else {
          throw new InvalidOrganismPositionException(inhabitant);
        }
    }

    public boolean checkIfInhabitable(Organism inhabitant){
        int nation = inhabitant.getNation();
        int foundFriendlyNeighbours = 0;
        for (TileImplementation neighbour: neighbours){
            if (neighbour.isInhabitated() && neighbour.getInhabitant().getNation() == nation){
                foundFriendlyNeighbours++;
            }
        }
        return foundFriendlyNeighbours > 0 && foundFriendlyNeighbours < maximumNeighbouringFriendsCount;
    }

    public void uncheckedSetIntabitant(Organism inhabitant){
      // Force setting inhabitant without checking neighbours - required for stronghold's organism initialization
      this.inhabitant = inhabitant;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public void addNeighbour(TileImplementation tile){
      neighbours.add(tile);
    }

    public void setStronghold(){
      stronghold = true;
    }

    public boolean isStronghold(){
      return stronghold;
    }

    public void checkAppealAndReact(int appeal){
      int knownAppeal = getInhabitant().getAppeal();
      if (knownAppeal != appeal){
        inhabitant = null;
      }
    }

    int getMaximumNeighbouringFriendsCount() {
        return maximumNeighbouringFriendsCount;
    }

    void setMaximumNeighbouringFriendsCount(int maximumNeighbouringFriendsCount) {
        this.maximumNeighbouringFriendsCount = maximumNeighbouringFriendsCount;
    }
}
