package bot;

import board.Board;
import javafx.scene.paint.Color;

public abstract class Player {

    private Color color;
    private String name;
    private int id;
    private int remainingPoints;
    private int pointsPerTurn;
    private int strongholdsNumber;
    private static final int VISIBLE_RANGE = 5;
    private static final int POINTS_PER_ORGANISM = 1;
    private static final int POINTS_PER_STRONGHOLDS = 1;
    private PlayerBoard playerBoard;


    Player(Color color, String name, int id, PlayerBoard playerBoard){
        this.color = color;
        this.name = name;
        this.id = id;
        this.playerBoard = playerBoard;
        pointsPerTurn = 0;
        strongholdsNumber = 0;
        remainingPoints = 0;
    }

    Player(Color color, String name, int id, Board board) {
        this(color,name,id, (PlayerBoard) null);
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

    public int getRemainingPoints() {
        return remainingPoints;
    }

    public int getPointsPerTurn() {
        return pointsPerTurn;
    }

    public int getStrongholdsNumber() {
        return strongholdsNumber;
    }

    protected void startTurn(){
        remainingPoints += getPointsPerTurn();
        playerBoard.updateVision();
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

    void subPoints(int cost) {
        remainingPoints -= cost;
    }

    public void addStronhold() {
        pointsPerTurn += POINTS_PER_STRONGHOLDS;
        strongholdsNumber++;
    }

    public void removeStronhold() {
        pointsPerTurn -= POINTS_PER_STRONGHOLDS;
        strongholdsNumber--;
    }

    public void addOrganism() {
        pointsPerTurn += POINTS_PER_ORGANISM;
        playerBoard.updateVision();
    }

    public void removeOrganism() {
        pointsPerTurn -= POINTS_PER_ORGANISM;
        playerBoard.updateVision();
    }

    public boolean isAlive() {
        return strongholdsNumber == 0;
    }

    int getVisibleRange(){
        return VISIBLE_RANGE;
    }
}
