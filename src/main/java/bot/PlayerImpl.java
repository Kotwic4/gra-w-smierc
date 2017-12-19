package bot;

import board.Board;
import board.Coordinates;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PlayerImpl implements Player {

    private Color color;
    private String name;
    private int id;
    private int points;
    private int pointsPerTurn;
    private int strongholds;
    private static final int visibleRange = 5;
    private PlayerBoard playerBoard;

    PlayerImpl(Color color, String name, int id, Board board) {
        this.color = color;
        this.name = name;
        this.id = id;
        this.playerBoard = new PlayerBoardImpl(board,this);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    protected void startTurn(){
        points += pointsPerTurn;
    }

    protected void endTurn(){

    }

    protected abstract void doTurn();

    @Override
    public void makeTurn() {
        startTurn();
        doTurn();
        endTurn();
    }

    @Override
    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    @Override
    public int getRemainingPoints() {
        return points;
    }

    void subPoints(int cost) {
        points -= cost;
    }

    @Override
    public void addStronhold(Coordinates coordinates) {
        //todo
    }

    @Override
    public void removeStronhold(Coordinates coordinates) {
        //todo
    }

    @Override
    public void addOrganism() {
        //todo
    }

    @Override
    public void removeOrganism() {
        //todo
    }

    @Override
    public boolean isAlive() {
        //todo
        return true;
    }

    int getVisibleRange(){
        return visibleRange;
    }
}
