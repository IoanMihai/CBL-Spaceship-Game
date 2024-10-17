import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class Game extends JPanel implements ActionListener {
    JFrame gameWindow = new JFrame("SBL Space Ship Game");
    JPanel healthPanel;
    JPanel scorePanel;
    JPanel gamePanel;
    JProgressBar healthBar;
    JTextField playerHealthText;
    JTextField playerScoreText;
    JTextField scoreNumber;
    JButton exitButton;
    Player player = new Player(490, 500, 1080);
    ArrayList<Projectile> projectiles = new ArrayList<>();
    ArrayList<Projectile> enemyProjectiles = new ArrayList<>();
    KeyInput keyInput = new KeyInput();
    Timer timer = new Timer(10, this);
    ArrayList<Enemy> enemies = new ArrayList<>();
    private int waveNumber = 1;

    public void builIt() {
        SwingUtilities.invokeLater( () -> {

            gameWindow.add(this);
            enemies = createEnemies();
            this.setBackground(Color.BLACK);
            this.setFocusable(true);
            this.addKeyListener(keyInput);
            this.setLayout(null);

            gamePanel = new JPanel();
            gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

            createHealthPanel();
            createScorePanel();

            gamePanel.add(healthPanel);
            gamePanel.add(scorePanel);

            gameWindow.getContentPane().add(gamePanel, BorderLayout.SOUTH);
            
            buttonListeners();
            gameWindow.setSize(1080, 720);
            gameWindow.setVisible(true);
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            timer.start();
        } );
    }

    public void createScorePanel() {
        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.setBackground(Color.RED);
        playerScoreText = new JTextField(5);
        playerScoreText.setText("Score:");
        playerScoreText.setBackground(Color.RED);
        playerScoreText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        playerScoreText.setBorder(BorderFactory.createEmptyBorder());
        playerScoreText.setEditable(false);
        scorePanel.add(playerScoreText);
        scorePanel.add(Box.createRigidArea(new Dimension(50, 30)));
        scoreNumber = new JTextField(5);
        scoreNumber.setBackground(Color.RED);
        scoreNumber.setText("0");
        scoreNumber.setFont(new Font("Times New Roman", Font.BOLD, 20));
        scoreNumber.setBorder(BorderFactory.createEmptyBorder());
        scoreNumber.setEditable(false);
        scorePanel.add(scoreNumber);
        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.WHITE);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        scorePanel.add(exitButton);
        scorePanel.add(Box.createRigidArea(new Dimension(50, 30)));
    }
    

    public void createHealthPanel() {
        healthPanel = new JPanel();
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.X_AXIS));
        healthBar = new JProgressBar();
        playerHealthText = new JTextField(8);
        playerHealthText.setText("Health:");
        playerHealthText.setBackground(Color.RED);
        playerHealthText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        playerHealthText.setBorder(BorderFactory.createEmptyBorder());
        playerHealthText.setEditable(false);
        healthBar.setPreferredSize(new Dimension(300, 30));
        healthBar.setValue(100);
        healthPanel.add(playerHealthText);
        healthPanel.add(Box.createRigidArea(new Dimension(300, 30)));
        healthPanel.add(healthBar);
        healthPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        healthPanel.setBackground(Color.RED);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);  
        for (Projectile projectile: projectiles) {
            projectile.draw(g, Constants.PLAYER_DEFAULT_PROJECTILE); 
        }

        for (Projectile enemProjectile : enemyProjectiles) {
            enemProjectile.draw(g, Constants.ENEMY_DEFAULT_PROJECTILE);
        }

        for (Enemy enemy: enemies) {
            if (enemy.getIsAlive()) {
                enemy.draw(g);
            }
        }
        scoreNumber.setText(Integer.toString(player.getScore()));

        if (player.getHealth() <= 0) {
            ScoreMenu scoreMenu = new ScoreMenu(player.getScore());
            gameWindow.setVisible(false);
            gameWindow.dispose();
            scoreMenu.buildIt();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        keyInput.reduceTimer();
        healthBar.setValue(player.getHealth());

        char keyPressed = keyInput.getKey();
        char direction = keyInput.getDir();
        if (direction == 'a') {
            player.move(false);
        } else if (direction == 'd') {
            player.move(true);
        } 
        if (keyPressed == ' ') {
            projectiles.add(new Projectile(player.getX()+20, 490));
            keyInput.resetKey();
        }

        for (Enemy enemy : enemies) {
            enemy.update();
            if (enemy.isAlive() && (enemy.getX() < player.getX() + 10 && enemy.getX() > player.getX() - 10)) {
                enemyProjectiles.add(new Projectile(enemy.getX() + 17, enemy.getY() + 30));
            }
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
                            if (enemy.getIsAlive()) {
                                tempProjectiles.remove(projectile);
                            }
                        }
                    }
                    if (enemy.getHealth() <= 0) {
                        boolean flag = enemy.kill();
                        if (flag) {
                            player.increaseScore();
                        }
                    }
                }
            }
        }

        ArrayList<Projectile> tempEnemyProjectiles = new ArrayList<>();
        for (Projectile enemProjectile : enemyProjectiles) {
            enemProjectile.updateEnemyProjectile();
            if (enemProjectile.getY() >= 0) {
                tempEnemyProjectiles.add(enemProjectile);
            }

            if (enemProjectile.getRectangle() != null) {
                if (enemProjectile.getRectangle().intersects(player.getRectangle())) {
                    player.decreaseHealth(1);
                    tempEnemyProjectiles.remove(enemProjectile);
                }
            }  
        }

        if (!areThereMoreEnemies(enemies)) {
            waveNumber ++;
            enemies.clear();
            enemies = createEnemies();
        }
        enemyProjectiles = tempEnemyProjectiles;
        projectiles = tempProjectiles;
        gameWindow.repaint();
    }

    public ArrayList<Enemy> createEnemies() {
        
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        if (waveNumber % 2 == 1) {
            for (int i = 0; i <= 8; i++) {
                enemyList.add(new MovingEnemy(10, 10, 100 * i + 100, 30));
            }
            for (int i = 0; i <= 3; i++) {
                enemyList.add(new Enemy(10, 10, 100 * i + 100, 130));
            }
        } else {
            enemyList.add(new BossEnemy(100, 10, 100, 100));
            for (int i = 0; i <= 8; i++) {
                enemyList.add(new MovingEnemy(10, 10, 100 * i + 100, 30));
            }
        }
        return enemyList;
    }

    public boolean areThereMoreEnemies(ArrayList<Enemy> enemyList) {
        for (Enemy e : enemyList) {
            if (e.getIsAlive()) {
                return true;
            }
        }
        return false;
    }

    void buttonListeners() {
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);
                gameWindow.dispose();
                Menu menu = new Menu();
                menu.buildMenu();
            }
        });
    }
}
