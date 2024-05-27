package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private MainController controller;

    public MainWindow(MainController controller) {
        this.controller = controller;

        setTitle("Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // foto
        JPanel panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("images/civilizationsbackground.jpg");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
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
        titulo.setForeground(Color.BLACK);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelPrincipal.add(titulo, gbc);

        // botones
        JButton btnCrearPartida = new JButton("Crear Partida");
        JButton btnCargarPartida = new JButton("Cargar Partida");
        JButton btnSalir = new JButton("Salir");

        // acción botones
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

