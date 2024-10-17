import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Projectile {
    private final int SPEED = 10;
    private final int X;
    private int y;
    private Rectangle rectangle;

    public Projectile(int x, int y) {
        this.X = x;
        this.y = y;
    }

    public void update() {
        y -= SPEED;
    }

    public void draw(Graphics g, int type) {
        switch (type) {
            case Constants.PLAYER_DEFAULT_PROJECTILE:
                g.setColor(Color.RED);
                rectangle = new Rectangle(X, y, 10, 30);
                g.fillRect(X, y, 10, 30);
                break;
            case Constants.ENEMY_DEFAULT_PROJECTILE:
                g.setColor(Color.GREEN);
                rectangle = new Rectangle(X, y, 5, 15);
                g.fillRect(X, y, 5, 15);
                break;
            default:
                break;
        }
    } 

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getY() {
        return y;
    }

}
