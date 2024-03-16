//package com.final_project_csc308_winter_2024;
//import javazoom.jl.player.Player;
//
//import java.io.InputStream;
//
//public class PlayMusic implements Runnable{
//    private final String resourcePath;
//    public PlayMusic(String resourcePath){
//        this.resourcePath = resourcePath;
//    }
//
//
//    @Override
//    public void run() {
//        try {
//            // Get the resource as a stream
//            InputStream inputStream = getClass().getResourceAsStream(resourcePath);
//            if (inputStream == null) {
//                throw new IllegalArgumentException("Resource not found: " + resourcePath);
//            }
//            Player playMP3 = new Player(inputStream);
//            playMP3.play();
//        }
//        catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//
//    }
//
//
//}
