package board;

import bot.Player;

public class Organism {
  
    private final int nation;

    private Player player;

    private int appeal;

    public Organism(int nation){
        this.nation = nation;
    }

    public int getNation() {
        return nation;
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

        if (getNation() != organism.getNation()) return false;
        return getAppeal() == organism.getAppeal();
    }
}
