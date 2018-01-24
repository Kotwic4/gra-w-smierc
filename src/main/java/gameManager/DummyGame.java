package gameManager;

import javafx.scene.paint.Color;

import java.awt.*;

/**
 * Created by Michał Pawłowicz on 02.01.18.
 */
final class DummyGame {
    class DummyPlayer {
        public DummyPlayer(int id, Color color){
            this.id = id;
            this.color = color;
        }
        private final int id;
        private final Color color;

        public int getId() {
            return id;
        }

        public Color getColor() {
            return color;
        }
    }

    class DummyBoard {
        class DummyTile {
            class DummyOrganism {
                public DummyOrganism(int playerID){
                    this.playerID = playerID;
                }
                private final int playerID;
                public int getPlayerID(){
                    return playerID;
                }
            }

            public DummyTile(int cost, boolean stronghold, DummyOrganism organism) {
                this.cost = cost;
                this.stronghold = stronghold;
                this.organism = organism;
            }

            private final int cost;
            private final boolean stronghold;
            private final DummyOrganism organism;

            public int getCost() {
                return cost;
            }

            public boolean getStronghold() {
                return stronghold;
            }

            public DummyOrganism getOrganism() {
                return organism;
            }
        }

        private final DummyTile[][] tiles;
        private final int width;
        private final int height;

        public DummyBoard(DummyTile[][] tiles, int width, int height) {
            this.tiles = tiles;
            this.width = width;
            this.height = height;
        }

        public DummyTile[][] getTiles() {
            return tiles;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public DummyGame(DummyBoard board, DummyPlayer[] players)
    {
        this.board = board;
        this.players = players;
    }

    public DummyGame(DummyBoard board)
    {
        this.board = board;
        this.players = new DummyPlayer[0];
    }

    public int getPlayersNumber(){ return (players != null) ? players.length : 0; }
    public int getBoardLenght(){ return (board != null) ? board.getTiles().length : 0; }
    public Color getPlayerColor(int i){ return players[i].getColor(); }
    public int getPlayerId(int i){ return players[i].getId(); }
    public Dimension getBoardDimension(){
        return (board != null) ? new Dimension(board.width, board.height) : new Dimension(0, 0);
    }
    public int getNumberOfTiles(){ return board.tiles.length; }
    public int getCostOnTile(int i, int j){ return board.tiles[i][j].getCost(); }
    public boolean getStrongholdOfTile(int i, int j){ return board.tiles[i][j].getStronghold(); }

    private final DummyBoard board;
    private final DummyPlayer[] players;
}