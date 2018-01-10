package board;

public class TileAlreadyInhabitedException extends RuntimeException {
    public TileAlreadyInhabitedException(Coordinates coords) {
        super("Tile " + coords.toString() + " already inhabited.");
    }
}
