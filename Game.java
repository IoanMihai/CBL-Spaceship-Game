import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class Game extends JPanel implements ActionListener {
    JFrame gameWindow = new JFrame("SBL Space Ship Game");
    Player player = new Player(490, 600, 1080);
    ArrayList<Projectile> projectiles = new ArrayList<>();
    KeyInput keyInput = new KeyInput();
    Timer timer = new Timer(10, this);
    ArrayList<Enemy> enemies = new ArrayList<>();

    public void builIt() {
        SwingUtilities.invokeLater( () -> {

            gameWindow.add(this);
            enemies = createEnemies();
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);  
        for (Projectile projectile: projectiles) {
            projectile.draw(g); 
        }
        for (Enemy enemy: enemies) {
            if (enemy.isAlive()) {
                enemy.draw(g);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char keyPressed = keyInput.getKey();
        char direction = keyInput.getDir();
        if (direction == 'a') {
            player.move(false);
        } else if (direction == 'd') {
            player.move(true);
        } 
        if (keyPressed == ' ') {
            projectiles.add(new Projectile(player.getX()+20, 590));
            keyInput.resetKey();
        }

        ArrayList<Projectile> tempProjectiles = new ArrayList<>();
        for (Projectile projectile: projectiles) {
            projectile.update();
            if (projectile.getY() >= 0) {
                tempProjectiles.add(projectile);
            }


            if (projectile.getRectangle() != null) {
                for (Enemy enemy : enemies) {
                    if (enemy.getRectangle() != null) {
                        if (projectile.getRectangle().intersects(enemy.getRectangle())) {
                            enemy.setHealth(enemy.getHealth() - 5);
                        }
                    }
                    if (enemy.getHealth() <= 0) {
                        enemy.kill();
                    }
                }
            }
        }
        projectiles = tempProjectiles;
        System.out.println(projectiles.size());
        gameWindow.repaint();
    }

    public ArrayList<Enemy> createEnemies() {
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        for (int i = 0; i <= 8; i ++) {
            enemyList.add(new Enemy(10, 10, 100*i + 100, 30));
        }

        return enemyList;
    }
}
