package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import classes.Civilization;
import classes.attackunits.AttackUnit;
import classes.attackunits.Cannon;
import classes.attackunits.CrossBow;
import classes.attackunits.Spearman;
import classes.attackunits.Swordsman;
import exceptions.ResourceException;
import interfaces.MilitaryUnit;
import interfaces.Variables;
import utils.Battle;

public class Main {
	private int countFleet = 0;

	public static void main(String[] args) {
		
		Main m = new Main();
		Battle b = new Battle();
		Civilization cv = new Civilization();
		
		//BATALLAS:
		
		cv.setWood(1000000000);
		cv.setFood(1000000000);
		cv.setIron(1000000000);
		
		try {
			cv.new_Swordsman(2);
			cv.new_Spearman(0);
//			cv.new_Crossbow(4);
//			cv.new_Cannon(1);
//			cv.new_Catapult(3);
			cv.new_ArrowTower(5);
			
			
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ArrayList<ArrayList> enemy = m.createEnemyArmy();
		
		m.viewThread(enemy);
		
		
		b.mainBattle(cv.getArmy(), enemy);

		System.out.println("\n\n" + b.getBattleDevelopment());
		
		System.out.println("\n\n\n" + b.getBattleReport(0));
		
		
		
		
		
		
		//TIMER TASK:
//		cv.setWood(Variables.CIVILIZATION_WOOD_GENERATED);
//		cv.setFood(Variables.CIVILIZATION_FOOD_GENERATED);
//		cv.setIron(Variables.CIVILIZATION_IRON_GENERATED);
//		
//		try {
//			cv.new_Carpentry();
////			cv.new_Farm();
////			cv.new_Smithy();
//		} catch (ResourceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		TimerTask task = new TimerTask() {
//
//			public void run() {
//				cv.setWood(cv.getWood() + (cv.getCarpentry() * Variables.CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY));
//				cv.setFood(cv.getFood() + (cv.getFarm() * Variables.CIVILIZATION_FOOD_GENERATED_PER_FARM));
//				cv.setIron(cv.getIron() + (cv.getSmithy() * Variables.CIVILIZATION_IRON_GENERATED_PER_SMITHY));
//				cv.setMana(cv.getMana() + (cv.getMagicTower() * Variables.CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER));
//				
//				System.out.println("Madera: " + cv.getWood());
//				System.out.println("Comida: " + cv.getFood());
//				System.out.println("Hierro: " + cv.getIron());
//				System.out.println("Mana: " + cv.getMana());
//				System.out.println("*********************");
//				
//			}
//			
//		};
//		
//		Timer timer = new Timer();
//		
//		timer.schedule(task, 1, 10000);
		

	}
	
	
	//METODO PARA CREAR ARMY ENEMIGAS
	public ArrayList<ArrayList> createEnemyArmy() {
		int enemyWood;
		int enemyFood;
		int enemyIron;
		enemyWood = Variables.WOOD_BASE_ENEMY_ARMY + ((countFleet * Variables.ENEMY_FLEET_INCREASE)*Variables.WOOD_BASE_ENEMY_ARMY/100);
		enemyFood = Variables.FOOD_BASE_ENEMY_ARMY + ((countFleet * Variables.ENEMY_FLEET_INCREASE)*Variables.FOOD_BASE_ENEMY_ARMY/100);
		enemyIron = Variables.IRON_BASE_ENEMY_ARMY + ((countFleet * Variables.ENEMY_FLEET_INCREASE)*Variables.IRON_BASE_ENEMY_ARMY/100);
		
		
		
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
	
	
	//PRINT DE LA ARMY ENEMIGA
	
	public void viewThread(ArrayList<ArrayList> enemigos) {
		System.out.println("NEW threat COMMING\n");
		System.out.printf("%-15s %d\n", "Swordsman", enemigos.get(0).size());
        System.out.printf("%-15s %d\n", "Spearman", enemigos.get(1).size());
        System.out.printf("%-15s %d\n", "Crossbow", enemigos.get(2).size());
        System.out.printf("%-15s %d\n", "Cannon", enemigos.get(3).size());
	}

}
