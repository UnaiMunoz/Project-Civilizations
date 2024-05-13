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
}