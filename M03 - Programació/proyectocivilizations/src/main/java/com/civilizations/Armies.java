package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Armies extends JFrame {

    private int civilizationId;
    private GameWindow gameWindow;

    public Armies(String username, int civilizationId, GameWindow gameWindow) {

        this.civilizationId = civilizationId; // Asignar el civilizationId aquí
        this.gameWindow = gameWindow; // Asignar la referencia de GameWindow
        System.out.println("ID de buildings: " + this.civilizationId);

        setTitle("Armies");

        // Crear el título
        JLabel titleLabel = new JLabel("Armies", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 36));  // Puedes ajustar el tamaño y la fuente

        // Crear los botones con imágenes redimensionadas y textos
        JButton button1 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\swordsman2.jpg", "Swordsman", 100, 100);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setSwordsman(Armies.this.civilizationId);
                handleCreationResult(result, "Swordsman");
            }
        });

        JButton button2 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\cannon.png", "Cannon", 100, 100);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setCannon(Armies.this.civilizationId);
                handleCreationResult(result, "Cannon");
            }
        });

        JButton button3 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\spearman.png", "Spearman", 100, 100);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setSpearman(Armies.this.civilizationId);
                handleCreationResult(result, "Spearman");
            }
        });

        JButton button4 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\crossbow.png", "Crossbow", 100, 100);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setCrossbow(Armies.this.civilizationId);
                handleCreationResult(result, "Crossbow");
            }
        });

        JButton button5 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\catapult.png", "Catapult", 100, 100);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setCatapult(Armies.this.civilizationId);
                handleCreationResult(result, "Catapult");
            }
        });

        JButton button6 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\arrowtower.png", "Arrow Tower", 100, 100);
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setArrowTower(Armies.this.civilizationId);
                handleCreationResult(result, "Arrow Tower");
            }
        });

        JButton button7 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\rockettower.png", "Rocket Tower", 100, 100);
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setRocketLauncherTower(Armies.this.civilizationId);
                handleCreationResult(result, "Rocket Tower");
            }
        });

        JButton button8 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\magician.png", "Magician", 100, 100);
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setMagician(Armies.this.civilizationId);
                handleCreationResult(result, "Magician");
            }
        });

        JButton button9 = createButton("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\priest.png", "Priest", 100, 100);
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
                int result = civilizationArmyDAO.setPriest(Armies.this.civilizationId);
                handleCreationResult(result, "Priest");
            }
        });

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);
        button4.setFocusable(false);
        button5.setFocusable(false);
        button6.setFocusable(false);
        button7.setFocusable(false);
        button8.setFocusable(false);
        button9.setFocusable(false);

        // Crear un contenedor para los botones con GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // 3 filas, 3 columnas, espacio horizontal y vertical de 10
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.setOpaque(false);  // Hacer el panel transparente para mostrar la imagen de fondo

        // Crear un contenedor principal con BorderLayout
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("M03 - Programació/proyectocivilizations/src/main/java/com/civilizations/Images/game2.jpg");  // Ruta de la imagen de fondo
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);  // Añadir el título en la parte superior
        mainPanel.add(buttonPanel, BorderLayout.CENTER);  // Añadir los botones en el centro

        // Agregar el contenedor principal al marco
        getContentPane().add(mainPanel);
        setSize(900, 500);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setVisible(true);
    }

    // Método para crear botón con imagen redimensionada y tamaño preferido
    private JButton createButton(String imagePath, String text, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Garamond", Font.PLAIN, 18));  // Cambiar la tipografía del botón
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setPreferredSize(new Dimension(width, height + 30));  // Añadir espacio para el texto

        return button;
    }

    private void handleCreationResult(int result, String unitType) {
        if (result == 1) {
            JOptionPane.showMessageDialog(Armies.this, "Se ha creado " + unitType, "Creación de Ejército", JOptionPane.INFORMATION_MESSAGE);
            gameWindow.UpdateFields();
        } else if (result == -1) {
            JOptionPane.showMessageDialog(Armies.this, "Error al crear " + unitType + ". No tienes suficientes recursos.", "Error de Creación", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Crear una instancia de Buildings
        SwingUtilities.invokeLater(() -> new GameWindow("testUser"));
    }
}
