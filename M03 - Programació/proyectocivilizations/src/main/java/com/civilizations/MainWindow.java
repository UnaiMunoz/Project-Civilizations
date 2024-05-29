package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainWindow extends JFrame {
    public MainWindow() {
        // Configure the window
        setTitle("Civilizations");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a custom JPanel to draw the background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Load the background image
                    Image backgroundImage = ImageIO.read(new File("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\civilizations.jpg"));
                    // Draw the background image
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(null);

        // Create a JLabel for the title
        JLabel title = new JLabel("Civilizations", SwingConstants.CENTER);
        title.setForeground(Color.BLACK); // Text color
        title.setFont(new Font("Garamond", Font.BOLD, 36)); // Text font
        title.setBounds(200, 50, 400, 50); // Position and size of the title
        panel.add(title);

        // Create custom buttons and add them to the panel
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit");

        newGameButton.setBounds(50, 150, 130, 50);
        loadGameButton.setBounds(50, 220, 130, 50);
        exitButton.setBounds(50, 290, 130, 50);

        // Set styles for the buttons
        Font buttonFont = new Font("Garamond", Font.BOLD, 16);
        Color buttonTextColor = Color.BLACK;

        newGameButton.setForeground(buttonTextColor);
        newGameButton.setFont(buttonFont);

        loadGameButton.setForeground(buttonTextColor);
        loadGameButton.setFont(buttonFont);

        exitButton.setForeground(buttonTextColor);
        exitButton.setFont(buttonFont);

        // Add ActionListener to the "New Game" button
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                SwingUtilities.invokeLater(() -> {
                    NewGameWindow newGameWindow = new NewGameWindow();
                    newGameWindow.setVisible(true);
                });
            }
        });
        
        // Add ActionListener to the "Load Game" button
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                SwingUtilities.invokeLater(() -> {
                    LoadGameWindow loadGameWindow = new LoadGameWindow();
                    loadGameWindow.setVisible(true);
                });
            }
        });

        // Add ActionListener to the "Exit" button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the application
            }
        });

        panel.add(newGameButton);
        panel.add(loadGameButton);
        panel.add(exitButton);

        // Add the panel to the JFrame
        add(panel);
    }

    public static void main(String[] args) {
        // Create and display the window
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}