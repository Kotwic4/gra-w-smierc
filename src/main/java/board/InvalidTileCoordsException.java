package board;

/**
 * Created by Paweł Taborowski on 19.12.17.
 */
public class InvalidTileCoordsException extends RuntimeException{
    public InvalidTileCoordsException(Coordinates coords) {
        super("There is no tile at " + coords.toString() + ".");
    }
}
