package gameManager;

import board.Organism;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.FileAlreadyExistsException;
import java.util.Optional;

class GameSerializer {
    public static StringWriter save(Game game) {
        /*if(new File(path).exists()) {
            throw new FileAlreadyExistsException(path);
        }*/
        JsonSerializer<Organism> serializer = (src, typeOfSrc, context) -> {
            JsonObject Organism = new JsonObject();

            Organism.addProperty("playerID", src.getPlayer().getId());

            return Organism;
        };
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Organism.class, serializer)
                .create();
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

}