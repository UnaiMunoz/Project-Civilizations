package com.civilizations;

import java.util.ArrayList;

public class BattleMain {
    private ArrayList<MilitaryUnit>[] civilizationArmy;
    private ArrayList<MilitaryUnit>[] enemyArmy;

    public BattleMain(ArrayList<MilitaryUnit>[] civilizationArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;
    }

    public void startBattle() {
        // Crear una instancia de Battle usando los ejércitos proporcionados
        Battle battle = new Battle(civilizationArmy, enemyArmy);

        // Simular la batalla
        battle.simulateBattle();

        // Obtener y mostrar los reportes de la batalla
        String battleReport = battle.getBattleReport(1);
        String battleDevelopment = battle.getBattleDevelopment();

        System.out.println("Battle Development:");
        System.out.println(battleDevelopment);
        System.out.println("Battle Report:");
        System.out.println(battleReport);
    }
}
