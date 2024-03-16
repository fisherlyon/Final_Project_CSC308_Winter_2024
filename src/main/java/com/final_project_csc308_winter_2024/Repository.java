package com.final_project_csc308_winter_2024;

import java.beans.PropertyChangeSupport;

public class Repository extends PropertyChangeSupport {

    private static Repository instance = null;
    private int counter = 0;
    private Tower[] towers = new Tower[3];
    private int[] nextStep = new int[3]; // move disk # from tower # to tower #
    private long bestTime = 0;

    private Repository() {
        super(new Object());
    }

    public static Repository getInstance() {
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void move(int old_tower, int new_tower) {
        counter += 1;
        Disk disk = towers[old_tower].getDisks().peek();
        if (disk.getWeight() < towers[new_tower].getDisks().peek().getWeight()) {
            towers[old_tower].getDisks().pop();
            towers[new_tower].getDisks().push(disk);
            firePropertyChange("counter", null, counter);
            firePropertyChange("towers", null, towers);
        }
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
