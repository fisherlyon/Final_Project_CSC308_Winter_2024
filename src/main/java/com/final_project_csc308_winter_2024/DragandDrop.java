import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javazoom.jl.player.Player;

import java.io.InputStream;

public class DragandDrop extends JPanel implements MouseListener, MouseMotionListener {
    private Cursor openHandCursor;
    private Cursor grabbedHandCursor;
    private int x, y; // position of shape
    private int offsetX, offsetY;
    private boolean dragging = false;


    public DragandDrop() {
        x = 200;
        y = 200;
        initializeCursors();
        addMouseListener(this);
        addMouseMotionListener(this);
        playMusic("sad-piano-background.mp3");
    }

    private void initializeCursors() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        try {
            Image openHandImage = ImageIO.read(getClass().getResourceAsStream("/open_hand.png"));
            openHandCursor = toolkit.createCustomCursor(openHandImage, new Point(0, 0), "Open Hand Cursor");

            Image grabbedHandImage = ImageIO.read(getClass().getResourceAsStream("/grabbed_hand.png"));
            grabbedHandCursor = toolkit.createCustomCursor(grabbedHandImage, new Point(0, 0), "Grabbed Hand Cursor");

            setCursor(openHandCursor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playMusic(String resourcePath) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    // Get the resource as a stream
                    InputStream inputStream = getClass().getResourceAsStream(resourcePath);
                    if (inputStream == null) {
                        throw new IllegalArgumentException("Resource not found: " + resourcePath);
                    }
                    Player playMP3 = new Player(inputStream);
                    playMP3.play();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.cyan);
        graphics.fillRect(x, y, 100, 100);
    }

    @Override
    public void mousePressed(MouseEvent press) {
        int mouse_x = press.getX();
        int mouse_y = press.getY();
        if (mouse_x > x && mouse_x < x + 100 && mouse_y > y && mouse_y < y + 100) {
            dragging = true;
            offsetX = mouse_x - x;
            offsetY = mouse_y - y;
        }

        if (press.getButton() == MouseEvent.BUTTON1) {
            setCursor(grabbedHandCursor); // button 1 is left click on mouse
        }
    }

    @Override
    public void mouseReleased(MouseEvent stop) {
        dragging = false;
        setCursor(openHandCursor); // true and false for hand open or close
    }

    @Override
    public void mouseDragged(MouseEvent drag) {
        if (dragging) {
            x = drag.getX() - offsetX;
            y = drag.getY() - offsetY;
            repaint();
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