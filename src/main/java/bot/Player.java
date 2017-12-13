package bot;

import board.IBoard;
import gui.IPlayerGui;

import java.awt.*;

public abstract class Player implements IPlayer {

    private Color color;
    private String name;
    private int id;
    private int points;
    private int pointsPerTurn;
    private int strongholds;
    private static final int visibleRange = 5;
    private IPlayerBoard playerBoard;

    public Player(Color color, String name, int id, IBoard board) {
        this.color = color;
        this.name = name;
        this.id = id;
        this.playerBoard = new PlayerBoard(board,this);
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

    void startTurn(IPlayerGui gui){
        points += pointsPerTurn;
    }

    void endTurn(IPlayerGui gui){

    }

    @Override
    public IPlayerBoard getPlayerBoard() {
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
    public void addStronhold() {
        //todo
    }

    @Override
    public void removeStronhold() {
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
