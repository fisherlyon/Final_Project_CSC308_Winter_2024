package com.final_project_csc308_winter_2024;

import java.awt.*;

public class Disk {
    public static final int DEFAULT_HEIGHT = 20;
    private int width;
    private int height;
    private int originalWidth;
    private int originalHeight;
    private Color color;
    private Tower tower;
    private int x, y;
    private int weight;
    private Color stripeColor;
    private int stripeWidth;

    public Disk(int width, int height, Color color, int weight) {
        this.width = width;
        this.height = height;
        this.originalWidth = width;
        this.originalHeight = height;
        this.color = color;
        this.weight = weight;

        // Initialize zebra stripe properties
        this.stripeWidth = 1; // Default stripe width
    }

    public void setZebraStripes(Color stripeColor, int stripeWidth) {
        this.stripeColor = stripeColor;
        this.stripeWidth = stripeWidth;
    }

    @Override
    public String toString() {
        return "Disk #" + weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void removeFromTower() {
        if (tower != null) {
            tower.removeDisk(this);
            tower = null;
        }
    }

    public void draw(Graphics g) {
        if (tower != null) {
            int towerX = tower.getX();
            int towerY = tower.getY();
            int diskY = towerY - originalHeight / 2 - tower.getDisks().indexOf(this) * (originalHeight + 2); // Adjust for spacing between disks
            x = towerX - originalWidth / 2;
            y = diskY;
            g.setColor(color);
            g.fillRect(x, y, originalWidth, originalHeight); // Use originalWidth and originalHeight for consistent disk size

            // Draw zebra stripes
            for (int i = 0; i < height; i += stripeWidth) {
                g.setColor(stripeColor);
                g.fillRect(x, y + i, width, stripeWidth);
            }
        }
    }

    public boolean contains(int x, int y) {
        int dx = x - (this.x + width / 2);
        int dy = y - (this.y + height / 2);
        int distanceSquared = dx * dx + dy * dy;
        return distanceSquared <= (width / 2) * (width / 2);
    }
}

