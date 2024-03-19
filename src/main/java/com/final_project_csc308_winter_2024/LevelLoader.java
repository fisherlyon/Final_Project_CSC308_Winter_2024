package com.final_project_csc308_winter_2024;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelLoader {

    public LevelLoader() {
    }

    private int selectLevel;
    private String[] levelOptions = {"3 Disks", "4 Disks", "5 Disks"};

    public int getselectLevel() {
        return selectLevel;
    }

    public int selectLevel() {
        int choice = JOptionPane.showOptionDialog(null, "Select Level", "Tower of Hanoi Level", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, levelOptions, levelOptions[0]);
        return choice;
    }


}