package com.civilizations;

public class AttackUnit {

    int armor;
    int initialArmor;
    int baseDamage;
    int experience;
    boolean sanctified;

    public AttackUnit(int armor, int initialArmor, int baseDamage, int experience, boolean sanctified) {

        this.armor = armor;
        this.initialArmor = initialArmor;
        this.baseDamage = baseDamage;
        this.experience = experience;
        this.sanctified = sanctified;

    }

    public int getArmor() {
        return armor;
    }

    public int getBaseDamage() {
        return baseDamage;
    }


    public int getInitialArmor() {
        return initialArmor;
    }

    public int getExperience() {
        return experience;
    }

    public boolean getSanctified() {
        return sanctified;
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

    public void setSanctified(boolean sanctified) {
        this.sanctified = sanctified;
    }
    
}
