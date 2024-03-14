package com.final_project_csc308_winter_2024;

import javax.swing.*;
import java.awt.*;

public class Disk extends JPanel {
    private int width;
    private Color color;
    private Tower tower;
    private int x, y;
    private int weight;


    public Disk(int width, Color color, int weight) {
        this.width = width;
        this.color = color;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
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
            int diskY = towerY - tower.getHeight() / 2 - tower.getDisks().indexOf(this) * (tower.getHeight() / tower.getDisks().size());
            x = towerX - width / 2;
            y = diskY;
            g.setColor(color);
            g.fillRect(x, y, width, tower.getHeight() / tower.getDisks().size());
        }
    }
}
