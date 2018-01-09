package board;

class CostAlreadyAssignedException extends RuntimeException {
    CostAlreadyAssignedException(Tile tile) {
        super("Tile " + tile.getCoords().toString() + " has already it's cost set.");
    }
}
