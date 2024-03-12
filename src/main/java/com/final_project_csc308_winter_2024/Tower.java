package com.final_project_csc308_winter_2024;

import java.awt.*;
import java.util.Stack;

public class Tower {
    private Stack<Disk> disks;
    private int x, y; // Position of the tower
    private int width, height; // Dimensions of the tower
    private static final Color TOWER_COLOR = Color.GRAY;

    public Tower(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.disks = new Stack<>();
    }

    public void addDisk(Disk disk) {
        disks.push(disk);
        disk.setTower(this);
    }

    public void removeDisk(Disk disk) {
        disks.remove(disk);
    }

    public Stack<Disk> getDisks() {
        return disks;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public Disk removeTopDisk() {
        if (!disks.isEmpty()) {
            return disks.pop();
        }
        return null; // Tower is empty
    }

    public Disk getTopDisk() {
        if (!disks.isEmpty()) {
            return disks.peek();
        }
        return null; // Tower is empty
    }

    public void draw(Graphics g) {
        // Draw tower
        g.setColor(TOWER_COLOR);
        g.fillRect(x - width / 2, y - height / 2, width, height);

        // Draw disks
        for (Disk disk : disks) {
            disk.draw(g);
        }
    }
}
