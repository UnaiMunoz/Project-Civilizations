package com.civilizations;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadGameWindow extends JFrame {

    private JTable table;
    private JButton loadButton;

    public LoadGameWindow() {
        setTitle("Load Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Map<String, Object>> gamesList = new ArrayList<>(); // Lista vacía

        String[] columnNames = {"CIVILIZATION_ID", "NAME"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Map<String, Object> game : gamesList) {
            BigDecimal civilizationId = (BigDecimal) game.get("CIVILIZATION_ID");
            int civilizationIdInt = civilizationId.intValue();
            String name = (String) game.get("NAME");
            model.addRow(new Object[]{civilizationIdInt, name});
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadButton = new JButton("Load Game");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Código para cargar el juego omitido ya que no hay datos en la lista de juegos
                    JOptionPane.showMessageDialog(null, "No games available to load.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a game to load.");
                }
            }
        });

        add(loadButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoadGameWindow loadGameWindow = new LoadGameWindow();
            loadGameWindow.setVisible(true);
        });
    }
}
