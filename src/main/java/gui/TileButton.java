package gui;

import board.Coordinates;
import bot.PlayerTile;
import javafx.scene.control.Button;


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
        if(playerTile!=null && playerTile.getColor().isPresent())
            setStyle("-fx-background-color: #" + playerTile.getColor().get().toString().substring(2, 8));
        else
            setStyle("-fx-background-color: #000000");


    }
}
