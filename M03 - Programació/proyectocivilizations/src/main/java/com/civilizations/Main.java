package com.civilizations;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main implements Variables {
    public ArrayList<MilitaryUnit>[] enemyArmy;
    private Civilization civilization;
    private Timer timer;
    private static final double[] unitProbabilities = {35.0, 25.0, 20.0, 20.0};
    private boolean enemyApproaching;
    private boolean enemyArmyCreated;
    public ArrayList<MilitaryUnit>[] civilizationArmy;
    public Main() {
        civilization = new Civilization(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        timer = new Timer();
        enemyApproaching = false;
        enemyArmyCreated = false;
        initializeEnemyArmy();
        civilizationArmy = new ArrayList[8]; // Assuming there are 8 types of military units
        for (int i = 0; i < civilizationArmy.length; i++) {
            civilizationArmy[i] = new ArrayList<>();}
    }

    // Initialize enemyArmy array
    private void initializeEnemyArmy() {
        enemyArmy = new ArrayList[4]; // Creamos un array de listas para las unidades
    for (int i = 0; i < enemyArmy.length; i++) {
        enemyArmy[i] = new ArrayList<>(); // Inicializamos cada lista de unidades
    }

    // Agregamos unidades predefinidas
    addUnitToEnemyArmy(new Swordsman(), 20); // Agrega 20 espaderos
    addUnitToEnemyArmy(new Spearman(), 10); // Agrega 10 ballesteros
    }

// Timer task for attacking
private TimerTask taskAttack = new TimerTask() {
    public void run() {
        System.out.println("¡Un ejército enemigo te atacará!");
        createEnemyArmy();
        enemyArmyCreated = true;
        // Programar la batalla un minuto después de que se avise que viene un ejército enemigo
        Timer battleTimer = new Timer();
        battleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                BattleMain battleMain = new BattleMain(civilizationArmy, enemyArmy);
                battleMain.startBattle();
            }
        }, 30000); // 60 segundos = 60000 milisegundos
    }
};

    // Timer task for resource generation
    private TimerTask taskResources = new TimerTask() {
        public void run() {
            civilization.setFood(civilization.getFood() + CIVILIZATION_FOOD_GENERATED);
            if (civilization.getFarm() >= 1){
                civilization.setFood(civilization.getFood() + CIVILIZATION_FOOD_GENERATED_PER_FARM);
            }
            civilization.setWood(civilization.getWood() + CIVILIZATION_WOOD_GENERATED);
            if (civilization.getCarpentry() >= 1){
                civilization.setWood(civilization.getWood() + CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY);
            }
            civilization.setIron(civilization.getIron() + CIVILIZATION_IRON_GENERATED);
            if (civilization.getSmithy() >= 1){
                civilization.setIron(civilization.getIron() + CIVILIZATION_IRON_GENERATED_PER_SMITHY);
            }
            //GESTION DE MANÁ
            if (civilization.getMagicTower() >= 1) {
                civilization.setMana(civilization.getMana() + CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER);
            }
        }
    };

    public void setEnemyApproaching(boolean approaching) {
        this.enemyApproaching = approaching;
    }

    public boolean isEnemyApproaching() {
        return enemyApproaching;
    }

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

    //
    private boolean isWithinResourceLimits() {
        int woodCost = 0;
        int ironCost = 0;
        int foodCost = 0;
    
        // Calculate total resource costs of the enemy army
        for (ArrayList<MilitaryUnit> units : enemyArmy) {
            for (MilitaryUnit unit : units) {
                woodCost += unit.getWoodCost();
                ironCost += unit.getIronCost();
                foodCost += unit.getFoodCost();
            }
        }
    
        // Check if resource costs exceed the limits
        if (woodCost > WOOD_BASE_ENEMY_ARMY * ENEMY_FLEET_INCREASE ||
            ironCost > IRON_BASE_ENEMY_ARMY * ENEMY_FLEET_INCREASE ||
            foodCost > FOOD_BASE_ENEMY_ARMY * ENEMY_FLEET_INCREASE) {
            return false;
        }
    
        return true;
    }

    // Create enemy army
    public void createEnemyArmy() {
        if (!enemyArmyCreated) {
            initializeEnemyArmy();
            Random random = new Random();
            boolean armyCreated = false;
    
            // Repeat until the army is not empty and within resource limits
            while (!armyCreated || !isWithinResourceLimits()) {
                // Empty the enemy army before creating a new one
                emptyEnemyArmy();
    
                for (int i = 0; i < unitProbabilities.length; i++) {
                    double probability = unitProbabilities[i];
                    if (random.nextDouble() < (probability / 100)) {
                        int numUnits = random.nextInt(100);
                        for (int j = 0; j < numUnits; j++) {
                            switch (i) {
                                case 0:
                                    addUnitToEnemyArmy(new Swordsman(), numUnits);
                                    break;
                                case 1:
                                    addUnitToEnemyArmy(new Spearman(), numUnits);
                                    break;
                                case 2:
                                    addUnitToEnemyArmy(new Crossbow(), numUnits);
                                    break;
                                case 3:
                                    addUnitToEnemyArmy(new Cannon(), numUnits);
                                    break;
                            }
                        }
                    }
                }
    
                // Check if the army is not empty
                for (ArrayList<MilitaryUnit> units : enemyArmy) {
                    if (!units.isEmpty()) {
                        armyCreated = true;
                        break;
                    }
                }
    
                // If the army is still empty, or resource limits are exceeded, reinitialize
                if (!armyCreated || !isWithinResourceLimits()) {
                    initializeEnemyArmy();
                }
            }
            
            enemyArmyCreated = true;
        } else {
            System.out.println("El ejército enemigo ya está creado.");
        }
    }
    
    private void emptyEnemyArmy() {
        // Vaciar el ejército enemigo
        for (int i = 0; i < enemyArmy.length; i++) {
            enemyArmy[i].clear();
        }
    }

    // Display the enemy threat
    public void viewThreat() {
        System.out.println("Ejército enemigo:");
        for (int i = 0; i < enemyArmy.length; i++) {
            ArrayList<MilitaryUnit> units = enemyArmy[i];
            if (!units.isEmpty()) {
                String unitName = "";
                switch (i) {
                    case 0:
                        unitName = "Swordsman";
                        break;
                    case 1:
                        unitName = "Spearman";
                        break;
                    case 2:
                        unitName = "Crossbow";
                        break;
                    case 3:
                        unitName = "Cannon";
                        break;
                }
                System.out.println("Units of type " + unitName + ": " + units.size());
            }
        }
    }
    //TU EJERCITO
    public void printCivilizationArmy() {
        System.out.println("Tu ejército:");
        for (int i = 0; i < civilizationArmy.length; i++) {
            ArrayList<MilitaryUnit> units = civilizationArmy[i];
            if (!units.isEmpty()) {
                String unitName = "¡No tienes Tropas!";
                switch (i) {
                    case 0:
                        unitName = "Swordsman";
                        break;
                    case 1:
                        unitName = "Spearman";
                        break;
                    case 2:
                        unitName = "Crossbow";
                        break;
                    case 3:
                        unitName = "Cannon";
                        break;
                    case 4:
                        unitName = "Arrow Tower";
                        break;
                    case 5:
                        unitName = "Catapult";
                        break;
                    case 6:
                        unitName = "Rocket Launcher Tower";
                        break;
                    case 7:
                        unitName = "Magician";
                        break;
                    case 8:
                        unitName = "Priest";
                        break;
                }
                System.out.println("Unidades de tipo " + unitName + ": " + units.size());
            }
        }
    }
    
    

    // Main menu
    public void mainMenu() {
        System.out.println("Main Menu ");
        System.out.println("1. Train units");
        System.out.println("2. Build structures");
        System.out.println("3. Upgrade technologies");
        System.out.println("4. Battle reports");
        System.out.println("5. Enemy army");
        System.out.println("6. View resources");
        System.out.println("7. View army");
        System.out.println("8. Exit");
    }

    // Show resources
    public void mostrarRecursos() {
        System.out.println("Recursos actuales:");
        System.out.println("Food: " + civilization.getFood());
        System.out.println("Wood: " + civilization.getWood());
        System.out.println("Iron: " + civilization.getIron());
        System.out.println("Mana: " + civilization.getMana());
    }

    // Units menu
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

    // Buildings menu
    public void buildingsMenu() {
        System.out.println("Structures ");
        System.out.println("1. Church");
        System.out.println("2. Magic Tower");
        System.out.println("3. Farm");
        System.out.println("4. Carpentry");
        System.out.println("5. Smithy");
        System.out.println("6. Return");
    }

    // Technology upgrade menu
    public void technologyUpgradeMenu() {
        System.out.println("Technologies Upgrade ");
        System.out.println("1. Attack Unit");
        System.out.println("2. Defense Unit");
        System.out.println("3. Return");
    }
    //INDICE
    public void addUnitToCivilizationArmy(MilitaryUnit unit, int count) {
        // Find the index of the unit type and add units to the corresponding ArrayList
        if (unit instanceof Swordsman) {
            civilizationArmy[0].add(unit);
        } else if (unit instanceof Spearman) {
            civilizationArmy[1].add(unit);
        } else if (unit instanceof Crossbow) {
            civilizationArmy[2].add(unit);
        } else if (unit instanceof Cannon) {
            civilizationArmy[3].add(unit);
        } else if (unit instanceof ArrowTower) {
            civilizationArmy[4].add(unit);
        } else if (unit instanceof Catapult) {
            civilizationArmy[5].add(unit);
        } else if (unit instanceof RocketLauncherTower) {
            civilizationArmy[6].add(unit);
        } else if (unit instanceof Magician) {
            civilizationArmy[7].add(unit);
        }
    }
    public void addUnitToEnemyArmy(MilitaryUnit unit, int count) {
        if (unit instanceof Swordsman) {
            enemyArmy[0].add(unit);
        } else if (unit instanceof Spearman) {
            enemyArmy[1].add(unit);
        } else if (unit instanceof Crossbow) {
            enemyArmy[2].add(unit);
        } else if (unit instanceof Cannon) {
            enemyArmy[3].add(unit);
        } else if (unit instanceof ArrowTower) {
            enemyArmy[4].add(unit);
        } else if (unit instanceof Catapult) {
            enemyArmy[5].add(unit);
        } else if (unit instanceof RocketLauncherTower) {
            enemyArmy[6].add(unit);
        } else if (unit instanceof Magician) {
            enemyArmy[7].add(unit);
        }
    }

    // Train units method
    public void crearUnidades() {
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        while (true) {
            unitsMenu();
            System.out.print("\nSelect an option: ");
            int opcion = scanner.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        System.out.println("How many swordsmen do you want to train: ");
                        int opcionSwordsman = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newSwordsman(opcionSwordsman);
                        for (int i = 0; i < opcionSwordsman; i++) {
                            addUnitToCivilizationArmy(new Swordsman(), opcionSwordsman);
                        }
                        break;
                    case 2:
                        System.out.println("How many spearmen do you want to train: ");
                        int opcionSpearman = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newSpearman(opcionSpearman);
                        for (int i = 0; i < opcionSpearman; i++) {
                            addUnitToCivilizationArmy(new Spearman(), opcionSpearman);
                        }
                        break;
                    case 3:
                        System.out.println("How many crossbow do you want to train: ");
                        int opcionCrossbow = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newCrossbow(opcionCrossbow);
                        for (int i = 0; i < opcionCrossbow; i++) {
                            addUnitToCivilizationArmy(new Crossbow(), opcionCrossbow);
                        }
                        break;

                    
                    case 4:
                        System.out.println("How many cannon do you want to train: ");
                        int opcionCannon = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newCannon(opcionCannon);
                        for (int i = 0; i < opcionCannon; i++) {
                            addUnitToCivilizationArmy(new Cannon(), opcionCannon);
                        }
                        break;                    case 5:
                        System.out.println("How many arrow tower do you want to train: ");
                        int opcionArrowTower = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newArrowTower(opcionArrowTower);
                        break;
                        case 6:
                        System.out.println("How many catapults do you want to train: ");
                        int opcionCatapult = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newCatapult(opcionCatapult);
                        for (int i = 0; i < opcionCatapult; i++) {
                            addUnitToCivilizationArmy(new Catapult(), opcionCatapult);
                        }
                        break;
                    case 7:
                        System.out.println("How many rocket launchers do you want to train: ");
                        int opcionRocketLauncher = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newRocketLauncher(opcionRocketLauncher);
                        for (int i = 0; i < opcionRocketLauncher; i++) {
                            addUnitToCivilizationArmy(new RocketLauncherTower(), opcionRocketLauncher);
                        }
                        break;
                    case 8:
                        System.out.println("How many magicians do you want to train: ");
                        int opcionMagician = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newMagician(opcionMagician);
                        for (int i = 0; i < opcionMagician; i++) {
                            addUnitToCivilizationArmy(new Magician(), opcionMagician);
                        }
                        break;
                    case 9:
                        if (civilization.getChurch() < 1) {
                            System.out.println("You need to build a church first before training priests.");
                            break;
                        }
                        System.out.println("How many priests do you want to train: ");
                        int opcionPriest = scanner.nextInt();
                        limpiarPantalla();
                        civilization.newPriest(opcionPriest);
                        for (int i = 0; i < opcionPriest; i++) {
                            addUnitToCivilizationArmy(new Priest(), opcionPriest);
                        }
                        break;
                    //
                    case 10:
                        limpiarPantalla();
                        return;
                    default:
                        limpiarPantalla();
                        System.out.println("Invalid option");
                        break;
                    
                }
            } catch (ResourceException | BuildingException e) {
                limpiarPantalla();
                System.out.println("Failed to train unit: " + e.getMessage());
            }
            System.out.println();  // Blank line for readability
        }
    }

    // Build structures method
    public void crearEdificios() {
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        while (true) {
            buildingsMenu();
            System.out.print("\nSelect an option: ");
            int opcion = scanner.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        limpiarPantalla();
                        civilization.newChurch();
                        break;
                    case 2:
                        limpiarPantalla();
                        civilization.newMagicTower();
                        break;
                    case 3:
                        limpiarPantalla();
                        civilization.newFarm();
                        break;
                    case 4:
                        limpiarPantalla();
                        civilization.newCarpentry();
                        break;
                    case 5:
                        limpiarPantalla();
                        civilization.newSmithy();
                        break;
                    case 6:
                        limpiarPantalla();
                        return;
                    default:
                        limpiarPantalla();
                        System.out.println("Invalid option");
                }
            } catch (ResourceException e) {
                limpiarPantalla();
                System.out.println("Failed to build structure: " + e.getMessage());
            }
            System.out.println();  // Blank line for readability
        }
    }

    // Upgrade technologies method
    public void mejorarTecnologias() {
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        while (true) {
            technologyUpgradeMenu();
            System.out.print("\nSelect an option: ");
            int opcion = scanner.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        limpiarPantalla();
                        civilization.upgradeTechnologyAttack();
                        break;
                    case 2:
                        limpiarPantalla();
                        civilization.upgradeTechnologyDefense();
                        break;
                    case 3:
                        limpiarPantalla();
                        return;
                    default:
                        limpiarPantalla();
                        System.out.println("Invalid option");
                }
            } catch (ResourceException e) {
                limpiarPantalla();
                System.out.println("You can't upgrade your technology: " + e.getMessage());
            }
        }
    }

    // Battle reports method
    public void reportesDeBatalla() {
        limpiarPantalla();
        System.out.println("Mostrando Reportes de Batalla...");
        // Implement logic to show battle reports
    }
    

    // Main method
    public static void main(String[] args) {
        Main mainInstance = new Main();
        Scanner scanner = new Scanner(System.in);
        mainInstance.limpiarPantalla();
        mainInstance.startTimerResources(0, 30000); // Generate resources every 30 seconds

        // Start attack timer with a delay of 5 minutes and a period of 3 minutes
        mainInstance.startTimerAttack(30000, 60000);

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
                    mainInstance.limpiarPantalla();
                    mainInstance.mejorarTecnologias();
                    break;
                case 4:
                    mainInstance.limpiarPantalla();
                    mainInstance.reportesDeBatalla();
                    break;
                case 5:
                    mainInstance.limpiarPantalla();
                    mainInstance.setEnemyApproaching(true); // Set enemy approaching
                    mainInstance.viewThreat(); // Create enemy army
                    break;
                case 6:
                    mainInstance.limpiarPantalla();
                    mainInstance.mostrarRecursos();
                    break;
                case 7:
                    mainInstance.limpiarPantalla();
                    mainInstance.printCivilizationArmy();
                    break;    
                case 8:
                    System.out.println("Game Over");
                    scanner.close();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println();  // Blank line for readability
        }
    }
}
