package com.civilizations;

public class Spearman extends AttackUnit{
    
    private int initialArmor;
    
    public Spearman(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_SPEARMAN;
        }
        else {
            armor = ARMOR_SPEARMAN + (civilization.getTechnologyDefense() * PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_SPEARMAN;
        }
        else{
            baseDamage = BASE_DAMAGE_SPEARMAN + (civilization.getTechnologyAttack() * PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_SPEARMAN;
    }
    
    public Spearman() {
        super(ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN);
        this.initialArmor = ARMOR_SPEARMAN;
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
        return FOOD_COST_SPEARMAN;
    }

    public int getWoodCost(){
        return WOOD_COST_SPEARMAN;
    }

    public int getIronCost(){
        return IRON_COST_SPEARMAN;
    }

    public int getManaCost(){
        return MANA_COST_SPEARMAN;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_SPEARMAN;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_SPEARMAN;
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