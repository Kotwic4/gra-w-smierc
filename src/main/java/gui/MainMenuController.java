package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController {

    @FXML
    private Button newGameButton;
    @FXML
    private Button boardEditorButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;

    @FXML
    private URL location;


    @FXML
    private ResourceBundle resources;
    public MainMenuController() {
    }
    @FXML
    private void printOutput()
    {
        newGameButton.setText("aaaa");
    }

    @FXML
    private void sceneHandler() throws IOException {
        System.out.println("Scene changing...");
        Parent root = FXMLLoader.load(getClass().getResource("/Game.fxml"));
        Stage window=(Stage)newGameButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void exitButtonHandler() {
        Stage stage =(Stage)newGameButton.getScene().getWindow();
        stage.close();
    }

}
