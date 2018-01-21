package util;

import board.Coordinates;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class BoardHelper<T> {

    public void setNeighbours(T[][] tiles, BiConsumer<T, List<T>> lambda) {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                List<T> neighbours = getNeighbours(tiles, new Coordinates(x, y));
                lambda.accept(tiles[x][y], neighbours);
            }
        }
    }

    List<T> getNeighbours(T[][] tiles, Coordinates coordinates) {
        List<T> neighbours = new LinkedList<>();
        getTile(tiles, new Coordinates(coordinates.getX() - 1, coordinates.getY() - 1)).ifPresent(neighbours::add);
        getTile(tiles, new Coordinates(coordinates.getX() - 1, coordinates.getY())).ifPresent(neighbours::add);
        getTile(tiles, new Coordinates(coordinates.getX() - 1, coordinates.getY() + 1)).ifPresent(neighbours::add);
        getTile(tiles, new Coordinates(coordinates.getX(), coordinates.getY() - 1)).ifPresent(neighbours::add);
        getTile(tiles, new Coordinates(coordinates.getX(), coordinates.getY() + 1)).ifPresent(neighbours::add);
        getTile(tiles, new Coordinates(coordinates.getX() + 1, coordinates.getY() - 1)).ifPresent(neighbours::add);
        getTile(tiles, new Coordinates(coordinates.getX() + 1, coordinates.getY())).ifPresent(neighbours::add);
        getTile(tiles, new Coordinates(coordinates.getX() + 1, coordinates.getY() + 1)).ifPresent(neighbours::add);
        return neighbours;
    }

    Optional<T> getTile(T[][] tiles, Coordinates cords) {
        if (cords.getX() < tiles.length && cords.getY() < tiles[0].length && cords.getX() >= 0 && cords.getY() >= 0) {
            return Optional.of(tiles[cords.getX()][cords.getY()]);
        } else {
            return Optional.empty();
        }
    }
}
