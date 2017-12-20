package GameManager;

public class Main {


    public static void main(String[] args) {
        // write your code here
        GameBuilder a = new GameBuilder(10,10);
        a.createBoard();
        //Game game = a.getGameInstance();

        Game game = GameSerializer.load("testboard.txt");
        GameSerializer.gsonSave(game, "testboard.txt");
    }
}



