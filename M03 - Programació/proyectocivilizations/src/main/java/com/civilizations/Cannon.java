package com.civilizations;

public class Cannon extends AttackUnit{
    private int initialArmor;
    public Cannon(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_CANNON;
        }
        else {
            armor = ARMOR_CANNON + (civilization.getTechnologyDefense() * PLUS_ARMOR_CANNON_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_CANNON;
        }
        else{
            baseDamage = BASE_DAMAGE_CANNON + (civilization.getTechnologyAttack() * PLUS_ATTACK_CANNON_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_CANNON;
    }
    public Cannon() {
        super(ARMOR_CANNON, BASE_DAMAGE_CANNON);
        this.initialArmor = ARMOR_CANNON;
    }
}