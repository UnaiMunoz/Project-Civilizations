package com.civilizations;

public class Swordsman extends AttackUnit{
    private int initialArmor;
    public Swordsman(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_SWORDSMAN;
        }
        else {
            armor = ARMOR_SWORDSMAN + (civilization.getTechnologyDefense() * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_SWORDSMAN;
        }
        else{
            baseDamage = BASE_DAMAGE_SWORDSMAN + (civilization.getTechnologyAttack() * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_SWORDSMAN;
    }
    public Swordsman() {
        super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
        this.initialArmor = ARMOR_SWORDSMAN;
    }
    public int attack(){
        return super.baseDamage;
    }

    public void takeDamage(int receivedDamage){
        super.armor -= receivedDamage;
    }

    public int getActualArmor(){
        return super.armor;
    }

    public int getFoodCost(){
        return FOOD_COST_SWORDSMAN;
    }

    public int getWoodCost(){
        return WOOD_COST_SWORDSMAN;
    }

    public int getIronCost(){
        return IRON_COST_SWORDSMAN;
    }

    public int getManaCost(){
        return MANA_COST_SWORDSMAN;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_SWORDSMAN;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_SWORDSMAN;
    }

    public void resetArmor(){
        super.armor = super.initialArmor;
    }

    public void setExperience(int n){
        super.experience = n;
    }

    public int getExperience(){
        return super.experience;
    }
}