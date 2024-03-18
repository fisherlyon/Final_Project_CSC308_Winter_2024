package com.final_project_csc308_winter_2024;

import javax.swing.*;

public class LevelLoader extends JPanel {

    public LevelLoader(){
    }

    public int selectLevel(){
        String[] levelOptions = {"3 Disks", "4 Disks", "5 Disks"};
        int choice = JOptionPane.showOptionDialog(null, "Select Level", "Tower of Hanoi Level", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, levelOptions ,levelOptions[0]);
        return choice;
    }
}
