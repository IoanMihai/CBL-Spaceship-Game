import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Projectile {
    private final int SPEED = 10;
    private final int X;
    private int y;

    public Projectile(int x, int y) {
        this.X = x;
        this.y = y;
    }

    public void update() {
        y -= SPEED;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(X, y, 10, 30);
    }

    public int getY() {
        return y;
    }

}
