package bot;

import gui.PlayerController;

public class GuiPlayer extends PlayerStrategy {

    private PlayerController gui;

    public GuiPlayer(PlayerController gui) {
        this.gui = gui;
    }

    @Override
    public void doTurn(Player player) {
        gui.doGuiTurn(player);
    }

}
