import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game implements ActionListener {
    JFrame gameWindow = new JFrame("SBL Space Ship Game");
    Player player = new Player(490, 600, 1080);
    KeyInput keyInput = new KeyInput();
    Timer timer = new Timer(10, this);

    public void builIt() {
        SwingUtilities.invokeLater( () -> {

            gameWindow.add(player);
            player.setBackground(Color.BLACK);
            player.setFocusable(true);
            player.addKeyListener(keyInput);

            gameWindow.setSize(1080,720);
            gameWindow.setVisible(true);
            gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            timer.start();
        } );
    }

    public void actionPerformed(ActionEvent e) {
        char keyPressed = keyInput.getKey();
        if(keyPressed == 'a') {
            player.move(false);
        } else if(keyPressed == 'd') {
            player.move(true);
        }
        gameWindow.repaint();
    }
}
