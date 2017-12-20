package board;

/**
 * Created by Paweł Taborowski on 19.12.17.
 */
public class InvalidOrganismPositionException extends Exception{
    public InvalidOrganismPositionException(Organism organism) {
        super("Impossible to put " + organism.getNation() + "'s Organism on a tile with no connection to a Stronghold");
    }
}
