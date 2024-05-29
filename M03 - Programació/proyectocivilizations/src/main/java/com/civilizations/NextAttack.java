package com.civilizations;

import javax.swing.*;
import java.awt.*;

public class NextAttack extends JFrame {
    public NextAttack() {
        setTitle("Next Attack");
        setSize(300, 200);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un panel principal con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Añadir márgenes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaciado entre componentes

        // Crear y añadir las etiquetas y campos de texto al panel
        JLabel swordsmanLabel = new JLabel("Swordsman:");
        JTextField swordsmanField = new JTextField(10);
        swordsmanField.setEditable(false);

        JLabel spearmanLabel = new JLabel("Spearman:");
        JTextField spearmanField = new JTextField(10);
        spearmanField.setEditable(false);

        JLabel cannonLabel = new JLabel("Cannon:");
        JTextField cannonField = new JTextField(10);
        cannonField.setEditable(false);

        JLabel crossbowLabel = new JLabel("Crossbow:");
        JTextField crossbowField = new JTextField(10);
        crossbowField.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(swordsmanLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(swordsmanField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(spearmanLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(spearmanField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(cannonLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(cannonField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(crossbowLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(crossbowField, gbc);

        // Agregar el panel principal al marco
        getContentPane().add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Crear una instancia de NextAttack
        SwingUtilities.invokeLater(NextAttack::new);
    }
}