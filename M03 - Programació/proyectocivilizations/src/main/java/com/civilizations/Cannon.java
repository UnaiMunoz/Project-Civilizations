package com.civilizations;

public class Cannon extends AttackUnit{

    private int initialArmor;

    public Cannon(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_CANNON;
        }
        else {
            armor = ARMOR_CANNON + (civilization.getTechnologyDefense() * PLUS_ARMOR_CANNON_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_CANNON;
        }
        else{
            baseDamage = BASE_DAMAGE_CANNON + (civilization.getTechnologyAttack() * PLUS_ATTACK_CANNON_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_CANNON;
    }
    
    public Cannon() {
        super(ARMOR_CANNON, BASE_DAMAGE_CANNON);
        this.initialArmor = ARMOR_CANNON;
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
        return FOOD_COST_CANNON;
    }

    public int getWoodCost(){
        return WOOD_COST_CANNON;
    }

    public int getIronCost(){
        return IRON_COST_CANNON;
    }

    public int getManaCost(){
        return MANA_COST_CANNON;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_CANNON;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_CANNON;
    }

    public void resetArmor(){
        super.armor = super.initialArmor;
    }

    public void setExperience(int n){
        super.experience = n;
    }

    public int getExperience(){
        return super.experience;
    };
}