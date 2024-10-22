import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class BossEnemy extends Enemy {
    private int SPEED = 7;
    private boolean movingDirectionX;
    private boolean movingDirectionY;
    private final int WIDTH = 1080;
    private final int HEIGHT = 300;
    

    public BossEnemy(String image, int h, int d, int initialX, int initialY) {
        super(image, h, d, initialX, initialY);
        movingDirectionX = true;
        movingDirectionY = false;
    }
    
    @Override
    public void update() {
        if (super.getX() > WIDTH - 50 || super.getX() < 0) {
            movingDirectionX = !movingDirectionX;
        }

        if(super.getY() > HEIGHT - 90 || super.getY() < 0) {
            movingDirectionY = !movingDirectionY;
        }


        if (movingDirectionX) {
            super.setX(SPEED);
        } else {
            super.setX(-SPEED);
        }

        if(movingDirectionY) {
            super.setY(SPEED);
        } else {
            super.setY(-SPEED);
        }

        super.updateRectangle(80, 80);
    }
}
