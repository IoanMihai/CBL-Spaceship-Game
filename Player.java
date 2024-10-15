import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player {
    private final int SPEED = 10;
    private final int Y;
    private int x;
    private final int WIDTH;
    private int score;


    public Player(int startX, int startY, int width){
        this.x = startX;
        this.Y = startY;
        this.WIDTH = width;
        this.score = 0;
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

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

}
