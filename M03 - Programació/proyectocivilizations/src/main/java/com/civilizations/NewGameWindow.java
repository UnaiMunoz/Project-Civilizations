package com.civilizations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameWindow extends JFrame {

    public NewGameWindow() {
        // Configuración de la ventana
        setTitle("Add Username");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear panel principal con imagen de fondo
        BackgroundPanel panel = new BackgroundPanel("M03 - Programació\\proyectocivilizations\\src\\main\\java\\com\\civilizations\\Images\\game2.jpg");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Crear y añadir el título
        JLabel titleLabel = new JLabel("Add Username");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK); // Cambiar el color del texto para que sea visible
        panel.add(titleLabel);
        
        // Añadir un espacio entre los componentes
        panel.add(Box.createRigidArea(new Dimension(0, 125)));
        
        // Crear y añadir el campo de texto
        JTextField usernameField = new JTextField(20);
        usernameField.setMaximumSize(new Dimension(200, usernameField.getPreferredSize().height)); // Limitar el tamaño máximo
        usernameField.setPreferredSize(new Dimension(200, usernameField.getPreferredSize().height)); // Tamaño preferido
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el campo de texto
        panel.add(usernameField);
        
        // Añadir un espacio entre los componentes
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        
        // Crear y añadir el botón de inicio
        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(startButton);

        // Añadir ActionListener al botón "Start"
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText(); // Obtener el texto del campo de texto
                
                if (!username.isEmpty()) { // Verificar si el campo de texto no está vacío
                    CivilizationDAO civilizationDAO = new CivilizationDAO(); // Crear una instancia de CivilizationDAO
                    civilizationDAO.addUser(username); // Llamar al método addUser con el nombre de usuario obtenido
                    
                    dispose(); // Cerrar la ventana actual
                    SwingUtilities.invokeLater(() -> {
                        GameWindow gameWindow = new GameWindow();
                        gameWindow.setVisible(true);
                    });
                } else {
                    // Mostrar un mensaje de error si el campo de texto está vacío
                    JOptionPane.showMessageDialog(NewGameWindow.this, "Please enter a username", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        
        // Añadir el panel a la ventana
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
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
