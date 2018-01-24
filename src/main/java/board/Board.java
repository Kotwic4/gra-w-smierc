package board;

import bot.Player;
import util.BoardHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;

/*
Use this as follows :
Board board = new Board.BoardBuilder(width, height).markAsStronghold(coords).setInhabitant(coords, nation).build();
*/

public class Board {
    private final Tile[][] tiles;
    private final List<? extends Tile> strongholdList;
    private final int width;
    private final int height;
    private final Random random;
    private int lastAppeal;

    public static class BoardBuilder {
        private TileImplementation[][] tiles;
        private List<TileImplementation> strongholdList;
        private final int width;
        private final int height;

        public BoardBuilder(int width, int height, BoardHelper<TileImplementation> boardHelper) {
            this.width = width;
            this.height = height;
            tiles = new TileImplementation[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    tiles[i][j] = new TileImplementation(new Coordinates(i, j));
                }
            }
            strongholdList = new LinkedList<>();
            boardHelper.setNeighbours(tiles, TileImplementation::setNeighbours);
        }

        public BoardBuilder markAsStronghold(Coordinates coords) throws InvalidTileCoordsException {
            TileImplementation tile = getTileImplementation(coords);
            tile.setStronghold();
            strongholdList.add(tile);
            return this;
        }

        public TileImplementation getTileImplementation(Coordinates coords) throws InvalidTileCoordsException {
            try {
                return tiles[coords.getX()][coords.getY()];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidTileCoordsException(coords);
            }
        }

        public BoardBuilder setInhabitant(Coordinates coords, Player player) throws TileAlreadyInhabitedException, InvalidTileCoordsException {
            getTileImplementation(coords).uncheckedSetIntabitant(new Organism(player));
            return this;
        }

        public BoardBuilder setTileCost(int cost, Coordinates coordinates) throws CostAlreadyAssignedException {
            TileImplementation tileImplementation = getTileImplementation(coordinates);
            tileImplementation.setCost(cost);
            return this;
        }

        public int getMaximumNeighbouringFriendsCount() {
            int maximumNeighbouringFriendsCount = 0;
            try {
                maximumNeighbouringFriendsCount = getTileImplementation(new Coordinates(0, 0)).getMaximumNeighbouringFriendsCount();
            } catch (InvalidTileCoordsException e) {
                e.getMessage();
            }

            return maximumNeighbouringFriendsCount;
        }

        public BoardBuilder setMaximumNeighbouringFriendsCount(int maximumNeighbouringFriendsCount) throws MaximumNeighbouringFriendsCountAssignedException {
            int currentValue = getMaximumNeighbouringFriendsCount();
            if (currentValue != TileImplementation.DEFAULT_NEIGHBOURING_FRIENDS_COUNT) {
                throw new MaximumNeighbouringFriendsCountAssignedException();
            }
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    getTileImplementation(new Coordinates(x, y)).setMaximumNeighbouringFriendsCount(maximumNeighbouringFriendsCount);
                }
            }
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }

    private Board(BoardBuilder builder) {
        this.tiles = builder.tiles;
        this.strongholdList = builder.strongholdList;
        this.width = tiles.length;
        this.height = tiles[0].length;
        random = new Random();
    }

    public Tile getTile(Coordinates coords) throws InvalidTileCoordsException {
        try {
            return tiles[coords.getX()][coords.getY()];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTileCoordsException(coords);
        }
    }

    public void markAndClear() {
        int appeal = lastAppeal;
        while (appeal == lastAppeal) {
            appeal = random.nextInt(10000);
        }
        lastAppeal = appeal;

        for (Tile stronghold : strongholdList) {
            if (stronghold.isInhabited()) {
                stronghold.getInhabitant().setAppeal(appeal);
                stronghold.broadcastAppeal(appeal);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j].isInhabited()) {
                    tiles[i][j].checkAppealAndReact(appeal);
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (width != board.width) return false;
        if (height != board.height) return false;
        if (lastAppeal != board.lastAppeal) return false;
        if (!Arrays.deepEquals(tiles, board.tiles)) return false;
        if (strongholdList != null ? !strongholdList.equals(board.strongholdList) : board.strongholdList != null)
            return false;
        return random != null ? random.equals(board.random) : board.random == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(tiles);
        result = 31 * result + (strongholdList != null ? strongholdList.hashCode() : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + (random != null ? random.hashCode() : 0);
        result = 31 * result + lastAppeal;
        return result;
    }
}
