package com.civilizations;

import javax.swing.*;
import java.awt.*;

public class Armies extends JFrame {
    public Armies() {
        setTitle("Armies");

        // Crear el título
        JLabel titleLabel = new JLabel("Armies", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 36));  // Puedes ajustar el tamaño y la fuente

        // Crear los botones con imágenes redimensionadas y textos
        JButton button1 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\swordsman2.jpg", "Swordsman", 100, 100);
        JButton button2 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\cannon.png", "Cannon", 100, 100);
        JButton button3 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\spearman.png", "Spearman", 100, 100);
        JButton button4 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\crossbow.png", "Crossbow", 100, 100);
        JButton button5 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\catapult.png", "Catapult", 100, 100);
        JButton button6 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\arrowtower.png", "Arrow Tower", 100, 100);
        JButton button7 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\rockettower.png", "Rocket Tower", 100, 100);
        JButton button8 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\magician.png", "Magician", 100, 100);
        JButton button9 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\priest.png", "Priest", 100, 100);

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);
        button6.setFocusable(false);
        button7.setFocusable(false);
        button8.setFocusable(false);
        button9.setFocusable(false);

        // Crear un contenedor para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // 3 filas, 3 columnas, espacio horizontal y vertical de 10
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
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

        // Añadir ActionListener para mostrar ventana emergente
        button.addActionListener(e -> JOptionPane.showMessageDialog(Armies.this, "Se ha creado " + text, "Creación de Ejército", JOptionPane.INFORMATION_MESSAGE));

        return button;
    }

    public static void main(String[] args) {
        // Crear una instancia de Armies
        SwingUtilities.invokeLater(Armies::new);
    }
}