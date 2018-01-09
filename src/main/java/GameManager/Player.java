package GameManager;


import java.awt.*;

public class Player {
    Color color;
    int id;

    public Player(Color color, int id) {
        this.color = color;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        return color != null ? color.equals(player.color) : player.color == null;
    }
}
