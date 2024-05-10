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
}

