import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MovingEnemy extends Enemy{
    private int SPEED = 5;
    private boolean movingDirection;
    private final int WIDTH = 1080;

    public MovingEnemy(int h, int d, int initialX, int initialY) {
        super(h, d, initialX, initialY);
        this.movingDirection = new Random().nextBoolean();

    }

    @Override
    public void update() {
        if (super.getX() > WIDTH - 50) {
            movingDirection = !movingDirection;
        } else if (super.getX() < 0) {
            movingDirection = !movingDirection;
        }

        if (movingDirection) {
            super.setX(SPEED);
        } else {
            super.setX(-SPEED);
        }

        super.updateRectangle(40 ,40);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(super.getX(), super.getY(), 40, 40);
    }

}
