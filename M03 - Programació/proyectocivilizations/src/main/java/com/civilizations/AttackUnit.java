package com.civilizations;

public class AttackUnit implements MilitaryUnit, Variables {
    int civilization_id;
    int unit_id;
    int armor;
    int initialArmor;
    int baseDamage;
    int experience;
    boolean sanctified;

    public AttackUnit(int civilization_id, int unit_id, int armor, int initialArmor, int baseDamage, int experience, boolean sanctified) {
        this.civilization_id = civilization_id;
        this.unit_id = unit_id;
        this.armor = armor;
        this.initialArmor = initialArmor;
        this.baseDamage = baseDamage;
        this.experience = experience;
        this.sanctified = sanctified;
    }

    public AttackUnit() {}

    public AttackUnit(int armor, int baseDamage) {
        this.armor = armor;
        this.baseDamage = baseDamage;
    }

    public AttackUnit(int civilization_id, int unit_id, int armor, int baseDamage) {
        this.civilization_id = civilization_id;
        this.unit_id = unit_id;
        this.armor = armor;
        this.baseDamage = baseDamage;
    }

    public int getCivilization_id() {
        return this.civilization_id;
    }

    public void setCivilization_id(int civilization_id) {
        this.civilization_id = civilization_id;
    }

    public int getUnit_id() {
        return this.unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public int attack() {
        return baseDamage;
    }

    public void takeDamage(int receivedDamage) {
        armor -= receivedDamage;
        if (armor <= 0) {
            armor = 0;
        }
    }

    public int getActualArmor() {
        return armor;
    }

    @Override
    public int getFoodCost() {
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
        return 0;
    }

    public int getWoodCost() {
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
        return 0;
    }

    public int getIronCost() {
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
        return 0;
    }

    public int getManaCost() {
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
        armor = initialArmor;
    }

    public void setExperience(int n) {
        experience = n;
    }

    public int getExperience() {
        return experience;
    }

    public int getInitialArmor() {
        return initialArmor;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public boolean isSanctified() {
        return sanctified;
    }

    public void setSanctified(boolean sanctified) {
        this.sanctified = sanctified;
    }

    public void SanctifiedBuff() {
        if (sanctified) {
            baseDamage += baseDamage * (PLUS_ATTACK_UNIT_SANCTIFIED / 100);
            armor += armor * (PLUS_ARMOR_UNIT_SANCTIFIED / 100);
        }
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
