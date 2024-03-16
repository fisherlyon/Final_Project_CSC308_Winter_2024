/**
 * A simple class for measuring elapsed time.
 */
package com.final_project_csc308_winter_2024;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTrial {
    private Timer timer; 
    private long elapsedTime; 
    private boolean running; 

    /**
     * Constructs a TimeTrial object with initial values.
     */
    public TimeTrial() {
        timer = new Timer(); 
        elapsedTime = 0;
        running = false;
    }

    /**
     * Starts the timer.
     */
    public void start() {
        if (!running) {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    elapsedTime += 100; 
                }
            }, 0, 100); 
            running = true; 
        }
    }

    /**
     * Stops the timer.
     *
     * @return The elapsed time in milliseconds.
     */
    public long stop() {
        if (running) { 
            timer.cancel(); 
            running = false;
        }
        return elapsedTime; 
    }

    /**
     * Restarts the timer.
     */
    public void restart() {
        if (!running) {
            timer = new Timer();
            elapsedTime = 0;
            start(); 
        }
    }

    /**
     * Retrieves the elapsed time in milliseconds.
     *
     * @return The elapsed time in milliseconds.
     */
    public long getElapsedTime() {
        return elapsedTime; // Return elapsed time
    }

    /**
     * Formats the elapsed time as a string in the format "mm:ss.SSS" (minutes:seconds.milliseconds).
     *
     * @return The formatted elapsed time string.
     */
    public String formatElapsedTime() {
        long milliseconds = elapsedTime % 1000; 
        long seconds = (elapsedTime / 1000) % 60; // Extract seconds
        long minutes = (elapsedTime / (1000 * 60)) % 60; // Extract minutes
        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds); // Format the time string
    }
}
