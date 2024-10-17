import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ScoreMenu implements ActionListener{
    private static int highScore = 0;
    private int currentScore;

    JFrame frame = new JFrame("You Died!");
    JButton tryAgainButton;
    JButton backToMenu;
    JLabel gameOver;
    JPanel menuPanel;
    JTextField highScoreText;

    ScoreMenu(int score) {
        this.currentScore = score;
    }

    private void setHighScore() {
        if (currentScore > highScore) {
            highScore = currentScore;
        }
    }

    void buildIt() {
        setHighScore();

        gameOver = new JLabel("GAME OVER!", SwingConstants.CENTER);
        gameOver.setFont(new Font("Serif", Font.BOLD, 50));

        gameOver.setPreferredSize(new Dimension(1080, 100));
        gameOver.setOpaque(true);
        gameOver.setBackground(Color.RED);
        frame.getContentPane().add(gameOver, BorderLayout.PAGE_START);
        frame.setSize(new Dimension(1080, 720));


        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.BLACK);
        menuPanel.add(Box.createRigidArea(new Dimension(300, 100)));
        menuPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

        tryAgainButton = new JButton("TRY AGAIN");
        backToMenu = new JButton("MAIN MENU");

        tryAgainButton.setFont(new Font("Times New Roman", Font.BOLD, 40));
        backToMenu.setFont(new Font("Times New Roman", Font.BOLD, 40));

        tryAgainButton.setBackground(Color.WHITE);
        backToMenu.setBackground(Color.WHITE);

        tryAgainButton.setSize(new Dimension(600, 100));
        backToMenu.setSize(new Dimension(600, 100));

        highScoreText = new JTextField("20");
        highScoreText.setText("Highscore: " + Integer.toString(highScore));
        highScoreText.setBackground(Color.BLACK);
        highScoreText.setFont(new Font("Times New Roman", Font.BOLD, 40));
        highScoreText.setBorder(BorderFactory.createEmptyBorder());
        highScoreText.setEditable(false);
        highScoreText.setForeground(Color.WHITE);
        

        menuPanel.add(tryAgainButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        menuPanel.add(backToMenu);
        menuPanel.add(Box.createRigidArea(new Dimension(200, 100)));
        menuPanel.add(highScoreText);
        

        frame.getContentPane().add(menuPanel, BorderLayout.SOUTH);
        frame.setBackground(Color.BLACK);
        buttonListeners();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
