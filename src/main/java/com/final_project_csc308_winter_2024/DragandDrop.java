package com.final_project_csc308_winter_2024;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javazoom.jl.player.Player;

import java.io.InputStream;

public class DragandDrop implements MouseListener, MouseMotionListener {
    private Cursor openHandCursor;
    private Cursor grabbedHandCursor;
    private int offsetX, offsetY;
    private boolean dragging = false;
    private Disk disk;


    public DragandDrop(Disk disk) {
        this.disk = disk;
        initializeCursors();
        disk.addMouseListener(this);
        disk.addMouseMotionListener(this);
    }

    private void initializeCursors() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        try {
            Image openHandImage = ImageIO.read(getClass().getResourceAsStream("/open_hand.png"));
            openHandCursor = toolkit.createCustomCursor(openHandImage, new Point(0, 0), "Open Hand Cursor");

            Image grabbedHandImage = ImageIO.read(getClass().getResourceAsStream("/grabbed_hand.png"));
            grabbedHandCursor = toolkit.createCustomCursor(grabbedHandImage, new Point(0, 0), "Grabbed Hand Cursor");

            // Initially set the cursor to open hand
            disk.setCursor(openHandCursor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void mousePressed(MouseEvent press) {
        int mouse_x = press.getX();
        int mouse_y = press.getY();
        if (mouse_x > disk.getX() && mouse_x < disk.getX() + 100 && mouse_y > disk.getY() && mouse_y < disk.getY() + 100) {
            dragging = true;
            offsetX = mouse_x - disk.getX();
            offsetY = mouse_y - disk.getY();
        }

        if (press.getButton() == MouseEvent.BUTTON1) {
            disk.setCursor(grabbedHandCursor); // button 1 is left click on mouse
        }
    }

    @Override
    public void mouseReleased(MouseEvent stop) {
        dragging = false;
        disk.setCursor(openHandCursor); // true and false for hand open or close
    }

    @Override
    public void mouseDragged(MouseEvent drag) {
        if (dragging) {
            disk.setX(drag.getX() - offsetX);
            disk.setY(drag.getY() - offsetY);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}

}