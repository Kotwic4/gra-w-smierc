package board;

/**
 * Created by Paweł Taborowski on 19.12.17.
 */
public class CostAlreadyAssignedException extends RuntimeException {
    public CostAlreadyAssignedException(Tile tile) {
        super("Tile " + tile.getCoords().toString() + " has already it's cost set.");
    }
}
