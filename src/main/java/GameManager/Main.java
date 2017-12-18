package GameManager;

public class Main {


    public static void main(String[] args) {
        // write your code here
        GameBuilder a = new GameBuilder(10);
        a.createBoard();
        Game game = a.getGameInstance();
        GameSerializer.gsonSave(game, "testboard.txt");
        GameSerializer.load("testboard.txt");
    }
}



