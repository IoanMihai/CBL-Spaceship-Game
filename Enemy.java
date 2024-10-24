import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The enemy is used to track and update the enemy, it has
 * an integer health and damage, an x and y posistion, and a
 * boolean if it's alive.
 */

public class Enemy {
    private int health;
    private int damage;
    private int x;
    private int y;
    private Image enemyImage;
    private Rectangle rectangle; //Used for collisions
    private boolean isAlive = true;

    /**
     * The constuctor for the Enemy class
     * @param image The path to the image used to represent the enemy
     * @param h The amount of health the enemy has
     * @param d The amount of damage the enemy does
     * @param initialX The initial x posistion of the enemy
     * @param initialY The initial y posistion of the enemy
     */
    Enemy(String image, int h, int d, int initialX, int initialY) {
        this.health = h;
        this.damage = d;
        this.x = initialX;
        this.y = initialY;
        this.isAlive = true;
        Toolkit t = Toolkit.getDefaultToolkit();
        enemyImage = t.getImage(image);
    }   

    /**
     * Updates the enemy on the screen
     */
    public void update() {
        //shoot projectile or any other things we need to change in the game loop
        this.updateRectangle(40, 40);
    }

    /**
     * Draws the enemy to the screen
     * @param g The graphics of the panel
     */
    public void draw(Graphics g) {
        if (this.isAlive()) {
            g.drawImage(enemyImage, x, y, null);
        }
    }

    /**
     * Updates the isAlive boolean
     * @return isAlive
     */
    public boolean kill() {
        if (isAlive) {
            isAlive = false;
            return true;
        } else {
            return false;
        }
        
    }

    public void updateRectangle(int width, int height) {
        rectangle = new Rectangle(x, y, width, height);
    }

    //Getters and Setters

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

    public boolean isAlive() {
        return isAlive;
    }

}
