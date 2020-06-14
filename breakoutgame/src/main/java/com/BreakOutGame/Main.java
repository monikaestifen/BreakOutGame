package com.BreakOutGame;
import javax.swing.*;

public class Main {

    static public final int WIDTH = 700;
    static public final int HEIGHT = 600;

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();
        obj.setBounds(10,10,WIDTH,HEIGHT);
        obj.setTitle("BreakOut Game");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }
}
