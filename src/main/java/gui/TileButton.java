package gui;

import board.Coordinates;
import bot.PlayerTile;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.Observer;


public class TileButton extends Button implements Observer{
    Coordinates coordinates;
    private PlayerTile playerTile;

    TileButton(Coordinates coordinates) {
        super(" ");
        this.coordinates = coordinates;
    }

    public void assignPlayerTile(PlayerTile playerTile){
        if(this.playerTile!=null)
            playerTile.deleteObserver(this);
        this.playerTile = playerTile;
        playerTile.addObserver(this);
        updateButtonColor();
    }

    public void inhabitAssignedTile(){
        if(playerTile!=null)
            playerTile.inhabit();
        updateButtonColor();
    }

    private void updateButtonColor(){
        if(playerTile!=null && playerTile.isVisible())
            setStyle("-fx-background-color: #" + playerTile.getColor().orElse(Color.WHITE).toString().substring(2, 8));
        else
            setStyle("-fx-background-color: #000000");
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        Platform.runLater(this::updateButtonColor);
    }
}
