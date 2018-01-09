package board;

/**
 * Created by Paweł Taborowski on 19.12.17.
 */
public class InvalidOrganismPositionException extends Exception{
    public InvalidOrganismPositionException(Organism organism) {
        super("Impossible to put " + organism.getPlayer() + "'s Organism on a tile with either no connection to a Stronghold or too many friendly neighbouring units");
    }
}
