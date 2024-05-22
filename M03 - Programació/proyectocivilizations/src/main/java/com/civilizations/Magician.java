package com.civilizations;

public class Magician extends SpecialUnit{
    
    public Magician() {
        super(0, BASE_DAMAGE_MAGICIAN);
    }

    public Magician(int armor, int baseDamage, Civilization civilization) {
        super(armor,baseDamage);
        armor = 0;
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_MAGICIAN;
        }
        else{
            baseDamage = BASE_DAMAGE_MAGICIAN + (civilization.getTechnologyAttack() * PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY) * 10;
        }
    }
}