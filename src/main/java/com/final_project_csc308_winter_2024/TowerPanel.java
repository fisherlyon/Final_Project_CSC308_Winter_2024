package com.final_project_csc308_winter_2024;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TowerPanel extends JPanel implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tower[] towers = Repository.getInstance().getTowers();
        for (int i = 0; i < 3; i++) {
            towers[i].draw(g);
            for(Disk disk : towers[i].getDisks()){
                disk.draw(g);
            }
        }
    }
}
