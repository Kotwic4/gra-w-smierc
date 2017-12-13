package gui;

import bot.IPlayer;

public interface IPlayerGui {
    void makeGuiTurn(IPlayer player);
    void startNoGuiTurn(IPlayer player);
    void endNoGuiTurn(IPlayer player);
}
