package board;

import java.util.List;
import java.util.LinkedList;

public class Tile {
    private Organism inhabitant;
    private int cost;
    private Coordinates coords;
    private List<Tile> neighbours;
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

    public void setInhabitant(Organism inhabitant) {
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

    public boolean getStronghold(){
      return stronghold;
    }
}
