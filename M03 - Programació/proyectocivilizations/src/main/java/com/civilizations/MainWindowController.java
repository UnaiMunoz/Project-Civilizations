package com.civilizations;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;

public class MainWindowController {
    private Player player;
    private Thread playerThread;

    public void playBackgroundMusic(String filePath) {
        playerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                    player = new Player(fileInputStream);
                    player.play();
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        playerThread.start();
    }

    public void stopBackgroundMusic() {
        if (player != null) {
            player.close();
        }
        if (playerThread != null && playerThread.isAlive()) {
            playerThread.interrupt();
        }
    }

    public void startNewGame(JFrame currentWindow) {
        stopBackgroundMusic();
        currentWindow.dispose();
        SwingUtilities.invokeLater(() -> {
            NewGameWindow newGameWindow = new NewGameWindow();
            newGameWindow.setVisible(true);
        });
    }

    public void loadGame(JFrame currentWindow) {
        stopBackgroundMusic();
        currentWindow.dispose();
        SwingUtilities.invokeLater(() -> {
            LoadGameWindow loadGameWindow = new LoadGameWindow();
            loadGameWindow.setVisible(true);
        });
    }

    public void exitApplication() {
        stopBackgroundMusic();
        System.exit(0);
    }
}
