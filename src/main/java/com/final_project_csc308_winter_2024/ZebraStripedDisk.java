package com.final_project_csc308_winter_2024;

import java.awt.*;

public class ZebraStripedDisk extends Disk {
    private Color stripeColor;
    private int stripeWidth;

    public ZebraStripedDisk(int width, int height, Color color, int weight) {
        super(width, height, color, weight);
        this.stripeColor = Color.BLACK; // Default stripe color
        this.stripeWidth = 2; // Default stripe width
    }

    public void setZebraStripes(Color stripeColor, int stripeWidth) {
        this.stripeColor = stripeColor;
        this.stripeWidth = stripeWidth;
    }

//    @Override
//    public void draw(Graphics g) {
//        super.draw(g); // Draw the disk first
//
//        // Draw zebra stripes
//        g.setColor(stripeColor);
//        int xStart = getX();
//        int xEnd = getX() + getWidth();
//        int y = getY();
//        boolean drawStripe = true; // Flag to alternate between drawing and skipping stripes
//        while (xStart < xEnd) {
//            if (drawStripe) {
//                g.fillRect(xStart, y, Math.min(stripeWidth, xEnd - xStart), getHeight());
//            }
//            xStart += 2 * stripeWidth; // Move to the next stripe position
//            drawStripe = !drawStripe; // Toggle the flag
//        }
//    }

    @Override
    public void draw(Graphics g) {
        super.draw(g); // Draw the disk first
        drawStripes(g); // Draw the zebra stripes
    }

    private void drawStripes(Graphics g) {
        // Draw zebra stripes
        g.setColor(stripeColor);
        int xStart = getX();
        int xEnd = getX() + getWidth();
        int y = getY();
        boolean drawStripe = true; // Flag to alternate between drawing and skipping stripes
        while (xStart < xEnd) {
            if (drawStripe) {
                g.fillRect(xStart, y, Math.min(stripeWidth, xEnd - xStart), getHeight());
            }
            xStart += 2 * stripeWidth; // Move to the next stripe position
            drawStripe = !drawStripe; // Toggle the flag
        }
    }
}
