package com.civilizations;

public class Priest extends SpecialUnit{

    public Priest(){
        super(0, 0);
    }

    public Priest(int armor, int baseDamage) {
        super(armor, baseDamage);
            armor = 0;
            baseDamage = 0;
    }

    public Priest(int id, int civilizationid, int armor, int baseDamage, int experience) {
        super(armor, baseDamage);
        armor = 0;
        baseDamage = 0;
        this.experience = experience;
    }

    public void takeDamage(int receivedDamage){
        super.armor -= receivedDamage;
    }

    public int getActualArmor(){
        return super.armor;
    }

    public int getFoodCost(){
        return FOOD_COST_PRIEST;
    }

    public int getWoodCost(){
        return WOOD_COST_PRIEST;
    }

    public int getIronCost(){
        return IRON_COST_PRIEST;
    }

    public int getManaCost(){
        return MANA_COST_PRIEST;
    }

    public int getChanceGeneratingWaste(){
        return CHANCE_GENERATNG_WASTE_PRIEST;
    }

    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_PRIEST;
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
    @Override
    public String getName() {
        return "Priest";
    }
}
