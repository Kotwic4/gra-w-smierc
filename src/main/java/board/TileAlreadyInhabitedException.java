package board;

public class TileAlreadyInhabitedException extends Exception {
    public TileAlreadyInhabitedException(Coordinates coords) {
        super("Tile (" + coords.getX() + ", " + coords.getY() + ") already inhabited.");
    }
}
