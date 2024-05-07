package utils;

import java.util.ArrayList;

import interfaces.MilitaryUnit;

public class Battle {
	private ArrayList<MilitaryUnit> civilizationArmy;
	private ArrayList<MilitaryUnit> enemyArmy;
	private ArrayList<ArrayList> armies = new ArrayList(2); //ArrayList de 2 filas y 9 columnas
	private String battleDevelopment;
	private int[][] initialCostFleet; //Array de 2 filas 
	private int initialNumberUnitsCivilization;
	private int initialNumberUnitsEnemy;
	private int[] wasteWoodIron; //Array [madera, iron]
	private int enemyDrops;
	private int civilizationDrops;
	private int[][] resourcesLooses; //Array de 2 filas y 4 columnas
	private int[][] initialArmies; //Array de 2 filas y 9 columnas
	private int[] actualNumberUnitsCivilization;
	private int[] actualNumberUnitsEnemy; //Arrays para contabilizar unidades
}
