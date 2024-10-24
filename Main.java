import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * The main class builds the main menu and creates an instance of the game class
 * to run the game.
 */
class Main {
    //GUI Variables
    private JFrame frame = new JFrame("SPACESHIP");
    private JButton playButton;
    private JButton quitButton;
    private JPanel buttonPanel;
    private JPanel infoPanel;
    private JInternalFrame infoFrame;
    private JButton infoButton;

    /**
     * Will build the initial menu.
     */
    void buildMenu() {
        //Creates the title
        JLabel title = new JLabel("SPACESHIP", SwingConstants.CENTER);
        Font titleFont = new Font("Dialog", Font.BOLD, 50);
        title.setFont(titleFont);
        title.setPreferredSize(new Dimension(1080, 100));
        title.setOpaque(true);
        title.setBackground(Color.decode("#117E8E"));
        
        //Create the button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createRigidArea(new Dimension(220, 100)));

        //Create the play button
        Toolkit t = Toolkit.getDefaultToolkit();

        playButton = new JButton();
        playButton.setIcon(new ImageIcon(t.getImage("Assets/play.png")));
        playButton.setBackground(Color.BLACK);
        playButton.setBorder(null);

        //Create the quit button
        quitButton = new JButton();
        quitButton.setIcon(new ImageIcon(t.getImage("Assets/exit1.png")));
        quitButton.setBackground(Color.BLACK);
        quitButton.setBorder(null);

        //Add all to the button panel
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        buttonPanel.add(quitButton);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setPreferredSize(new Dimension(frame.getWidth() - 200, frame.getHeight()));

        //Create info panel
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        infoPanel.add(Box.createRigidArea(new Dimension(220, 100)));
        infoPanel.setBackground(Color.BLACK);

        //Info button
        infoButton = new JButton();
        infoButton.setIcon(new ImageIcon(t.getImage("Assets/info.png")));
        infoButton.setBackground(Color.BLACK);
        infoButton.setBorder(null);
        infoPanel.add(infoButton);

        //Create a panel in the internal frame
        infoFrame = new JInternalFrame("Information", false, true, true, false);
        JPanel iPanel = new JPanel();
        iPanel.setLayout(new BoxLayout(iPanel, BoxLayout.Y_AXIS));
        iPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        iPanel.setBackground(Color.BLACK);

        Font infoFont = new Font("Dialog", Font.BOLD, 20);
        //Create healing icon
        JLabel healingIcon = new JLabel();
        Image healingImage = t.getImage("Assets/hp_potion_x5.png")
            .getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        healingIcon.setIcon(new ImageIcon(healingImage));

        //Create healing info label
        JLabel healingInfo = new JLabel("This powerup heals you", JLabel.CENTER);
        healingInfo.setFont(infoFont);
        healingInfo.setForeground(Color.decode("#117E8E"));
        
        //Create damage icon
        JLabel dmgIcon = new JLabel();
        Image dmgImageInfo = t.getImage("Assets/boost_upgrade.png")
            .getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        dmgIcon.setIcon(new ImageIcon(dmgImageInfo));

        //Create damage info label
        JLabel dmgInfo = new JLabel("This powerup increases your damage", SwingConstants.CENTER);
        dmgInfo.setFont(infoFont);
        dmgInfo.setForeground(Color.decode("#117E8E"));

        //Create controls info label
        JLabel controlsInfo = new JLabel("Use 'A' and 'D' to move and 'Space' to shoot", 
            SwingConstants.CENTER);
        controlsInfo.setFont(infoFont);
        controlsInfo.setForeground(Color.decode("#117E8E"));

        //Add all to the info panel in the internal frame
        iPanel.add(controlsInfo);
        iPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        iPanel.add(healingIcon);
        iPanel.add(healingInfo);
        iPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        iPanel.add(dmgIcon);
        iPanel.add(dmgInfo);
        
        //Add and set the internal frame
        infoFrame.add(iPanel);
        infoFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        infoFrame.setVisible(false);
        infoFrame.setBackground(Color.BLACK);
        infoFrame.setPreferredSize(new Dimension(500, 400));
        infoFrame.pack();

        //Add all to frame
        frame.add(infoFrame);
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.EAST);
        frame.getContentPane().add(title, BorderLayout.PAGE_START);

        //Finish off the frame
        frame.setPreferredSize(new Dimension(1080, 720));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonListeners();
    }

    /**
     * Adds button listeners to the buttons.
     */
    void buttonListeners() {
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                Game game = new Game();
                game.builIt();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.setVisible(true);
            }
        });
    }

    public static void main(String[] a) {
        new Main().buildMenu();
    }
}