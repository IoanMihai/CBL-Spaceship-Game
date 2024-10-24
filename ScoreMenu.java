import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The score menu class creates the entire retry window after the player dies
 * it is used in the game class.
 */
public class ScoreMenu {
    //Score variables
    private static int highScore = 0; //Overall highscore of the session
    private int currentScore; //Score of the last game the player played
    //GUI Variables
    private JFrame frame = new JFrame("You Died!");
    private JButton tryAgainButton;
    private JButton backToMenu;
    private JLabel gameOver;
    private JPanel menuPanel;
    private JTextField highScoreText;

    /**
     * Constructor for the score menu
     * @param score The players previous score
     */
    ScoreMenu(int score) {
        this.currentScore = score;
    }

    /**
     * Updates the highscore if the current score is higher
     */
    private void setHighScore() {
        if (currentScore > highScore) {
            highScore = currentScore;
        }
    }

    /**
     * Builds the retry menu
     */
    void buildIt() {
        setHighScore();

        //Create "game over" text
        gameOver = new JLabel("GAME OVER!", SwingConstants.CENTER);
        gameOver.setFont(new Font("Dialog", Font.BOLD, 50));
        gameOver.setPreferredSize(new Dimension(1080, 100));
        gameOver.setOpaque(true);
        gameOver.setBackground(Color.decode("#117E8E"));

        //get frame ready
        frame.getContentPane().add(gameOver, BorderLayout.PAGE_START);
        frame.setSize(new Dimension(1080, 720));

        //Create the menu panel (for the buttons)
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.BLACK);
        menuPanel.add(Box.createRigidArea(new Dimension(300, 100)));
        menuPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        //Create the retry button
        Toolkit t = Toolkit.getDefaultToolkit();

        tryAgainButton = new JButton();
        tryAgainButton.setBackground(Color.BLACK);
        tryAgainButton.setIcon(new ImageIcon(t.getImage("Assets/retry.png")));
        tryAgainButton.setBorder(null);

        //Create the back to menu button
        backToMenu = new JButton();
        backToMenu.setBackground(Color.BLACK);
        backToMenu.setIcon(new ImageIcon(t.getImage("Assets/exit2.png")));
        backToMenu.setBorder(null);

        //create the highscore text
        highScoreText = new JTextField("20");
        highScoreText.setText("Highscore: " + Integer.toString(highScore));
        highScoreText.setBackground(Color.BLACK);
        highScoreText.setFont(new Font("Dialog", Font.BOLD, 40));
        highScoreText.setBorder(BorderFactory.createEmptyBorder());
        highScoreText.setEditable(false);
        highScoreText.setForeground(Color.decode("#117E8E"));
        
        //Add everything to the menu panel
        menuPanel.add(tryAgainButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        menuPanel.add(backToMenu);
        menuPanel.add(Box.createRigidArea(new Dimension(200, 100)));
        menuPanel.add(highScoreText);
        
        //add all to the frame
        
        frame.getContentPane().add(menuPanel, BorderLayout.SOUTH);
        frame.setBackground(Color.BLACK);
        buttonListeners();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Adds a button listener to the buttons
     */
    void buttonListeners() {
        tryAgainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                Game game = new Game();
                game.builIt();
            }
            
        });

        backToMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                Menu menu = new Menu();
                menu.buildMenu();
            }
            
        });
    }

}
