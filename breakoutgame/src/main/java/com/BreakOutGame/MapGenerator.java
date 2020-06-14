package com.BreakOutGame;
import java.awt.*;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    public MapGenerator(int row, int col) {
        map =new int[row][col];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                map[i][j] = 1; //klocen nie zbity przez piłkę
            }
        }

        brickWidth = 540/col;
        brickHeight = 150/row;
    }

    public void draw (Graphics2D g) {
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if(map[i][j] > 0) {
                    if (i%6 == 0){
                        Color myRed = new Color (255, 0, 24);
                        g.setColor(myRed);
                    } else if (i%6 == 1) {
                        Color myOrange = new Color (255, 165, 44);
                        g.setColor(myOrange);
                    } else if (i%6 == 2) {
                        Color myYellow = new Color (255, 255, 65);
                        g.setColor(myYellow);
                    } else if (i%6 == 3) {
                        Color myGreen = new Color (0, 128, 24);
                        g.setColor(myGreen);
                    } else if (i%6 == 4) {
                        Color myBlue = new Color (0, 0, 249);
                        g.setColor(myBlue);
                    } else if (i%6 == 5) {
                        Color myViolet = new Color (134, 0, 125);
                        g.setColor(myViolet);
                    }
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    //ramki do klocków (by było wydać ich granice)
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }

}
