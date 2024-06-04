package com.civilizations;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

    private MainWindowController controller;

    public MainWindow() {
        // Configurar la ventana
        setTitle("Civilizations");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar el controlador
        controller = new MainWindowController();

        // Reproducir la música de fondo
        controller.playBackgroundMusic("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\BGM\\AmomentPeace.mp3");

        // Crear un JPanel personalizado para dibujar la imagen de fondo
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Cargar la imagen de fondo
                    Image backgroundImage = ImageIO.read(new File("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/civilizations.jpg"));
                    // Dibujar la imagen de fondo
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(null);

        // Crear un JLabel para el título
        JLabel titulo = new JLabel("Civilizations", SwingConstants.CENTER);
        titulo.setForeground(Color.BLACK); // Color del texto
        titulo.setFont(new Font("Garamond", Font.BOLD, 36)); // Fuente del texto
        titulo.setBounds(200, 50, 400, 50); // Posición y tamaño del título
        panel.add(titulo);

        // Crear botones personalizados y añadirlos al panel
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit");

        newGameButton.setBounds(50, 150, 130, 50);
        loadGameButton.setBounds(50, 220, 130, 50);
        exitButton.setBounds(50, 290, 130, 50);

        // Establecer estilo para los botones
        newGameButton.setForeground(Color.BLACK);
        newGameButton.setFont(new Font("Garamond", Font.BOLD, 16));

        loadGameButton.setForeground(Color.BLACK);
        loadGameButton.setFont(new Font("Garamond", Font.BOLD, 16));

        exitButton.setForeground(Color.BLACK);
        exitButton.setFont(new Font("Garamond", Font.BOLD, 16));

        // Agregar ActionListener a los botones
        newGameButton.addActionListener(e -> controller.startNewGame(this));
        loadGameButton.addActionListener(e -> controller.loadGame(this));
        exitButton.addActionListener(e -> controller.exitApplication());

        panel.add(newGameButton);
        panel.add(loadGameButton);
        panel.add(exitButton);

        // Añadir el panel al JFrame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
