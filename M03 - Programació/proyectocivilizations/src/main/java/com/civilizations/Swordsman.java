package com.civilizations;

public class Swordsman extends AttackUnit{
    private int initialArmor;
    public Swordsman(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_SWORDSMAN;
        }
        else {
            armor = ARMOR_SWORDSMAN + (civilization.getTechnologyDefense() * PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_SWORDSMAN;
        }
        else{
            baseDamage = BASE_DAMAGE_SWORDSMAN + (civilization.getTechnologyAttack() * PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_SWORDSMAN;
    }
    public Swordsman() {
        super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
        this.initialArmor = ARMOR_SWORDSMAN;
    }
}