package com.civilizations;

public class Swordsman extends AttackUnit {

    public Swordsman(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_SWORDSMAN;
        } else {
            armor = ARMOR_SWORDSMAN + (civilization.getTechnologyDefense() * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_SWORDSMAN;
        } else {
            baseDamage = BASE_DAMAGE_SWORDSMAN + (civilization.getTechnologyAttack() * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_SWORDSMAN;
    }

    public Swordsman(int id, int civilizationid, int armor, int baseDamage, int experience) {
        super(civilizationid, id, armor, baseDamage);
        this.experience = experience;
        this.initialArmor = ARMOR_SWORDSMAN;
    }

    public Swordsman() {
        super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
        this.initialArmor = ARMOR_SWORDSMAN;
    }

    @Override
    public int attack() {
        return super.baseDamage;
    }

    @Override
    public void takeDamage(int receivedDamage) {
        super.armor -= receivedDamage;
        if (super.armor <= 0) {
            super.armor = 0;
        }
    }

    @Override
    public int getActualArmor() {
        return super.armor;
    }

    @Override
    public int getFoodCost() {
        return FOOD_COST_SWORDSMAN;
    }

    @Override
    public int getWoodCost() {
        return WOOD_COST_SWORDSMAN;
    }

    @Override
    public int getIronCost() {
        return IRON_COST_SWORDSMAN;
    }

    @Override
    public int getManaCost() {
        return MANA_COST_SWORDSMAN;
    }

    @Override
    public int getChanceGeneratinWaste() {
        return CHANCE_GENERATNG_WASTE_SWORDSMAN;
    }

    @Override
    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_SWORDSMAN;
    }

    @Override
    public void resetArmor() {
        super.armor = super.initialArmor;
    }

    @Override
    public void setExperience(int n) {
        super.experience = n;
    }

    @Override
    public int getExperience() {
        return super.experience;
    }

    @Override
    public String getName() {
        return "Swordsman";
    }
}
