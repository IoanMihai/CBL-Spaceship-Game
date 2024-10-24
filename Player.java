import java.awt.*;

/*
 * The player clas tracks and updates the player, the player has an x
 * and y posistion, speed, health, damage, score, and a wave counter
 */
public class Player {
    private final int speed = 10;
    private final int y;
    private int x;
    private final int width; //The width of the frame the game is in
    private int score;
    private int playerHealth;
    private int waveCounter; //Used to scale up the waves in difficulty
    private Rectangle rectangle; //Used for collisions
    private Image playerImage;
    private int damage;

    /**
     * Constructor for the Player class.
     * @param startX The initial x posistion of the player 
     * @param startY The y posistion of the player
     * @param width The width of the screen
     */

    public Player(int startX, int startY, int width, int initialDamage){
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.score = 0;
        this.waveCounter = 0;
        this.playerHealth = 100;
        Toolkit t = Toolkit.getDefaultToolkit();
        playerImage = t.getImage("Assets/Player.png");
        this.rectangle = new Rectangle(startX, startY, width, width);
        this.damage = initialDamage;
    }

    /**
     * Shifts the Player left or right, while not letting the player go of the screen.
     * @param dir The direction the player need to move in
     */

    public void move(boolean dir) {
        if (dir) {
            x += speed;
        } else {
            x -= speed;
        }

        if (x > width - 50) {
            x = width - 50;
        } else if (x < 0) {
            x = 0;
        }
        rectangle = new Rectangle(x, y, 50, 50);
    }

    /**
     * Draws the player to the screen.
     * @param g The graphics of the panel
     */
    public void draw(Graphics g) {
        g.drawImage(playerImage, x, y, null);
    }

    //Getters and Setters

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
