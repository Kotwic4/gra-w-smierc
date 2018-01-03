package GameManager;

import board.Board;
import board.Coordinates;
import board.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Michal Pawłowicz on 02.01.18.
 */
public class GameDeserializer {

    public DummyGame dummyGame;

    public GameDeserializer(DummyGame dummyGame){
        this.dummyGame = dummyGame;
    }

    public Optional<Game> deserialize(){
        if(dummyGame == null){
            return Optional.empty();
        }

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < dummyGame.getPlayersNumber(); i++)
            players.add(new Player(dummyGame.getPlayerColor(i), dummyGame.getPlayerId(i)));

        Board board = new Board(dummyGame.getBoardDimension().width,dummyGame.getBoardDimension().height);
        for (int i = 0; i < dummyGame.getBoardLenght(); i++) {
            for (int j = 0; j < dummyGame.getNumberOfTiles(); j++) {
                Tile tile = board.getTile(new Coordinates(i, j));
                tile.setCost(dummyGame.getCostOnTile(i, j));
                if (dummyGame.getStrongholdOfTile(i, j)) {
                    board.markAsStronghold(new Coordinates(i, j));
                }
            }
        }

        return Optional.of(new Game(players, board));
    }
}
