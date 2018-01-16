package gameManager;

import board.Board;
import board.Coordinates;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class GameSerializerTest {

    String testSaveAndLoad = "{\"players\": [],\"board\": {\"tiles\": [[{\"cost\": 0,\"stronghold\": false},{\"cost\": 1,\"stronghold\": false},{\"cost\": 2,\"stronghold\": false}],[{\"cost\": 3,\"stronghold\": true},{\"cost\": 4,\"stronghold\": true},{\"cost\": 5,          \"stronghold\": true        }],[{\"cost\": 6,\"stronghold\": false},{\"cost\": 7,\"stronghold\": false},{\"cost\": 8,\"stronghold\": false}]],\"width\": 3,\"height\": 3}}\r\n";

    @Test
    public void testSaveAndLoad(){
        GameBuilder gb = new GameBuilder(3,3);
        Board.BoardBuilder boardBuilder = gb.boardBuilder;
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                boardBuilder.setTileCost(3 * i + j,new Coordinates(i,j));

            }
            boardBuilder.markAsStronghold(new Coordinates(1,i));
        }

        Game expected = gb.createBoard().getGameInstance();

        StringWriter sw = GameSerializer.save(gb.getGameInstance());//, "testSaveAndLoad.txt"

        Game result = null;

        DummyGame dummyGame = GameSerializer.load(new StringReader(sw.toString())).get();
        result = new GameDeserializer(dummyGame).deserialize().get();


        assertEquals(expected, result);
    }

    @Test
    public void loadTest(){
        assertEquals(GameSerializer.load(new StringReader(testSaveAndLoad)).isPresent(), true);
    }

    @Test
    public void testLoadAndSave(){
        DummyGame dummyGame = null;
        Game game = null;
        assertEquals(GameSerializer.load(new StringReader(testSaveAndLoad)).isPresent(), true);
        dummyGame = GameSerializer.load(new StringReader(testSaveAndLoad)).get();
        assertEquals(new GameDeserializer(dummyGame).deserialize().isPresent(), true);
        game = new GameDeserializer(dummyGame).deserialize().get();
        GameSerializer.save(game);//, "testSaveAndLoad2.txt"
        try {
            byte[] f1 = Files.readAllBytes(Paths.get("testSaveAndLoad.txt"));
            byte[] f2 = Files.readAllBytes(Paths.get("testSaveAndLoad2.txt"));
            //assertArrayEquals(f1.toString().getBytes(),f2.toString().getBytes());
            assertEquals(f1.length, f2.length);
            for(int i=0; i<f1.length; i++)
            {
                assertEquals(f1[i], f2[i]);
            }
        } catch (IOException e) {
            assert false;
            e.printStackTrace();
        }
    }


}