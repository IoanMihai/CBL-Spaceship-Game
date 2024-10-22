import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 */

public class Projectile {
    private final int SPEED = 10;
    private final int X;
    private int y;
    private Rectangle rectangle;
    private int damage;

    /**
     * The constructor for the Projectile class
     * @param x The x posistion of the projectile
     * @param y The initial y posistion of the projectile
     */
    public Projectile(int x, int y, int d) {

        this.X = x;
        this.y = y;
        this.damage = d;
    }

    /**
     * shifts the projectile up (used to update player projectiles)
     */
    public void update() {
        y -= SPEED;
        rectangle = new Rectangle(X, y, 10, 30);
    }

    /**
     * shifts the projectile down (used to update enemy projectiles)
     */
    public void updateEnemyProjectile() {
        y += SPEED;
        rectangle = new Rectangle(X, y, 5, 15);
    }

    /**
     * Draws the projectile to the screen
     * @param g The graphics for the panel
     * @param type The type of projectile that needs to be drawn
     */
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

    //Getters and Setters

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
