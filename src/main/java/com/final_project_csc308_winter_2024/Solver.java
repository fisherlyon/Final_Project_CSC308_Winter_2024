package com.final_project_csc308_winter_2024;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private List<String> moves; // List to store move instructions
    private int currentMoveIndex = 0; //track the current move

    public Solver() {
        this.moves = new ArrayList<>(); // Initialize the moves list
    }

    public void hanoi(int from, int to, int buf, int nmv) {
        if (nmv > 0) {
            hanoi(from, buf, to, nmv-1);
            moves.add("Move disk " + nmv + " from Tower " + (from + 1) + " to Tower " + (to + 1));
            hanoi(buf, to, from, nmv-1);
        }
    }

    public List<String> getMoves() {
        return moves;
    }

    // Method to get the first move
    public String getFirstMove() {
        if (!moves.isEmpty()) {
            return moves.get(0);
        } else {
            // Return null or a default message indicating no moves are available
            return "Congratulations, you completed the towers!";
        }
    }

    // Method to get the next move and increment the move index
    public String getNextMove() {
        if (currentMoveIndex < moves.size()) {
            String move = moves.get(currentMoveIndex);
            currentMoveIndex++; // Move to the next index
            return move;
        } else {
            return "No more moves available"; // When all moves have been shown
        }
    }

    // maybe a method that just figures out the next move only?
}
