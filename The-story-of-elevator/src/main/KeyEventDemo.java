package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyEventDemo extends JFrame{
    private KeyboardPanel keyboardPanel = new KeyboardPanel();

    public KeyEventDemo(){
        add( keyboardPanel);

        keyboardPanel.setFocusable(true);

    }

    public static void main(String[] args){
        KeyEventDemo frame = new KeyEventDemo();
        frame.setTitle( "KeyEventDemo");
        frame.setSize(300,300);
        frame.setLocationRelativeTo( null);
        frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        frame.setVisible( true);
    }

    static class KeyboardPanel extends JPanel{
        private int x = 100;
        private int y = 100;
        private char KeyChar = 'A' ;

        public KeyboardPanel(){
            addKeyListener( new KeyAdapter(){
                public void keyPressed(KeyEvent e){
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_DOWN: System.out.println(1+" ");;break;
                        case KeyEvent.VK_UP: System.out.println(1+" "); break;
//                        case keyEvent: x -= 10; break;
                        case KeyEvent.VK_RIGHT: System.out.println(1+" "); break;
                        case KeyEvent.VK_LEFT: System.out.println(1+" "); break;
                        //default: keyChar = e.getKeyChar();
                    }

                    repaint();
                }
            });
        }

        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            g.setFont( new Font("TimesRoman" ,Font.PLAIN,24));
            g.drawString(String. valueOf(KeyChar), x, y);
        }
    }
}