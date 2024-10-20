import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Upgrades {
    private int WIDTH = 40;
    private int HEIGHT = 40;
    private int x;
    private int y;
    private BufferedImage upgraBufferedImage;
    private Image upgradeImage;
    private final int HEAL = 10;
    private Rectangle rectangle;
    private int type;
    
    Upgrades(int initialX, int initialY, int givenType) {
        this.x = initialX;
        this.y = initialY; 
        this.rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        this.type = givenType;
        readImage();
    }

    private void readImage() {
        switch (type) {
            case Constants.HEALING_UPGRADE:
                try {
                    upgraBufferedImage = ImageIO.read(new File("Assets/hp_potion_x5.png"));
                } catch (IOException ex) {
                    System.out.println("No image found for healing asset. ");
                }
                upgradeImage = upgraBufferedImage.getScaledInstance(WIDTH, HEIGHT, 
                    Image.SCALE_DEFAULT);
                break;
            case Constants.DAMAGE_UPGRADE:
                try {
                    upgraBufferedImage = ImageIO.read(new File("Assets/boost_upgrade.png"));
                } catch (IOException ex) {
                    System.out.println("No image found for healing asset. ");
                }
                upgradeImage = upgraBufferedImage.getScaledInstance(WIDTH, HEIGHT, 
                    Image.SCALE_DEFAULT);
                break;
            default:
                System.out.println("No image found for this item.");
                break;
        }
    }

    public void update() {
        y += 3;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.drawImage(upgradeImage, x, y, null);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getHealIncrease() {
        return HEAL;
    }

    public int getUpgradeType() {
        return type;
    }

}
