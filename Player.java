import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player extends JPanel {
    private final int SPEED = 10;
    private final int Y;
    private int x;
    private final int WIDTH;


    public Player(int startX, int startY, int width){
        this.x = startX;
        this.Y = startY;
        this.WIDTH = width;
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

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, Y, 50, 50);
        
    }


}
