package main;

import java.util.ArrayList;
import gui.dc_gui;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import classes.Civilization;
import classes.dc_classes;
import classes.attackunits.AttackUnit;
import classes.attackunits.Cannon;
import classes.attackunits.CrossBow;
import classes.attackunits.Spearman;
import classes.attackunits.Swordsman;
import exceptions.MiSQLException;
import exceptions.ResourceException;
import interfaces.GameGuiListener;
import interfaces.MilitaryUnit;
import interfaces.Variables;
import utils.Battle;
import utils.dc_database;

public class Main {
	private int countFleet = 0;
    private dc_gui dc_gui;
    private dc_classes classes;
	private dc_database database;

	public static void main(String[] args) {		
        Main m = new Main();

        
		//Battle b = new Battle();
        
        //instanciar controladores de dominio
        
        
        dc_database database = new dc_database();
        m.database = new dc_database();
        


        
        dc_classes classes = new dc_classes();
        m.dc_gui = new dc_gui();
        m.dc_gui.invoke_main_menu();
        m.dc_gui.setGgl(new GameGuiListener() {
			
			//metodo para cargar juego
			public void load_game_gui() {
				
				//actualizamos los datos en dc_gui, que lo pasara a game gui
				this.update_resources();
				
				m.dc_gui.load_game();
				
			}

			public void update_resources() {
				
				
				//aqui obtendremos los datos de la partida de la bd
				
				int iron = 15000;
				int wood = 15000;
				int food = 15000;
				int mana = 15000;

				m.dc_gui.setFood(food);
				m.dc_gui.setWood(wood);
				m.dc_gui.setIron(iron);
				m.dc_gui.setMana(mana);


			}
			public void update_resources_db(int food,int wood, int iron, int mana) {
				System.out.println(food);
				System.out.println(wood);
				System.out.println(iron);
				System.out.println(mana);

				System.out.println("Resources updated");
			}

			@Override
			public void update_resources(int food, int wood, int iron, int mana) {
				
			}

			@Override
			public void update_army_db(int tipo_tropa) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void update_structures_db(String structuretype,int number_structures) {
				
				if (structuretype == "Church") {
					classes.getCv().setChurch(classes.getCv().getChurch()+1);

				}else if (structuretype == "Magic Tower") {
					classes.getCv().setMagicTower(classes.getCv().getMagicTower()+1);

				}else if (structuretype == "Carpentry") {
					classes.getCv().setCarpentry(classes.getCv().getCarpentry()+1);
				}else if (structuretype == "Smithy") {
					classes.getCv().setSmithy(classes.getCv().getSmithy()+1);
				}else if (structuretype == "Farm") {
					classes.getCv().setFarm(classes.getCv().getFarm()+1);
				}
				System.out.println("Estructura creada!");
				}
			

			@Override
			public void update_technologies_db(int attack_technology, int defense_technology) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
        
        
        
        
		
//		//TIMER TASK:
//
//		ConnectionDB cbd = new ConnectionDB(url, user, pass);
		
		classes.getCv().setWood(1000000000);
		classes.getCv().setFood(1000000000);
		classes.getCv().setIron(1000000000);
//		
//		try {
//			cbd.sacarBattleLog(1,2);
//			
//		} catch (MiSQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
//		TimerTask task = new TimerTask() {
//
//			public void run() {
//				cv.setWood(cv.getWood() + Variables.CIVILIZATION_WOOD_GENERATED);
//				cv.setFood(cv.getFood() + Variables.CIVILIZATION_FOOD_GENERATED);
//				cv.setIron(cv.getIron() + Variables.CIVILIZATION_IRON_GENERATED);
//				
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
//		timer.schedule(task, 1, 10000);
//		
//		
//		b.mainBattle(cv.getArmy(), enemy);

//		System.out.println("\n\n" + b.getBattleDevelopment());
		
		
		
		
		
		
//		//TIMER TASK:
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
