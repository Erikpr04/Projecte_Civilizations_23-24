package main;

import java.util.ArrayList;
import java.util.Random;

import classes.Civilization;
import classes.attackunits.Cannon;
import classes.attackunits.CrossBow;
import classes.attackunits.Spearman;
import classes.attackunits.Swordsman;
import interfaces.Variables;

public class Main {
	private int countFleet = 0;

	public static void main(String[] args) {
		Main m = new Main();
		ArrayList<ArrayList> enemigos = m.createEnemyArmy();
		
		m.viewThread(enemigos);
		

	}
	
	public ArrayList<ArrayList> createEnemyArmy() {
		int enemyWood;
		int enemyFood;
		int enemyIron;
		
		if (countFleet == 0) {
			enemyWood = Variables.WOOD_BASE_ENEMY_ARMY;
			enemyFood = Variables.FOOD_BASE_ENEMY_ARMY;
			enemyIron = Variables.IRON_BASE_ENEMY_ARMY;
		
		} else {
			enemyWood = Variables.WOOD_BASE_ENEMY_ARMY + ((countFleet * Variables.ENEMY_FLEET_INCREASE)*Variables.WOOD_BASE_ENEMY_ARMY/100);
			enemyFood = Variables.FOOD_BASE_ENEMY_ARMY + ((countFleet * Variables.ENEMY_FLEET_INCREASE)*Variables.FOOD_BASE_ENEMY_ARMY/100);
			enemyIron = Variables.IRON_BASE_ENEMY_ARMY + ((countFleet * Variables.ENEMY_FLEET_INCREASE)*Variables.IRON_BASE_ENEMY_ARMY/100);;
		}
		
		
		
		ArrayList<ArrayList> enemy = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
            enemy.add(new ArrayList<ArrayList>());
        }
		
		while (enemyWood >= Variables.WOOD_COST_SWORDSMAN && enemyFood >= Variables.FOOD_COST_SWORDSMAN && enemyIron >= Variables.IRON_COST_SWORDSMAN) {
			Random newUnit = new Random();
			int randomNumber = newUnit.nextInt(100) + 1;
			
			if (randomNumber <= 35) {
				//Se crea Swordsman
				enemyWood -= Variables.WOOD_COST_SWORDSMAN;
				enemyFood -= Variables.FOOD_COST_SWORDSMAN;
				enemyIron -= Variables.IRON_COST_SWORDSMAN;
				
				enemy.get(0).add(new Swordsman());
			
			} else if (randomNumber > 35 && randomNumber <= 60) {
				//Se crea Spearman
				
				if (enemyWood >= Variables.WOOD_COST_SPEARMAN && enemyFood >= Variables.FOOD_COST_SPEARMAN && enemyIron >= Variables.IRON_COST_SPEARMAN) {
					
					enemyWood -= Variables.WOOD_COST_SPEARMAN;
					enemyFood -= Variables.FOOD_COST_SPEARMAN;
					enemyIron -= Variables.IRON_COST_SPEARMAN;
					
					enemy.get(1).add(new Spearman());
				}
			
			} else if (randomNumber > 60 && randomNumber <= 80) {
				//Se crea Crossbow
				
				if (enemyWood >= Variables.WOOD_COST_CROSSBOW && enemyFood >= Variables.FOOD_COST_CROSSBOW && enemyIron >= Variables.IRON_COST_CROSSBOW) {
					
					enemyWood -= Variables.WOOD_COST_CROSSBOW;
					enemyFood -= Variables.FOOD_COST_CROSSBOW;
					enemyIron -= Variables.IRON_COST_CROSSBOW;
					
					enemy.get(2).add(new CrossBow());
				}
			
			} else if (randomNumber > 80 && randomNumber <= 100) {
				//Se crea Cannon
				
				if (enemyWood >= Variables.WOOD_COST_CANNON && enemyFood >= Variables.FOOD_COST_CANNON && enemyIron >= Variables.IRON_COST_CANNON) {
					
					enemyWood -= Variables.WOOD_COST_CANNON;
					enemyFood -= Variables.FOOD_COST_CANNON;
					enemyIron -= Variables.IRON_COST_CANNON;
					
					enemy.get(3).add(new Cannon());
				}
				
			}
		}
		
		countFleet += 1;
		return enemy;
	}
	
	
	public void viewThread(ArrayList<ArrayList> enemigos) {
		System.out.println("Swordsman: " + enemigos.get(0).size());
		System.out.println("Spearman: " + enemigos.get(1).size());
		System.out.println("Crossbow: " + enemigos.get(2).size());
		System.out.println("Cannon: " + enemigos.get(3).size());
	}

}
