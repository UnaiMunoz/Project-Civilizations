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
        // Configurar la ventana
        setTitle("Civilizations");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un JPanel personalizado para dibujar la imagen de fondo
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Cargar la imagen de fondo
                    Image backgroundImage = ImageIO.read(new File("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\civilizations.jpg"));
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
        JButton boton1 = new JButton("New Game");
        JButton boton2 = new JButton("Load Game");
        JButton boton3 = new JButton("Exit");

        boton1.setBounds(50, 150, 130, 50);
        boton2.setBounds(50, 220, 130, 50);
        boton3.setBounds(50, 290, 130, 50);

        // Establecer estilo para los botones
        boton1.setForeground(Color.BLACK);
        boton1.setFont(new Font("Garamond", Font.BOLD, 16));

        boton2.setForeground(Color.BLACK);
        boton2.setFont(new Font("Garamond", Font.BOLD, 16));

        boton3.setForeground(Color.BLACK);
        boton3.setFont(new Font("Garamond", Font.BOLD, 16));

        // Agregar ActionListener al botón "Exit"
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(boton1);
        panel.add(boton2);
        panel.add(boton3);

        // Añadir el panel al JFrame
        add(panel);
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        MainWindow ventana = new MainWindow();
        ventana.setVisible(true);
    }
}




