package board;

import bot.Player;

public class Organism {

    private final Player player;

    private int appeal;

    public Organism(Player player) {
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    int getAppeal() {
        return appeal;
    }

    void setAppeal(int appeal) {
        this.appeal = appeal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organism organism = (Organism) o;

        if (!getPlayer().equals(organism.getPlayer())) return false;
        return getAppeal() == organism.getAppeal();
    }
}
