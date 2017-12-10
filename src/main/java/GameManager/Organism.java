package GameManager;


public class Organism {
    transient Player player;
    int playerId;

    public Organism(Player player) {
        this.player = player;
        this.playerId = player.id;
    }
}
