import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyInput implements KeyListener{
    private char key;
    private char dir;

    public KeyInput() {
        key = '\u0000';
        dir = '\u0000';
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
            dir = e.getKeyChar();
        }
        key = e.getKeyChar();
    }
    @Override
    public void keyTyped(KeyEvent e) { 
        if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
            dir = e.getKeyChar();
        }
        key = e.getKeyChar();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyChar() == dir) {
            dir = '\u0000';
        }
    }

    public char getKey() {
        return key;
    }

    public char getDir() {
        return dir;
    }

    public void resetKey() {
        key = '\u0000';
    }
}
