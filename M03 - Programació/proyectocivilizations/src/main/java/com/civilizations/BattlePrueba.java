package com.civilizations;

import java.util.ArrayList;
import java.util.Random;

public class BattlePrueba implements Variables {
    private ArrayList<MilitaryUnit> civilizationArmy;
    private ArrayList<MilitaryUnit> enemyArmy;
    private ArrayList<ArrayList<MilitaryUnit>> armies;
    private String battleDevelopment;
    private int[][] initialCostFleet;
    private int initialNumberUnitsCivilization;
    private int initialNumberUnitsEnemy;
    private int[] wasteWoodIron;
    private int enemyDrops;
    private int civilizationDrops;
    private int[][] resourcesLooses;
    private int[][] initialArmies;
    private int[] actualNumberUnitsCivilization;
    private int[] actualNumberUnitsEnemy;
    private int numLine = 0;
    private static final Random random = new Random();

    // Contadores de bajas
    private int civilizationCasualties = 0;
    private int enemyCasualties = 0;

    // DAOs
    private CivilizationArmyDAO civilizationDAO;
    private EnemyDAO enemyDAO;

    // Bandera para controlar si la batalla ha terminado
    private boolean isBattleFinished = false;

    // Constructor
    public BattlePrueba(int civilizationId, int enemyId) {
        this.civilizationDAO = new CivilizationArmyDAO();
        this.enemyDAO = new EnemyDAO();

        this.civilizationArmy = civilizationDAO.getAllUnitsByCivilization(civilizationId);
        this.enemyArmy = enemyDAO.getEnemyArmy(enemyId);
        this.armies = new ArrayList<>(2);
        this.armies.add(civilizationArmy);
        this.armies.add(enemyArmy);
        this.battleDevelopment = "";
        this.initialCostFleet = new int[2][3];
        this.wasteWoodIron = new int[2];
        this.resourcesLooses = new int[2][4];
        this.initialArmies = new int[2][9];
        this.actualNumberUnitsCivilization = new int[9];
        this.actualNumberUnitsEnemy = new int[4];
        this.initialNumberUnitsCivilization = this.initialFleetNumber(civilizationArmy);
        this.initialNumberUnitsEnemy = this.initialFleetNumber(enemyArmy);
        this.initInitialArmies();
    }

    public void startBattle(int civilizationId) {
        int num_battle =+ 1; // Incrementar correctamente el número de batalla
        BattleStatsDAO battleStatsDAO = new BattleStatsDAO();
        battleStatsDAO.saveBattleStats(civilizationId, num_battle, 0, 0);

        while (remainderPercentageFleet(civilizationArmy) > 20 && remainderPercentageFleet(enemyArmy) > 20) {
            int attacker = random.nextInt(2);
            if (attacker == 0) {
                attack(civilizationArmy, enemyArmy, civilizationId, num_battle);
            } else {
                attack(enemyArmy, civilizationArmy, civilizationId, num_battle);
            }
            battleDevelopment += "********************CHANGE ATTACKER********************\n";
        }

        updateResourcesLooses();
        battleStatsDAO.updateBattleResources(civilizationId, num_battle, wasteWoodIron[0], wasteWoodIron[1]);

        // Determinar el ganador
        String winner;
        if (remainderPercentageFleet(civilizationArmy) > remainderPercentageFleet(enemyArmy)) {
            winner = "Civilization";
        } else {
            winner = "Enemy";
        }

        // Registrar el ganador y las bajas en el log
        battleDevelopment += "Winner: " + winner + "\n" +
                               "Civilization casualties: " + civilizationCasualties + "\n" +
                               "Enemy casualties: " + enemyCasualties + "\n";
        saveBattleLog(civilizationId, num_battle, generateNumLine(), battleDevelopment);

        // Marcar la batalla como finalizada
        isBattleFinished = true;
        EnemyDAO enemyDAO = new EnemyDAO();
        enemyDAO.deleteEnemyArmy();
    }

    public void initInitialArmies() {
        for (MilitaryUnit unit : civilizationArmy) {
            System.out.println("Unit name: " + unit.getName());
            switch (unit.getName().toUpperCase()) {
                case "SWORDSMAN":
                    initialArmies[0][0]++;
                    actualNumberUnitsCivilization[0]++;
                    break;
                case "SPEARMAN":
                    initialArmies[0][1]++;
                    actualNumberUnitsCivilization[1]++;
                    break;
                case "CROSSBOW":
                    initialArmies[0][2]++;
                    actualNumberUnitsCivilization[2]++;
                    break;
                case "CANNON":
                    initialArmies[0][3]++;
                    actualNumberUnitsCivilization[3]++;
                    break;
                case "ARROW_TOWER":
                    initialArmies[0][4]++;
                    actualNumberUnitsCivilization[4]++;
                    break;
                case "CATAPULT":
                    initialArmies[0][5]++;
                    actualNumberUnitsCivilization[5]++;
                    break;
                case "ROCKET_LAUNCHER_TOWER":
                    initialArmies[0][6]++;
                    actualNumberUnitsCivilization[6]++;
                    break;
                case "MAGICIAN":
                    initialArmies[0][7]++;
                    actualNumberUnitsCivilization[7]++;
                    break;
                case "PRIEST":
                    initialArmies[0][8]++;
                    actualNumberUnitsCivilization[8]++;
                    break;
            }
        }
        for (MilitaryUnit unit : enemyArmy) {
            switch (unit.getName().toUpperCase()) {
                case "SWORDSMAN":
                    initialArmies[1][0]++;
                    actualNumberUnitsEnemy[0]++;
                    break;
                case "SPEARMAN":
                    initialArmies[1][1]++;
                    actualNumberUnitsEnemy[1]++;
                    break;
                case "CROSSBOW":
                    initialArmies[1][2]++;
                    actualNumberUnitsEnemy[2]++;
                    break;
                case "CANNON":
                    initialArmies[1][3]++;
                    actualNumberUnitsEnemy[3]++;
                    break;
            }
        }
    }

    public void updateResourcesLooses() {
        resourcesLooses[0][0] = initialCostFleet[0][0] - fleetResourceCost(civilizationArmy)[0];
        resourcesLooses[0][1] = initialCostFleet[0][1] - fleetResourceCost(civilizationArmy)[1];
        resourcesLooses[0][2] = initialCostFleet[0][2] - fleetResourceCost(civilizationArmy)[2];
        resourcesLooses[0][3] = resourcesLooses[0][2] + resourcesLooses[0][1] / 5 + resourcesLooses[0][0] / 10;

        resourcesLooses[1][0] = initialCostFleet[1][0] - fleetResourceCost(enemyArmy)[0];
        resourcesLooses[1][1] = initialCostFleet[1][1] - fleetResourceCost(enemyArmy)[1];
        resourcesLooses[1][2] = initialCostFleet[1][2] - fleetResourceCost(enemyArmy)[2];
        resourcesLooses[1][3] = resourcesLooses[1][2] + resourcesLooses[1][1] / 5 + resourcesLooses[1][0] / 10;
    }

    public int[] fleetResourceCost(ArrayList<MilitaryUnit> army) {
        int[] cost = new int[4];
        for (MilitaryUnit unit : army) {
            cost[0] += unit.getWoodCost();
            cost[1] += unit.getFoodCost();
            cost[2] += unit.getIronCost();
            cost[3] += unit.getManaCost();
        }
        return cost;
    }

    public int initialFleetNumber(ArrayList<MilitaryUnit> army) {
        return army.size();
    }

    public int remainderPercentageFleet(ArrayList<MilitaryUnit> army) {
        return (int) ((double) army.size() / initialFleetNumber(army) * 100);
    }

    public int getGroupDefender(ArrayList<MilitaryUnit> army) {
        return random.nextInt(army.size());
    }

    public int getCivilizationGroupAttacker() {
        return random.nextInt(civilizationArmy.size());
    }

    public int getEnemyGroupAttacker() {
        return random.nextInt(enemyArmy.size());
    }

    public void resetArmyArmor() {
        for (MilitaryUnit unit : civilizationArmy) {
            unit.resetArmor();
        }
        for (MilitaryUnit unit : enemyArmy) {
            unit.resetArmor();
        }
    }

    public String getBattleReport(int battles) {
        return "Number of battles: " + battles + "\n" + "Number of drops: Civilization " + civilizationDrops + " - Enemy " + enemyDrops;
    }

    public String getBattleDevelopment() {
        return battleDevelopment;
    }

    public void attack(ArrayList<MilitaryUnit> attackers, ArrayList<MilitaryUnit> defenders, int civilizationId, int numBattle) {
        int attackerIndex = random.nextInt(attackers.size());
        int defenderIndex = random.nextInt(defenders.size());

        MilitaryUnit attacker = attackers.get(attackerIndex);
        MilitaryUnit defender = defenders.get(defenderIndex);

        int damage = attacker.attack();
        defender.takeDamage(damage);

        if (defender.getActualArmor() <= 0) {
            if (defenders == civilizationArmy) {
                removeUnitFromDatabase(defender, civilizationDAO);
                boolean generates_waste = unitGeneratesWaste(defender);
                if (generates_waste) {
                    generateWaste(defender);
                }
                civilizationCasualties++; // Incrementar bajas de la civilización
            } else if (defenders == enemyArmy) {
                enemyDAO.deleteEnemyTroop(defender.getUnit_id());
                boolean generates_waste = unitGeneratesWaste(defender);
                if (generates_waste) {
                    generateWaste(defender);
                }
                enemyCasualties++; // Incrementar bajas del enemigo
            }
            defenders.remove(defenderIndex);
            battleDevelopment += "Unit destroyed.\n";
        }

        battleDevelopment += "Attacker: " + attacker.getName() + " | Defender: " + defender.getName() + "\n";
        int numLine = generateNumLine();
        saveBattleLog(civilizationId, numBattle, numLine, battleDevelopment);

        battleDevelopment += "Damage: " + damage + " | Defender's remaining armor: " + defender.getActualArmor() + "\n\n";
        saveBattleLog(civilizationId, numBattle, numLine, battleDevelopment);
    }

    private boolean unitGeneratesWaste(MilitaryUnit unit) {
        int chance = unit.getChanceGeneratinWaste(); // Obtener la probabilidad de la unidad
        return random.nextInt(100) < chance; // Se generan residuos si el número aleatorio es menor que la probabilidad
    }

    private void generateWaste(MilitaryUnit unit) {
        // Calcular la cantidad de residuos de madera y hierro
        int woodCost = unit.getWoodCost();
        int ironCost = unit.getIronCost();

        int woodWaste = woodCost * PERCENTATGE_WASTE / 100;
        int ironWaste = ironCost * PERCENTATGE_WASTE / 100;

        // Restar la cantidad de residuos de los recursos totales del ejército
        wasteWoodIron[0] += woodWaste; // Sumar residuos de madera
        wasteWoodIron[1] += ironWaste; // Sumar residuos de hierro
    }

    private void removeUnitFromDatabase(MilitaryUnit unit, CivilizationArmyDAO civilizationDAO) {
        if (unit instanceof SpecialUnit) {
            civilizationDAO.deleteSpecialUnit(unit.getUnit_id());
            System.out.println("SpecialUnit deleted: " + unit.getUnit_id());
        } else if (unit instanceof AttackUnit) {
            civilizationDAO.deleteAttackUnit(unit.getUnit_id());
            System.out.println("AttackUnit deleted: " + unit.getUnit_id());
        } else if (unit instanceof DefenseUnit) {
            civilizationDAO.deleteDefenseUnit(unit.getUnit_id());
            System.out.println("DefenseUnit deleted: " + unit.getUnit_id());
        }
    }

    // Getters y Setters para pruebas o uso adicional
    public ArrayList<MilitaryUnit> getCivilizationArmy() {
        return civilizationArmy;
    }

    public ArrayList<MilitaryUnit> getEnemyArmy() {
        return enemyArmy;
    }

    public void saveBattleStats(int civilizationId, int numBattle) {
        // Guardar las estadísticas para la civilización
        BattleStatsDAO civilizationBattleStatsDAO = new BattleStatsDAO();
        civilizationBattleStatsDAO.saveBattleStats(civilizationId, numBattle, 0, 0);
    }

    private int generateNumLine() {
        numLine++;
        return numLine;
    }

    public void saveBattleLog(int civilizationId, int numBattle, int numLine, String battleDevelopment) {
        BattleLogDAO battleLogDAO = new BattleLogDAO();
        battleLogDAO.saveLogEntry(civilizationId, numBattle, numLine, battleDevelopment);
    }

    public boolean isBattleFinished() {
        return isBattleFinished;
    }
}
