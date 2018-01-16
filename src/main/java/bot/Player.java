package bot;

import javafx.scene.paint.Color;

public class Player {

    private Color color;
    private String name;
    private int id;
    private int remainingPoints = 0;
    private int pointsPerTurn = 0;
    private int strongholdsNumber = 0;
    private PlayerBoard playerBoard = null;
    private PlayerStrategy playerStrategy = null;
    private static final int VISIBLE_RANGE = 5;
    private static final int POINTS_PER_ORGANISM = 1;
    private static final int POINTS_PER_STRONGHOLDS = 10;

    public Player(Color color, String name, int id) {
        this.color = color;
        this.name = name;
        this.id = id;
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

    public void setPlayerStrategy(PlayerStrategy playerStrategy) {
        this.playerStrategy = playerStrategy;
    }

    public void makeTurn() {
        if (playerStrategy != null) {
            playerStrategy.startTurn(this);
            playerStrategy.doTurn(this);
            playerStrategy.endTurn(this);
        }
    }

    public void setPlayerBoard(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    void addPoints(int value) {
        remainingPoints += value;
    }

    void subPoints(int cost) throws NotEnoughPointsException {
        if (cost > remainingPoints) throw new NotEnoughPointsException();
        remainingPoints -= cost;
    }

    public void addStronhold() {
        pointsPerTurn += POINTS_PER_STRONGHOLDS;
        strongholdsNumber++;
    }

    public void removeStronhold() {
        if (strongholdsNumber == 0) throw new PlayerHaveNoStrongholdsException();
        pointsPerTurn -= POINTS_PER_STRONGHOLDS;
        strongholdsNumber--;
    }

    public void addOrganism() {
        pointsPerTurn += POINTS_PER_ORGANISM;
        if(playerBoard != null){
            playerBoard.update();
        }
    }

    public void removeOrganism() {
        pointsPerTurn -= POINTS_PER_ORGANISM;
        if(playerBoard != null){
            playerBoard.update();
        }
    }

    public boolean isAlive() {
        return strongholdsNumber != 0;
    }

    int getVisibleRange() {
        return VISIBLE_RANGE;
    }


}
