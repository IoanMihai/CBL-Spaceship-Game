import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Game extends JPanel implements ActionListener {
    JFrame gameWindow = new JFrame("SBL Space Ship Game");
    Player player = new Player(490, 600, 1080);
    ArrayList<Projectile> projectiles = new ArrayList<>();
    KeyInput keyInput = new KeyInput();
    Timer timer = new Timer(10, this);

    public void builIt() {
        SwingUtilities.invokeLater( () -> {

            gameWindow.add(this);
            this.setBackground(Color.BLACK);
            this.setFocusable(true);
            this.addKeyListener(keyInput);

            gameWindow.setSize(1080,720);
            gameWindow.setVisible(true);
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            timer.start();
        } );
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        player.draw(g);  
        for(Projectile projectile: projectiles) {
            projectile.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char keyPressed = keyInput.getKey();
        char direction = keyInput.getDir();
        if(direction == 'a') {
            player.move(false);
        } else if(direction == 'd') {
            player.move(true);
        } 
        if (keyPressed == ' ') {
            projectiles.add(new Projectile(player.getX()+20, 590));
            keyInput.resetKey();
        }

        ArrayList<Projectile> tempProjectiles = new ArrayList<>();
        for(Projectile projectile: projectiles) {
            projectile.update();
            if(projectile.getY() >= 0) {
                tempProjectiles.add(projectile);
            }
        }
        projectiles = tempProjectiles;
        System.out.println(projectiles.size());
        gameWindow.repaint();
    }
}
