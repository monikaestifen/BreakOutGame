package com.BreakOutGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JPanel implements MouseListener {

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;

    Rectangle smallSizeButton = new Rectangle(Main.WIDTH/3 + 50, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
    Rectangle mediumSizeButton = new Rectangle(Main.WIDTH/3 + 50, 250, BUTTON_WIDTH, BUTTON_HEIGHT);
    Rectangle largeSizeButton = new Rectangle(Main.WIDTH/3 + 50, 350, BUTTON_WIDTH, BUTTON_HEIGHT);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font titleFont = new Font("arial", Font.BOLD, 50);
        g.setFont(titleFont);
        g.setColor(Color.white);
        g.drawString("Break Out Game", Main.WIDTH/5, 100);

        g2d.draw(smallSizeButton);
        Font buttonFont = new Font ("arial", Font.BOLD, 30);
        g2d.setColor(Color.white);
        g2d.setFont(buttonFont);
        g2d.drawString("6x15", Main.WIDTH/3 + 65, 185);

        g2d.draw(mediumSizeButton);
        g2d.drawString("12x30", Main.WIDTH/3 + 60, 285);

        g2d.draw(largeSizeButton);
        g2d.drawString("24x60", Main.WIDTH/3 + 60, 385);
    }

    @Override
    public void mouseClicked(MouseEvent event) { }

    @Override
    public void mousePressed(MouseEvent event) {
        int mx = event.getX();
        int my = event.getY();

        if (mx >= smallSizeButton.getX() && mx <= smallSizeButton.getX() + smallSizeButton.getWidth()) {
            if (my >= smallSizeButton.getY() && my <= smallSizeButton.getY() + smallSizeButton.getHeight()) {
                Gameplay.ROWS = 6;
                Gameplay.COLUMNS = 15;
                Gameplay.state = Gameplay.STATE.GAME;
            } else if (my >= mediumSizeButton.getY() && my <= mediumSizeButton.getY() + mediumSizeButton.getHeight()) {
                Gameplay.ROWS = 12;
                Gameplay.COLUMNS = 30;
                Gameplay.state = Gameplay.STATE.GAME;
            } else if (my >= largeSizeButton.getY() && my <= largeSizeButton.getY() + largeSizeButton.getHeight()) {
                Gameplay.ROWS = 24;
                Gameplay.COLUMNS = 60;
                Gameplay.state = Gameplay.STATE.GAME;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) { }

    @Override
    public void mouseEntered(MouseEvent event) { }

    @Override
    public void mouseExited(MouseEvent event) { }
}
