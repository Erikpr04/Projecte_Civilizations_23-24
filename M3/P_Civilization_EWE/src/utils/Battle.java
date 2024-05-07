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
	
	
	//GETTERS Y SETTERS
	
	public ArrayList<MilitaryUnit> getCivilizationArmy() {
		return civilizationArmy;
	}
	public void setCivilizationArmy(ArrayList<MilitaryUnit> civilizationArmy) {
		this.civilizationArmy = civilizationArmy;
	}
	public ArrayList<MilitaryUnit> getEnemyArmy() {
		return enemyArmy;
	}
	public void setEnemyArmy(ArrayList<MilitaryUnit> enemyArmy) {
		this.enemyArmy = enemyArmy;
	}
	public ArrayList<ArrayList> getArmies() {
		return armies;
	}
	public void setArmies(ArrayList<ArrayList> armies) {
		this.armies = armies;
	}
	public String getBattleDevelopment() {
		return battleDevelopment;
	}
	public void setBattleDevelopment(String battleDevelopment) {
		this.battleDevelopment = battleDevelopment;
	}
	public int[][] getInitialCostFleet() {
		return initialCostFleet;
	}
	public void setInitialCostFleet(int[][] initialCostFleet) {
		this.initialCostFleet = initialCostFleet;
	}
	public int getInitialNumberUnitsCivilization() {
		return initialNumberUnitsCivilization;
	}
	public void setInitialNumberUnitsCivilization(int initialNumberUnitsCivilization) {
		this.initialNumberUnitsCivilization = initialNumberUnitsCivilization;
	}
	public int getInitialNumberUnitsEnemy() {
		return initialNumberUnitsEnemy;
	}
	public void setInitialNumberUnitsEnemy(int initialNumberUnitsEnemy) {
		this.initialNumberUnitsEnemy = initialNumberUnitsEnemy;
	}
	public int[] getWasteWoodIron() {
		return wasteWoodIron;
	}
	public void setWasteWoodIron(int[] wasteWoodIron) {
		this.wasteWoodIron = wasteWoodIron;
	}
	public int getEnemyDrops() {
		return enemyDrops;
	}
	public void setEnemyDrops(int enemyDrops) {
		this.enemyDrops = enemyDrops;
	}
	public int getCivilizationDrops() {
		return civilizationDrops;
	}
	public void setCivilizationDrops(int civilizationDrops) {
		this.civilizationDrops = civilizationDrops;
	}
	public int[][] getResourcesLooses() {
		return resourcesLooses;
	}
	public void setResourcesLooses(int[][] resourcesLooses) {
		this.resourcesLooses = resourcesLooses;
	}
	public int[][] getInitialArmies() {
		return initialArmies;
	}
	public void setInitialArmies(int[][] initialArmies) {
		this.initialArmies = initialArmies;
	}
	public int[] getActualNumberUnitsCivilization() {
		return actualNumberUnitsCivilization;
	}
	public void setActualNumberUnitsCivilization(int[] actualNumberUnitsCivilization) {
		this.actualNumberUnitsCivilization = actualNumberUnitsCivilization;
	}
	public int[] getActualNumberUnitsEnemy() {
		return actualNumberUnitsEnemy;
	}
	public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
		this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
	}
	
	
	
	//METODOS
	
	
	public String getBattleReport(int battles) {
		//Resumen de la batalla, battles es el numero de batallas que hemos acumulado
		return "";
	}
	
	public String BattleDevelopment1() {
		//Paso a paso de la batalla
		return "";
	}
	
	public void initInitialArmies() {
		//Inicializar el Array initialArmies y asi poder calcular reportes
		
	}
	
	public void updateResourcesLooses() {
		//Generar el Array de perdidas
		
	}
	
	public void fleetResourceCoust(ArrayList<MilitaryUnit> army) {
		//Calcular costes del ejercito
		
	}
	
	public void initialFleetNumber(ArrayList<MilitaryUnit> army) {
		//Calcular numero de unidades iniciales de cada ejercito
		
	}
	
	public int remainderPercentageFleet(ArrayList<MilitaryUnit> army) {
		//Calcular porcentajes de unidades que quedan respecto a los ejercitos iniciales
		return 0;
	}
	
	public int getGroupDefender(ArrayList<MilitaryUnit> army) {
		//Dado un ejercito nos devuelve el grupo defensor 0-3 enemy, 0-8 civilization
		return 0;
	}
	
	public int getCivilizationGroupAttacker() {
		//Escoger el grupo atacante de la civilizacion
		return 0;
	}
	
	public int getEnemyGroupAttacker() {
		//Escoger el grupo atacante del enemigo
		return 0;
	}
	
	public void resetArmyArmor() {
		//Resetear armadura del ejercito
		
	}
}
