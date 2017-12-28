package board;

public class Organism {
    private final int nation;
    private int appeal;

    public Organism(int nation){
        this.nation = nation;
    }

    public int getNation() {
        return nation;
    }

    int getAppeal() {
        return appeal;
    }

    void setAppeal(int appeal) {
        this.appeal = appeal;
    }
}
