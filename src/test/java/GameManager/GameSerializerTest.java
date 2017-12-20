package GameManager;

import board.Coordinates;
import board.Tile;
import org.junit.Test;

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
        GameSerializer.gsonSave(gb.getGameInstance(), "test.txt");
        Game game = GameSerializer.load("test.txt");
        Tile[][] tiles2 = game.board.getTiles();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                assert tiles2[i][j].getCost() == 3*i+j;

            }
    }

}