import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Projectile {
    private final int SPEED = 10;
    private final int X;
    private int y;
    private Rectangle rectangle;
    private int damage;

    public Projectile(int x, int y, int d) {
        this.X = x;
        this.y = y;
        this.damage = d;
    }

    public void update() {
        y -= SPEED;
        rectangle = new Rectangle(X, y, 10, 30);
    }

    public void updateEnemyProjectile() {
        y += SPEED;
        rectangle = new Rectangle(X, y, 5, 15);
    }

    public void draw(Graphics g, int type) {
        Toolkit t = Toolkit.getDefaultToolkit();
        switch (type) {
            case Constants.PLAYER_DEFAULT_PROJECTILE:
                g.drawImage(t.getImage("Assets/PlayerProjectile.png"), X, y, null);
                break;
            case Constants.ENEMY_DEFAULT_PROJECTILE:
                g.drawImage(t.getImage("Assets/EnemyProjectile.png"), X, y, null);
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

    public int getDamage() {
        return damage;
    }
}
