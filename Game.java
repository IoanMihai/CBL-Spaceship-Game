import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import javax.swing.*;

/**
 * 
 */

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

    /**
     * Creates the game window and panel, also initializes all the actial listeners
     */
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

    /**
     * 
     */
    public void createScorePanel() {
        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.setBackground(Color.decode("#117E8E"));
        playerScoreText = new JTextField(5);
        playerScoreText.setText("Score:");
        playerScoreText.setBackground(Color.decode("#117E8E"));
        playerScoreText.setFont(new Font("Dialog", Font.BOLD, 20));
        playerScoreText.setBorder(BorderFactory.createEmptyBorder());
        playerScoreText.setEditable(false);
        scorePanel.add(playerScoreText);
        scorePanel.add(Box.createRigidArea(new Dimension(50, 30)));
        scoreNumber = new JTextField(5);
        scoreNumber.setBackground(Color.decode("#117E8E"));
        scoreNumber.setText("0");
        scoreNumber.setFont(new Font("Dialog", Font.BOLD, 20));
        scoreNumber.setBorder(BorderFactory.createEmptyBorder());
        scoreNumber.setEditable(false);
        scorePanel.add(scoreNumber);
        exitButton = new JButton();
        exitButton.setBackground(Color.WHITE);
        Toolkit t = Toolkit.getDefaultToolkit();
        exitButton.setIcon(new ImageIcon(t.getImage("Assets/exit3.png")));
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setFont(new Font("Dialog", Font.BOLD, 20));
        scorePanel.add(exitButton);
        scorePanel.add(Box.createRigidArea(new Dimension(50, 30)));
    }
    
    /**
     * 
     */
    public void createHealthPanel() {
        healthPanel = new JPanel();
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.X_AXIS));
        healthBar = new JProgressBar();
        playerHealthText = new JTextField(8);
        playerHealthText.setText("Health:");
        playerHealthText.setBackground(Color.decode("#117E8E"));
        playerHealthText.setFont(new Font("Dialog", Font.BOLD, 20));
        playerHealthText.setBorder(BorderFactory.createEmptyBorder());
        playerHealthText.setEditable(false);
        healthBar.setPreferredSize(new Dimension(300, 30));
        healthBar.setValue(100);
        healthPanel.add(playerHealthText);
        healthPanel.add(Box.createRigidArea(new Dimension(300, 30)));
        healthPanel.add(healthBar);
        healthPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        healthPanel.setBackground(Color.decode("#117E8E"));
    }

    /**
     * 
     */
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

    /**
     * 
     */
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

    /**
     * 
     * @return
     */
    public ArrayList<Enemy> createEnemies() {
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        if(player.getWave()%5 == 0 && player.getWave() != 0) {
            enemyList.add(new BossEnemy("Assets/Boss.png", player.getWave() * 3, player.getWave(), 100, 100));
        } else {
            Random random = new Random();
            ArrayList<int[]> posistions = new ArrayList<>();
            for (int i = 0; i <= player.getWave()-random.nextInt(10) - 3 && i < 9; i++) {
                int[] pos = new int[] {random.nextInt(10), 30};

                while(contains(posistions, pos)) {
                    pos[0] ++;
                    if(pos[0] >= 10) {
                        pos[0] = 0;
                    }
                } 

                posistions.add(pos);

                enemyList.add(new MovingEnemy("Assets/MovingEnemy.png", player.getWave() + 1, player.getWave(), pos[0] * 100 + 100, pos[1]));
            }
            for (int i = 0; i <= player.getWave()/2 -random.nextInt(5) + 5 && i < 18; i++) {
                int[] pos = new int[] {random.nextInt(10), random.nextInt(2)};

                while(contains(posistions, pos)) {
                    pos[0] ++;
                    if(pos[0] >= 10 && pos[1] == 1) {
                        pos[0] = 0;
                        pos[1] = 0;
                    } else if (pos[0] >= 10) {
                        pos[0] = 0;
                        pos[1] = 1;
                    }
                } 
                
                posistions.add(pos);
                
                enemyList.add(new Enemy("Assets/BasicEnemy.png", player.getWave() + 1, player.getWave(), pos[0] * 100 + 100, pos[1] * 100 + 130));
            }
        }
            

        //enemyList.add(new BossEnemy(10, 10, 100, 100));
        return enemyList;
    }

    /**
     * Checks whether all enemies are killed
     * @param enemyList The list of all enemies that were spawned in the wave
     * @return A boolean of whether all the enemies of the waves dies (false) or if there are some left (true)
     */
    public boolean areThereMoreEnemies(ArrayList<Enemy> enemyList) {
        for (Enemy e : enemyList) {
            if (e.getIsAlive()) {
                return true;
            }
        }
        player.increaseWaveCounter();
        return false;
    }

    /**
     * 
     */
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

    /**
     * Checks if an int array is within an int array arrayList
     * @param arrayList An int array array list
     * @param array The int array that needs to be found
     * @return A boolean whether the int array is within the array list
     */
    public boolean contains(ArrayList<int[]> arrayList, int[] array) {
        for(int[] arr : arrayList) {
            if(arr[0] == array[0] && arr[1] == array[1]) {
                return true;
            }
        }
        return false;
    }
}
