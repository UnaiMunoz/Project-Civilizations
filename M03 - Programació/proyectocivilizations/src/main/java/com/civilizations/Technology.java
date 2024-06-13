package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Technology extends JFrame {

    private int civilizationId;
    private GameWindow gameWindow;
    private TechnologyDAO technologyDAO;

    public Technology(String username, int civilizationId, GameWindow gameWindow) {
        this.civilizationId = civilizationId;
        this.gameWindow = gameWindow;
        this.technologyDAO = new TechnologyDAO();

        setTitle("Technology");

        JLabel titleLabel = new JLabel("Technology", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 36));

        JButton upgradeAttackButton = createButton("Upgrade Attack Tech", "M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\upgradeAttack.png", 200, 100, "Se ha mejorado el ataque de las tropas");
        JButton upgradeDefenseButton = createButton("Upgrade Defense Tech", "M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\upgradeDefense.png", 200, 100, "Se ha mejorado la defensa de las tropas");

        upgradeAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpgradeAction("attack");
            }
        });

        upgradeDefenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpgradeAction("defense");
            }
        });

        upgradeAttackButton.setFocusable(false);
        upgradeDefenseButton.setFocusable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(upgradeAttackButton);
        buttonPanel.add(upgradeDefenseButton);
        buttonPanel.setOpaque(false);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\game2.jpg");
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text, String imagePath, int width, int height, String message) {
        JButton button = new JButton(text);
        button.setFont(new Font("Garamond", Font.PLAIN, 18));
        button.setPreferredSize(new Dimension(width, height + 30));

        // Añadir la imagen al botón
        ImageIcon icon = new ImageIcon(imagePath);
        button.setIcon(icon);

        button.addActionListener(e -> JOptionPane.showMessageDialog(Technology.this, message, "Mejora de Tecnología", JOptionPane.INFORMATION_MESSAGE));

        return button;
    }

    private void handleUpgradeAction(String type) {
        try {
            if (type.equals("attack")) {
                technologyDAO.upgradeAttackTechnology(civilizationId);
            } else if (type.equals("defense")) {
                technologyDAO.upgradeDefenseTechnology(civilizationId);
            }
            gameWindow.UpdateFields();
            JOptionPane.showMessageDialog(Technology.this, "Mejora completada", "Mejora de Tecnología", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(Technology.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow("testUser"));
    }
}
