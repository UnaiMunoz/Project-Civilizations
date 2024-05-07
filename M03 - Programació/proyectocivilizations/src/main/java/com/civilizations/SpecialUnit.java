package com.civilizations;

public class SpecialUnit {

    int armor;
    int initialArmor;
    int baseDamage;
    int experience;

    public SpecialUnit(int armor, int initialArmor, int baseDamage, int experience) {
        
        this.armor = armor;
        this.initialArmor = initialArmor;
        this.baseDamage = baseDamage;
        this.experience = experience;

    }

    public int getArmor() {
        return armor;
    }

    public int getInitialArmor() {
        return initialArmor;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getExperience() {
        return experience;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setInitialArmor(int initialArmor) {
        this.initialArmor = initialArmor;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    
}
