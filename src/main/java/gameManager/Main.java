package gameManager;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

public class Main {


    public static void main(String[] args) {
        GameBuilder a = new GameBuilder(10,10);
        a.createBoard();
        //Game game = a.getGameInstance();

        /*
         * [TODO]
         * I will refactor that, immediately!
         */
        try {
            DummyGame dummyGame = GameSerializer.load("test").get();
            GameDeserializer gameDeserializer = new GameDeserializer(dummyGame);
            Game game = gameDeserializer.deserialize().get();
            GameSerializer.save(game, "test2");
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}



