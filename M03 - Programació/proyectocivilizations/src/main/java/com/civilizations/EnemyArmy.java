package com.civilizations;

import java.util.ArrayList;
import java.util.Random;


public class EnemyArmy implements Variables,MilitaryUnit{
    CivilizationDAO civilizationDAO = new CivilizationDAO();
    private int unitCounter = 0;
    public ArrayList<MilitaryUnit>[] enemyArmy;
    private static final double[] unitProbabilities = {35.0, 25.0, 20.0, 20.0};
    private boolean enemyApproaching;
    private boolean enemyArmyCreated;
    public ArrayList<MilitaryUnit>[] civilizationArmy;
    int unit_id;
    String username;
    int armor;
    int initialArmor;
    int baseDamage;
    int experience;
    boolean sanctified;

    public EnemyArmy(int civilizationId) {
        enemyApproaching = false;
        enemyArmyCreated = false;
        initializeEnemyArmy();
    }

    public void setEnemyApproaching(boolean approaching) {
        this.enemyApproaching = approaching;
    }

    public boolean isEnemyApproaching() {
        return enemyApproaching;
    }


    // Initialize enemyArmy array
    private void initializeEnemyArmy() {
        enemyArmy = new ArrayList[4];
        for (int i = 0; i < enemyArmy.length; i++) {
            enemyArmy[i] = new ArrayList<>();
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


    // Check if resource costs exceed the limits
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

    // Método para crear el ejército enemigo
    public void createEnemyArmy(int id) {
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
                    EnemyDAO enemyDAO = new EnemyDAO();
                    if (random.nextDouble() < (probability / 100)) {
                        int numUnits = random.nextInt(100);
                        for (int j = 0; j < numUnits; j++) {
                            unitCounter++;
                            switch (i) {
                                case 0:
                                    Swordsman swordsman = new Swordsman();
                                    addUnitToEnemyArmy(new Swordsman(), numUnits);
                                    enemyDAO.insertEnemyTroop(id, unitCounter, "Swordsman",swordsman.getActualArmor(), swordsman.attack(), 0);

                                    break;
                                case 1:
                                    Spearman spearman = new Spearman();
                                    addUnitToEnemyArmy(new Spearman(), numUnits);
                                    enemyDAO.insertEnemyTroop(id, unitCounter, "Spearman",spearman.getActualArmor(), spearman.attack(), 0);
                                    break;
                                case 2:
                                    Crossbow crossbow = new Crossbow();
                                    addUnitToEnemyArmy(new Crossbow(), numUnits);
                                    enemyDAO.insertEnemyTroop(id, unitCounter, "Crossbow",crossbow.getActualArmor(), crossbow.attack(), 0);
                                    break;
                                case 3:
                                    Cannon cannon = new Cannon();
                                    addUnitToEnemyArmy(new Cannon(), numUnits);
                                    enemyDAO.insertEnemyTroop(id, unitCounter, "Cannon", cannon.getActualArmor(), cannon.attack(), 0);
                                    break;
                            }
                        }
                    }
                }
    
                for (ArrayList<MilitaryUnit> units : enemyArmy) {
                    if (!units.isEmpty()) {
                        armyCreated = true;
                        break;
                    }
                }
    
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

    //
    public int attack() {
        // Return the attack power of the unit.
        return baseDamage;
    }
    public int getUnit_id(){
        return unit_id;
    }
    public void takeDamage(int receivedDamage) {
        // Reduce armor by the received damage.
        armor -= receivedDamage;
        if (armor <= 0) {
            armor = 0;
        }
    }

    public int getActualArmor() {
        // Return the current armor level after taking damage.
        return armor;
    }

    @Override
    public int getFoodCost() {
        // Return the food cost of creating a new unit.
        switch (this.getClass().getSimpleName()) {
            case "Swordsman":
                return FOOD_COST_UNITS[0];
            case "Spearman":
                return FOOD_COST_UNITS[1];
            case "Crossbow":
                return FOOD_COST_UNITS[2];
            case "Cannon":
                return FOOD_COST_UNITS[3];
        }
    
    return 0;}
       


    public int getWoodCost() {
        // Return the wood cost of creating a new unit.
        switch (this.getClass().getSimpleName()) {
            case "Swordsman":
                return WOOD_COST_UNITS[0];
            case "Spearman":
                return WOOD_COST_UNITS[1];
            case "Crossbow":
                return WOOD_COST_UNITS[2];
            case "Cannon":
                return WOOD_COST_UNITS[3];
        }
    
    return 0;}    

    public int getIronCost() {
        // Return the iron cost of creating a new unit.
        switch (this.getClass().getSimpleName()) {
            case "Swordsman":
                return IRON_COST_UNITS[0];
            case "Spearman":
                return IRON_COST_UNITS[1];
            case "Crossbow":
                return IRON_COST_UNITS[2];
            case "Cannon":
                return IRON_COST_UNITS[3];
        }
    
    return 0;}    

    public int getManaCost() {
        // Return the mana cost of creating a new unit.
        return 0;
    }

    public int getChanceGeneratinWaste() {
        if (armor <= 0) {
            switch (this.getClass().getSimpleName()) {
                case "Swordsman":
                    return CHANCE_GENERATNG_WASTE_SWORDSMAN;
                case "Spearman":
                    return CHANCE_GENERATNG_WASTE_SPEARMAN;
                case "Crossbow":
                    return CHANCE_GENERATNG_WASTE_CROSSBOW;
                case "Cannon":
                    return CHANCE_GENERATNG_WASTE_CANNON;
            }
        }
        return 0;
    }

    public int getChanceAttackAgain() {
        // Return the probability of attacking again.
        switch (this.getClass().getSimpleName()) {
            case "Swordsman":
                return CHANCE_ATTACK_AGAIN_SWORDSMAN;
            case "Spearman":
                return CHANCE_ATTACK_AGAIN_SPEARMAN;
            case "Crossbow":
                return CHANCE_ATTACK_AGAIN_CROSSBOW;
            case "Cannon":
                return CHANCE_ATTACK_AGAIN_CANNON;
        }
        return 0;
    }

    public void resetArmor() {
        // Reset armor to its initial value.
        armor = initialArmor;
    }

    public void setExperience(int n) {
        // Set the experience to a new value.
        experience = n;
    }

    public int getExperience() {
        // Return the current experience of the unit.
        return experience;
    }

    public int getInitialArmor() {
        // Return the initial armor value.
        return initialArmor;
    }

    public int getBaseDamage() {
        // Return the base damage value.
        return baseDamage;
    }

    public boolean isSanctified() {
        // Return the sanctified status of the unit.
        return sanctified;
    }

    public void setSanctified(boolean sanctified) {
        // Set the sanctified status of the unit.
        this.sanctified = sanctified;
    }
    public void SanctifiedBuff(){
        if (sanctified){
            baseDamage += baseDamage * (PLUS_ATTACK_UNIT_SANCTIFIED /100);
            armor += armor * (PLUS_ARMOR_UNIT_SANCTIFIED /100) ;

        }
    }


}
    
