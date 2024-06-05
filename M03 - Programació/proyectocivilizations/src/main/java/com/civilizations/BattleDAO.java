package com.civilizations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleDAO {
    private Random random = new Random();

    public void fight1v1(int id, int unitID, int baseDamage) {
        CivilizationArmyDAO civilizationArmyDAO = new CivilizationArmyDAO();
        EnemyDAO enemyDAO = new EnemyDAO();
        
        List<AttackUnit> civilizationAttackUnits = civilizationArmyDAO.getAttackUnits(id);
        List<DefenseUnit> civilizationDefenseUnits = civilizationArmyDAO.getDefenseUnits(id);
        List<SpecialUnit> civilizationSpecialUnits = civilizationArmyDAO.getSpecialUnits(id);
        List<AttackUnit> enemyAttackUnits = enemyDAO.getEnemyArmy(id);

        List<MilitaryUnit> civilizationArmy = new ArrayList<>();
        civilizationArmy.addAll(civilizationAttackUnits);
        civilizationArmy.addAll(civilizationDefenseUnits);
        civilizationArmy.addAll(civilizationSpecialUnits);

        // Select a unit from the civilization army
        MilitaryUnit civilizationUnit = selectUnitBasedOnProbability(civilizationArmy, Variables.CHANCE_ATTACK_CIVILIZATION_UNITS);

        // Select a unit from the enemy army
        MilitaryUnit enemyUnit = selectUnitBasedOnProbability(enemyAttackUnits, Variables.CHANCE_ATTACK_ENEMY_UNITS);

        // Fight until one of the armies is reduced to 20% of its initial capacity
        int civilizationInitialCount = civilizationArmy.size();
        int enemyInitialCount = enemyAttackUnits.size();
        while (civilizationArmy.size() > civilizationInitialCount * 0.2 && enemyAttackUnits.size() > enemyInitialCount * 0.2) {
            // Civilization unit attacks enemy unit
            int damageDealt = calculateDamage(baseDamage, enemyUnit.getActualArmor());
            enemyUnit.takeDamage(damageDealt);

            // Check if the enemy unit is defeated
            if (enemyUnit.getActualArmor() <= 0) {
                handleWasteGeneration(enemyUnit);
                enemyAttackUnits.remove(enemyUnit);
                if (enemyAttackUnits.isEmpty()) break;
                enemyUnit = selectUnitBasedOnProbability(enemyAttackUnits, Variables.CHANCE_ATTACK_ENEMY_UNITS);
            }

            // Check if the civilization unit attacks again
            if (random.nextInt(100) < civilizationUnit.getChanceAttackAgain()) {
                continue;
            }

            // Enemy unit attacks civilization unit
            damageDealt = calculateDamage(enemyUnit.attack(), civilizationUnit.getActualArmor());
            civilizationUnit.takeDamage(damageDealt);

            // Check if the civilization unit is defeated
            if (civilizationUnit.getActualArmor() <= 0) {
                handleWasteGeneration(civilizationUnit);
                civilizationArmy.remove(civilizationUnit);
                if (civilizationArmy.isEmpty()) break;
                civilizationUnit = selectUnitBasedOnProbability(civilizationArmy, Variables.CHANCE_ATTACK_CIVILIZATION_UNITS);
            }

            // Check if the enemy unit attacks again
            if (random.nextInt(100) < enemyUnit.getChanceAttackAgain()) {
                continue;
            }
        }

        // Determine the winner
        String winner = (civilizationArmy.size() > enemyAttackUnits.size()) ? "Civilization" : "Enemy";
        System.out.println("The winner is: " + winner);
    }

    private MilitaryUnit selectUnitBasedOnProbability(List<? extends MilitaryUnit> army, int[] probabilities) {
        int totalWeight = 0;
        for (int weight : probabilities) {
            totalWeight += weight;
        }

        int randomWeight = random.nextInt(totalWeight);
        int currentWeight = 0;

        for (int i = 0; i < army.size(); i++) {
            currentWeight += probabilities[i];
            if (randomWeight < currentWeight) {
                return army.get(i);
            }
        }

        return army.get(0); // fallback, should never hit this
    }

    private int calculateDamage(int baseDamage, int armor) {
        // Damage calculation: base damage minus armor
        return Math.max(0, baseDamage - armor);
    }

    private void handleWasteGeneration(MilitaryUnit unit) {
        int chance = getWasteChanceForUnit(unit);
        if (random.nextInt(100) < chance) {
            generateWaste(unit);
        }
    }

    private int getWasteChanceForUnit(MilitaryUnit unit) {
        if (unit instanceof AttackUnit) {
            switch (unit.getClass().getSimpleName()) {
                case "Swordsman":
                    return Variables.CHANCE_GENERATNG_WASTE_SWORDSMAN;
                case "Spearman":
                    return Variables.CHANCE_GENERATNG_WASTE_SPEARMAN;
                case "Cannon":
                    return Variables.CHANCE_GENERATNG_WASTE_CANNON;
                case "Crossbow":
                    return Variables.CHANCE_GENERATNG_WASTE_CROSSBOW;
            }
        } else if (unit instanceof DefenseUnit) {
            switch (unit.getClass().getSimpleName()) {
                case "ArrowTower":
                    return Variables.CHANCE_GENERATNG_WASTE_ARROWTOWER;
                case "Catapult":
                    return Variables.CHANCE_GENERATNG_WASTE_CATAPULT;
                case "RocketLauncherTower":
                    return Variables.CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER;
            }
        } else if (unit instanceof SpecialUnit) {
            switch (unit.getClass().getSimpleName()) {
                case "Magician":
                    return Variables.CHANCE_GENERATNG_WASTE_MAGICIAN;
                case "Priest":
                    return Variables.CHANCE_GENERATNG_WASTE_PRIEST;
            }
        }
        return 0; // fallback, should never hit this
    }

    private void generateWaste(MilitaryUnit unit) {
        int woodWaste = unit.getWoodCost() * Variables.PERCENTATGE_WASTE / 100;
        int ironWaste = unit.getIronCost() * Variables.PERCENTATGE_WASTE / 100;
        // Add woodWaste and ironWaste to the civilization's resources
        // Implementation needed based on your resource management logic
    }
}
