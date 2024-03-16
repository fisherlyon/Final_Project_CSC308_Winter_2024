package com.final_project_csc308_winter_2024;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {


    public App() {
//        PlayMusic music = new PlayMusic("sad-piano-background.mp3");
//        Thread t = new Thread(music);
//        t.start();

        setTitle("Tower of Hanoi");
        setLocation(100, 100);

        Tower tower = Repository.getInstance().getTowers()[0] = new Tower(200, 300, 20, 200);
        Tower tower1 = Repository.getInstance().getTowers()[1] = new Tower(400, 300, 20, 200);
        Tower tower2 = Repository.getInstance().getTowers()[2] = new Tower(600, 300, 20, 200);

        Disk disk = new Disk(100, 80, Color.BLUE, 3);
        Disk disk1 = new Disk(80, 60, Color.GREEN, 2);
        Disk disk2 = new Disk(60, 40, Color.YELLOW, 1);

        tower.addDisk(disk);
        tower.addDisk(disk1);
        tower.addDisk(disk2);

        // Create TowerPanel and TutorPanel
        TowerPanel towerPanel = new TowerPanel();
        TutorPanel tutorPanel = new TutorPanel();
        tutorPanel.setBackground(Color.RED);
        towerPanel.setBackground(Color.WHITE);

//        new DragandDrop(disk);
//        DragandDrop drag = new DragandDrop(disk);
//        towerPanel.add(drag);
//        getContentPane().add(disk); // need this

        // Set layout for the App frame
        setLayout(new GridLayout(1, 2)); // 1 row, 2 columns

        // Add TowerPanel to the left side (first column)
//        JPanel leftPanel = new JPanel(new BorderLayout());
//        leftPanel.add(towerPanel, BorderLayout.CENTER);
//        add(leftPanel);

        add(towerPanel);

        // Add TutorPanel to the right side (second column)
        add(tutorPanel);

        System.out.println("Hello World!");
    }

    public static void main( String[] args ) {
        App main = new App();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(800, 600);
        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}