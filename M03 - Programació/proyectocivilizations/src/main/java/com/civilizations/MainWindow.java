package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

public class MainWindow extends JFrame {
    private MainController controller;

    public MainWindow(MainController controller) {
        this.controller = controller;

        setTitle("Menú Principal");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un JPanel personalizado para dibujar la imagen de fondo
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Cargar la imagen de fondo
                    Image backgroundImage = ImageIO.read(new File("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/images/civilizationsbackground.jpg"));
                    // Dibujar la imagen de fondo
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panelPrincipal.setLayout(new GridBagLayout());

        // Configuración del Layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Título
        JLabel titulo = new JLabel("CIVILIZATIONS");
        titulo.setFont(new Font("Serif", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelPrincipal.add(titulo, gbc);

        // Configurar el color de fondo y el borde del título
        titulo.setBackground(new Color(139, 69, 19)); // color marrón
        titulo.setOpaque(true); // hacer el fondo visible

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelPrincipal.add(titulo, gbc);

        // Botones
        JButton btnCrearPartida = new JButton("Crear Partida");
        JButton btnCargarPartida = new JButton("Cargar Partida");
        JButton btnSalir = new JButton("Salir");

        // Establecer estilo para los botones
        btnCrearPartida.setForeground(Color.BLACK);
        btnCrearPartida.setFont(new Font("Garamond", Font.BOLD, 16));

        btnCargarPartida.setForeground(Color.BLACK);
        btnCargarPartida.setFont(new Font("Garamond", Font.BOLD, 16));

        btnSalir.setForeground(Color.BLACK);
        btnSalir.setFont(new Font("Garamond", Font.BOLD, 16));

        // Acción botones
        btnCrearPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.crearPartida();
            }
        });

        btnCargarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cargarPartida();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.salir();
            }
        });

        // Configuración para centrar cada botón
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelPrincipal.add(btnCrearPartida, gbc);

        gbc.gridy = 2;
        panelPrincipal.add(btnCargarPartida, gbc);

        gbc.gridy = 3;
        panelPrincipal.add(btnSalir, gbc);

        add(panelPrincipal);
    }
}
