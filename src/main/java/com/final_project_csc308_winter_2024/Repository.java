package com.final_project_csc308_winter_2024;

import java.beans.PropertyChangeSupport;
import java.awt.*;

public class Repository extends PropertyChangeSupport {

    private static Repository instance = null;
    private Solver solver; // solver stuff
    private int counter = 0;
    private Tower[] towers = new Tower[3];
    private int[] nextStep = new int[3]; // move disk # from tower # to tower #
    private long bestTime = 0;
    private int gameOver = 0;

    private int level = -1;
    private Repository() {
        super(new Object());
        initializeGame();
        this.solver = new Solver(); // Initialize the solver here
    }

    public static Repository getInstance() {
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    private void initializeGame() {
        if (level == -1){
            LevelLoader levelLoader = new LevelLoader();
            int level = levelLoader.selectLevel();
            setLevel(level);
        }
        if (level == 0){
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
        if (level == 1){
            towers[0] = new Tower(200, 300, 20, 200, 0);
            towers[1] = new Tower(400, 300, 20, 200, 1);
            towers[2] = new Tower(600, 300, 20, 200, 2);

            Disk disk = new Disk(100, 40, Color.BLUE, 4);
            Disk disk1 = new Disk(80, 40, Color.GREEN, 3);
            Disk disk2 = new Disk(60, 40, Color.YELLOW, 2);
            Disk disk3 = new Disk(40, 40, Color.BLACK, 1);

            towers[0].addDisk(disk);
            towers[0].addDisk(disk1);
            towers[0].addDisk(disk2);
            towers[0].addDisk(disk3);
        }
        if (level == 2){
            towers[0] = new Tower(200, 300, 20, 200, 0);
            towers[1] = new Tower(400, 300, 20, 200, 1);
            towers[2] = new Tower(600, 300, 20, 200, 2);

            Disk disk = new Disk(100, 40, Color.BLUE, 5);
            Disk disk1 = new Disk(80, 40, Color.GREEN, 4);
            Disk disk2 = new Disk(60, 40, Color.YELLOW, 3);
            Disk disk3 = new Disk(40, 40, Color.BLACK, 2);
            Disk disk4 = new Disk(20, 40, Color.CYAN, 1);

            towers[0].addDisk(disk);
            towers[0].addDisk(disk1);
            towers[0].addDisk(disk2);
            towers[0].addDisk(disk3);
            towers[0].addDisk(disk4);
        }
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
        this.level = -1;
        initializeGame();
        gameOver = 0;
        counter = 0;
        firePropertyChange("towers", null, towers);
        firePropertyChange("gameOver", null, gameOver);
        firePropertyChange("counter", null, counter);
    }

    public void solveGame() {
        // Re-use the same solver instance to solve the game
        solver.getMoves().clear(); // Clear previous moves
        solver.hanoi(towers[0].getID(), towers[2].getID(), towers[1].getID(), towers[0].getDisks().size());
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

    public void setLevel(int level) {
        this.level = level;
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

    public Solver getSolver() { // Getter for the solver
        return solver;
    }

    public String formatElapsedTime(long elapsedTime) {
        long milliseconds = elapsedTime % 1000; // Extract milliseconds
        long seconds = (elapsedTime / 1000) % 60; // Extract seconds
        long minutes = (elapsedTime / (1000 * 60)) % 60; // Extract minutes
        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds); // Format the time string
    }
}
