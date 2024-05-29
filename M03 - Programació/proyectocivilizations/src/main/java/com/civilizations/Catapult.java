package com.civilizations;

public class Catapult extends DefenseUnit {
    
    private int initialArmor;

    public Catapult(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_CATAPULT;
        }
        else {
            armor = ARMOR_CATAPULT + (civilization.getTechnologyDefense() * PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_CATAPULT;
        }
        else{
            baseDamage = BASE_DAMAGE_CATAPULT + (civilization.getTechnologyAttack() * PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_CATAPULT;
    }
    
    public Catapult() {
        super(ARMOR_CATAPULT, BASE_DAMAGE_CATAPULT);
        this.initialArmor = ARMOR_CATAPULT;
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
        return FOOD_COST_CATAPULT;
    }

    public int getWoodCost(){
        return WOOD_COST_CATAPULT;
    }

    public int getIronCost(){
        return IRON_COST_CATAPULT;
    }

    public int getManaCost(){
        return MANA_COST_CATAPULT;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_CATAPULT;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_CATAPULT;
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