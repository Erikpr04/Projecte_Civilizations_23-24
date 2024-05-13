package main;

import java.util.ArrayList;
import java.util.Random;

import classes.Civilization;
import classes.attackunits.AttackUnit;
import classes.attackunits.Cannon;
import classes.attackunits.CrossBow;
import classes.attackunits.Spearman;
import classes.attackunits.Swordsman;
import exceptions.ResourceException;
import interfaces.MilitaryUnit;
import interfaces.Variables;

public class Main {
	private int countFleet = 0;

	public static void main(String[] args) {
		
		Civilization cv = new Civilization();
		
		cv.setWood(100000);
		cv.setFood(100000);
		cv.setIron(100000);
		
		try {
			cv.new_Swordsman(1);
			cv.new_Crossbow(2);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cv.getArmy());
		
		if (!cv.getArmy().get(2).isEmpty()) {
			System.out.println("Hola");
		}
		
		MilitaryUnit swordman = (MilitaryUnit) cv.getArmy().get(0).get(0);
		
		System.out.println("Attack: " + swordman.attack() + ", Armor: " + swordman.getActualArmor());
		
		cv.sanctifyUnits();
		
		System.out.println("Attack: " + swordman.attack() + ", Armor: " + swordman.getActualArmor());
		

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
		System.out.println("NEW threat COMMING\n");
		System.out.printf("%-15s %d\n", "Swordsman", enemigos.get(0).size());
        System.out.printf("%-15s %d\n", "Spearman", enemigos.get(1).size());
        System.out.printf("%-15s %d\n", "Crossbow", enemigos.get(2).size());
        System.out.printf("%-15s %d\n", "Cannon", enemigos.get(3).size());
	}

}
