package bot;

import board.Board;
import board.Coordinates;
import javafx.scene.paint.Color;

public abstract class Player {

    private Color color;
    private String name;
    private int id;
    private int points;
    private int pointsPerTurn;
    private int strongholds;
    private static final int visibleRange = 5;
    private PlayerBoard playerBoard;

    Player(Color color, String name, int id, Board board) {
        this.color = color;
        this.name = name;
        this.id = id;
        this.playerBoard = new PlayerBoard(board,this);
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected void startTurn(){
        points += pointsPerTurn;
    }

    protected void endTurn(){

    }

    protected abstract void doTurn();

    public void makeTurn() {
        startTurn();
        doTurn();
        endTurn();
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public int getRemainingPoints() {
        return points;
    }

    void subPoints(int cost) {
        points -= cost;
    }

    public void addStronhold(Coordinates coordinates) {
        //todo
    }

    public void removeStronhold(Coordinates coordinates) {
        //todo
    }

    public void addOrganism() {
        //todo
    }

    public void removeOrganism() {
        //todo
    }

    public boolean isAlive() {
        //todo
        return true;
    }

    int getVisibleRange(){
        return visibleRange;
    }
}
