package gui;

import board.Coordinates;
import bot.PlayerTile;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


public class TileButton extends Button {
    Coordinates coordinates;
    private PlayerTile playerTile;

    TileButton(Coordinates coordinates) {
        super(" ");
        this.coordinates = coordinates;
    }

    public void assignPlayerTile(PlayerTile playerTile){
        this.playerTile = playerTile;
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
}
