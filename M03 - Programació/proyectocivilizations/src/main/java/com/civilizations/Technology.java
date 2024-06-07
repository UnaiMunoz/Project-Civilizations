package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Technology extends JFrame {

    private int civilizationId;
    private GameWindow gameWindow;

    public Technology(String username, int civilizationId, GameWindow gameWindow) {

        this.civilizationId = civilizationId; // Asignar el civilizationId aquí
        this.gameWindow = gameWindow; // Asignar la referencia de GameWindow
        System.out.println("ID de buildings: " + this.civilizationId);

        setTitle("Technology");

        // Crear el título
        JLabel titleLabel = new JLabel("Technology", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 36));  // Puedes ajustar el tamaño y la fuente

        // Crear los botones con textos
        JButton upgradeAttackButton = createButton("Upgrade Attack Tech", 200, 100);
        upgradeAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Upgrade Attack Tech ActionListener ejecutado");
                System.out.println("ID de civilization al pulsar botón: " + Technology.this.civilizationId);
                TechnologyDAO TechnologyDAO = new TechnologyDAO();
                TechnologyDAO.upgradeAttackTech(Technology.this.civilizationId);
                gameWindow.UpdateFields();
            }
        });

        JButton upgradeDefenseButton = createButton("Upgrade Defense Tech", 200, 100);
        upgradeDefenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Upgrade Defense Tech ActionListener ejecutado");
                System.out.println("ID de civilization al pulsar botón: " + Technology.this.civilizationId);
                TechnologyDAO TechnologyDAO = new TechnologyDAO();
                TechnologyDAO.upgradeDefenseTech(Technology.this.civilizationId);
                gameWindow.UpdateFields();
            }
        });

        upgradeAttackButton.setFocusable(false);
        upgradeDefenseButton.setFocusable(false);

        // Crear un contenedor para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // 1 fila, 2 columnas, espacio horizontal y vertical de 10
        buttonPanel.add(upgradeAttackButton);
        buttonPanel.add(upgradeDefenseButton);
        buttonPanel.setOpaque(false);  // Hacer el panel transparente para mostrar la imagen de fondo

        // Crear un contenedor principal con BorderLayout
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/game2.jpg");  // Ruta de la imagen de fondo
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);  // Añadir el título en la parte superior
        mainPanel.add(buttonPanel, BorderLayout.CENTER);  // Añadir los botones en el centro

        // Agregar el contenedor principal al marco
        getContentPane().add(mainPanel);
        setSize(900, 500);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setVisible(true);
    }

    // Método para crear botón con tamaño preferido
    private JButton createButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Garamond", Font.PLAIN, 18));  // Cambiar la tipografía del botón
        button.setPreferredSize(new Dimension(width, height + 30));  // Añadir espacio para el texto

        // Añadir ActionListener para mostrar ventana emergente
        button.addActionListener(e -> JOptionPane.showMessageDialog(Technology.this, "Se ha creado " + text, "Mejora de Tecnología", JOptionPane.INFORMATION_MESSAGE));

        return button;
    }

    public static void main(String[] args) {
        // Crear una instancia de Technology
        SwingUtilities.invokeLater(() -> new GameWindow("testUser"));
    }
}
