import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Upgrades {
    private int WIDTH = 16;
    private int HEIGHT = 16;
    private int x;
    private int y;
    private BufferedImage healingPotion;
    private int heal;
    
    Upgrades(int healNumber, int initialX, int initialY) {
        try {
            healingPotion = ImageIO.read(new File("Assets/hp_potion.png"));
        } catch (IOException ex) {
            System.out.println("Ghinion frate");
        }

        this.heal = healNumber;
        this.x = initialX;
        this.y = initialY;
    }

    public void update() {
        y += 3;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.drawImage(healingPotion, x, y, null);
    }

}
