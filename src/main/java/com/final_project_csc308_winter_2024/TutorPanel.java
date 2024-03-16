package com.final_project_csc308_winter_2024;

import javax.swing.*;
import java.awt.*;

public class TutorPanel extends JPanel {
    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tutor tutor = new Tutor();
        tutor.draw(g);
    }


}
