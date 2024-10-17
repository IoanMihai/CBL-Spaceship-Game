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


    public Player(int startX, int startY, int width){
        this.x = startX;
        this.Y = startY;
        this.WIDTH = width;
        this.score = 0;
        this.playerHealth = 100;
    }

    public void move(boolean dir){
        if(dir){
            x += SPEED;
        } else {
            x -= SPEED;
        }

        if(x > WIDTH - 50) {
            x = WIDTH - 50;
        } else if(x < 0){
            x = 0;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x, Y, 50, 50);
    }

    public int getX(){
        return x;
    }

    public String getScore() {
        String s = Integer.toString(score);
        return s;
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
}
