import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

interface EnemyInterface 
{
    void spawn();
}

public class Enemy {
    private int health;
    private int damage;
    private int x;
    private int y;
    private File enemyImage;
    private Rectangle rectangle;
    private boolean isAlive = true;
    private int SPEED = 5;
    private final int WIDTH = 1080;
    private boolean movingDirection;

    Enemy(int h, int d, int initialX, int initialY) {
        this.health = h;
        this.damage = d;
        this.x = initialX;
        this.y = initialY;
        this.movingDirection = false;
        // enemyImage = image;
    }   

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public void draw(Graphics g) {
        if (this.isAlive()) {
            g.setColor(Color.PINK);
            rectangle = new Rectangle(x, y, 40, 40);
            g.fillRect(x, y, 40, 40);
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    public void move() {
        
        if (x > WIDTH - 50) {
            movingDirection = !movingDirection;
        } else if (x < 0) {
            movingDirection = !movingDirection;
        }

        if (movingDirection) {
            x += SPEED;
        } else {
            x -= SPEED;
        }
    }

}
