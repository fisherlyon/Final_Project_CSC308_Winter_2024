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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tutorBounds.contains(e.getPoint())) {
                    tutor.setMessage("Move " + "disk[num] " + "to " + "tower[num]"); // Set the message
                    System.out.println("Tutor image clicked! Setting message."); // for testing purposes
                    revalidate();
                    repaint();
                    // if clicked on run solver(t1, t2, t3) whatever state this is, and display whatever the first move
                    // is
                    // tutor.setMessage(move disk[num] to tower[num])
                }
            }
        });
    }
    //End of Du
}
