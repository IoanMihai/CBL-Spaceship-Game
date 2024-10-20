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
    private Image enemyImage;
    private Rectangle rectangle;
    private boolean isAlive = true;
    private int SPEED = 5;
    private final int WIDTH = 1080;
    private boolean movingDirection;

    Enemy(String image, int h, int d, int initialX, int initialY) {
        this.health = h;
        this.damage = d;
        this.x = initialX;
        this.y = initialY;
        this.isAlive = true;
        Toolkit t=Toolkit.getDefaultToolkit();
        enemyImage = t.getImage(image);
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

    public void draw(Graphics g) {
        if (this.isAlive()) {
            g.drawImage(enemyImage, x, y, null);
        }
    }


    public boolean isAlive() {
        return isAlive;
    }

    public boolean kill() {
        if (isAlive) {
            isAlive = false;
            return true;
        } else {
            return false;
        }
        
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

    public void updateRectangle(int width, int height) {
        rectangle = new Rectangle(x, y, width, height);
    }

}
