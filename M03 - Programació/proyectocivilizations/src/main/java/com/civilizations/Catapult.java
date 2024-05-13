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
}