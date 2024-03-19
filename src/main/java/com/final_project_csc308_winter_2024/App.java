package com.final_project_csc308_winter_2024;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {

    TimeTrialPanel timeTrialPanel = new TimeTrialPanel(new TimeTrial());
    JComboBox<String> dropdown;
    String[] levelOptions = {"3 Disks", "4 Disks", "5 Disks"};
    public App() {
        PlayMusic music = new PlayMusic("sad-piano-background.mp3");

        Thread t = new Thread(music);
        t.start();

        setTitle("Tower of Hanoi");
        setLocation(0, 0);

        // Create TowerPanel and TutorPanel
        TowerPanel towerPanel = new TowerPanel();
        TutorPanel tutorPanel = new TutorPanel();
        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(new GridLayout(2, 1)); // 1 row, 2 columns
        rightPanel.add(tutorPanel);
        rightPanel.add(timeTrialPanel);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        restartButton.setFont(new Font("Monaco", Font.BOLD, 20));
        towerPanel.add(restartButton);
        dropdown = new JComboBox<>(levelOptions);
//        dropdown.setBounds(100,100, 50, 50);
        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selected = (String) combo.getSelectedItem();
                if (selected.equals("3 Disks")) {
                    Repository.getInstance().setLevel(0);
                }
                if (selected.equals("4 Disks")) {
                    Repository.getInstance().setLevel(1);
                }
                if (selected.equals("5 Disks")) {
                    Repository.getInstance().setLevel(2);
                }
            }
        });
        towerPanel.add(dropdown);

        setLayout(new GridLayout(1, 2));
        add(towerPanel);
        add(rightPanel);
    }

    public static void main( String[] args ) {
        App main = new App();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(1920, 1080);
        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Repository.getInstance().restart();
        timeTrialPanel.getTimeTrial().stop();
    }
}