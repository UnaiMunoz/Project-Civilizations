package com.civilizations;

public class ArrowTower extends DefenseUnit {
    private int initialArmor;
    public ArrowTower(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_ARROWTOWER;
        }
        else {
            armor = ARMOR_ARROWTOWER + (civilization.getTechnologyDefense() * PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_ARROWTOWER;
        }
        else{
            baseDamage = BASE_DAMAGE_ARROWTOWER + (civilization.getTechnologyAttack() * PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_ARROWTOWER;
    }
    public ArrowTower() {
        super(ARMOR_ARROWTOWER, BASE_DAMAGE_ARROWTOWER);
        this.initialArmor = ARMOR_ARROWTOWER;
    }
    public void takeDamage(int receivedDamage){
        super.armor -= receivedDamage;
    }

    public int getActualArmor(){
        return super.armor;
    }

    public int getFoodCost(){
        return FOOD_COST_ARROWTOWER;
    }

    public int getWoodCost(){
        return WOOD_COST_ARROWTOWER;
    }

    public int getIronCost(){
        return IRON_COST_ARROWTOWER;
    }

    public int getManaCost(){
        return MANA_COST_ARROWTOWER;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_ARROWTOWER;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_ARROWTOWER;
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

