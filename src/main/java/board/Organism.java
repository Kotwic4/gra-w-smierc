package board;

public class Organism {
    private int nation;
    private int password;

    public Organism(int nation){
        this.nation = nation;
    }

    public int getNation() {
        return nation;
    }

    int getPassword() {
        return password;
    }

    void setPassword(int password) {
        this.password = password;
    }  
}
