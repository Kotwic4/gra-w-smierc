package gameManager;

import board.Board;
import board.Coordinates;
import board.Tile;
import bot.Player;
import util.BoardHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Michal Pawłowicz on 02.01.18.
 */
public class GameDeserializer {

    private DummyGame dummyGame;

    public GameDeserializer(DummyGame dummyGame){
        this.dummyGame = dummyGame;
    }

    public Optional<Game> deserialize(){
        if(dummyGame == null){
            return Optional.empty();
        }

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < dummyGame.getPlayersNumber(); i++)
            players.add(new Player(dummyGame.getPlayerColor(i), "", dummyGame.getPlayerId(i)));

        Board.BoardBuilder boardBuilder = new Board.BoardBuilder(dummyGame.getBoardDimension().width,dummyGame.getBoardDimension().height, new BoardHelper<>());
        for (int i = 0; i < dummyGame.getBoardLenght(); i++) {
            for (int j = 0; j < dummyGame.getNumberOfTiles(); j++) {
                boardBuilder.setTileCost(dummyGame.getCostOnTile(i, j),new Coordinates(i,j));
                if (dummyGame.getStrongholdOfTile(i, j)) {
                    boardBuilder.markAsStronghold(new Coordinates(i, j));
                }
            }
        }

        return Optional.of(new Game(players, boardBuilder.build()));
    }
}
