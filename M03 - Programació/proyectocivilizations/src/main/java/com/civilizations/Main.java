package com.civilizations;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    Civilization civilization = new Civilization(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    private Timer timer = new Timer();

    private TimerTask taskAttack = new TimerTask() {
        public void run() {
            System.out.println("Te van a atacar en poco.");
        }
    };

    private TimerTask taskResources = new TimerTask() {
        public void run() {
            System.out.println("Bienvenido");
        }
    };

    public void startTimerAttack(long delay, long period) {
        timer.schedule(taskAttack, delay, period);
    }

    public void startTimerResources(long delay, long period) {
        timer.schedule(taskResources, delay, period);
    }

    public void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Create units");
        System.out.println("2. Build structures");
        System.out.println("3. Upgrade technologies");
        System.out.println("4. Battle reports");
        System.out.println("5. Enemy army");
        System.out.println("6. Exit");
    }

    public void buildingsMenu() {
        System.out.println("Structures ");
        System.out.println("1. Church");
        System.out.println("2. Magic Tower");
        System.out.println("3. Farm");
        System.out.println("4. Carpentry");
        System.out.println("5. Smithy");
        System.out.println("6. Return");
    }

    public void crearUnidades() {
        limpiarPantalla();
        System.out.println("Creando Unidades...");
        // Implementar lógica para crear unidades
    }

    public void crearEdificios() {
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        while (true) {
            buildingsMenu();
            System.out.print("\nSelect an option: ");
            civilization.setWood(100000);
            civilization.setFood(100000);
            civilization.setIron(100000);
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    try {
                        limpiarPantalla();
                        civilization.newChurch();
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to build church: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        limpiarPantalla();
                        civilization.newMagicTower();
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to build magic tower: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        limpiarPantalla();
                        civilization.newFarm();
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to build farm: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        limpiarPantalla();
                        civilization.newCarpentry();
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to build carpentry: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        limpiarPantalla();
                        civilization.newSmithy();
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to build smithy: " + e.getMessage());
                    }
                    break;
                case 6:
                    limpiarPantalla();
                    return;
                default:
                    limpiarPantalla();
                    System.out.println("Invalid option");
            }
            System.out.println();  // Línea en blanco para mejorar la legibilidad
        }
        // Implementar lógica para crear edificios
    }

    public void mejorarTecnologias() {
        limpiarPantalla();
        System.out.println("Mejorando Tecnologías...");
        // Implementar lógica para mejorar tecnologías
    }

    public void reportesDeBatalla() {
        limpiarPantalla();
        System.out.println("Mostrando Reportes de Batalla...");
        // Implementar lógica para mostrar reportes de batalla
    }

    public void verEjercitoEnemigo() {
        limpiarPantalla();
        System.out.println("Viendo el Ejército Enemigo...");
        // Implementar lógica para ver el ejército enemigo
    }


    public static void main(String[] args) {
        Main mainInstance = new Main();
        Scanner scanner = new Scanner(System.in);
        mainInstance.limpiarPantalla();
        
        // Inicia el temporizador con un retraso de 5 minutos (300,000 milisegundos) y un período de 3 minutos (180,000 milisegundos)
        mainInstance.startTimerAttack(300000, 180000);
        
        while (true) {
            mainInstance.mainMenu();
            System.out.print("\nSelecciona una opción: ");
            int opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    mainInstance.crearUnidades();
                    break;
                case 2:
                    mainInstance.crearEdificios();
                    break;
                case 3:
                    mainInstance.mejorarTecnologias();
                    break;
                case 4:
                    mainInstance.reportesDeBatalla();
                    break;
                case 5:
                    mainInstance.verEjercitoEnemigo();
                    break;
                case 6:
                    System.out.println("Game Over");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println();  // Línea en blanco para mejorar la legibilidad
        }
    }
}
