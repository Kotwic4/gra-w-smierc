package GameManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class GameSerializer {
    public static void save(Game game, String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonedGame = gson.toJson(game);
        try (PrintWriter fileWriter = new PrintWriter(path)) {
            fileWriter.println(jsonedGame);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Game load(String path) {
        List<Player> players = null;
        Board board = null;
        try (JsonReader reader = new JsonReader(new FileReader(path))) {
            Gson gson = new Gson();

            DummyGame dummyGame = gson.fromJson(reader, DummyGame.class);
            players = new ArrayList<>();
            System.out.println(dummyGame);
            for (int i = 0; i < dummyGame.players.length; i++)
                players.add(new Player(dummyGame.players[i].color, dummyGame.players[i].id));
            board = new Board(dummyGame.board.size);
            for (int i = 0; i < dummyGame.board.board.length; i++)
                for (int j = 0; j < dummyGame.board.board[i].length; j++)
                    board.setTile(new Tile(dummyGame.board.board[i][j].cost, dummyGame.board.board[i][j].isBase), i, j);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Game(players, board);
    }

    /**
     * Created by Albert on 10.12.2017.
     */
    static class DummyGame {
        class DummyPlayer {
            int id;
            Color color;
        }

        class DummyBoard {
            class DummyTile {
                class DummyOrganism {
                    int playerId;
                }

                int cost;
                boolean isBase;
                DummyOrganism organism;
            }

            DummyTile[][] board;
            int size;
        }

        DummyBoard board;
        DummyPlayer[] players;
    }
}
