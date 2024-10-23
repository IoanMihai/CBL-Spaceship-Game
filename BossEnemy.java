/**
 * 
 */

public class BossEnemy extends Enemy{

    private int speed = 7;
    private boolean movingDirectionX;
    private boolean movingDirectionY;
    private final int width = 1080;
    private final int height = 300;
    
    /**
     * The constructor for the Boss Enemy
     * @param image The path of the image used to represent the Boss Enemy
     * @param h The amount of health the Boss Enemy has
     * @param d The amount of damage the Boss Enemy does
     * @param initialX The initial x posistion of the Boss Enemy
     * @param initialY The initial y posistion of the Boss Enemy
     */
    public BossEnemy(String image, int h, int d, int initialX, int initialY) {
        super(image, h, d, initialX, initialY);
        movingDirectionX = true;
        movingDirectionY = false;
    }
    
    /**
     * Moves the boss enemy up and down, only allowing it to be on part of the screen.
     * Also updates the boss enemy's rectangle
     */
    @Override
    public void update() {
        if (super.getX() > width - 50 || super.getX() < 0) {
            movingDirectionX = !movingDirectionX;
        }

        if (super.getY() > height - 90 || super.getY() < 0) {
            movingDirectionY = !movingDirectionY;
        }


        if (movingDirectionX) {
            super.setX(speed);
        } else {
            super.setX(-speed);
        }

        if (movingDirectionY) {
            super.setY(speed);
        } else {
            super.setY(-speed);
        }

        super.updateRectangle(80, 80);
    }
}
