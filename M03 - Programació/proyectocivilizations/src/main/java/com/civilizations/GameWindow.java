package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends JFrame implements Variables {

    private JLabel timerLabel;
    private JTextField maderaTextField, comidaTextField, hierroTextField, manaTextField;
    private int seconds = 0;
    private int civilizationId;
    private String username;

    // Constructor para nueva partida
    public GameWindow(String username) {
        this.username = username;
        CivilizationDAO civilizationDAO = new CivilizationDAO();
        civilizationId = civilizationDAO.getCivilizationIdByName(username);
        initGUI(0, 0, 0, 0);  // Inicializa la GUI con valores iniciales
        System.out.println("ID: " + civilizationId);
    }

    // Constructor para cargar partida
    public GameWindow(List<Map<String, Object>> gameData) {
        int madera = 0, comida = 0, hierro = 0, mana = 0;
        if (gameData != null && !gameData.isEmpty()) {
            Map<String, Object> gameState = gameData.get(0);

            if (gameState.containsKey("wood_amount")) {
                madera = (Integer) gameState.get("wood_amount");
            }
            if (gameState.containsKey("food_amount")) {
                comida = (Integer) gameState.get("food_amount");
            }
            if (gameState.containsKey("iron_amount")) {
                hierro = (Integer) gameState.get("iron_amount");
            }
            if (gameState.containsKey("mana_amount")) {
                mana = (Integer) gameState.get("mana_amount");
            }
        }
        initGUI(madera, comida, hierro, mana);
    }

    private void initGUI(int madera, int comida, int hierro, int mana) {
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
        maderaTextField.setText(String.valueOf(madera));

        comidaTextField = new JTextField(10);
        comidaTextField.setEditable(false);
        comidaTextField.setText(String.valueOf(comida));

        hierroTextField = new JTextField(10);
        hierroTextField.setEditable(false);
        hierroTextField.setText(String.valueOf(hierro));

        manaTextField = new JTextField(10);
        manaTextField.setEditable(false);
        manaTextField.setText(String.valueOf(mana));

        JButton construccionesButton = new JButton("Buildings");
        JButton ejercitoButton = new JButton("Train");
        JButton nextAttackButton = new JButton("Next Attack");
        JButton VerEjercitoButton = new JButton("View your army");   
        JButton battleReportButton = new JButton("Battle Report");
        JButton statsButton = new JButton("Civilization Stats");
        JButton salirButton = new JButton("Exit");

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

        construccionesButton.addActionListener(e -> new Buildings(username, civilizationId, this));
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        infoPanel.add(construccionesButton, gbc);

        ejercitoButton.addActionListener(e -> new Armies(username, civilizationId, this));
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        infoPanel.add(ejercitoButton, gbc);

        VerEjercitoButton.addActionListener(e -> new YourArmy(username, civilizationId, this));
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        infoPanel.add(VerEjercitoButton, gbc);


        nextAttackButton.addActionListener(e -> new NextAttack(username, civilizationId, this));
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        infoPanel.add(nextAttackButton, gbc);

        battleReportButton.addActionListener(e -> new BattleReport());
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        infoPanel.add(battleReportButton, gbc);

        statsButton.addActionListener(e -> new CivilizationStatsView(civilizationId));
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        infoPanel.add(statsButton, gbc);

        timerLabel = new JLabel("Tiempo: 00:00");
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        infoPanel.add(timerLabel, gbc);

        salirButton.addActionListener(e -> dispose());
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        infoPanel.add(salirButton, gbc);

        // Crear y agregar el botón de advertencia de enemigos
        add(infoPanel, BorderLayout.EAST);

        // Iniciar el temporizador del reloj
        javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                int minutes = seconds / 60;
                int remainingSeconds = seconds % 60;
                String timeFormatted = String.format("%02d:%02d", minutes, remainingSeconds);
                timerLabel.setText("Tiempo: " + timeFormatted);
            }
        });
        timer.start();

        // Crear un objeto TimerTask para actualizar la cantidad de madera cada 30 segundos
        TimerTask resourceTimer = new TimerTask() {
            @Override
            public void run() {
                CivilizationDAO civilizationDAO = new CivilizationDAO();
                civilizationDAO.UpdateAmounts(civilizationId);
                UpdateFields();
            }
        };

        // Crear un objeto Timer
        Timer resourceUpdateTimer = new Timer();

        // Programar el TimerTask para que se ejecute cada 30 segundos
        resourceUpdateTimer.scheduleAtFixedRate(resourceTimer, 0, 30000);

        // Crear un objeto TimerTask para los ataques enemigos entrantes
        TimerTask enemyAttackTimer = new TimerTask() {
            @Override
            public void run() {
                EnemyArmy enemyArmy = new EnemyArmy(civilizationId);
                EnemyDAO enemyDAO = new EnemyDAO();
                System.out.print("ID DE CIVILIZACION A LA QUE SE ENFRENTA EL ENEMIGO: " + civilizationId);
                enemyArmy.createEnemyArmy(civilizationId);
                EnemyDAO.UnitCounts counts = enemyDAO.viewIncomingThreat(civilizationId);

                // Mostrar el botón de advertencia y el popup
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(GameWindow.this, "An enemy army is approaching!!!!", "Enemy Alert", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Iniciar la batalla después de un minuto
                    Timer battleStartTimer = new Timer();
                    battleStartTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // Lógica para iniciar la batalla aquí
                            SwingUtilities.invokeLater(() -> new BattleWindow(civilizationId, civilizationId));
                        }
                    }, 30000); // 60000 milisegundos = 1 minuto
                });
                

                // Aquí puedes actualizar los campos correspondientes en la GUI si es necesario
                System.out.println("Swordsman: " + counts.swordsmanCount);
                System.out.println("Spearman: " + counts.spearmanCount);
                System.out.println("Cannon: " + counts.cannonCount);
                System.out.println("Crossbow: " + counts.crossbowCount);
            }
        };
        // Crear un objeto Timer para los ataques enemigos
        Timer incomingAttackTimer = new Timer();

        // Programar el TimerTask para que se ejecute cada 3 minutos
        incomingAttackTimer.scheduleAtFixedRate(enemyAttackTimer, 30000, 40000);
            }

    // Método para actualizar los campos de recursos
    public void UpdateFields() {
        CivilizationDAO civilizationDAO = new CivilizationDAO();
        maderaTextField.setText(String.valueOf(civilizationDAO.getWood(civilizationId)));
        comidaTextField.setText(String.valueOf(civilizationDAO.getFood(civilizationId)));
        hierroTextField.setText(String.valueOf(civilizationDAO.getIron(civilizationId)));
        manaTextField.setText(String.valueOf(civilizationDAO.getMana(civilizationId)));
    }
}
