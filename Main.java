import java.awt.*;
import javax.swing.*;

class Menu {
    JFrame frame = new JFrame("SPACESHIP");
    JButton playButton;
    JButton quitButton;
    JPanel panel;

    void buildMenu() {
        JLabel title = new JLabel("SPACESHIP", SwingConstants.CENTER);
        Font titleFont = new Font("Times New Roman", Font.BOLD, 20);
        title.setFont(titleFont);
        title.setPreferredSize(new Dimension(1080, 100));
        title.setOpaque(true);
        title.setBackground(Color.RED);
        frame.getContentPane().add(title, BorderLayout.PAGE_START);
        
        panel = new JPanel(new FlowLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(1080, 720));
        panel.setBackground(Color.BLACK);
        panel.setOpaque(true);
        
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        playButton = new JButton("PLAY");
        quitButton = new JButton("QUIT");

        playButton.setPreferredSize(new Dimension(1080, 100));
        quitButton.setPreferredSize(new Dimension(1080, 100));

        playButton.setBackground(Color.GREEN);
        quitButton.setBackground(Color.RED);

        panel.add(Box.createRigidArea(new Dimension(1080, 200)));
        panel.add(playButton, BorderLayout.SOUTH);
        panel.add(Box.createRigidArea(new Dimension(1080, 300)));
        panel.add(quitButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] a) {
        new Menu().buildMenu();
    }

}