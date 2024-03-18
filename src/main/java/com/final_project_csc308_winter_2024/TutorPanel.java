package com.final_project_csc308_winter_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TutorPanel extends JPanel {
    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Use the existing 'tutor' instance instead of creating a new one.
        tutor.draw(g);
    }

    //DU Tran stuff to click the tutor for help
    private Tutor tutor;
    private final Rectangle tutorBounds = new Rectangle(400, 100, 350, 400);

    public TutorPanel() {
        this.tutor = new Tutor(); // Initialize the tutor once here

        //initial tutor message
        tutor.setMessage("I'm Javier, click on me if you need any assistance!");
        Repository.getInstance().solveGame(); // game is already pre solve to create a solutions list
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tutorBounds.contains(e.getPoint())) {
                    Solver solver = Repository.getInstance().getSolver();
                    String nextMove = solver.getNextMove(); // Use getNextMove for sequential tips
                    tutor.setMessage(nextMove);
                    repaint();
                }
            }
        });
    //End of Du
    }
}
