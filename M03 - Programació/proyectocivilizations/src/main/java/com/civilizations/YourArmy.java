package com.civilizations;

import javax.swing.*;
import java.awt.*;


public class YourArmy extends JFrame {

    private JTextField swordsmanField;
    private JTextField spearmanField;
    private JTextField cannonField;
    private JTextField crossbowField;
    private JTextField arrowTowerField;
    private JTextField catapultField;
    private JTextField rlTowerField;
    private JTextField magicianField;
    private JTextField priestField;

    public YourArmy(String username, int civilizationId, GameWindow gameWindow) {
        setTitle("Civilization Army");
        setSize(500, 500);
        setLocationRelativeTo(null);  // Center the window on the screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Add margins
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Spacing between components

        // Create and add labels and text fields to the panel
        JLabel attackUnitsLabel = new JLabel("YOUR UNITS");
        JLabel swordsmanLabel = new JLabel("Swordsman:");
        swordsmanField = new JTextField(10);
        swordsmanField.setEditable(false);

        JLabel spearmanLabel = new JLabel("Spearman:");
        spearmanField = new JTextField(10);
        spearmanField.setEditable(false);

        JLabel cannonLabel = new JLabel("Cannon:");
        cannonField = new JTextField(10);
        cannonField.setEditable(false);

        JLabel crossbowLabel = new JLabel("Crossbow:");
        crossbowField = new JTextField(10);
        crossbowField.setEditable(false);

        JLabel arrowTowerLabel = new JLabel("Arrow Tower:");
        arrowTowerField = new JTextField(10);
        arrowTowerField.setEditable(false);

        JLabel catapultLabel = new JLabel("Catapult:");
        catapultField = new JTextField(10);
        catapultField.setEditable(false);

        JLabel rlTowerLabel = new JLabel("Rocket Launcher Tower:");
        rlTowerField = new JTextField(10);
        rlTowerField.setEditable(false);

        JLabel magicianLabel = new JLabel("Magician:");
        magicianField = new JTextField(10);
        magicianField.setEditable(false);

        JLabel priestLabel = new JLabel("Priest:");
        priestField = new JTextField(10);
        priestField.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(attackUnitsLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(swordsmanLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(swordsmanField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        mainPanel.add(spearmanLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(spearmanField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        mainPanel.add(cannonLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(cannonField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        mainPanel.add(crossbowLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(crossbowField, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        mainPanel.add(arrowTowerLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(arrowTowerField, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        mainPanel.add(catapultLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(catapultField, gbc);

        gbc.gridy = 7;
        gbc.gridx = 0;
        mainPanel.add(rlTowerLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(rlTowerField, gbc);

        gbc.gridy = 8;
        gbc.gridx = 0;
        mainPanel.add(magicianLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(magicianField, gbc);

        gbc.gridy = 9;
        gbc.gridx = 0;
        mainPanel.add(priestLabel, gbc);
        gbc.gridx = 1;
        mainPanel.add(priestField, gbc);

        // Add the main panel to the frame
        getContentPane().add(mainPanel);

        // Display the window
        setVisible(true);

    // Get and display values from the database
    loadUnitCounts(civilizationId);
    }

    private void loadUnitCounts(int civilizationId) {
        CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
        CivilizationArmyDAO.UnitCounts counts = civilizationArmyDAO.CountArmy(civilizationId);

        swordsmanField.setText(String.valueOf(counts.swordsmanCount));
        spearmanField.setText(String.valueOf(counts.spearmanCount));
        cannonField.setText(String.valueOf(counts.cannonCount));
        crossbowField.setText(String.valueOf(counts.crossbowCount));
        arrowTowerField.setText(String.valueOf(counts.arrowTowerCount));
        catapultField.setText(String.valueOf(counts.catapultCount));
        rlTowerField.setText(String.valueOf(counts.rlTowerCount));
        magicianField.setText(String.valueOf(counts.magicianCount));
        priestField.setText(String.valueOf(counts.priestCount));
    }

    public static void main(String[] args) {
        // Create an instance of NextAttack with an example civilizationId
        SwingUtilities.invokeLater(() -> new GameWindow("testUser"));
    }
    }

        
