package bot;

import board.Board;
import board.Coordinates;
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
    private static final int POINTS_PER_STRONGHOLDS = 1;

    Player(Color color, String name, int id){
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

    void setPlayerStrategy(PlayerStrategy playerStrategy){
        this.playerStrategy = playerStrategy;
    }

    public void makeTurn() {
        playerStrategy.doTurn(this);
    }

    public void createPlayerBoard(Board board){
        int width = board.getWidth();
        int height = board.getHeight();
        PlayerTile[][] playerTiles = new PlayerTile[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                playerTiles[x][y] = new PlayerTile(board.getTile(new Coordinates(x,y)), this);
            }
        }
        this.playerBoard = new PlayerBoard(playerTiles);
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    void addPoints(int value){
        remainingPoints += value;
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
        playerBoard.update();
    }

    public void removeOrganism() {
        pointsPerTurn -= POINTS_PER_ORGANISM;
        playerBoard.update();
    }

    public boolean isAlive() {
        return strongholdsNumber == 0;
    }

    int getVisibleRange(){
        return VISIBLE_RANGE;
    }


}
