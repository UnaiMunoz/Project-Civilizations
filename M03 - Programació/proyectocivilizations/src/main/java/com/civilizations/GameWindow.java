package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private JLabel timerLabel;
    private JTextField maderaTextField, comidaTextField, hierroTextField, manaTextField;
    private int seconds = 0;

    public GameWindow() {
        setTitle("Civilizations");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de la imagen o video
        JPanel mediaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\gamegif.gif");
                Image image = imageIcon.getImage();
                int width = getWidth();
                int height = getHeight();
                g.drawImage(image, 0, 0, width, height, this);
            }
        };
        mediaPanel.setPreferredSize(new Dimension(600, 500));
        add(mediaPanel, BorderLayout.CENTER);

        // Panel de la columna de información
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel maderaLabel = new JLabel("Wood:");
        JLabel comidaLabel = new JLabel("Food:");
        JLabel hierroLabel = new JLabel("Iron:");
        JLabel manaLabel = new JLabel("Mana:");

        maderaTextField = new JTextField(10);
        maderaTextField.setEditable(false);
        comidaTextField = new JTextField(10);
        comidaTextField.setEditable(false);
        hierroTextField = new JTextField(10);
        hierroTextField.setEditable(false);
        manaTextField = new JTextField(10);
        manaTextField.setEditable(false);

        JButton construccionesButton = new JButton("Buildings");
        JButton ejercitoButton = new JButton("Armies");
        JButton nextAttackButton = new JButton("Next Attack");
        JButton battleReportButton = new JButton("Battle Report");
        JButton statsButton = new JButton("Civilization Stats");

        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(maderaLabel, gbc);
        gbc.gridx = 1;
        infoPanel.add(maderaTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(comidaLabel, gbc);
        gbc.gridx = 1;
        infoPanel.add(comidaTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(hierroLabel, gbc);
        gbc.gridx = 1;
        infoPanel.add(hierroTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(manaLabel, gbc);
        gbc.gridx = 1;
        infoPanel.add(manaTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        infoPanel.add(Box.createVerticalStrut(20), gbc);

        // ActionListener para el botón "Buildings"
        construccionesButton.addActionListener(e -> {
            Buildings buildingsWindow = new Buildings();
        });
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        infoPanel.add(construccionesButton, gbc);

        // ActionListener para el botón "Armies"
        ejercitoButton.addActionListener(e -> {
            Armies armiesWindow = new Armies();
        });
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        infoPanel.add(ejercitoButton, gbc);

        // ActionListener para el botón "Next Attack"
        nextAttackButton.addActionListener(e -> {
            // Aquí abre la ventana o ejecuta el código relacionado con "Next Attack"
            NextAttack nextAttackWindow = new NextAttack();
        });
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        infoPanel.add(nextAttackButton, gbc);

        // ActionListener para el botón "Battle Report"
        battleReportButton.addActionListener(e -> {
            // Aquí abre la ventana o ejecuta el código relacionado con "Battle Report"
            BattleReport battleReportWindow = new BattleReport();
        });
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        infoPanel.add(battleReportButton, gbc);

        statsButton.addActionListener(e -> {
            // Aquí abre la ventana o ejecuta el código relacionado con "Battle Report"
            StatsCivilization statsCivilization = new StatsCivilization();
        });
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        infoPanel.add(statsButton, gbc);

        // Temporizador
        timerLabel = new JLabel("Tiempo: 00:00");
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        infoPanel.add(timerLabel, gbc);

        add(infoPanel, BorderLayout.EAST);

        // Iniciar el temporizador
        Timer timer = new Timer(1000, e -> {
            seconds++;
            int minutes = seconds / 60;
            int remainingSeconds = seconds % 60;
            String timeFormatted = String.format("%02d:%02d", minutes, remainingSeconds);
            timerLabel.setText("Tiempo: " + timeFormatted);
        });
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}