package board;

import java.util.List;
import java.util.LinkedList;

public class Tile {
    private Organism inhabitant;
    private int cost;
    private Coordinates coords;
    private transient List<Tile> neighbours;
    private boolean stronghold;

    public Tile(Coordinates coords) {
      this.coords = coords;
      neighbours = new LinkedList<Tile>();
    }

    public boolean isInhabitated(){
        if(this.inhabitant!=null){
            return true;
        }
        return false;
    }

    void broadcastAppeal(int appeal){
        for(Tile neighbour: neighbours){
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
        // Check, if this Player's organism would have a neighbour (connected to the stronghold) if it was on this Tile.
        int nation = inhabitant.getNation();
        boolean  managedToSet = false;
        for (Tile neighbour: neighbours){
          if (neighbour.isInhabitated() && neighbour.getInhabitant().getNation() == nation){
            this.inhabitant = inhabitant;
            managedToSet = true;
            break;
          }
        }
        if (!managedToSet){
          throw new InvalidOrganismPositionException(inhabitant);
        }
    }

    public void uncheckedSetIntabitant(Organism inhabitant){
      // Force set inhabitant without checking neighbours - for stronghold's organism initialization
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

    public void addNeighbour(Tile tile){
      neighbours.add(tile);
    }

    public void setStronghold(){
      stronghold = true;
    }

    public boolean isStronghold(){
      return stronghold;
    }
}
