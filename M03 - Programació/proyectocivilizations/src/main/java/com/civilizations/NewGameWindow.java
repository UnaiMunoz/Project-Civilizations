package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameWindow extends JFrame {

    public NewGameWindow() {
        setTitle("Add Username");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\game2.jpg");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel titleLabel = new JLabel("Add Username");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        panel.add(titleLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 125)));

        JTextField usernameField = new JTextField(20);
        usernameField.setMaximumSize(new Dimension(200, usernameField.getPreferredSize().height));
        usernameField.setPreferredSize(new Dimension(200, usernameField.getPreferredSize().height));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(usernameField);

        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();

                if (!username.isEmpty()) {
                    CivilizationDAO civilizationDAO = new CivilizationDAO();
                    civilizationDAO.addUser(username);

                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        GameWindow gameWindow = new GameWindow(username);
                        gameWindow.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(NewGameWindow.this, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton loadButton = new JButton("Load Game");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loadButton);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    LoadGameWindow loadGameWindow = new LoadGameWindow();
                    loadGameWindow.setVisible(true);
                });
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        SwingUtilities.invokeLater(() -> {
            NewGameWindow window = new NewGameWindow();
            window.setVisible(true);
        });
    }
}

// Clase para el panel de fondo
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        try {
            backgroundImage = new ImageIcon(fileName).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);}
        }
    }