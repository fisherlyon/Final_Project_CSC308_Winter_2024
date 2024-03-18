package com.final_project_csc308_winter_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
        // Add a PropertyChangeListener to listen for changes in gameOver property
        Repository.getInstance().addPropertyChangeListener("gameOver", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int newValue = (int) evt.getNewValue();
                if (newValue == 1) {
                    tutor.setMessage("Congratulations! You've won!");
                    Image newImage = loadImage("src/main/resources/javierwin.png"); // Load the new image
                    tutor.setTutorImage(newImage); // Set the new image for the tutor
                    repaint();
                }
                if (newValue == 0) {
                    tutor.setMessage("I'm Javier, click on me if you need any assistance!");
                    Image newImage = loadImage("src/main/resources/tutor1.png"); // Load the new image
                    tutor.setTutorImage(newImage); // Set the new image for the tutor
                    Solver solver = Repository.getInstance().getSolver();
                    solver.resetMoves(); // Reset the nextMoves list back to index 0
                    repaint();
                }
            }
        });
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
    // Method to load a new image
    private Image loadImage(String imagePath) {
        ImageIcon ii = new ImageIcon(imagePath);
        return ii.getImage();
    }
}
