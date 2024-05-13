package com.civilizations;

public class RocketLauncherTower extends DefenseUnit{
    private int initialArmor;
    public RocketLauncherTower(int armor, int baseDamage, Civilization civilization) {
        super(armor, baseDamage);
        if (civilization.getTechnologyDefense() <= 1) {
            armor = ARMOR_ROCKETLAUNCHERTOWER;
        }
        else {
            armor = ARMOR_ROCKETLAUNCHERTOWER + (civilization.getTechnologyDefense() * PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY) * 10;
        }
        if (civilization.getTechnologyAttack() <= 1) {
            baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER;
        }
        else{
            baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (civilization.getTechnologyAttack() * PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY) * 10;
        }
        initialArmor = ARMOR_ROCKETLAUNCHERTOWER;
    }
    public RocketLauncherTower() {
        super(ARMOR_ROCKETLAUNCHERTOWER, BASE_DAMAGE_ROCKETLAUNCHERTOWER);
        this.initialArmor = ARMOR_ROCKETLAUNCHERTOWER;
    }
}