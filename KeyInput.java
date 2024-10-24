import java.awt.event.*;

/**
 * The key input class implements the key listener class and handels
 * the keyboard inputs.
 */
public class KeyInput implements KeyListener {
    private char key; //The key being pressed
    private char dir; //The direction the player should move
    private int timer; //A timer so the can't spam 'space'

    /**
     * Construtor for the Key Input class, sets all instance variables to the standard value.
     */
    public KeyInput() {
        key = '\u0000';
        dir = '\u0000';
        timer = 0;
    }

    /**
     * Gets the key pressed event and assigns inctance variables appropiate values.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
            dir = e.getKeyChar();
        } 
        
        if (timer == 0) {
            key = e.getKeyChar();
        }
    }

    /**
     * Gets the key typed event and assigns inctance variables appropiate values.
     */
    @Override
    public void keyTyped(KeyEvent e) { 
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') {
            dir = e.getKeyChar();
        }
        if (timer == 0) {
            key = e.getKeyChar();
        }
    }

    /**
     * Gets the key released event and resets the direction.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == dir) {
            dir = '\u0000';
        }
    }

    /**
     * resets the key variable to the standard value and resets the timer.
     */
    public void resetKey() {
        key = '\u0000';
        timer = 10;
    }

    /**
     * Reduces the timer by 1.
     */
    public void reduceTimer() {
        if (timer > 0) {
            timer--;
        }
    }

    //Getters and Setters
    public char getKey() {
        return key;
    }

    public char getDir() {
        return dir;
    }
}
