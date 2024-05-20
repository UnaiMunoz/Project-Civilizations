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
            System.out.println("Se han añadido mas recursos");
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
        System.out.println("Main Menu ");
        System.out.println("1. Train units");
        System.out.println("2. Build structures");
        System.out.println("3. Upgrade technologies");
        System.out.println("4. Battle reports");
        System.out.println("5. Enemy army");
        System.out.println("6. Exit");
    }

    public void unitsMenu() {
        System.out.println("Units ");
        System.out.println("1. Swordsman");
        System.out.println("2. Spearman");
        System.out.println("3. Crossbow");
        System.out.println("4. Cannon");
        System.out.println("5. Arrow Tower");
        System.out.println("6. Catapult");
        System.out.println("7. Rocket Launcher");
        System.out.println("8. Magician");
        System.out.println("9. Priest");
        System.out.println("10. Exit");

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
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        while (true) {
            unitsMenu();
            System.out.print("\nSelect an option: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        System.out.println("How many swordsmen do you want to train: ");
                        int opcionSwordsman = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newSwordsman(opcionSwordsman);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train swordsman : " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("How many spearmen do you want to train: ");
                        int opcionSpearman = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newSpearman(opcionSpearman);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train spearman: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("How many crossbow do you want to train: ");
                        int opcionCrossbow = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newCrossbow(opcionCrossbow);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train crossbow: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("How many cannon do you want to train: ");
                        int opcionCannon = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newCannon(opcionCannon);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train cannon: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("How many arrow tower do you want to train: ");
                        int opcionArrowTower = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newArrowTower(opcionArrowTower);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train arrow tower: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        System.out.println("How many catapult do you want to train: ");
                        int opcionCatapult = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newCatapult(opcionCatapult);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train catapult: " + e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        System.out.println("How many rocket launcher do you want to train: ");
                        int opcionRocketLauncher = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newRocketLauncher(opcionRocketLauncher);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train rocket launcher: " + e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        System.out.println("How many magician do you want to train: ");
                        int opcionMagician = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newMagician(opcionMagician);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train magician: " + e.getMessage());
                    } catch (BuildingException e) {
                    limpiarPantalla();
                    System.out.println("Failed to train magician: " + e.getMessage());
                }
                    break;
                case 9:
                    try {
                        System.out.println("How many priest do you want to train: ");
                        int opcionPriest = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newPriest(opcionPriest);
                    } catch (ResourceException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train priest: " + e.getMessage());
                    } catch (BuildingException e) {
                        limpiarPantalla();
                        System.out.println("Failed to train priest: " + e.getMessage());
                    }
                    break;
                case 10:
                    limpiarPantalla();
                    return;
                default:
                    limpiarPantalla();
                    System.out.println("Invalid option");
            }
            System.out.println();  // Línea en blanco para mejorar la legibilidad
        }
    }
    

    public void crearEdificios() {
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        while (true) {
            buildingsMenu();
            System.out.print("\nSelect an option: ");
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
