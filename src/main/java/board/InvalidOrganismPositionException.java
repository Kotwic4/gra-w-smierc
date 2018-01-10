package board;

import bot.Player;

class InvalidOrganismPositionException extends RuntimeException {
    InvalidOrganismPositionException(Player player) {
        super("Impossible to put " + player.getName() + "'s Organism on a tile with either no connection to a Stronghold or too many friendly neighbouring units");
    }
}
