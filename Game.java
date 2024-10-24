import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * The game class creates the entire game window and contains the game loop
 * The class contains an instance of the Player class, an arraylist of the
 * enemies, enemy projectiles, player projectiles, upgrades, and an instance of
 * the key input class.
 */
public class Game extends JPanel implements ActionListener {
    //GUI variables
    private JFrame gameWindow = new JFrame("CBL Space Ship Game");
    private JPanel healthPanel;
    private JPanel scorePanel;
    private JPanel gamePanel;
    private JProgressBar healthBar;
    private JTextField playerHealthText;
    private JTextField playerScoreText;
    private JTextField scoreNumber;
    private JButton exitButton;
    //Game variables
    private Player player = new Player(490, 500, 
        1080, Constants.INITIAL_PLAYER_DAMAGE); //The player itself
    private ArrayList<Projectile> projectiles = 
            new ArrayList<>(); //List of all player projectiles on screen
    private ArrayList<Projectile> enemyProjectiles = 
            new ArrayList<>(); //List of all enemy projectiles on screen
    private ArrayList<Upgrades> upgrades = new ArrayList<>(); //List of all upgrades on screen
    private KeyInput keyInput = new KeyInput(); //key input to handle the keyboard inputs
    private Timer timer = new Timer(10, this); //Timer for main game loop
    private ArrayList<Enemy> enemies = new ArrayList<>(); //All enemies in the current wave

    /**
     * Creates the game window and panel, also initializes all the actial listeners.
     */
    public void builIt() {
        SwingUtilities.invokeLater(() -> {
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
        });
    }

    /**
     * Creates a panel which displays the player's score.
     */
    public void createScorePanel() {
        //Create the panel
        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.setBackground(Color.decode("#117E8E"));

        //Create the "score" text
        playerScoreText = new JTextField(5);
        playerScoreText.setText("Score:");
        playerScoreText.setBackground(Color.decode("#117E8E"));
        playerScoreText.setFont(new Font("Dialog", Font.BOLD, 20));
        playerScoreText.setBorder(BorderFactory.createEmptyBorder());
        playerScoreText.setEditable(false);

        //Create the score number text
        scorePanel.add(Box.createRigidArea(new Dimension(50, 30)));
        scoreNumber = new JTextField(5);
        scoreNumber.setBackground(Color.decode("#117E8E"));
        scoreNumber.setText("0");
        scoreNumber.setFont(new Font("Dialog", Font.BOLD, 20));
        scoreNumber.setBorder(BorderFactory.createEmptyBorder());
        scoreNumber.setEditable(false);

        //create the exit button
        exitButton = new JButton();
        exitButton.setBackground(Color.WHITE);
        Toolkit t = Toolkit.getDefaultToolkit();
        exitButton.setIcon(new ImageIcon(t.getImage("Assets/exit3.png")));
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setFont(new Font("Dialog", Font.BOLD, 20));

        //Add everything to the panel
        scorePanel.add(playerScoreText);
        scorePanel.add(scoreNumber);
        scorePanel.add(exitButton);
        scorePanel.add(Box.createRigidArea(new Dimension(50, 30)));
    }
    
    /**
     * Creates a panel which displays the health.
     */
    public void createHealthPanel() {
        //Create the health panel
        healthPanel = new JPanel();
        healthPanel.setLayout(new BoxLayout(healthPanel, BoxLayout.X_AXIS));
        healthBar = new JProgressBar();

        //create the "health" text
        playerHealthText = new JTextField(8);
        playerHealthText.setText("Health:");
        playerHealthText.setBackground(Color.decode("#117E8E"));
        playerHealthText.setFont(new Font("Dialog", Font.BOLD, 20));
        playerHealthText.setBorder(BorderFactory.createEmptyBorder());
        playerHealthText.setEditable(false);

        //create the health bar
        healthBar.setPreferredSize(new Dimension(300, 30));
        healthBar.setValue(100);

        //Add all to the panel
        healthPanel.add(playerHealthText);
        healthPanel.add(Box.createRigidArea(new Dimension(300, 30)));
        healthPanel.add(healthBar);
        healthPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        healthPanel.setBackground(Color.decode("#117E8E"));
    }

    /**
     * Draws all the game components to the screen.
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

        for (Upgrades upgrade : upgrades) {
            upgrade.draw(g);
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
     * Main Game loop will deal with all the events and update 
     * all the game components.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        keyInput.reduceTimer();
        healthBar.setValue(player.getHealth());

        //get the key being pressed and move the player or spawn a projectile
        char keyPressed = keyInput.getKey();
        char direction = keyInput.getDir();
        if (direction == 'a') {
            player.move(false);
        } else if (direction == 'd') {
            player.move(true);
        } 
        if (keyPressed == ' ') {
            projectiles.add(new Projectile(player.getX() + 20, 490, player.getDamage()));
            keyInput.resetKey();
        }

        //update all the upgrades
        for (Upgrades upgrade : upgrades) {
            upgrade.update();
        }
        
        //Update all the enemies
        for (Enemy enemy : enemies) {
            enemy.update();
            //Spawn enemy projectiles
            if (enemy.isAlive()) {
                if (enemy instanceof BossEnemy) {
                    if (enemy.getX() % 20 == 0) {
                        enemyProjectiles.add(new Projectile(enemy.getX() + 17, 
                            enemy.getY() + 30, enemy.getDamage()));  
                    }
                } else if (enemy.getX() < player.getX() + 10 
                            && enemy.getX() > player.getX() - 10) {
                    enemyProjectiles.add(new Projectile(enemy.getX() + 17, 
                                enemy.getY() + 30, enemy.getDamage())); 
                }
            }

            if (enemy.isAlive() && (enemy.getX() < player.getX() + 10 
                && enemy.getX() > player.getX() - 10)) {
                enemyProjectiles.add(new Projectile(enemy.getX() + 17, 
                    enemy.getY() + 30, enemy.getDamage()));  
            }
        }

        //Update the projectiles
        ArrayList<Projectile> tempProjectiles = new ArrayList<>();
        for (Projectile projectile: projectiles) {
            projectile.update();
            if (projectile.getY() >= 0) {
                tempProjectiles.add(projectile);
            }

            //Detect a collision with an enemy
            if (projectile.getRectangle() != null) {
                for (Enemy enemy : enemies) {
                    if (enemy.getRectangle() != null) {
                        if (projectile.getRectangle().intersects(enemy.getRectangle())) {
                            enemy.setHealth(enemy.getHealth() - player.getDamage());
                            if (enemy.getIsAlive()) {
                                tempProjectiles.remove(projectile);
                            }
                        }
                    }
                    if (enemy.getHealth() <= 0) {
                        boolean flag = enemy.kill();
                        if (flag) {
                            player.increaseScore();
                            Random randomUpgrades = new Random();
                            if (randomUpgrades.nextInt(21) % 3  == 0) {
                                upgrades.add(new Upgrades(enemy.getX(), enemy.getY(), 
                                    Constants.HEALING_UPGRADE));
                            } else if (randomUpgrades.nextInt(21) % 5 == 0) {
                                upgrades.add(new Upgrades(enemy.getX(), enemy.getY(), 
                                    Constants.DAMAGE_UPGRADE));
                            }
                        }
                    }
                }
            }
        }
        
        //Update the enemy projectiles
        ArrayList<Projectile> tempEnemyProjectiles = new ArrayList<>();
        for (Projectile enemProjectile : enemyProjectiles) {
            enemProjectile.updateEnemyProjectile();
            if (enemProjectile.getY() >= 0) {
                tempEnemyProjectiles.add(enemProjectile);
            }
            
            //Detect a collision with the player
            if (enemProjectile.getRectangle() != null) {
                if (enemProjectile.getRectangle().intersects(player.getRectangle())) {
                    player.decreaseHealth(enemProjectile.getDamage());
                    tempEnemyProjectiles.remove(enemProjectile);
                }
            }  
        }

        if (!areThereMoreEnemies(enemies)) {
            enemies.clear();
            enemies = createEnemies();
        }

        //Detect collision with upgrades
        ArrayList<Upgrades> tempUpgrades = new ArrayList<>();
        for (Upgrades upgrade : upgrades) {
            if (upgrade != null) {

                if (upgrade.getRectangle().intersects(player.getRectangle())) {

                    switch (upgrade.getUpgradeType()) {
                        case Constants.HEALING_UPGRADE:
                            player.increaseHealth(upgrade.getHealIncrease());
                            break;
                        case Constants.DAMAGE_UPGRADE:
                            player.setDamage(player.getDamage() + 5);
                            break;
                        default:
                            break;
                    }

                } else if (upgrade.getY() >= 0) {
                    tempUpgrades.add(upgrade);
                }
            }
        }
        
        //Set all lists
        upgrades = tempUpgrades;
        enemyProjectiles = tempEnemyProjectiles;
        projectiles = tempProjectiles;
        //Update the window
        gameWindow.repaint();
    }

    /**
     * Creates a new wave of enemies.
     * @return list of Enemies
     */
    public ArrayList<Enemy> createEnemies() {
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        if (player.getWave() % 5 == 0 && player.getWave() != 0) {
            //Spawn a boss
            enemyList.add(new BossEnemy("Assets/Boss.png", 
                player.getWave() * 100, player.getWave() * 3 + 1, 100, 100));
        } else {
            //Spawn a wave
            Random random = new Random();
            ArrayList<int[]> posistions = new ArrayList<>();
            //Spawn all moving enemies
            for (int i = 0; i <= player.getWave() - random.nextInt(10) - 3 && i < 9;  i++) {
                int[] pos = new int[] {random.nextInt(10), 30};

                //Ensure none overlap
                while (contains(posistions, pos)) {
                    pos[0]++;
                    if (pos[0] >= 10) {
                        pos[0] = 0;
                    }
                } 
                posistions.add(pos);

                //Spawn the enemy
                enemyList.add(new MovingEnemy("Assets/MovingEnemy.png", 
                    player.getWave() * 3 + 1, player.getWave() + 1, pos[0] * 100 + 100, pos[1]));
            }
            //Spawn all regular enemies
            for (int i = 0; i <= player.getWave() / 2 - random.nextInt(5) + 5 && i < 18;  i++) {
                int[] pos = new int[] {random.nextInt(10), random.nextInt(2)};

                //Ensure none overlap
                while (contains(posistions, pos)) {
                    pos[0]++;
                    if (pos[0] >= 10 && pos[1] == 1) {
                        pos[0] = 0;
                        pos[1] = 0;
                    } else if (pos[0] >= 10) {
                        pos[0] = 0;
                        pos[1] = 1;
                    }
                } 
                posistions.add(pos);
                
                //spawn the enemy
                enemyList.add(new Enemy("Assets/BasicEnemy.png", player.getWave() * 3 + 1, 
                    player.getWave() + 1, pos[0] * 100 + 100, pos[1] * 100 + 130));
            }
        }
        return enemyList;
    }

    /**
     * Checks whether all enemies are killed.
     * @param enemyList The list of all enemies that were spawned in the wave
     * @return A boolean of whether all the enemies of 
     *      the waves dies (false) or if there are some left (true)
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
     * Adds a button Listener to the exit button.
     */
    void buttonListeners() {
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setVisible(false);
                gameWindow.dispose();
                Main menu = new Main();
                menu.buildMenu();
            }
        });
    }

    /**
     * Checks if an int array is within an int array arrayList.
     * @param arrayList An int array array list
     * @param array The int array that needs to be found
     * @return A boolean whether the int array is within the array list
     */
    public boolean contains(ArrayList<int[]> arrayList, int[] array) {
        for (int[] arr : arrayList) {
            if (arr[0] == array[0] && arr[1] == array[1]) {
                return true;
            }
        }
        return false;
    }
}
