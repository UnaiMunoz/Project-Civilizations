package com.civilizations;

import javax.swing.SwingUtilities;

public class MainSwing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow(new MainController()).setVisible(true);
            }
        });
    }
}

