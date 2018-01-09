package gui;

import board.Coordinates;
import javafx.scene.control.Button;


public class PositionedButton  extends Button {
    Coordinates coordinates;

    public PositionedButton(Coordinates coordinates, String text2) {
        super(text2);
        this.coordinates = coordinates;
    }
}
