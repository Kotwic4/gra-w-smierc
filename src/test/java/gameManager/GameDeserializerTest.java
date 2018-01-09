//package gameManager;
//
//import board.Board;
//import org.junit.Test;
//import java.util.LinkedList;
//import static org.junit.Assert.assertEquals;
//
//
///**
// * Created by Michał Pawłowicz on 02.01.18.
// */
//public class GameDeserializerTest {
//    @Test
//    public void gameDeserializeTest(){
//        Game expected = new Game(
//                new LinkedList<>(),
//                new Board(2, 2)
//        );
//
//        DummyGame dummyGame = GameSerializer.loadFromString("{\"players\":[],\"board\":{\"tiles\":[[{\"cost\":0,\"stronghold\":false},{\"cost\":0,\"stronghold\":false}],[{\"cost\":0,\"stronghold\":false},{\"cost\":0,\"stronghold\":false}]],\"width\":2,\"height\":2}}");
//        GameDeserializer gameDeserializer = new GameDeserializer(dummyGame);
//        Game result = gameDeserializer.deserialize().get();
//        assertEquals(result, expected);
//    }
//}
