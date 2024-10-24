import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * The upgrade class tracks and updates the upgrades, the upgrade has an
 * x and y posistion and a type.
 */
public class Upgrades {
    private int width = 40; //Width of the upgrade
    private int height = 40; //Height of the upgrade
    private int x;
    private int y;
    private BufferedImage upgraBufferedImage;
    private Image upgradeImage;
    private final int heal = 10; //how much the healing upgrade heals the player
    private Rectangle rectangle; //Used for collisions
    private int type; //Type of upgrade it is
    
    /**
     * The constructor for the upgrade class.
     * @param initialX The x posistion of the upgrade
     * @param initialY The initial y posistion of the upgrade
     * @param givenType The type of upgrade it is (3 = healing upgrade, 4 = damage upgrade)
     */
    Upgrades(int initialX, int initialY, int givenType) {
        this.x = initialX;
        this.y = initialY; 
        this.rectangle = new Rectangle(x, y, width, height);
        this.type = givenType;
        readImage();
    }

    /**
     * Sets the image to the correct image depending on the type of upgrade.
     */
    private void readImage() {
        switch (type) {
            case Constants.HEALING_UPGRADE:
                try {
                    upgraBufferedImage = ImageIO.read(new File("Assets/hp_potion_x5.png"));
                } catch (IOException ex) {
                    System.out.println("No image found for healing asset. ");
                }
                upgradeImage = upgraBufferedImage.getScaledInstance(width, height, 
                    Image.SCALE_DEFAULT);
                break;
            case Constants.DAMAGE_UPGRADE:
                try {
                    upgraBufferedImage = ImageIO.read(new File("Assets/boost_upgrade.png"));
                } catch (IOException ex) {
                    System.out.println("No image found for healing asset. ");
                }
                upgradeImage = upgraBufferedImage.getScaledInstance(width, height, 
                    Image.SCALE_DEFAULT);
                break;
            default:
                System.out.println("No image found for this item.");
                break;
        }
    }

    /**
     * Updates the upgarde by moving it down.
     */
    public void update() {
        y += 3;
        rectangle = new Rectangle(x, y, width, height);
    }

    /**
     * Draws the upgrade to the screen.
     * @param g the graphics of the panel
     */
    public void draw(Graphics g) {
        g.drawImage(upgradeImage, x, y, null);
    }

    //Getters and Setters
    public int getY() {
        return y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getHealIncrease() {
        return heal;
    }

    public int getUpgradeType() {
        return type;
    }

}
