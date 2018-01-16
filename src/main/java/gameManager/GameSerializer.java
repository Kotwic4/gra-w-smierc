package gameManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Optional;

class GameSerializer {
    public static StringWriter save(Game game) {
        /*if(new File(path).exists()) {
            throw new FileAlreadyExistsException(path);
        }*/

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonedGame = gson.toJson(game);
        StringWriter sw = new StringWriter();
        sw.write(jsonedGame);
        return sw;
    }

    public static Optional<DummyGame> load(StringReader stringReader) {
        DummyGame dummyGame;
        try (JsonReader reader = new JsonReader(stringReader)) {
            Gson gson = new Gson();
            dummyGame = gson.fromJson(reader, DummyGame.class);
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(dummyGame);
    }

    public static DummyGame loadFromString(String jsonGame)
    {
        DummyGame dummyGame;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        dummyGame = gson.fromJson(jsonGame, DummyGame.class);
        return dummyGame;
    }

}