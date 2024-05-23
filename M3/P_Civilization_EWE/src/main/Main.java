package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gui.Game_gui.MiPanelito;
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
import classes.defenseunits.ArrowTower;
import classes.defenseunits.Catapult;
import classes.defenseunits.RocketLauncherTower;
import classes.specialunits.Magician;
import classes.specialunits.Priest;
import exceptions.BuildingException;
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
    private dc_classes classes =  new dc_classes();
	private boolean shownotification;
    private dc_database database = new dc_database();
    private ArrayList<ArrayList> enemy_army;
    private Battle b;

	
    
    //metodo para cargar datos de clase cv a gui
	
	public void load_data_class_gui() {


		try {
			dc_gui.getGui_obj().update_resources_quantity(classes.getCv().getIron(),"iron");
			dc_gui.getGui_obj().update_resources_quantity(classes.getCv().getWood(),"wood");
			dc_gui.getGui_obj().update_resources_quantity(classes.getCv().getFood(),"food");
			dc_gui.getGui_obj().update_resources_quantity(classes.getCv().getMana(),"mana");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
	public String viewThread(ArrayList<ArrayList> enemigos) {
	    String threatString = "";
	    threatString += "NEW threat coming soon, prepare yourself!\n";
	    threatString += String.format("%-12s %7d\n", "Swordsman:", enemigos.get(0).size());
	    threatString += String.format("%-12s %9d\n", "Spearman:", enemigos.get(1).size());
	    threatString += String.format("%-13s %7d\n", "Crossbow:", enemigos.get(2).size());
	    threatString += String.format("%-12s %11d\n", "Cannon:", enemigos.get(3).size());
	    return threatString;
	}



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	


	public static void main(String[] args) {		
        Main m = new Main();
        dc_database database = new dc_database();
        m.database = new dc_database();
        


        
        m.dc_gui = new dc_gui(m.classes.getCv());
        m.dc_gui.invoke_main_menu();
                
        
        
        
        
        
		//TIMER TASK:

		
		TimerTask resourcestask = new TimerTask() {

			public void run() {
				try {
									
				

				System.out.println("carpentries " +m.classes.getCv().getCarpentry());
				System.out.println("smithy " +m.classes.getCv().getSmithy());
				m.classes.getCv().setWood(m.classes.getCv().getWood()+100000+ Variables.CIVILIZATION_WOOD_GENERATED+ (m.classes.getCv().getCarpentry() * Variables.CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY));
				m.classes.getCv().setFood(m.classes.getCv().getFood() + Variables.CIVILIZATION_FOOD_GENERATED+(m.classes.getCv().getFarm() * Variables.CIVILIZATION_FOOD_GENERATED_PER_FARM));
				m.classes.getCv().setIron(m.classes.getCv().getIron() + Variables.CIVILIZATION_IRON_GENERATED+(m.classes.getCv().getSmithy() * Variables.CIVILIZATION_IRON_GENERATED_PER_SMITHY));
				m.classes.getCv().setMana(m.classes.getCv().getMana() +100000+ (m.classes.getCv().getMagicTower() * Variables.CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER));
				
				
				
				System.out.println("Madera: " + m.classes.getCv().getWood());
				System.out.println("Comida: " + m.classes.getCv().getFood());
				System.out.println("Hierro: " + m.classes.getCv().getIron());
				System.out.println("Mana: " + m.classes.getCv().getMana());
				System.out.println("*********************");
	            //m.dc_gui.getGui_obj().showCustomPanel(m.dc_gui.getGui_obj().getMainpanel());
	            
	    		System.out.println("iron actual" + m.classes.getCv().getIron());
	    		System.out.println(m.classes.getCv().getSmithy());

	    		
	    		
	    		//CARGAMOS RECURSOS EN BBDD
	    		
	    		m.load_data_class_gui();
	    		
	    		




				
				

				}catch (Exception e){
					e.printStackTrace();
					
				}
				
			}
			
		};
		
        m.b = new Battle();

		
		
		TimerTask shownotificationtask = new TimerTask() {

			public void run() {
				try {

									
					//en esta tarea mostraremos la notificacion que avisara que viene un ejercito
					m.enemy_army = m.createEnemyArmy();
					
					m.dc_gui.getGui_obj().showCustomPanel(m.dc_gui.getGui_obj().getMainpanel(),m.viewThread(m.enemy_army));

			        

				

				}catch (Exception e){
					e.printStackTrace();
					
				}
				
			}
			
		};
		
		
		TimerTask startbattle = new TimerTask() {

			public void run() {
				try {
									
					//en esta tarea mostraremos la notificacion que avisara que viene un ejercito
					System.out.println("hola");

					m.b.mainBattle(m.classes.getCv().getArmy(), m.enemy_army);
					System.out.println(m.b.getBattleDevelopment());

					
					m.dc_gui.getGui_obj().showBattleWindow(m.b.getBattleDevelopment());
					
					
					

				
				

				}catch (Exception e){
					e.printStackTrace();
					
				}
				
			}
			
		};
		
		
		
		
		
		//METODOS 
		

		


		
		
		

		
		Timer maintimer = new Timer();
		

        

        
		//Battle b = new Battle();
        
        //instanciar controladores de dominio
        
        

        m.dc_gui.setGgl(new GameGuiListener() {
			
			//metodo para cargar juego
			public void load_game_gui() {
				
				maintimer.schedule(resourcestask, 10000, 10000);
				maintimer.schedule(shownotificationtask, 20000, 1200000);
				maintimer.schedule(startbattle, 25000, 1200000);
				
				
			}

			public void update_resources() {
				
							

				m.dc_gui.setFood(m.classes.getCv().getFood());
				m.dc_gui.setWood(m.classes.getCv().getWood());
				m.dc_gui.setIron(m.classes.getCv().getIron());
				m.dc_gui.setMana(m.classes.getCv().getMana());
				

			}
			public void update_resources_db(int food,int wood, int iron, int mana) {
				m.classes.getCv().setFood(food);
				m.classes.getCv().setWood(wood);
				m.classes.getCv().setIron(iron);
				m.classes.getCv().setMana(mana);

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
					m.classes.getCv().setChurch(m.classes.getCv().getChurch()+1);

				}else if (structuretype == "Magic Tower") {
					m.classes.getCv().setMagicTower(m.classes.getCv().getMagicTower()+1);

				}else if (structuretype == "Carpentry") {
					m.classes.getCv().setCarpentry(m.classes.getCv().getCarpentry()+1);
				}else if (structuretype == "Smithy") {
					m.classes.getCv().setSmithy(m.classes.getCv().getSmithy()+1);
				}else if (structuretype == "Farm") {
					m.classes.getCv().setFarm(m.classes.getCv().getFarm()+1);
				}
				System.out.println("Estructura creada!");
				}
			

			@Override
			public void update_technologies_db(int attack_technology, int defense_technology) {
				// TODO Auto-generated method stub
				
			}

			public int[] getcv_army_values() {
			    int[] cv_array = new int[11]; // Declaración y creación del array

			    cv_array[0] = m.classes.getCv().getArmy().get(0).size();
			    // Repite para las demás posiciones
			    cv_array[1] = m.classes.getCv().getArmy().get(1).size();
			    cv_array[2] = m.classes.getCv().getArmy().get(2).size();
			    cv_array[3] = m.classes.getCv().getArmy().get(3).size();
			    cv_array[4] = m.classes.getCv().getArmy().get(4).size();
			    cv_array[5] = m.classes.getCv().getArmy().get(5).size();
			    cv_array[6] = m.classes.getCv().getArmy().get(6).size();
			    cv_array[7] = m.classes.getCv().getArmy().get(7).size();
			    cv_array[8] = m.classes.getCv().getArmy().get(8).size();
			    cv_array[9] = m.classes.getCv().getTechnologyAttack();
			    cv_array[10] = m.classes.getCv().getTechnologyDefense();

				
				return cv_array;
			}

			// Método para establecer el ejército de la civilización
			public void create_troop(int soldierTypeIndex, int numSoldiers) {
			    if (soldierTypeIndex < 0 || soldierTypeIndex >= m.classes.getCv().getArmy().size()) {
			        // Si el índice del tipo de soldado está fuera de los límites del ArrayList, mostrar un mensaje de error
			        System.out.println("Error: Tipo de soldado inválido.");
			        return;
			    }

			   

			    // Crear los soldados del tipo especificado
	                try {
			        switch (soldierTypeIndex) {
		            case 0:
						m.classes.getCv().new_Swordsman(numSoldiers);
						System.out.println("soldados:");
						System.out.println((m.classes.getCv().getArmy().get(0).size()));
						break;
		            case 1:
		                m.classes.getCv().new_Spearman(numSoldiers);
		                break;
		            case 2:
		                m.classes.getCv().new_Crossbow(numSoldiers);
		                break;
		            case 3:
		                m.classes.getCv().new_Cannon(numSoldiers);
		                break;
		            case 4:
		                m.classes.getCv().new_ArrowTower(numSoldiers);
		                break;
		            case 5:
		                m.classes.getCv().new_Catapult(numSoldiers);
		                break;
		            case 6:
		                m.classes.getCv().new_RocketLauncher(numSoldiers);
		                break;
		            case 7:
		                m.classes.getCv().new_Magician(numSoldiers);
		                break;
		            case 8:
		                m.classes.getCv().new_Priest(numSoldiers);
		                break;
		            default:
		                // Manejo de caso no válido
		                System.out.println("Tipo de soldado no válido: ");
		                break;
		        }
		    }catch (ResourceException | BuildingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			    }


			@Override
			public void create_farm() {
				try {
					m.classes.getCv().new_Farm();
				} catch (ResourceException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void create_church() {
				try {
					m.classes.getCv().new_Church();
				} catch (ResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public void create_carpentry() {
				try {
					m.classes.getCv().new_Carpentry();
				} catch (ResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public void create_smithy() {
				try {
					m.classes.getCv().new_Smithy();
				} catch (ResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}

			@Override
			public void create_magic_tower() {
				try {
					m.classes.getCv().new_MagicTower();
				} catch (ResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public boolean sanctifyunits() {
			    int totalUnits = 0;
			    ArrayList<ArrayList> armies = m.classes.getCv().getArmy();
			    
			    // Iterar sobre cada array en el ArrayList, excluyendo el último
			    for (int i = 0; i < armies.size() - 1; i++) {
			        totalUnits += armies.get(i).size(); // Sumar el tamaño de cada array
			    }
			    System.out.println("terminado de iterar" + totalUnits);
				
				if (totalUnits >= 1) {
					m.classes.getCv().sanctifyUnits();
					return true;
				}
				else {
					return false;
				}
				
			}

			@Override
			public void load_db_data() {
				m.update_panels();
				//cargar datos de recursos de la bbdd en clases
				
				//ahora cargamos de clases a gui
				m.load_data_class_gui();
				
			}


});
        
        
        
        
        
        
		
//		//TIMER TASK:
//
		//ConnectionDB cbd = new ConnectionDB(url, user, pass);
		
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

//		System.out.println("\n\n" + b.getBattleDevelopment());
		
		
		
		
		
	
		
		

	}
	
	

	

    public void update_panels() {
        MiPanelito[][] subPanels = dc_gui.getGui_obj().getSubPanels(); // Obtener la matriz de subpaneles
        dc_database database = new dc_database();
        ResultSet resultSet = database.getOccupiedPanels();
        String[] structureList = new String[]{"farm", "smithy", "church", "magic_tower", "carpentry"};

        if (resultSet != null) {
            try {
                // Iterar sobre los resultados
                while (resultSet.next()) {
                    // Obtener datos de la base de datos
                    String structureType = resultSet.getString("structure_type");
                    boolean isOccupied = resultSet.getInt("is_occupied") == 1; // Convertir el entero a booleano
                    int xPosition = resultSet.getInt("x_position");
                    int yPosition = resultSet.getInt("y_position");

                    // Buscar la posición en structureList
                    int position = -1; // Inicializamos en -1 para indicar que no se encontró el elemento
                    for (int i = 0; i < structureList.length; i++) {
                        if (structureList[i].equals(structureType)) {
                            position = i;
                            break;
                        }
                    }

                    if (position != -1 && isOccupied) {
                        subPanels[yPosition][xPosition].setCurrentImage(subPanels[yPosition][xPosition].getBuildingImages()[position]);
                        subPanels[yPosition][xPosition].setIsoccupied(isOccupied);

                        switch (position) {
                            case 0:
                                dc_gui.getGui_obj().getListener().create_farm();
                                break;
                            case 1:
                                dc_gui.getGui_obj().getListener().create_smithy();
                                break;
                            case 2:
                                dc_gui.getGui_obj().getListener().create_church();
                                break;
                            case 3:
                                dc_gui.getGui_obj().getListener().create_magic_tower();
                                break;
                            case 4:
                                dc_gui.getGui_obj().getListener().create_carpentry();
                                break;
                            default:
                                break;
                        }
                    } else {
                        System.out.println("Structure isn't occupied");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Cerrar el ResultSet y liberar recursos
                try {
                    if (resultSet != null) resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
}
