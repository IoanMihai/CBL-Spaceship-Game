import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


class Menu implements ActionListener {
    JFrame frame = new JFrame("SPACESHIP");
    JButton playButton;
    JButton quitButton;
    JPanel buttonPanel;

    void buildMenu() {
        JLabel title = new JLabel("SPACESHIP", SwingConstants.CENTER);
        Font titleFont = new Font("Dialog", Font.BOLD, 50);
        title.setFont(titleFont);
        title.setPreferredSize(new Dimension(1080, 100));
        title.setOpaque(true);
        title.setBackground(Color.decode("#117E8E"));
        frame.getContentPane().add(title, BorderLayout.PAGE_START);
        frame.setPreferredSize(new Dimension(1080, 720));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createRigidArea(new Dimension(220, 100)));

        Toolkit t = Toolkit.getDefaultToolkit();
        playButton = new JButton();
        playButton.setIcon(new ImageIcon(t.getImage("Assets/play.png")));
        playButton.setBackground(Color.BLACK);
        playButton.setBorder(null);

        quitButton = new JButton();
        quitButton.setIcon(new ImageIcon(t.getImage("Assets/exit1.png")));
        quitButton.setBackground(Color.BLACK);
        quitButton.setBorder(null);

        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        buttonPanel.add(quitButton);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        
        Container contentPane = frame.getContentPane();
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonListeners();
    }

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
    }

    public static void main(String[] a) {
        new Menu().buildMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}