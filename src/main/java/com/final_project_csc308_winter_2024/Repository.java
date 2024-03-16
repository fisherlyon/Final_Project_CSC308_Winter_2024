package com.final_project_csc308_winter_2024;

import java.beans.PropertyChangeSupport;
import java.awt.*;

public class Repository extends PropertyChangeSupport {

    private static Repository instance = null;
    private int counter = 0;
    private Tower[] towers = new Tower[3];
    private int[] nextStep = new int[3]; // move disk # from tower # to tower #
    private long bestTime = 0;
    private int gameOver = 0;

    private Repository() {
        super(new Object());
        initializeGame();
    }

    public static Repository getInstance() {
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    private void initializeGame() {
        towers[0] = new Tower(200, 300, 20, 200, 0);
        towers[1] = new Tower(400, 300, 20, 200, 1);
        towers[2] = new Tower(600, 300, 20, 200, 2);

        Disk disk = new Disk(100, 40, Color.BLUE, 3);
        Disk disk1 = new Disk(80, 40, Color.GREEN, 2);
        Disk disk2 = new Disk(60, 40, Color.YELLOW, 1);

        towers[0].addDisk(disk);
        towers[0].addDisk(disk1);
        towers[0].addDisk(disk2);
    }

    public void move(int old_tower, int new_tower) {
        counter += 1;
        towers[new_tower].addDisk(towers[old_tower].removeTopDisk());
        firePropertyChange("counter", null, counter);
        firePropertyChange("towers", null, towers);

        if (towers[0].getDisks().isEmpty() && towers[1].getDisks().isEmpty()) {
            gameOver = 1;
            firePropertyChange("gameOver", null, gameOver);
        }
    }

    public void restart() {
        initializeGame();
    }

    public void changeBestTime(long time) {
        if (bestTime == 0 || time < bestTime) {
            bestTime = time;
            firePropertyChange("bestTime", null, bestTime);
        }
    }

    public int getCount() {
        return counter;
    }

    public Tower[] getTowers() {
        return towers;
    }

    public int[] getNextStep() {
        return nextStep;
    }

    public long getBestTime() {
        return bestTime;
    }

    public void setBestTime(long newTime) {
        bestTime = newTime;
    }

    public String formatElapsedTime(long elapsedTime) {
        long milliseconds = elapsedTime % 1000; // Extract milliseconds
        long seconds = (elapsedTime / 1000) % 60; // Extract seconds
        long minutes = (elapsedTime / (1000 * 60)) % 60; // Extract minutes
        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds); // Format the time string
    }
}
