package com.BreakOutGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import static java.awt.Color.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false; //gra nie powina rozpoczynać sie sama
    private int score = 0; // początkowa ilość pnktów

    private int totalBriks = 1;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;
    private Menu menu;

    public enum STATE {
        MENU,
        GAME
    }

    static public STATE state = STATE.MENU;

    static public int ROWS;
    static public int COLUMNS;

    public Gameplay() {
        menu = new Menu();
        addKeyListener(this);
        addMouseListener(menu);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        //tło
        g.setColor((black));
        g.fillRect(1,1,692, 592);

        if (state == STATE.MENU) {
            menu.render(g);
        } else if (state == STATE.GAME) {
            //generaownie mapy na podstawie przycisku
            if (map == null) {
                map = new MapGenerator(ROWS, COLUMNS);
                totalBriks = ROWS * COLUMNS;
                play = true;
            }

            //rysowanie mapy
            map.draw((Graphics2D) g);

            //ramki
            g.setColor((yellow));
            g.fillRect(0, 0, 3, 592);
            g.fillRect(0, 0, 692, 3);
            g.fillRect(691, 0, 3, 592);

            //punkty
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("" + score, 592, 30);

            //tacka
            g.setColor((green));
            g.fillRect(playerX, 550, 100, 8);

            //piłka
            g.setColor((yellow));
            g.fillOval(ballposX, ballposY, 5, 5);
        }

        if (totalBriks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Wygrałeś! ", 270, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Naciśnij enter by zacząć ponownie", 165, 350);
        }

        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Przegrałeś, Punkty: "+ score, 170, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Naciśnij enter by zacząć ponownie", 165, 350);
        }

        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (play) {
            if (new Rectangle(ballposX, ballposY, 5, 5).intersects(new Rectangle(playerX, 550,100, 8))) {
                ballYdir = -ballYdir;
            }

            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 5,5);

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(0, i, j);
                            totalBriks--;
                            score += 5;

                            if (ballposX + 4 <= brickRect.x || ballposX + 1 >= brickRect.x + ballRect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }

                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;

            if(ballposX < 0) {
                ballXdir = -ballXdir;
            }

            if(ballposY < 0) {
                ballYdir = -ballYdir;
            }

            if(ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }

        repaint();
    }

    //nie potrzebuję używać tych metod, ale są wymagane do poprawnego kompilowania programu
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        //sterowanie piłką i onrola wychodzenia poza pole
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;

                state = STATE.MENU;
                map = null;

                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }
}
