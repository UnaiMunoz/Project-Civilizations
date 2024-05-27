package com.civilizations;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
    private ArrayList<MilitaryUnit>[] civilizationArmy;
    private ArrayList<MilitaryUnit>[] enemyArmy;
    private ArrayList<MilitaryUnit>[][] armies; // [0]: civilizationArmy, [1]: enemyArmy
    private StringBuilder battleDevelopment;
    private int[][] initialCostFleet; // [0]: civilization costs, [1]: enemy costs
    private int initialNumberUnitsCivilization;
    private int initialNumberUnitsEnemy;
    private int[] wasteWoodIron; // [0]: wood, [1]: iron
    private int enemyDrops;
    private int civilizationDrops;
    private int[][] resourcesLooses; // [0]: civilization, [1]: enemy
    private int[][] initialArmies;
    private int[] actualNumberUnitsCivilization;
    private int[] actualNumberUnitsEnemy;
    private Random random;

    @SuppressWarnings("unchecked")
    public Battle(ArrayList<MilitaryUnit>[] civilizationArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;
        this.armies = new ArrayList[2][];
        this.armies[0] = civilizationArmy;
        this.armies[1] = enemyArmy;
        this.battleDevelopment = new StringBuilder();
        this.initialCostFleet = new int[2][3];
        this.wasteWoodIron = new int[2];
        this.resourcesLooses = new int[2][4];
        this.initialArmies = new int[2][civilizationArmy.length];
        this.actualNumberUnitsCivilization = new int[civilizationArmy.length];
        this.actualNumberUnitsEnemy = new int[enemyArmy.length];
        this.random = new Random();
        initInitialArmies();
        calculateInitialCosts();
        updateResourcesLooses();
    }

    private void initInitialArmies() {
        for (int i = 0; i < civilizationArmy.length; i++) {
            this.initialNumberUnitsCivilization += civilizationArmy[i].size();
            this.initialNumberUnitsEnemy += enemyArmy[i].size();
            this.initialArmies[0][i] = civilizationArmy[i].size();
            this.initialArmies[1][i] = enemyArmy[i].size();
            this.actualNumberUnitsCivilization[i] = civilizationArmy[i].size();
            this.actualNumberUnitsEnemy[i] = enemyArmy[i].size();
        }
    }

    private void calculateInitialCosts() {
        for (int i = 0; i < civilizationArmy.length; i++) {
            for (MilitaryUnit unit : civilizationArmy[i]) {
                initialCostFleet[0][0] += unit.getFoodCost();
                initialCostFleet[0][1] += unit.getWoodCost();
                initialCostFleet[0][2] += unit.getIronCost();
            }
            for (MilitaryUnit unit : enemyArmy[i]) {
                initialCostFleet[1][0] += unit.getFoodCost();
                initialCostFleet[1][1] += unit.getWoodCost();
                initialCostFleet[1][2] += unit.getIronCost();
            }
        }
    }

    private int selectAttackingGroup() {
        int[] civilizationGroupProbabilities = calculateGroupProbabilities(actualNumberUnitsCivilization);
        int[] enemyGroupProbabilities = calculateGroupProbabilities(actualNumberUnitsEnemy);

        int civilizationGroup = selectGroup(civilizationGroupProbabilities);
        int enemyGroup = selectGroup(enemyGroupProbabilities);

        return random.nextBoolean() ? civilizationGroup : enemyGroup;
    }

    private int[] calculateGroupProbabilities(int[] actualNumberUnits) {
        int totalUnits = 0;
        for (int units : actualNumberUnits) {
            totalUnits += units;
        }

        int[] groupProbabilities = new int[actualNumberUnits.length];
        for (int i = 0; i < actualNumberUnits.length; i++) {
            groupProbabilities[i] = (actualNumberUnits[i] * 100) / totalUnits;
        }
        return groupProbabilities;
    }

    private int selectGroup(int[] groupProbabilities) {
        int randomNumber = random.nextInt(100);
        int cumulativeProbability = 0;
        for (int i = 0; i < groupProbabilities.length; i++) {
            cumulativeProbability += groupProbabilities[i];
            if (randomNumber < cumulativeProbability) {
                return i;
            }
        }
        return groupProbabilities.length - 1;
    }

    private MilitaryUnit selectAttackingUnit(int attackingGroup) {
        int armyIndex = random.nextBoolean() ? 0 : 1;
        ArrayList<MilitaryUnit> army = armies[armyIndex][attackingGroup % armies[armyIndex].length];
        if (army.isEmpty()) {
            return null;
        }
        return army.get(random.nextInt(army.size()));
    }

    private int selectDefendingGroup(int attackingGroup) {
        int[] defendingGroupProbabilities = calculateGroupProbabilities(
                attackingGroup == 0 ? actualNumberUnitsEnemy : actualNumberUnitsCivilization);

        return selectGroup(defendingGroupProbabilities);
    }

    private MilitaryUnit selectDefendingUnit(int defendingGroup) {
        int armyIndex = defendingGroup == 0 ? 1 : 0;
        ArrayList<MilitaryUnit> army = armies[armyIndex][defendingGroup % armies[armyIndex].length];
        if (army.isEmpty()) {
            return null;
        }
        return army.get(random.nextInt(army.size()));
    }

    private void updateResourcesLooses() {
        for (int i = 0; i < civilizationArmy.length; i++) {
            for (MilitaryUnit unit : civilizationArmy[i]) {
                resourcesLooses[0][0] += unit.getFoodCost();
                resourcesLooses[0][1] += unit.getWoodCost();
                resourcesLooses[0][2] += unit.getIronCost();
            }
            for (MilitaryUnit unit : enemyArmy[i]) {
                resourcesLooses[1][0] += unit.getFoodCost();
                resourcesLooses[1][1] += unit.getWoodCost();
                resourcesLooses[1][2] += unit.getIronCost();
            }
        }
        resourcesLooses[0][3] = resourcesLooses[0][2] + (resourcesLooses[0][1] / 5) + (resourcesLooses[0][0] / 10);
        resourcesLooses[1][3] = resourcesLooses[1][2] + (resourcesLooses[1][1] / 5) + (resourcesLooses[1][0] / 10);
    }

    public void simulateBattle() {
        int maxRounds = 10;
        int rounds = 0;

        while (!isBattleOver() && rounds < maxRounds) {
            simulateRound();
            rounds++;
        }

        if (rounds >= maxRounds) {
            System.out.println("Maximum number of rounds reached without ending the battle.");
        }
    }

    private void simulateRound() {
        for (int i = 0; i < civilizationArmy.length; i++) {
            if (!civilizationArmy[i].isEmpty() && !enemyArmy[i].isEmpty()) {
                int attackingGroup = selectAttackingGroup();
                MilitaryUnit attackingUnit = selectAttackingUnit(attackingGroup);
                
                if (attackingUnit == null) {
                    continue; // Skip this iteration if no attacker is found
                }
    
                int defendingGroup = selectDefendingGroup(attackingGroup);
                MilitaryUnit defendingUnit = selectDefendingUnit(defendingGroup);
    
                if (defendingUnit == null) {
                    continue; // Skip this iteration if no defender is found
                }
    
                // Pass group indices to the fight method
                fight(attackingUnit, defendingUnit, attackingGroup, defendingGroup);
            }
        }
        updateCurrentUnits();
        updateUnitDrops();
    }
    
    private void updateUnitDrops() {
        civilizationDrops = 0;
        enemyDrops = 0;
        for (int i = 0; i < initialArmies[0].length; i++) {
            civilizationDrops += initialArmies[0][i] - actualNumberUnitsCivilization[i];
            enemyDrops += initialArmies[1][i] - actualNumberUnitsEnemy[i];
        }
    }

    private void fight(MilitaryUnit attacker, MilitaryUnit defender, int attackerGroup, int defenderGroup) {
        if (attacker == null || defender == null) {
            // Handle the situation where either attacker or defender is null
            return;
        }
    
        do {
            int damageDealt = attacker.attack();
            defender.takeDamage(damageDealt);
            battleDevelopment.append("Civilization attacks: ").append(attacker.getClass().getSimpleName())
                .append(" attacks ").append(defender.getClass().getSimpleName())
                .append("\nDamage dealt: ").append(damageDealt);
            int actualArmor = Math.max(defender.getActualArmor(), 0);
            battleDevelopment.append("\nDefender armor remaining: ").append(actualArmor).append("\n");
    
            if (defender.getActualArmor() <= 0) {
                battleDevelopment.append(defender.getClass().getSimpleName()).append(" eliminated.\n");
                if (random.nextInt(100) < defender.getChanceGeneratinWaste()) {
                    wasteWoodIron[0] += defender.getWoodCost() * 0.7;
                    wasteWoodIron[1] += defender.getIronCost() * 0.7;
                }
                armies[1][defenderGroup].remove(defender);
                break;
            }
    
            // If the defender is still alive, let it attack back
            damageDealt = defender.attack();
            attacker.takeDamage(damageDealt);
            battleDevelopment.append("Enemy attacks: ").append(defender.getClass().getSimpleName())
                .append(" attacks ").append(attacker.getClass().getSimpleName())
                .append("\nDamage dealt: ").append(damageDealt);
            actualArmor = Math.max(attacker.getActualArmor(), 0);
            battleDevelopment.append("\nAttacker armor remaining: ").append(actualArmor).append("\n");
    
            if (attacker.getActualArmor() <= 0) {
                battleDevelopment.append(attacker.getClass().getSimpleName()).append(" eliminated.\n");
                if (random.nextInt(100) < attacker.getChanceGeneratinWaste()) {
                    wasteWoodIron[0] += attacker.getWoodCost() * 0.7;
                    wasteWoodIron[1] += attacker.getIronCost() * 0.7;
                }
                armies[0][attackerGroup].remove(attacker);
                break;
            }
    
        } while (random.nextInt(100) < attacker.getChanceAttackAgain());
    }
    

    private void updateCurrentUnits() {
        for (int i = 0; i < civilizationArmy.length; i++) {
            actualNumberUnitsCivilization[i] = civilizationArmy[i].size();
            actualNumberUnitsEnemy[i] = enemyArmy[i].size();
        }
    }

    private boolean isBattleOver() {
        int remainingCivilizationUnits = 0;
        int remainingEnemyUnits = 0;
        for (int i = 0; i < actualNumberUnitsCivilization.length; i++) {
            remainingCivilizationUnits += actualNumberUnitsCivilization[i];
            remainingEnemyUnits += actualNumberUnitsEnemy[i];
        }

        int twentyPercentCivilization = (int) (initialNumberUnitsCivilization * 0.2);
        int twentyPercentEnemy = (int) (initialNumberUnitsEnemy * 0.2);

        return remainingCivilizationUnits <= twentyPercentCivilization || remainingEnemyUnits <= twentyPercentEnemy;
    }

    public String getBattleReport(int battles) {
        StringBuilder report = new StringBuilder();
        report.append("BATTLE NUMBER: ").append(battles).append("\n");
        pause(500);
        report.append("BATTLE STATISTICS\n");
        pause(500);
        report.append("Civilization Units Drops: ").append(civilizationDrops).append("\n");
        pause(500);
        report.append("Enemy Units Drops: ").append(enemyDrops).append("\n");
        pause(500);
        report.append("Cost Army Civilization: Food: ").append(resourcesLooses[0][0])
              .append(" Wood: ").append(resourcesLooses[0][1])
              .append(" Iron: ").append(resourcesLooses[0][2]).append("\n");
        pause(500);
        report.append("Cost Army Enemy: Food: ").append(resourcesLooses[1][0])
              .append(" Wood: ").append(resourcesLooses[1][1])
              .append(" Iron: ").append(resourcesLooses[1][2]).append("\n");
        pause(500);
        report.append("Losses Army Civilization: Food: ").append(resourcesLooses[0][0])
              .append(" Wood: ").append(resourcesLooses[0][1])
              .append(" Iron: ").append(resourcesLooses[0][2]).append("\n");
        pause(500);
        report.append("Losses Army Enemy: Food: ").append(resourcesLooses[1][0])
              .append(" Wood: ").append(resourcesLooses[1][1])
              .append(" Iron: ").append(resourcesLooses[1][2]).append("\n");
        pause(500);
        report.append("Waste Generated: Wood: ").append(wasteWoodIron[0])
              .append(" Iron: ").append(wasteWoodIron[1]).append("\n");
        pause(500);
        report.append("Battle Won by: ").append(getWinner()).append("\n");
        pause(500);
        return report.toString();
    }

    public String getBattleDevelopment() {
        return battleDevelopment.toString();
    }

    public String getWinner() {
        int weightedLossesCivilization = resourcesLooses[0][2] + (resourcesLooses[0][1] * 5) + (resourcesLooses[0][0] * 10);
        int weightedLossesEnemy = resourcesLooses[1][2] + (resourcesLooses[1][1] * 5) + (resourcesLooses[1][0] * 10);

        if (weightedLossesCivilization < weightedLossesEnemy) {
            return "Civilization";
        } else {
            return "Enemy";
        }
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
