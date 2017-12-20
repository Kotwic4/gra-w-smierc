package GameManager;

import board.Coordinates;
import board.InvalidOrganismPositionException;
import board.Organism;
import board.Tile;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class GameSerializerTest {

    @Test
    public void testSaveAndLoad(){
        GameBuilder gb = new GameBuilder(3,3);
        Tile[][] tiles = gb.board.getTiles();
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j].setCost(3 * i + j);

            }
            gb.board.markAsStronghold(new Coordinates(1,i));
        }
        assert gb.board.getStrongholdList().size() == 3;
        GameSerializer.gsonSave(gb.getGameInstance(), "test.txt");

        Game game = GameSerializer.load("test.txt");
        Tile[][] tiles2 = game.board.getTiles();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                assert tiles2[i][j].getCost() == 3*i+j;

            }
        List<Tile> strongholds= game.board.getStrongholdList();
        assert strongholds.size() == 3;

    }

    @Test
    public void testLoadAndSave(){
        Game game = GameSerializer.load("test.txt");
        GameSerializer.gsonSave(game, "test2.txt");
        try {
            byte[] f1 = Files.readAllBytes(Paths.get("test.txt"));
            byte[] f2 = Files.readAllBytes(Paths.get("test2.txt"));
            assertArrayEquals(f1,f2);
        } catch (IOException e) {
            assert false;
            e.printStackTrace();
        }
    }


}