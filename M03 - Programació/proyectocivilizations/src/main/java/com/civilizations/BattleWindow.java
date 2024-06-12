package com.civilizations;

import javax.swing.*;
import java.awt.*;

public class BattleWindow extends JFrame {

    private BattlePrueba battle;
    private JTextArea battleTextArea;

    public BattleWindow(int civilizationId,int enemyId) {
        setTitle("Battle Window");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Etiqueta de título
        JLabel titleLabel = new JLabel("Battle Window");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel para el área de texto de la batalla
        battleTextArea = new JTextArea();
        battleTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(battleTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar el panel principal a la ventana
        add(mainPanel);

        // Iniciar la batalla
        startBattle(civilizationId);

        // Hacer visible la ventana
        setVisible(true);
    }

    private void startBattle(int civilizationId) {
        // Crear una instancia de BattlePrueba
        battle = new BattlePrueba(civilizationId,civilizationId);

        // Iniciar la batalla
        battle.startBattle(civilizationId);

        // Mostrar el desarrollo de la batalla en el área de texto
        updateBattleTextArea();
    }

    private void updateBattleTextArea() {
        // Obtener el desarrollo de la batalla de BattlePrueba
        String battleDevelopment = battle.getBattleDevelopment();

        // Actualizar el área de texto con el desarrollo de la batalla
        battleTextArea.setText(battleDevelopment);
    }

}
