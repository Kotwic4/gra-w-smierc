package gui;

import bot.Player;

public interface TurnCommunicator {
    void startHeadlessTurn(Player player);
    void endHeadlessTurn(Player player);
}
