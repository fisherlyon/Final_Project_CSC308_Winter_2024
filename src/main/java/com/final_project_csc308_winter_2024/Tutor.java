package com.final_project_csc308_winter_2024;

import java.awt.*;

public class Tutor {
    int x = 100;
    int y = 200;
    int width = 400;
    int height = 100;

    int[] tailX = {480, 480, 530};
    int[] tailY = {280, 300, 300};

    public Tutor() {
        System.out.println("Hello From Tutor!");
    }

    public void draw(Graphics g) {
        System.out.println("Drawing Tutor!");
        g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, width, height, 20, 20); // Bubble rectangle with rounded corners
        // Draw the "tail" of the bubble
        g.fillPolygon(tailX, tailY, 3); // Triangle for the tail
        int f =  x + (width /4);
        int h = y + (height / 2);
        g.setColor(Color.BLACK);
        int disk = Repository.getInstance().getNextStep()[0] +1;
        int from = Repository.getInstance().getNextStep()[1] +1;
        int to = Repository.getInstance().getNextStep()[2] +1;
        g.drawString("Move disk " + disk + " from tower " + from + " to tower " + to, f, h);
    }

}
