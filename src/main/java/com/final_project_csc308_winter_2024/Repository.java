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

    private LevelLoader levelLoader = new LevelLoader();

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
        int DISK_HEIGHT = 15;
        int DISK_WIDTH = 20;

        if (level == -1){
            int revel = levelLoader.selectLevel();
            this.level = revel;
        }
        if (level == 0){
            towers[0] = new Tower(200, 300, 20, 200, 0);
            towers[1] = new Tower(400, 300, 20, 200, 1);
            towers[2] = new Tower(600, 300, 20, 200, 2);

            ZebraStripedDisk disk = new ZebraStripedDisk(DISK_WIDTH * 6, DISK_HEIGHT, Color.BLUE, 3);
            ZebraStripedDisk disk1 = new ZebraStripedDisk(DISK_WIDTH * 5, DISK_HEIGHT, Color.GREEN, 2);
            ZebraStripedDisk disk2 = new ZebraStripedDisk(DISK_WIDTH * 4, DISK_HEIGHT, Color.YELLOW, 1);

            disk.setZebraStripes(Color.BLACK, 1); // Customize zebra stripes if needed
            disk1.setZebraStripes(Color.BLACK, 1);
            disk2.setZebraStripes(Color.BLACK, 1);

            towers[0].addDisk(disk);
            towers[0].addDisk(disk1);
            towers[0].addDisk(disk2);
        }
        if (level == 1){
            towers[0] = new Tower(200, 300, 20, 200, 0);
            towers[1] = new Tower(400, 300, 20, 200, 1);
            towers[2] = new Tower(600, 300, 20, 200, 2);

            ZebraStripedDisk disk = new ZebraStripedDisk(DISK_WIDTH * 6, DISK_HEIGHT, Color.BLUE, 4);
            ZebraStripedDisk disk1 = new ZebraStripedDisk(DISK_WIDTH * 5, DISK_HEIGHT, Color.GREEN, 3);
            ZebraStripedDisk disk2 = new ZebraStripedDisk(DISK_WIDTH * 4, DISK_HEIGHT, Color.YELLOW, 2);
            ZebraStripedDisk disk3 = new ZebraStripedDisk(DISK_WIDTH * 3, DISK_HEIGHT, Color.ORANGE, 1);

            disk.setZebraStripes(Color.BLACK, 1);
            disk1.setZebraStripes(Color.BLACK, 1);
            disk2.setZebraStripes(Color.BLACK, 1);
            disk3.setZebraStripes(Color.BLACK, 1);

            towers[0].addDisk(disk);
            towers[0].addDisk(disk1);
            towers[0].addDisk(disk2);
            towers[0].addDisk(disk3);
        }
        if (level == 2){
            towers[0] = new Tower(200, 300, 20, 200, 0);
            towers[1] = new Tower(400, 300, 20, 200, 1);
            towers[2] = new Tower(600, 300, 20, 200, 2);

            ZebraStripedDisk disk = new ZebraStripedDisk(DISK_WIDTH * 6, DISK_HEIGHT, Color.BLUE, 5);
            ZebraStripedDisk disk1 = new ZebraStripedDisk(DISK_WIDTH * 5, DISK_HEIGHT, Color.GREEN, 4);
            ZebraStripedDisk disk2 = new ZebraStripedDisk(DISK_WIDTH * 4, DISK_HEIGHT, Color.YELLOW, 3);
            ZebraStripedDisk disk3 = new ZebraStripedDisk(DISK_WIDTH * 3, DISK_HEIGHT, Color.ORANGE, 2);
            ZebraStripedDisk disk4 = new ZebraStripedDisk(DISK_WIDTH * 2, DISK_HEIGHT, Color.CYAN, 1);

            disk.setZebraStripes(Color.BLACK, 1);
            disk1.setZebraStripes(Color.BLACK, 1);
            disk2.setZebraStripes(Color.BLACK, 1);
            disk3.setZebraStripes(Color.BLACK, 1);
            disk4.setZebraStripes(Color.BLACK, 1);

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

    public LevelLoader getLevelLoader(){
        return levelLoader;
    }
    public int getCount() {
        return counter;
    }

    public void setLevel(int level) {
        this.level = level;
        restart();
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
