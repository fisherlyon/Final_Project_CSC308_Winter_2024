package com.final_project_csc308_winter_2024;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class TowerPanel extends JPanel implements PropertyChangeListener, MouseListener, MouseMotionListener {

    public TowerPanel(){
        initializeCursors();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tower[] towers = Repository.getInstance().getTowers();
        for (Tower tower : towers) {
            tower.draw(g); // Draw the towers
            for(Disk disk : tower.getDisks()) {
                if (disk != draggingDisk) {
                    disk.draw(g); // Draw the disk at its position in the tower
                }
            }
        }
        // Draw the disk that is currently being dragged at its own x, y coordinates
        if (draggingDisk != null) {
            g.setColor(draggingDisk.getColor());
            g.fillRect(draggingDisk.getX() - draggingDisk.getWidth() / 2,
                    draggingDisk.getY() - draggingDisk.getHeight() / 2,
                    draggingDisk.getWidth(),
                    draggingDisk.getHeight());
        }
    }

    public static Disk draggingDisk;
    private Cursor openHandCursor;
    private Cursor grabbedHandCursor;
    private int offsetX, offsetY;
    private boolean dragging = false;
    private int mouseX, mouseY; // Last known mouse coordinates

    private void initializeCursors() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        try {
            Image openHandImage = ImageIO.read(getClass().getResourceAsStream("/open_hand.png"));
            openHandCursor = toolkit.createCustomCursor(openHandImage, new Point(0, 0), "Open Hand Cursor");

            Image grabbedHandImage = ImageIO.read(getClass().getResourceAsStream("/grabbed_hand.png"));
            grabbedHandCursor = toolkit.createCustomCursor(grabbedHandImage, new Point(0, 0), "Grabbed Hand Cursor");

            // Initially set the cursor to open hand
            setCursor(openHandCursor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent press) {
        mouseX = press.getX();
        mouseY = press.getY();

        Tower[] towers = Repository.getInstance().getTowers();
        for (Tower tower : towers) {
            Disk topDisk = tower.getTopDisk(); // Use a method that returns the top disk.
            if (topDisk != null && topDisk.contains(mouseX, mouseY)) {
                draggingDisk = topDisk;
                offsetX = mouseX - draggingDisk.getX();
                offsetY = mouseY - draggingDisk.getY();
                dragging = true;
                setCursor(grabbedHandCursor);
                break;
            }
        }
    }

    private Tower findNearestTower(int mouseX) {
        Tower[] towers = Repository.getInstance().getTowers();
        Tower nearestTower = null;
        int minDistance = Integer.MAX_VALUE;

        for (Tower tower : towers) {
            int distance = Math.abs(tower.getX() - mouseX);
            if (distance < minDistance) {
                minDistance = distance;
                nearestTower = tower;
            }
        }

        return nearestTower;
    }

    @Override
    public void mouseReleased(MouseEvent release) {
        if (draggingDisk != null) {
            // Determine which tower the disk should be dropped onto
            Tower nearestTower = findNearestTower(release.getX());
            if (nearestTower.canAddDisk(draggingDisk)) {
                Repository.getInstance().move(draggingDisk.getTower().getID(), nearestTower.getID());
            } else {
                // If it's an illegal move, return the disk to its original tower
                draggingDisk.getTower().addDisk(draggingDisk);
            }
        }
        dragging = false;
        draggingDisk = null; // Clear the reference to the dragging disk
        setCursor(openHandCursor);
        repaint(); // Repaint to show the disk in its new position
    }

    // MouseMotionListener methods
    @Override
    public void mouseDragged(MouseEvent dragged) {
        if (dragging && draggingDisk != null) {
            int newMouseX = dragged.getX();
            int newMouseY = dragged.getY();
            draggingDisk.setX(newMouseX - offsetX);
            draggingDisk.setY(newMouseY - offsetY);
            repaint(); // Repaint the panel to update the disk's position
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}


}
