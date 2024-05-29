package com.civilizations;

public class Crossbow extends AttackUnit{
    
    private int initialArmor;

    public Crossbow(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_CROSSBOW;
        }
        else {
            armor = ARMOR_CROSSBOW + (civilization.getTechnologyDefense() * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_CROSSBOW;
        }
        else{
            baseDamage = BASE_DAMAGE_CROSSBOW + (civilization.getTechnologyAttack() * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_CROSSBOW;
    }
    
    public Crossbow() {
        super(ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW);
        this.initialArmor = ARMOR_CROSSBOW;
    }
    
    public void takeDamage(int receivedDamage){
        super.armor -= receivedDamage;
    }

    public int getActualArmor(){
        return super.armor;
    }

    public int getFoodCost(){
        return FOOD_COST_CROSSBOW;
    }

    public int getWoodCost(){
        return WOOD_COST_CROSSBOW;
    }

    public int getIronCost(){
        return IRON_COST_CROSSBOW;
    }

    public int getManaCost(){
        return MANA_COST_CROSSBOW;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_CROSSBOW;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_CROSSBOW;
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