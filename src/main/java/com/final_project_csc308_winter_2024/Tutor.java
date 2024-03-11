package com.final_project_csc308_winter_2024;

import java.awt.*;

public class Tutor {

    public Tutor() {
        System.out.println("Hello From Tutor!");
    }

    public void draw(Graphics g) {
        System.out.println("Drawing Tutor!");
        g.setColor(Color.WHITE);
        g.fillRoundRect(100, 200, 400, 100, 20, 20); // Bubble rectangle with rounded corners

        // Draw the "tail" of the bubble
        int[] tailX = {150, 140, 150}; // X coordinates for the tail
        int[] tailY = {250, 260, 270}; // Y coordinates for the tail
        g.fillPolygon(tailX, tailY, 3); // Triangle for the tail
    }

}
