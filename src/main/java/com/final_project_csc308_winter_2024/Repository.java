package com.final_project_csc308_winter_2024;

import java.beans.PropertyChangeSupport;

public class Repository extends PropertyChangeSupport {

    private static Repository instance = null;
    private int counter = 0;
    private Tower[] towers = new Tower[3];
    private int[] nextStep = new int[3]; // move disk # from tower # to tower #

    private Repository() {
        super(new Object());
    }

    public static Repository getInstance(){
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void move(int old_tower, int new_tower) {
        counter += 1;
        Disk disk = towers[old_tower].peek();
        if (disk.getSize() < towers[new_tower].peek().getSize()) {
            towers[old_tower].pop();
            towers[new_tower].push(disk);
            firePropertyChange("counter", null, counter);
            firePropertyChange("towers", null, towers);
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
}
