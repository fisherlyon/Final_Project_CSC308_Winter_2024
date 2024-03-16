package com.final_project_csc308_winter_2024;

public class Solver {
    private Repository repository; // Reference to the Repository

    public Solver(Repository repository) {
        this.repository = repository;
    }

    public void hanoi(Tower from, Tower to, Tower buf, int nmv) {
        if (nmv > 1) {
            hanoi(from, buf, to, nmv - 1);
            moveDisk(from, to);
            repository.move(from.getID(), to.getID()); // Inform Repository about the move
            hanoi(buf, to, from, nmv - 1);
        } else {
            moveDisk(from, to);
            repository.move(from.getID(), to.getID()); // Inform Repository about the move
        }
    }

    public static void moveDisk(Tower source, Tower target) {
        Disk disk = source.getTopDisk();
        target.addDisk(disk);
        System.out.println("Move disk " + disk + " from " + source + " to " + target);
    }

    // maybe a method that just figures out the next move only?
}
