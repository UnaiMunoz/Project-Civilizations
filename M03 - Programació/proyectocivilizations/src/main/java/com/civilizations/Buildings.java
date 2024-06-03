package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buildings extends JFrame {

    private int civilizationId;

    // Constructor para nueva partida
    public Buildings(String username) {
        CivilizationDAO civilizationDAO = new CivilizationDAO();
        civilizationId = civilizationDAO.getCivilizationIdByName(username);
    }

    public Buildings() {
        setTitle("Buildings");

        // Crear el título
        JLabel titleLabel = new JLabel("Buildings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 36));  // Puedes ajustar el tamaño y la fuente

        // Crear los botones superiores con imágenes redimensionadas y textos
        JButton carpentryButton = createButton("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/carpentry.png", "Carpentry", 180, 140);
        JButton FarmButton = createButton("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/farm.png", "Farm", 180, 140);
        JButton SmithyButton = createButton("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/smithy.png", "Smithy", 180, 140);

        // Crear los botones inferiores con imágenes redimensionadas y textos
        JButton ChurchButton = createButton("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/church.png", "Church", 180, 140);
        JButton MaigcTowerButton = createButton("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/magictower.png", "Magic Tower", 180, 140);

        carpentryButton.setFocusable(false);
        FarmButton.setFocusable(false);
        SmithyButton.setFocusable(false);
        ChurchButton.setFocusable(false);
        MaigcTowerButton.setFocusable(false);

        // Crear un contenedor para los botones superiores
        JPanel topButtonPanel = new JPanel();
        topButtonPanel.setLayout(new BoxLayout(topButtonPanel, BoxLayout.X_AXIS)); // Establecer el layout horizontal
        topButtonPanel.add(carpentryButton);
        topButtonPanel.add(Box.createHorizontalStrut(40)); // Espacio entre el primer y segundo botón
        topButtonPanel.add(FarmButton);
        topButtonPanel.add(Box.createHorizontalStrut(40)); // Espacio entre el segundo y tercer botón
        topButtonPanel.add(SmithyButton);
        topButtonPanel.setOpaque(false);  // Hacer el panel transparente para mostrar la imagen de fondo

        // Crear un contenedor para los botones inferiores con FlowLayout centrado
        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomButtonPanel.add(ChurchButton);
        bottomButtonPanel.add(Box.createHorizontalStrut(40)); // Espacio entre los botones inferior
        bottomButtonPanel.add(MaigcTowerButton);
        bottomButtonPanel.setOpaque(false);  // Hacer el panel transparente para mostrar la imagen de fondo

        // Crear un contenedor principal con BoxLayout
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/game2.jpg");  // Ruta de la imagen de fondo
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(titleLabel);  // Añadir el título en la parte superior
        mainPanel.add(Box.createVerticalStrut(20));  // Espacio entre el título y los botones superiores
        mainPanel.add(topButtonPanel);  // Añadir los botones superiores
        mainPanel.add(Box.createVerticalStrut(20));  // Espacio entre los botones superiores e inferiores
        mainPanel.add(bottomButtonPanel);  // Añadir los botones inferiores

        // Agregar el contenedor principal al marco
        getContentPane().add(mainPanel);

        setSize(900, 500);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Método para crear botón con imagen redimensionada y tamaño preferido
    private JButton createButton(String imagePath, String text, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Garamond", Font.PLAIN, 18));  // Cambiar la tipografía del botón
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setPreferredSize(new Dimension(width, height + 30));  // Añadir espacio para el texto

        button.setFocusable(false);

        // Añadir ActionListener para mostrar ventana emergente
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Buildings.this, "Se ha creado " + text, "Creación de Edificio", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        // Crear una instancia de Buildings
        SwingUtilities.invokeLater(Buildings::new);
    }
}
