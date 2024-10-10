import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Menu implements ActionListener {
    JFrame frame = new JFrame("SPACESHIP");
    JButton playButton;
    JButton quitButton;
    JPanel buttonPanel;

    void buildMenu() {
        JLabel title = new JLabel("SPACESHIP", SwingConstants.CENTER);
        Font titleFont = new Font("Serif", Font.BOLD, 50);
        title.setFont(titleFont);
        title.setPreferredSize(new Dimension(1080, 100));
        title.setOpaque(true);
        title.setBackground(Color.RED);
        frame.getContentPane().add(title, BorderLayout.PAGE_START);
        frame.setPreferredSize(new Dimension(1080, 720));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createRigidArea(new Dimension(220, 100)));

        playButton = new JButton("PLAY");
        quitButton = new JButton("QUIT");

        playButton.setPreferredSize(new Dimension(200, 100));

        playButton.setBackground(Color.WHITE);
        quitButton.setBackground(Color.WHITE);
        playButton.setFont(new Font("Times New Roman", Font.BOLD, 40));
        quitButton.setFont(new Font("Times New Roman", Font.BOLD, 40));

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
                //TODO: go to next frame
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
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