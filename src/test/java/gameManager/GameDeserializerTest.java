package gameManager;

import board.Board;
import org.junit.Test;
import util.BoardHelper;

import java.io.StringReader;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;


/**
 * Created by Michał Pawłowicz on 02.01.18.
 */
public class GameDeserializerTest {
    @Test
    public void gameDeserializeTest(){
        Game expected = new Game(
                new LinkedList<>(),
                new Board.BoardBuilder(2,2,new BoardHelper<>()).build()
        );

        DummyGame dummyGame = GameSerializer.load(new StringReader("{\"players\":[],\"board\":{\"tiles\":[[{\"cost\":1,\"stronghold\":false},{\"cost\":1,\"stronghold\":false}],[{\"cost\":1,\"stronghold\":false},{\"cost\":1,\"stronghold\":false}]],\"width\":2,\"height\":2}}")).get();
        GameDeserializer gameDeserializer = new GameDeserializer(dummyGame);
        Game result = gameDeserializer.deserialize().get();
        assertEquals(expected,result);
    }
}
