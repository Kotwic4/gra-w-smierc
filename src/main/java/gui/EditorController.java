package gui;

        import board.Board;
        import bot.GuiPlayer;
        import bot.SimpleBot;
        import gameManager.Game;
        import gameManager.GameBuilder;
        import javafx.concurrent.Worker;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class EditorController {

        @FXML
        private Button newBoardButton;


        @FXML
        private void newBoardButtonHandler() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/BoardEdit.fxml"));
                Parent root = fxmlLoader.load();
                Stage window=(Stage)newBoardButton.getScene().getWindow();
                window.setScene(new Scene(root));
        }
}
