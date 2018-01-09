package board;

class InvalidTileCoordsException extends RuntimeException {
    InvalidTileCoordsException(Coordinates coords) {
        super("There is no tile at " + coords.toString() + ".");
    }
}
