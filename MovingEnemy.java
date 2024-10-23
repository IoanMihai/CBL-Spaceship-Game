import java.util.Random;

/**
 * 
 */

public class MovingEnemy extends Enemy {
    private int speed = 5;
    private boolean movingDirection;
    private final int width = 1080;
    
    /**
     * The constructor for the Moving Enemy.
     * @param image The path for to the image used to represent the moving enemy
     * @param h The amount of health the enemy has
     * @param d The amount of damage the enemy does
     * @param initialX The initial x posistion of the enemy
     * @param initialY The y posistion of the enemy
     */
    public MovingEnemy(String image, int h, int d, int initialX, int initialY) {
        super(image, h, d, initialX, initialY);
        this.movingDirection = new Random().nextBoolean();

    }

    /**
     * Moves the enemy either left or right and when it reaches the end of the screen it 
     * switches the direction it moves in. Then updates the enemy's rectangle
     */

    @Override
    public void update() {
        if (super.getX() > width - 50) {
            movingDirection = !movingDirection;
        } else if (super.getX() < 0) {
            movingDirection = !movingDirection;
        }

        if (movingDirection) {
            super.setX(speed);
        } else {
            super.setX(-speed);
        }

        super.updateRectangle(40, 40);
    }

}
