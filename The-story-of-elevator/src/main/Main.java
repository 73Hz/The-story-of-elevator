package main;
import java.math.*;

import static java.lang.StrictMath.max;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Main {
    public static void main(String[] args){
        KeyEventDemo frame = new KeyEventDemo();
        frame.setTitle( "KeyEventDemo");
        frame.setSize(300,300);
        frame.setLocationRelativeTo( null);
        frame.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        frame.setVisible( true);
        for(int i=0;i<100;i++){
            System.out.println(i+" ");
            TimeStop.stop(1);
        }

    }
    /*public static void main(String[] args){
        for(int i=0;i<10;i++){
            //keyTyped();
        }
        Elevator temp=new Elevator(10);
        temp.adduser(1,2,4);
        temp.adduser(1,1,5);
        temp.adduser(1,9,5);
        temp.adduser(1,5,4);
        temp.adduser(1,0,4);
        temp.adduser(1,3,6);
        temp.run();
    }*/
    public static void keyTyped(KeyEvent e) {
        if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
            System.out.println("ENTER");
        }
    }
}
