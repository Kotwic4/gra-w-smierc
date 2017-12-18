package board;

import java.util.List;

public class Tile {
    private Organism inhabitant;
    private int cost;
    private Coordinates coords;
    private List<Tile> neighbours;
    private boolean isStronghold;

    public Tile(Coordinates coords) {
      this.coords = coords;
      neighbours = new LinkedList<Tile>();
    }

    public boolean isInhabitated(){
        //TODO
        if(this.inhabitant!=null){
            return true;
        }
        return false;
    }

    void broadcastAppeal(int appeal){
        //TODO
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
      neighbours.append(tile);
    }

    public void setStronghold(){
      isStronghold = true;
    }
}
