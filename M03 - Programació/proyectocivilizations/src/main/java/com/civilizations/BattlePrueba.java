package com.civilizations;

import java.util.ArrayList;
import java.util.Random;

public class BattlePrueba {
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

    private static final Random random = new Random();

    // DAOs
    private CivilizationArmyDAO civilizationDAO;
    private EnemyDAO enemyDAO;

    // Constructor
    public BattlePrueba(int civilizationId, int enemyId) {
        this.civilizationDAO = new CivilizationArmyDAO();
        this.enemyDAO = new EnemyDAO();

        this.civilizationArmy = civilizationDAO.getAttackUnitsByCivilization(civilizationId);
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

    public void startBattle() {
        // Lógica para iniciar la batalla
        while (remainderPercentageFleet(civilizationArmy) > 20 && remainderPercentageFleet(enemyArmy) > 20) {
            int attacker = random.nextInt(2);
            if (attacker == 0) {
                attack(civilizationArmy, enemyArmy);
            } else {
                attack(enemyArmy, civilizationArmy);
            }
            battleDevelopment += "********************CHANGE ATTACKER********************\n";
        }
        updateResourcesLooses();
    }

    public void initInitialArmies() {
        for (MilitaryUnit unit : civilizationArmy) {
            switch (unit.getClass().getSimpleName()) {
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
            switch (unit.getClass().getSimpleName()) {
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
            switch (unit.getClass().getSimpleName()) {
                case "SWORDSMAN":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
                case "SPEARMAN":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
                case "CROSSBOW":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
                case "CANNON":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
                case "ARROW_TOWER":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
                case "CATAPULT":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
                case "ROCKET_LAUNCHER_TOWER":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
                case "MAGICIAN":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
                case "PRIEST":
                    cost[0] += unit.getWoodCost(); 
                    cost[1] += unit.getFoodCost();
                    cost[2] += unit.getIronCost();
                    cost[3] += unit.getManaCost();
                    break;
            }
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

    // Lógica de ataque básica
    private void attack(ArrayList<MilitaryUnit> attackers, ArrayList<MilitaryUnit> defenders) {
        int attackerIndex = random.nextInt(attackers.size());
        int defenderIndex = getGroupDefender(defenders);
    
        MilitaryUnit attacker = attackers.get(attackerIndex);
        MilitaryUnit defender = defenders.get(defenderIndex);
    
        int damage = attacker.attack();
        int armor = defender.getActualArmor();
        int actualDamage = Math.max(0, damage - armor);
    
        defender.takeDamage(Math.max(0, armor - actualDamage));
    
        battleDevelopment += "Attacker: " + attacker.getClass().getSimpleName() + " dealt " + actualDamage + " damage to " + defender.getClass().getSimpleName() + "\n";
    
        if (defender.getActualArmor() <= 0) {
            defenders.remove(defenderIndex);
            battleDevelopment += defender.getClass().getSimpleName() + " has been destroyed!\n";
            if (defenders == enemyArmy) {
                civilizationDrops++;
            } else {
                enemyDrops++;
            }
        }
    }
    
}
