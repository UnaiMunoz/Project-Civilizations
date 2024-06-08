package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class LoadGameWindow extends JFrame {

    private JComboBox<String> gameComboBox;

    public LoadGameWindow() {
        setTitle("Games");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Games");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        gameComboBox = new JComboBox<>();
        gameComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gameComboBox.setPreferredSize(new Dimension(200, 30));
        panel.add(gameComboBox);

        JButton loadButton = new JButton("Load Game");
        loadButton.setFont(new Font("Arial", Font.PLAIN, 14));
        loadButton.setPreferredSize(new Dimension(120, 30));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGame = (String) gameComboBox.getSelectedItem();
                if (selectedGame != null) {
                    GameWindow gameWindow = new GameWindow(selectedGame);
                    gameWindow.setVisible(true);
                    dispose();  // Cierra la ventana de carga
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a game to load.");
                }
            }
        });
        panel.add(loadButton);

        add(panel);

        populateComboBox();
    }

    private void populateComboBox() {
        AppData appData = AppData.getInstance();
        String sql = "SELECT name FROM civilization_stats";
        List<Map<String, Object>> resultList = appData.query(sql);
        for (Map<String, Object> row : resultList) {
            String name = (String) row.get("NAME");
            gameComboBox.addItem(name);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoadGameWindow loadGameWindow = new LoadGameWindow();
            loadGameWindow.setVisible(true);
        });
    }
}
