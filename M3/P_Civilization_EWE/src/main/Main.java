package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import gui.Game_gui.MiPanelito;
import gui.dc_gui;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import classes.Civilization;
import classes.dc_classes;
import classes.attackunits.Cannon;
import classes.attackunits.CrossBow;
import classes.attackunits.Spearman;
import classes.attackunits.Swordsman;
import exceptions.BuildingException;
import exceptions.MiSQLException;
import exceptions.NoUnitsException;
import exceptions.ResourceException;
import interfaces.BattleListener;
import interfaces.GameGuiListener;
import interfaces.Variables;
import utils.Battle;
import utils.dc_database;

public class Main {
    private dc_gui dc_gui;
    private dc_classes classes =  new dc_classes();
	private boolean shownotification;
    private dc_database database = new dc_database();
    private ArrayList<ArrayList> enemy_army;
    private Battle b;
    private int countFleet = classes.getCv().getBattles();


	
    
    //metodo para cargar datos de clase cv a gui
    
    
    public void load_all_data() {
    	
    	try {
			update_panels();
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	dc_gui.getGui_obj().setFood(classes.getCv().getFood());
    	dc_gui.getGui_obj().setIron(classes.getCv().getIron());
    	dc_gui.getGui_obj().setMana(classes.getCv().getMana());
    	dc_gui.getGui_obj().setWood(classes.getCv().getWood());
    	
    	dc_gui.getGui_obj().setAttackint(classes.getCv().getTechnologyAttack());
    	dc_gui.getGui_obj().setDefenseint(classes.getCv().getTechnologyDefense());
    	
    	//ahora cargamos tropas
    	
    	dc_gui.getGui_obj().getcv_data();
    	
    	
    	
    	//cambiamos valores de los label de comida, que se actualizaran en tiempo real

    	
    	
		try {
			dc_gui.getGui_obj().update_resources_quantity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		
		//sacamos battle report
		
		try {
			dc_gui.getGui_obj().setBattleLogs(database.getConnectionDB().sacar5BattleLog());
			dc_gui.getGui_obj().setBattleReports(database.getConnectionDB().sacar5BattleReports());

		} catch (MiSQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//sacamos battle report
		

		
		
		
		//actualizamos valores de nombre de usuario y foto
		
		
		//COMPLETAR FUNCION DE AQUI ABAJO CON 
		
		//dc_gui.getGui_obj().getUsernameLabel().setText( AQUI USUARIO DE LA BBDD);;
		
				
		

    }
    
    
    
    
	//METODO PARA CREAR ARMY ENEMIGAS
	public ArrayList<ArrayList> createEnemyArmy() throws MiSQLException {
		countFleet = classes.getCv().getBattles();
		
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
									
				


				m.classes.getCv().setWood(m.classes.getCv().getWood()+100000+ Variables.CIVILIZATION_WOOD_GENERATED+ (m.classes.getCv().getCarpentry() * Variables.CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY));
				m.classes.getCv().setFood(m.classes.getCv().getFood() +100000+ Variables.CIVILIZATION_FOOD_GENERATED+(m.classes.getCv().getFarm() * Variables.CIVILIZATION_FOOD_GENERATED_PER_FARM));
				m.classes.getCv().setIron(m.classes.getCv().getIron() +100000+ Variables.CIVILIZATION_IRON_GENERATED+(m.classes.getCv().getSmithy() * Variables.CIVILIZATION_IRON_GENERATED_PER_SMITHY));
				m.classes.getCv().setMana(m.classes.getCv().getMana() +100000+ (m.classes.getCv().getMagicTower() * Variables.CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER));
				
				
				
				
				System.out.println("Madera: " + m.classes.getCv().getWood());
				System.out.println("Comida: " + m.classes.getCv().getFood());
				System.out.println("Hierro: " + m.classes.getCv().getIron());
				System.out.println("Mana: " + m.classes.getCv().getMana());
				System.out.println("*********************");
	            //m.dc_gui.getGui_obj().showCustomPanel(m.dc_gui.getGui_obj().getMainpanel());
	            
	    		System.out.println("iron actual" + m.classes.getCv().getIron());
	    		System.out.println(m.classes.getCv().getSmithy());

	    		
	    		
	    		//CARGAMOS RECURSOS EN GUI
	    		
	    		//m.load_data_class_gui();
	    		m.dc_gui.getGui_obj().setWood(m.classes.getCv().getWood());
	    		m.dc_gui.getGui_obj().setFood(m.classes.getCv().getFood());
	    		System.out.println(m.classes.getCv().getFood());
	    		System.out.println(m.classes.getCv().getWood());
	    		m.dc_gui.getGui_obj().setIron(m.classes.getCv().getIron());
	    		System.out.println(m.classes.getCv().getIron());
	    		m.dc_gui.getGui_obj().setMana(m.classes.getCv().getMana());
	    		System.out.println(m.classes.getCv().getMana());

	    		m.dc_gui.getGui_obj().setAttackint(m.classes.getCv().getTechnologyAttack());
	    		m.dc_gui.getGui_obj().setDefenseint(m.classes.getCv().getTechnologyDefense());
	    		System.out.println("--------");
	    		
	    		System.out.println(m.dc_gui.getGui_obj().getWood());
	    		m.dc_gui.getGui_obj().update_resources_quantity();	    		
	    		System.out.println("recursos actualizados");
	    		
	    		
	    		
				

				}catch (Exception e){
					e.printStackTrace();
					
				}
				
			}
			
		};
		
        m.b = new Battle();

		m.b.setBattlelistener(new BattleListener() {
			
		
			public Civilization getCV_Battle() {
				return m.classes.getCv();
			}

			public void updatecv_after_battle(int[] resources) {
				
				m.classes.getCv().setWood(m.classes.getCv().getWood() + resources[0]); 
				m.classes.getCv().setIron(m.classes.getCv().getIron() + resources[1]); 

				
			}
		});
        
		
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
					
					try {
						m.b.mainBattle(m.classes.getCv().getArmy(), m.enemy_army);

					} catch (NoUnitsException e) {
						System.out.println(e.getMessage());
					}

					System.out.println(m.b.getBattleDevelopment());

					
					m.dc_gui.getGui_obj().showBattleWindow(m.b.getBattleDevelopment());
					
					//database.getConnectionDB().actualizarDatosCivilization(m.classes.getCv());

				}catch (Exception e){
					e.printStackTrace();
					
				}
				
			}
			
		};
		
		
		//METODOS
		Timer maintimer = new Timer();
		
        //instanciar controladores de dominio
        
        m.dc_gui.setGgl(new GameGuiListener() {
			
			//metodo para cargar juego
			public void load_game_gui() {
				
				maintimer.schedule(resourcestask, 10000, 10000);
				maintimer.schedule(shownotificationtask, 20000, 120000);
				maintimer.schedule(startbattle, 25000, 120000);
								
			}

			public void update_resources_togui() {
				
				try {
					m.dc_gui.getGui_obj().update_resources_quantity();
				} catch (Exception e) {
					e.printStackTrace();
				}

			
			}
			

			public void update_resources_db() throws MiSQLException {
				
				//METODO PRINCIPAL PARA GUARDAR TODO EN BBDD
				
				database.getConnectionDB().actualizarDatosCivilization(m.classes.getCv());

				System.out.println("Resources updated db");
				System.out.println("ppindex" + m.dc_gui.getGui_obj().getPpindex());
				database.uploadPanels(m.dc_gui.getGui_obj().getSubPanels(),m.dc_gui.getGui_obj().getPpindex());
				
				System.out.println("Panels updated db");

				//NOTICAMOS DE TODO EN LA BBDD
				m.dc_gui.getGui_obj().loadingPanel();
				
				
			}


		
			public void update_army_db() throws MiSQLException {
				database.getConnectionDB().actualizarUnitsBD(m.classes.getCv().getArmy());
				
			}

			
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
				try {
					database.getConnectionDB().actualizarDatosCivilization(m.classes.getCv());
				} catch (MiSQLException e) {
					e.printStackTrace();
				}
				System.out.println("Estructura creada!");
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
			public void create_troop(int soldierTypeIndex, int numSoldiers) throws MiSQLException {
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


			public void create_farm() {
				try {
					m.classes.getCv().new_Farm();
				} catch (ResourceException e) {
					e.printStackTrace();
				}
			}

		
			public void create_church() {
				try {
					m.classes.getCv().new_Church();
				} catch (ResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

	
			public void create_carpentry() {
				try {
					m.classes.getCv().new_Carpentry();
				} catch (ResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		
			public void create_smithy( ) {
				try {
					m.classes.getCv().new_Smithy();
				} catch (ResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}

	
			public void create_magic_tower() {
				try {
					m.classes.getCv().new_MagicTower();
				} catch (ResourceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

	
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


			public void load_db_data() throws MiSQLException {
				

				
				//cargar datos de recursos de la bbdd en clases
				database.getConnectionDB().obtenerDatosCivilization(m.classes.getCv());
				m.classes.getCv().setArmy(database.getConnectionDB().cargarUnitsBD());
				
				System.out.println("Wood despues de cargar partida (Civilization)" + m.classes.getCv().getWood());
				System.out.println("Iron despues de cargar partida (Civilization)" + m.classes.getCv().getIron());

				//ahora cargamos todo de clases y paneles a gui
				
				m.load_all_data();
				
				
				
				
				
				
				
				
				
			}

			@Override
			public void update_technologies() {
				m.classes.getCv().setTechnologyDefense(m.dc_gui.getGui_obj().getDefenseint());

				
			}

			public int getcvchurch() {
				// TODO Auto-generated method stub
				return m.classes.getCv().getChurch();
			}

			public int getcvmagic_tower() {
				return m.classes.getCv().getMagicTower();
			}

			public void update_resources() {
				
			}

			
			public void clear_and_startdb() throws MiSQLException, ResourceException {
				database.getConnectionDB().eliminarCivilizacion(m.classes.getCv().getId());
				System.out.println("Base de datos borrada");
				
				database.getConnectionDB().crearDatosCivilization(m.dc_gui.getUsername());
				System.out.println("Base de datos creada");
				
		        m.dc_gui.getGui_obj().setPpindex(m.dc_gui.getProfileindex());

				
				//cargar paneles por defecto del juego
				m.update_panels();
			}

			@Override
			public void refresh_battle_logs_reports() {
				try {
					m.dc_gui.getGui_obj().setBattleLogs(database.getConnectionDB().sacar5BattleLog());
					m.dc_gui.getGui_obj().setBattleReports(database.getConnectionDB().sacar5BattleReports());

				} catch (MiSQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}


		});
	}
        
	

	

	public void update_panels() throws ResourceException {
	    dc_database database = new dc_database();
	    ResultSet resultSet = database.getOccupiedPanels();
	    MiPanelito[][] subPanels = dc_gui.getGui_obj().getSubPanels(); // Obtener la matriz de subpaneles
	    String[] structureList = new String[]{"Farm", "Smithy", "Church", "Magic_Tower", "Carpentry"};
	    boolean isOccupied;
	    int ppindex = 1;
	
	    if (resultSet != null) {
	        try {
	            while (resultSet.next()) {
	                String structureType = resultSet.getString("structure_type");
	                int isOccupiednumber = resultSet.getInt("is_occupied");
	                if (isOccupiednumber == 0) {
	                	isOccupied = false;
	                }else {
	                	isOccupied = true;
	                }
	                int xPosition = resultSet.getInt("x_position");
	                int yPosition = resultSet.getInt("y_position");
	                ppindex = resultSet.getInt("ppindex");
	                int position = -1;

	                for (int i = 0; i < structureList.length; i++) {
	                    if (structureList[i].equals(structureType)) {
	                        position = i;
	                        break;
	                    }
	                }
	                

	                if (position != -1 && isOccupied) {
	                    MiPanelito panel = subPanels[yPosition][xPosition];
	                    panel.setCurrentImage(panel.getBuildingImages()[position]);
	                    panel.setIsoccupied(isOccupied);
	                    panel.revalidate();
	                    panel.repaint();

	                    switch (position) {
	                        case 0:
	                            classes.getCv().new_Farm();
	                            break;
	                        case 1:
	                            classes.getCv().new_Smithy();
	                            break;
	                        case 2:
	                            classes.getCv().new_Church();
	                            break;
	                        case 3:
	                            classes.getCv().new_MagicTower();
	                            break;
	                        case 4:
	                            classes.getCv().new_Carpentry();
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
	            try {
			        dc_gui.getGui_obj().setPpindex(ppindex);
	                if (resultSet != null) resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	}
	
}
