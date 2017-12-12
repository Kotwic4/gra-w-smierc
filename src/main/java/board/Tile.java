package board;

import java.util.List;

public class Tile {
    private Organism inhabitant;
    private int cost;
    private Pair coords;
    private List<Tile> neighbours;
    private boolean isStronghold;

    public boolean isInhabitated(){
        //TODO
        if(this.inhabitant!=null){
            return true;
        }
        return false;
    }

    void broadcastPassword(int password){
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

    public Pair getCoords() {
        return coords;
    }
}
