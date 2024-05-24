package com.civilizations;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
    private ArrayList<MilitaryUnit> civilizationArmy = new ArrayList<>();
    private ArrayList<MilitaryUnit> enemyArmy = new ArrayList<>();
    private ArrayList<MilitaryUnit>[][] armies = new ArrayList[2][9];
    private int[][] initialCostFleet;    
    private int initialNumberUnitsCivilization; 
    private int initialNumberUnitsEnemy;
    private int[] wasteWoodIron = new int[2]; // [0]: madera, [1]: hierro
    private int enemyDrops;
    private int civilizationDrops;
    private int[][] resourcesLooses = new int[2][4]; // [0]: civilización, [1]: enemigo
    private int[][] initialArmies = new int[2][9];
    private int[] actualNumberUnitsCivilization = new int[9];
    private int[] actualNumberUnitsEnemy = new int[9];
    private StringBuilder battleDevelopment = new StringBuilder();

}