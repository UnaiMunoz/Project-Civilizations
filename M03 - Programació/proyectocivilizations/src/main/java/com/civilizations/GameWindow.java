package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private JLabel timerLabel;
    private int seconds = 0;

    public GameWindow() {
        setTitle("Civilizations");
        setSize(900, 500); // Aumentar el ancho de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de la imagen o video
        JPanel mediaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\game2.jpg"); // Ruta al archivo de video o gif
                Image image = imageIcon.getImage();
                int width = getWidth();
                int height = getHeight();
                g.drawImage(image, 0, 0, width, height, this);
            }
        };
        mediaPanel.setPreferredSize(new Dimension(600, 500)); // Tamaño del panel
        add(mediaPanel, BorderLayout.CENTER);

        // Panel de la columna de información
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Añadir márgenes
        infoPanel.setPreferredSize(new Dimension(200, 500)); // Aumentar el ancho del panel de información

        JLabel maderaLabel = new JLabel("Wood");
        JLabel comidaLabel = new JLabel("Food");
        JLabel hierroLabel = new JLabel("Iron");
        JButton construccionesButton = new JButton("Buildings");
        JButton ejercitoButton = new JButton("Armies");

        // Añadir espacio entre los elementos
        infoPanel.add(maderaLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        infoPanel.add(comidaLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        infoPanel.add(hierroLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 100)));

        // ActionListener para el botón "Buildings"
        construccionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buildings buildingsWindow = new Buildings();
            }
        });
        infoPanel.add(construccionesButton);

        // ActionListener para el botón "Armies"
        ejercitoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí abre la ventana o ejecuta el código relacionado con "Armies"
                Armies armiesWindow = new Armies();
            }
        });
        infoPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        infoPanel.add(ejercitoButton);

        // Temporizador
        timerLabel = new JLabel("Tiempo: 00:00");
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(timerLabel);

        add(infoPanel, BorderLayout.EAST);

        // Iniciar el temporizador
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                int minutes = seconds / 60;
                int remainingSeconds = seconds % 60;
                String timeFormatted = String.format("%02d:%02d", minutes, remainingSeconds);
                timerLabel.setText("Tiempo: " + timeFormatted);
            }
        });
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameWindow();
            }
        });
    }
}
