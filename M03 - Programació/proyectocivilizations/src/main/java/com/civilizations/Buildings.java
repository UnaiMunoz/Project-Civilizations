package com.civilizations;

import javax.swing.*;
import java.awt.*;

public class Buildings extends JFrame {
    public Buildings() {
        setTitle("Buildings");

        // Crear los botones superiores con imágenes y establecer tamaño
        JButton button1 = new JButton(resizeImageIcon(new ImageIcon("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/carpentry.png"), 250, 200));
        JButton button2 = new JButton(resizeImageIcon(new ImageIcon("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/farm.png"), 250, 200));
        JButton button3 = new JButton(resizeImageIcon(new ImageIcon("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/smithy.png"), 250, 200));

        // Crear los botones inferiores con imágenes y establecer tamaño
        JButton button4 = new JButton(resizeImageIcon(new ImageIcon("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/church.png"), 250, 200));
        JButton button5 = new JButton(resizeImageIcon(new ImageIcon("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/magictower.png"), 250, 200));

        // Crear un contenedor para los botones superiores
        JPanel topButtonPanel = new JPanel();
        topButtonPanel.add(button1);
        topButtonPanel.add(button2);
        topButtonPanel.add(button3);

        // Crear un contenedor para los botones inferiores con FlowLayout centrado
        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomButtonPanel.add(button4);
        bottomButtonPanel.add(button5);

        // Crear un contenedor principal con BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.add(topButtonPanel, BorderLayout.NORTH);
        mainPanel.add(bottomButtonPanel, BorderLayout.CENTER);

        // Agregar el contenedor principal al marco
        getContentPane().add(mainPanel);

        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Método para redimensionar ImageIcon
    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        // Crear una instancia de Buildings
        SwingUtilities.invokeLater(Buildings::new);
    }
}
