package com.final_project_csc308_winter_2024;

import java.awt.*;

public class Disk {
    private int width;
    private Color color;
    private Tower tower;

    public Disk(int width, Color color) {
        this.width = width;
        this.color = color;
    }

    public int getWidth() {
        return width;
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

    public void removeFromTower() {
        if (tower != null) {
            tower.removeDisk(this);
            tower = null;
        }
    }

    public void draw(Graphics g) {
        if (tower != null) {
            int x = tower.getX() - width / 2;
            int y = tower.getY() - tower.getHeight() / 2 - tower.getDisks().indexOf(this) * (tower.getHeight() / tower.getDisks().size());
            g.setColor(color);
            g.fillRect(x, y, width, tower.getHeight() / tower.getDisks().size());
        }
    }
}
