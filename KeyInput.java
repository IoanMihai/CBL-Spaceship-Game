import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyInput implements KeyListener{
    private char key;

    public KeyInput() {
        key = '\u0000';
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyChar();
    }
    @Override
    public void keyTyped(KeyEvent e) { 
        key = e.getKeyChar();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        key = '\u0000';
    }

    public char getKey() {
        return key;
    }
}
