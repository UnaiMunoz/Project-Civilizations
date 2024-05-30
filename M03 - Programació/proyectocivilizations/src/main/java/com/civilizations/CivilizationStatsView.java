package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class CivilizationStatsView {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CivilizationStatsView::viewGame);
    }

    public static void viewGame() {
        String sql = "SELECT * FROM CIVILIZATION_STATS";
        AppData db = AppData.getInstance();
        List<Map<String, Object>> results = db.query(sql);

        // Verificar si se obtuvieron resultados
        if (results.isEmpty()) {
            System.out.println("No se obtuvieron datos de la base de datos.");
            return;
        } else {
            System.out.println("Datos obtenidos correctamente.");
        }

        // Crear el marco principal
        JFrame frame = new JFrame("Civilization Stats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Crear un panel principal con GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Definir los nombres de las columnas y los campos correspondientes
        String[] columnNames = {
                "Civilization_ID", "NAME", "Wood_Amount", "Iron_Amount", "Food_Amount", "Mana_Amount",
                "Magic_Tower_Counter", "Church_Counter", "Farm_Counter", "Smithy_Counter",
                "Carpentry_Counter", "Technology_Defense_Level", "Technology_Attack_Level", "Battles_Counter"
        };

        // Crear etiquetas y campos de texto
        int row = 0;
        for (Map<String, Object> civ : results) {
            for (int i = 0; i < columnNames.length; i++) {
                String columnName = columnNames[i];
                gbc.gridx = 0;
                gbc.gridy = row;
                mainPanel.add(new JLabel(columnName + ":"), gbc);

                gbc.gridx = 1;
                Object value = civ.get(columnName.toLowerCase().replace(" ", "_"));
                if (value == null) {
                    System.out.println("Valor nulo encontrado para la columna: " + columnName);
                    value = "N/A";  // Reemplazar valores nulos con "N/A"
                }
                JTextField textField = new JTextField(value.toString());
                textField.setEditable(false);
                mainPanel.add(textField, gbc);

                row++;
            }
            // Añadir un separador entre civilizaciones
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.gridwidth = 2;
            JSeparator separator = new JSeparator();
            mainPanel.add(separator, gbc);
            gbc.gridwidth = 1;
        }

        // Añadir el panel principal al marco
        frame.add(new JScrollPane(mainPanel));

        // Hacer visible el marco
        frame.setVisible(true);
    }
}
