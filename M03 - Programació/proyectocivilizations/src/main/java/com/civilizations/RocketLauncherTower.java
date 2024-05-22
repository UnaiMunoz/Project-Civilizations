package com.civilizations;

public class RocketLauncherTower extends DefenseUnit{
    
    private int initialArmor;
    
    public RocketLauncherTower(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_ROCKETLAUNCHERTOWER;
        }
        else {
            armor = ARMOR_ROCKETLAUNCHERTOWER + (civilization.getTechnologyDefense() * PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER;
        }
        else{
            baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (civilization.getTechnologyAttack() * PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_ROCKETLAUNCHERTOWER;
    }
    
    public RocketLauncherTower() {
        super(ARMOR_ROCKETLAUNCHERTOWER, BASE_DAMAGE_ROCKETLAUNCHERTOWER);
        this.initialArmor = ARMOR_ROCKETLAUNCHERTOWER;
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
        return FOOD_COST_ROCKETLAUNCHERTOWER;
    }

    public int getWoodCost(){
        return WOOD_COST_ROCKETLAUNCHERTOWER;
    }

    public int getIronCost(){
        return IRON_COST_ROCKETLAUNCHERTOWER;
    }

    public int getManaCost(){
        return MANA_COST_ROCKETLAUNCHERTOWER;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
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