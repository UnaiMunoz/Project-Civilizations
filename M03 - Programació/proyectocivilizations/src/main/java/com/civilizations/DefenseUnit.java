package com.civilizations;

public class DefenseUnit implements MilitaryUnit{
    int armor;
    int initialArmor;
    int baseDamage;
    int experience;
    boolean sanctified;

    public int getArmor() {
        return this.armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getInitialArmor() {
        return this.initialArmor;
    }

    public void setInitialArmor(int initialArmor) {
        this.initialArmor = initialArmor;
    }

    public int getBaseDamage() {
        return this.baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isSanctified() {
        return this.sanctified;
    }

    public void setSanctified(boolean sanctified) {
        this.sanctified = sanctified;
    }

    public DefenseUnit(int armor,int initialArmor,int baseDamage,int experience,boolean sanctified){
        this.armor = armor;
        this.initialArmor = initialArmor;
        this.baseDamage = baseDamage;
        this.experience = experience;
        this.sanctified = sanctified;        
    }

}

