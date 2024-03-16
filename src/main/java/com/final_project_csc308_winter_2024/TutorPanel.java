package com.final_project_csc308_winter_2024;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TutorPanel extends JPanel implements PropertyChangeListener {

    public TutorPanel() {
        Repository.getInstance().addPropertyChangeListener(this);
    }
    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        if (Repository.getInstance().getCount() == 0) {
//            System.out.println("No moves yet");
//        }
//        else{
            Tutor tutor = new Tutor();
            tutor.draw(g);
//        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
