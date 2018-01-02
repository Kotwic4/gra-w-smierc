package bot;

import board.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PlayerBoard {

    private PlayerTile[][] playerTiles;
    private int width;
    private int height;

    private boolean checkCoords(Coordinates cords){
        return cords.getX() < width && cords.getY() < height;
    }

    public PlayerBoard(PlayerTile[][] playerTiles) {
        this.playerTiles = playerTiles;
        width = playerTiles.length;
        height = playerTiles[0].length;
    }

    public Optional<PlayerTile> getPlayerTile(Coordinates cords) {
        if(checkCoords(cords)) return Optional.empty();
        else return Optional.of(playerTiles[cords.getX()][cords.getY()]);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<PlayerTile> getAccessibleTiles(){
        List<PlayerTile> list = new ArrayList<>();
        for(PlayerTile[] array : playerTiles){
            list.addAll(Arrays.asList(array));
        }
        return list.stream()
                .filter(PlayerTile::isAccessible)
                .collect(Collectors.toList());
    }

    void update(){
        //todo
    }


}
