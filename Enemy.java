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
    private boolean isAlive;
    //private final int WIDTH = 1080;

    Enemy(int h, int d, int initialX, int initialY) {
        this.health = h;
        this.damage = d;
        this.x = initialX;
        this.y = initialY;
        this.isAlive = true;
        // enemyImage = image;
    }   

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, 40, 40);
    }

    public void update() {
        //shoot projectile or any other things we need to change in the game loop
        this.updateRectangle(40, 40);
    }

    //getters and setters

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void setX(int speed) {
        x += speed;
    }

    protected void setY(int speed) {
        y += speed;
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

    public void updateRectangle(int width, int height) {
        rectangle = new Rectangle(x, y, width, height);
    }

}
