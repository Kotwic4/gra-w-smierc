package gameManager;

import board.Board;
import board.Coordinates;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class GameSerializerTest {

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

        Game expected = gb.getGameInstance();

        try {
            GameSerializer.save(gb.getGameInstance(), "testSaveAndLoad.txt");
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        }

        Game result = null;
        try {
            DummyGame dummyGame = GameSerializer.load("testSaveAndLoad.txt").get();
            result = new GameDeserializer(dummyGame).deserialize().get();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        assertEquals(expected, result);
    }

    @Test
    public void loadTest(){
        try {
            assertEquals(GameSerializer.load("testSaveAndLoad.txt").isPresent(), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadAndSave(){
        DummyGame dummyGame = null;
        Game game = null;
        try {
            assertEquals(GameSerializer.load("testSaveAndLoad.txt").isPresent(), true);
            dummyGame = GameSerializer.load("testSaveAndLoad.txt").get();
            assertEquals(new GameDeserializer(dummyGame).deserialize().isPresent(), true);
            game = new GameDeserializer(dummyGame).deserialize().get();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            GameSerializer.save(game, "testSaveAndLoad2.txt");
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        }
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