package GameManager;

public class Tile {
    int cost;
    boolean isBase;
    Organism organism;

    public Tile(int cost, boolean isBase) {
        this.cost = cost;
        this.isBase = isBase;
    }

    public Tile(int cost, boolean isBase, Organism organism) {

        this.cost = cost;
        this.isBase = isBase;
        this.organism = organism;
    }

    void setNNeighbour(Tile tile) {
    }

    void setENeighbour(Tile tile) {
    }

    void setWNeighbour(Tile tile) {
    }

    void setSNeighbour(Tile tile) {
    }

    void setNENeighbour(Tile tile) {
    }

    void setNWNeighbour(Tile tile) {
    }

    void setSENeighbour(Tile tile) {
    }

    void setSWNeighbour(Tile tile) {
    }

}
