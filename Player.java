import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player {
    private final int SPEED = 10;
    private final int Y;
    private int x;
    private final int WIDTH;
    private int score;
    private int playerHealth;
    private int waveCounter;
    private Rectangle rectangle;
    private Image playerImage;
    private int damage;


    public Player(int startX, int startY, int width, int initialDamage){
        this.x = startX;
        this.Y = startY;
        this.WIDTH = width;
        this.score = 0;
        this.waveCounter = 0;
        this.playerHealth = 100;
        Toolkit t = Toolkit.getDefaultToolkit();
        playerImage = t.getImage("Assets/Player.png");
        this.rectangle = new Rectangle(startX, startY, WIDTH, WIDTH);
        this.damage = initialDamage;
    }

    public void move(boolean dir){
        if (dir){
            x += SPEED;
        } else {
            x -= SPEED;
        }

        if(x > WIDTH - 50) {
            x = WIDTH - 50;
        } else if(x < 0){
            x = 0;
        }
        rectangle = new Rectangle(x, Y, 50, 50);
    }

    public void draw(Graphics g) {
        g.drawImage(playerImage, x, Y, null);
    }

    public int getX() {
        return x;
    }

    public int getWave() {
        return waveCounter;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public int getHealth() {
        return playerHealth;
    }

    public void decreaseHealth(int damage) {
        playerHealth -= damage;
    }

    public void increaseWaveCounter() {
        waveCounter++;
    }

    public void increaseHealth(int healthIncrease) {
        playerHealth = playerHealth + healthIncrease;
        if (playerHealth > 100) {
            playerHealth = 100;
        }
    }

    public void setDamage(int newDamage) { 
        damage = newDamage;
    }

    public int getDamage() {
        return damage;
    }
}
