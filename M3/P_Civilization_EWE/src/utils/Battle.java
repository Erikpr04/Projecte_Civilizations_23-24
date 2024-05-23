package utils;

import java.util.ArrayList;
import java.util.Random;

import classes.dc_classes;
import classes.attackunits.AttackUnit;
import classes.defenseunits.DefenseUnit;
import classes.specialunits.SpecialUnit;
import exceptions.MiSQLException;
import exceptions.NoUnitsException;
import interfaces.BattleListener;
import interfaces.MilitaryUnit;
import interfaces.Variables;

public class Battle {
	private  BattleListener battlelistener;

	private ArrayList<ArrayList> civilizationArmy;
	private ArrayList<ArrayList> enemyArmy;
	
//***
	private ArrayList<ArrayList> armies = new ArrayList(2); //ArrayList de 2 filas y 9 columnas
	
	private String battleDevelopment = "";
	private int[][] initialCostFleet; //Array de 2 filas 
	
	private int initialNumberUnitsCivilization;
	private int initialNumberUnitsEnemy;
	
	
//***
	private int[] wasteWoodIron; //Array [madera, iron]
	
	private int [] enemyDeaths;
	private int [] civilizationDeaths;
	
	private int[][] resourcesLooses; //Array de 2 filas y 4 columnas
	

	private int[][] initialArmies; //Array de 2 filas y 9 columnas
	private int[] actualNumberUnitsCivilization;
	private int[] actualNumberUnitsEnemy; //Arrays para contabilizar unidades
	
	
	
	
	
	
	
	
	
	
	
	public BattleListener getBattlelistener() {
		return battlelistener;
	}


	public void setBattlelistener(BattleListener battlelistener) {
		this.battlelistener = battlelistener;
	}


	public Battle() {
		super();

	}
	
	
	//GETTERS Y SETTERS
	
	public ArrayList<ArrayList> getCivilizationArmy() {
		return civilizationArmy;
	}
	public void setCivilizationArmy(ArrayList<ArrayList> civilizationArmy) {
		this.civilizationArmy = civilizationArmy;
	}
	public ArrayList<ArrayList> getEnemyArmy() {
		return enemyArmy;
	}
	public void setEnemyArmy(ArrayList<ArrayList> enemyArmy) {
		this.enemyArmy = enemyArmy;
	}
	
	public ArrayList<ArrayList> getArmies() {
		armies = new ArrayList<ArrayList>();
		armies.add(civilizationArmy);
		armies.add(enemyArmy);
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
	
	public int[][] getInitialCostFleet( ) {
		//introducimos los datos de cada army en una array conjunta
		initialCostFleet = new int [2][3];
		initialCostFleet[0] = fleetResourceCost(civilizationArmy);
		initialCostFleet[1] = fleetResourceCost(enemyArmy);
		
		return initialCostFleet;
	}
	public void setInitialCostFleet(int[][] initialCostFleet) {
		
		this.initialCostFleet = initialCostFleet;
	}
	public int getInitialNumberUnitsCivilization() {
		int initialCivilizationUnits = 0;
		for (int i = 0; i<initialArmies[0].length; i++) {
			initialCivilizationUnits += initialArmies[0][i]; 
		}
		return initialCivilizationUnits;
	}
	public void setInitialNumberUnitsCivilization(int initialNumberUnitsCivilization) {
		this.initialNumberUnitsCivilization = initialNumberUnitsCivilization;
	}
	public int getInitialNumberUnitsEnemy() {
		int initialEnemyUnits = 0;
		for (int i = 0; i<initialArmies[1].length; i++) {
			initialEnemyUnits += initialArmies[1][i]; 
		}
		return initialEnemyUnits;
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
	public int[] getEnemyDeaths() {
		return enemyDeaths;
	}
	public void setEnemyDeaths(int[] enemyDeaths) {
		this.enemyDeaths = enemyDeaths;
	}
	public int[] getCivilizationDeaths() {
		return civilizationDeaths;
	}
	public void setCivilizationDeaths(int[] civilizationDeaths) {
		this.civilizationDeaths = civilizationDeaths;
	}

	//introducimos los datos de cada army en una array conjunta para obtener las perdidas de los dos bandos
	public int[][] getResourcesLooses() {
	
		resourcesLooses = new int [2][4];
		resourcesLooses[0] = fleetResourceLooses(civilizationArmy);
		resourcesLooses[1] = fleetResourceLooses(enemyArmy);
		
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
		actualNumberUnitsCivilization = new int[9];
		
		for (int i = 0; i < 9; i++) {
			actualNumberUnitsCivilization[i] = getCivilizationArmy().get(i).size();
		}
		
		return actualNumberUnitsCivilization;
	}
	
	
	public void setActualNumberUnitsCivilization(int[] actualNumberUnitsCivilization) {
		this.actualNumberUnitsCivilization = actualNumberUnitsCivilization;
	}
	public int[] getActualNumberUnitsEnemy() {
		actualNumberUnitsEnemy = new int[4];
			
		for (int i = 0; i < 4; i++) {
			actualNumberUnitsEnemy[i] = getEnemyArmy().get(i).size();
		}
			
		return actualNumberUnitsEnemy;
	}
	public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
		this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
	}
	
	
	
	//METODOS
	
	public String getBattleReport(int battles) {
		String report = "";
		String[] nameUnits = {"Swordsman", "Spearman", "Crossbow", "Cannon", 
		                      "Arrow Tower", "Catapult", "Rocket Launcher Tower", 
		                      "Magician", "Priest"};

		// Encabezado de las estadísticas de batalla
		report += " BATTLE STATISTICS\n\n";

		// Encabezado de la sección de batalla
		report += String.format(" %-25s%4s%8s   %-16s%5s%10s\n\n", 
								"Civilization Army", 
								"Units", 
								"Drops", 
								"Enemy Army", 
								"Units", 
								"Drops\n\n");


		// Agregar información de cada tipo de unidad
		for (int i = 0; i < getCivilizationArmy().size(); i++) {
		    if (i < 4) {
		        // Unidades cuerpo a cuerpo y a distancia
		        report += String.format(" %-25s%5d%8d   %-16s%5d%8d\n\n",
		                                nameUnits[i],
		                                getInitialArmies()[0][i],
		                                getCivilizationDeaths()[i],
		                                nameUnits[i],
		                                getInitialArmies()[1][i],
		                                getEnemyDeaths()[i]);
		    } else {
		        // Otras unidades
		        report += String.format(" %-25s%5d%8d\n\n",
		                                nameUnits[i],
		                                getInitialArmies()[0][i],
		                                getCivilizationDeaths()[i]);
		    }
		}

		report += " *************************************************************************\n\n";

		// Encabezado de la sección de costos de recursos
		report += " Cost Army Civilization                   Cost Army Enemy\n\n";
		report += String.format(" Food:	 %8d                         Food:	 %8d\n", initialCostFleet[0][0], initialCostFleet[1][0]);
		report += String.format(" Wood:	 %8d                         Wood:	 %8d\n", initialCostFleet[0][1], initialCostFleet[1][1]);
		report += String.format(" Iron:	 %8d                         Iron:	 %8d\n\n", initialCostFleet[0][2], initialCostFleet[1][2]);
		report += " *************************************************************************\n\n";

		// Encabezado de la sección de pérdidas de recursos
		report += String.format(" Losses Army Civilization                 Losses Army Enemy\n\n", " ");
		report += String.format(" Food:	 %8d                         Food:	 %8d\n", getResourcesLooses()[0][0], getResourcesLooses()[1][0]);
		report += String.format(" Wood:	 %8d                         Wood:	 %8d\n", getResourcesLooses()[0][1], getResourcesLooses()[1][1]);
		report += String.format(" Iron:	 %8d                         Iron:	 %8d\n\n", getResourcesLooses()[0][2], getResourcesLooses()[1][2]);
		report += " *************************************************************************\n\n";

		// Desperdicios generados
		report += " Waste Generated:\n\n";
		report += String.format(" Wood:	 %8d\n", wasteWoodIron[0]);
		report += String.format(" Iron:	 %8d\n\n", wasteWoodIron[1]);

		// Mensaje final de batalla
		report += "\n Battle Winned by Civilization, We Collect Rubble\n";

	    report += "\n #########################################################################";

	    return report;
	}

	
	public void initInitialArmies() throws NoUnitsException {
		//Inicializar el Array initialArmies y asi poder calcular reportes
		// Bucle para realizar el recuento de las tropas tanto de civilización como enemigas
				initialArmies = new int[2][9];
					for (int i = 0; i < 2; i++) {
						for (int j = 0 ; j < 9; j++) {
							if (i == 0) {
								initialArmies[i][j] = getCivilizationArmy().get(j).size();
							}
							else if (i == 1) { 
								if(j < 4) { //Rellena arraylist hasta 4 (las unidades disponibles de los enemigos)
									initialArmies[i][j] = getEnemyArmy().get(j).size();
								}						
							}					
						}
					}			
					//igualamos a la initialArmies de la clase principal
					setInitialArmies(initialArmies);
				

	}
	
	//recuento de las tropas de cada bando (usamos initialArmies para calcular el recuento total de tropas)
	private boolean remainderPercentageFleet() {
		
		boolean stop = true;
		
		
		int initialCivilizationUnits = getInitialNumberUnitsCivilization();
		int initialEnemyUnits = getInitialNumberUnitsEnemy();
		
		int percentageCivilization = (int) (initialCivilizationUnits * 0.20);
		int percentageEnemy = (int) (initialEnemyUnits * 0.20);
		
		int []CivilizationUnitsRemainder = getActualNumberUnitsCivilization();
		int counterUnitsCivilization = 0;
		for (int i = 0; i < CivilizationUnitsRemainder.length; i++) {
			counterUnitsCivilization += CivilizationUnitsRemainder[i];
		}
		
		int []EnemyUnitsRemainder = getActualNumberUnitsEnemy();
		int counterUnitsEnemy = 0;
		for (int i = 0; i < EnemyUnitsRemainder.length; i++) {
			counterUnitsEnemy += EnemyUnitsRemainder[i];
		}
		
		//COMPROBAR SI SE CUMPLE que el recuento total sea inferior a 20%
		if (counterUnitsCivilization <= percentageCivilization || counterUnitsEnemy <= percentageEnemy) {
			stop = false;
		}		
		
		return stop;
	}
	
	
	private int getGroupDefender(ArrayList<ArrayList> armyDefend) {
	    int totalUnits = 0;
	    int defenseGroup = 0;
	    Random selectEnemyUnit = new Random();
	    int[] army;
	    
	    
	    
	    // Calcula la suma total de todas las unidades en el grupo
	    for(int i = 0; i < armyDefend.size(); i++) {
			totalUnits += armyDefend.get(i).size();
		}
	    
	    // Genera un número aleatorio entre 1 y la suma total
	    int selectUnitProbability = selectEnemyUnit.nextInt(totalUnits) + 1;
	    int randomCount = 0;

	    // Verifica el tamaño del array para determinar cómo manejar los datos
	    if (armyDefend.size() == 4) { // Enemigo
	    	army = getActualNumberUnitsEnemy();
	        for (int i = 0; i < armyDefend.size(); i++) {
                randomCount += army[i];
                if (selectUnitProbability <= randomCount) {
                    defenseGroup = i;
                    return defenseGroup;  // Retorna inmediatamente al encontrar una categoría válida
                }
	            
	        }
	    } else if (armyDefend.size() == 9) { // Jugador
	    	army = getActualNumberUnitsCivilization();
	        for (int i = 0; i < armyDefend.size(); i++) {
                randomCount += army[i];
                if (selectUnitProbability <= randomCount) {
                    defenseGroup = i;
                    return defenseGroup;  // Retorna inmediatamente al encontrar una categoría válida
                }
	            
	        }
	    }
	    
	    return defenseGroup;  // Si no se encuentra una categoría válida, se retorna la primera
	}
	
	private int getCivilizationGroupAttacker() {
		//Escoger el grupo atacante de la civilizacion (mismo algoritmo que el anterior)
		int attackerGroup = 0;
		Random selectUnit = new Random();
		int randomNumber = selectUnit.nextInt(100) +1;
		int attackChance = 0;
		
		for (int i = 0; i < 8; i++) {
			attackChance += Variables.CHANCE_ATTACK_CIVILIZATION_UNITS[i];
			if (randomNumber <= attackChance && !getCivilizationArmy().get(i).isEmpty()) {
				attackerGroup = i;
				break;
			}
		}
		
		return attackerGroup;
	}
	
	private int getEnemyGroupAttacker() {
		//Escoger el grupo atacante del enemigo
		int randomCount,selectUnitPorbability;
		int totalUnits = 0;
		int attackerGroup = 0;
		Random selectUnit = new Random();		
		int randomNumber = selectUnit.nextInt(100) + 1;
		int attackChance = 0;
		
		for (int i = 0; i < 4; i++) {
			attackChance += Variables.CHANCE_ATTACK_ENEMY_UNITS[i];
			if (randomNumber <= attackChance && !getEnemyArmy().get(i).isEmpty()) {
				attackerGroup = i;
			}
		}
		
		return attackerGroup;

	}
	
	private void resetArmyArmor() {
		//Resetear armadura del ejercito (QUITAR??)
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < getCivilizationArmy().get(i).size(); j++) {
				((MilitaryUnit) getCivilizationArmy().get(i).get(j)).resetArmor();
			}
		}
		
	}
	
	
	//OTROS METODOS
	
	//calcular los costes de cada ejercito de forma individual
	private int [] fleetResourceCost(ArrayList<ArrayList> army) {
		int[]initialCostUnits = new int[3];
		int foodCost = 0 , woodCost = 0, ironCost = 0;
		MilitaryUnit unit;
		
		for (int i = 0; i < army.size() ; i++) {
			if(! army.get(i).isEmpty()) { //comprobamos que exista al menos una unidad
				for (int j = 0; j < army.get(i).size() ; j++) {	
					
					if (army.size() == 9) { //army si es Civilization
						unit = (MilitaryUnit) civilizationArmy.get(i).get(j);
						foodCost += unit.getFoodCost();
						woodCost += unit.getWoodCost();
						ironCost += unit.getIronCost();
						
					}else if(army.size() == 4) { //army si es enemy
						unit = (MilitaryUnit) enemyArmy.get(i).get(j);
						foodCost += unit.getFoodCost();
						woodCost += unit.getWoodCost();
						ironCost += unit.getIronCost();
					}	
				}
		    }
		}
		//introducimos valores 
		initialCostUnits[0] = foodCost;
		initialCostUnits[1] = woodCost;
		initialCostUnits[2] = ironCost;
		return initialCostUnits;
	}
	
	//metodo para calcular de forma individual las perdidas de cada bando (DEATH UNITS), devuelve food/wood/iron/Points 
	private int [] fleetResourceLooses(ArrayList<ArrayList> army) {
		int[]fleetResourceLooses = new int[4];
		int foodCost = 0 , woodCost = 0, ironCost = 0, totalPoints = 0;
		
		for (int i = 0; i < army.size() ; i++) {
				if (army.size() == 9) { //army si es Civilization
					foodCost += (int) (Variables.FOOD_COST_UNITS[i] * civilizationDeaths[i]);
					woodCost += (int) (Variables.WOOD_COST_UNITS[i] * civilizationDeaths[i]);
					ironCost += (int) (Variables.IRON_COST_UNITS[i] * civilizationDeaths[i]);
					
				}else if(army.size() == 4) { //army si es enemy
					foodCost += (int) (Variables.FOOD_COST_UNITS[i] * enemyDeaths[i]);
					woodCost += (int) (Variables.WOOD_COST_UNITS[i] * enemyDeaths[i]);
					ironCost += (int) (Variables.IRON_COST_UNITS[i] * enemyDeaths[i]);
				}
				
		}
		
		totalPoints = ironCost + (5*woodCost) + (10*foodCost);
		//introducimos valores 
		fleetResourceLooses[0] = foodCost;
		fleetResourceLooses[1] = woodCost;
		fleetResourceLooses[2] = ironCost;
		fleetResourceLooses[3] = totalPoints;
		return fleetResourceLooses;
	}
	
	
	
//METODO DONDE TRANSCURRE LA BATALLA:
	public void mainBattle(ArrayList<ArrayList> myArmy, ArrayList<ArrayList> enemyArmy) throws MiSQLException, NoUnitsException {
		setCivilizationArmy(myArmy);
		setEnemyArmy(enemyArmy);
		
		
		initInitialArmies();

		//Generacion de los ejercitos de la batalla: 
		if (getInitialNumberUnitsCivilization() == 0) {
			setBattleDevelopment(getBattleDevelopment() + " \n\n\nCANT START BATTLE, CIVILIZATION HAS NO UNITS");
			System.out.println("---------"+getBattleDevelopment());
			throw new NoUnitsException("CANT START BATTLE, CIVILIZATION HAS NO UNITS");
			
		}

		
//Registramos los costes iniciales del ejercito
		initialCostFleet = getInitialCostFleet();
		
		//variables para almacenar los residuos
		int wood_waste_Civilization = 0;
		int iron_waste_Civilization = 0;
		int wood_waste_Enemy = 0;
		int iron_waste_Enemy = 0;
		
		//variable inicializada apra el turno extra
		int attackGroup = 0;
	
		//lista de las unidades muertas
		
		civilizationDeaths = new int[9];
		enemyDeaths = new int [4];
		
		//listas para almacenar las ids y eliminar en la BASE DE DATOS
		
		ArrayList<Integer> deathAttackUnitIds = new ArrayList<>();
		ArrayList<Integer> deathDefenseUnitIds = new ArrayList<>();
		ArrayList<Integer> deathSpecialUnitsIds = new ArrayList<>();
			
		setActualNumberUnitsCivilization(getActualNumberUnitsCivilization());
		setActualNumberUnitsEnemy(getActualNumberUnitsEnemy());
		
		String[] nameUnits= {"Swordsman", "Spearman", "Crossbow", "Cannon", 
								"Arrow Tower", "Catapult","Rocket Launcher Tower","Magician","Priest"};
		
		//Seleccionamos el orden de ataque
		
		boolean myTurn = false;
		boolean endBattle = true;
		boolean extraTurn = false;
	
		
		Random comienzo = new Random();
		
		if (comienzo.nextBoolean()) {
			myTurn = true;

			
		
		}else {
			myTurn = false;
		}

		
		Random  grupoAleatorio = new Random();
		Random  chanceAttack = new Random();
		
		while (endBattle) {
			//Turno de Civilizacion
			if (myTurn && endBattle) {
				
				if (!extraTurn) {
					attackGroup = getCivilizationGroupAttacker();
								
					while (myArmy.get(attackGroup).size() == 0) {
						attackGroup = getCivilizationGroupAttacker();
					}
					extraTurn = true;
				}
				
				if (extraTurn) {
					setBattleDevelopment(getBattleDevelopment()+ "\n****************** Civilization Turn ******************");
					int defenseGroup = getGroupDefender(enemyArmy);

					while (enemyArmy.get(defenseGroup).size() == 0) {
						defenseGroup = getGroupDefender(enemyArmy);
					}
					
					
					int randomUnit;
					MilitaryUnit unitDefending;
					MilitaryUnit unitAttacking;
					
				
					setBattleDevelopment(getBattleDevelopment()+ "\nCivilization Unit: "+nameUnits[attackGroup]+" will attack...");
					setBattleDevelopment(getBattleDevelopment()+ "\nEnemy Unit: "+nameUnits[defenseGroup]+" will defend...");
					
					
					//Seleccionamos una unidad ATACANTE aleatoria y una unidad DEFENSORA aleatoria:
					
					//unidad atacante
					Random randomUnitIndexAttack = new Random(); 		
					randomUnit = randomUnitIndexAttack.nextInt(myArmy.get(attackGroup).size());
					
					//aseguramos que el indice corresponde a una unidad valida y existente
					while (myArmy.get(attackGroup).isEmpty()){
						randomUnitIndexAttack = new Random(); 		
						randomUnit = randomUnitIndexAttack.nextInt(myArmy.get(attackGroup).size());
					}
					
					unitAttacking = (MilitaryUnit) myArmy.get(attackGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Attack Unit
					
					//unidad defensora
					Random randomUnitIndexDefense = new Random(); 	
					randomUnit = randomUnitIndexDefense.nextInt(enemyArmy.get(defenseGroup).size());
					
					//aseguramos que el indice corresponde a una unidad valida y existente
					while (enemyArmy.get(defenseGroup).isEmpty()){
						randomUnitIndexDefense = new Random(); 		
						randomUnit = randomUnitIndexDefense.nextInt(enemyArmy.get(defenseGroup).size());
					}
					
					unitDefending = (MilitaryUnit) enemyArmy.get(defenseGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Defense Unit
					
					
					//COMBATE:
					
					//unidad atacante golpea a defensor y baja armadura
					int dmg = unitAttacking.attack();
					int armor = unitDefending.getActualArmor();
					int armorResult = armor - dmg;
					unitDefending.takeDamage(dmg);
					enemyArmy.get(defenseGroup).set(randomUnit,unitDefending);
					setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[attackGroup]+" generates the damage = "+dmg);
					setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[defenseGroup]+" stays with = "+armorResult);
					
					//si armadura a 0, se generan residuos (prob definida en interfaz Variable)
					if (armorResult <= 0) {
						Random waste = new Random();
						int wasteProbability = waste.nextInt(101);
						//si hay prob, se devuelve un 70% de los recursos usados alcrear unidad (solo madera y hierrp)
						if (wasteProbability <= Variables.CHANCE_GENERATING_WASTE_UNITS[defenseGroup]){
							wood_waste_Civilization += (int) ((unitDefending.getWoodCost() * Variables.PERCENTATGE_WASTE)/100);
							iron_waste_Civilization += (int) ((unitDefending.getIronCost() * Variables.PERCENTATGE_WASTE)/100);
						}
						//Elimina la unidad con armor 0 (muere)
						enemyArmy.get(defenseGroup).remove(randomUnit);
						setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[defenseGroup]+" of Enemy is dead");
						
						//sumamos la unidad perdida en batalla
						enemyDeaths[defenseGroup] += 1;
						
						
					}
					
					//metodo para calcular 20% unidades totales y, en caso afirmativo, sale del bucle 
					endBattle = remainderPercentageFleet();
					
					if (endBattle) {
					
						//probabilidad de turno extra para atacar:
						Random attackAgain = new Random();
						int chanceAttackAgain = attackAgain.nextInt(101);
						if (chanceAttackAgain <= Variables.CHANCE_ATTACK_AGAIN_UNITS[attackGroup]) {
							setBattleDevelopment(getBattleDevelopment()+ "\nExtra turn Civilization.");
							myTurn = true;
				
							
						}else {
							//falta poner condicion turno extra
							myTurn = false;
							extraTurn = false;
						}
					}
				} 
			}	
			
			//Turno de Enemigos //enemyTurn
			else if (!myTurn && endBattle) {
				
				if (!extraTurn) {
					attackGroup = getEnemyGroupAttacker();
				
					while (enemyArmy.get(attackGroup).size() == 0) {
						attackGroup = getEnemyGroupAttacker();
					}
					extraTurn = true;
				}
				
				if (extraTurn) {
					setBattleDevelopment(getBattleDevelopment()+ "\n****************** Enemy Turn ******************");
					int defenseGroup = getGroupDefender(myArmy);
					while (myArmy.get(defenseGroup).size() == 0) {
						defenseGroup = getGroupDefender(myArmy);
					}
				
				
				
					int randomUnit;
					MilitaryUnit unitAttacking;
					MilitaryUnit unitDefending;
					
					setBattleDevelopment(getBattleDevelopment()+ "\nEnemy Unit: "+nameUnits[attackGroup]+" will attack...");
					setBattleDevelopment(getBattleDevelopment()+ "\nCivilization Unit: "+nameUnits[defenseGroup]+" will defend...");
					
					
					//Seleccionamos una unidad ATACANTE aleatoria y una unidad DEFENSORA aleatoria:
					
					//unidad atacante
					Random randomUnitIndexAttack = new Random(); 		
					randomUnit = randomUnitIndexAttack.nextInt(enemyArmy.get(attackGroup).size());
					
					//aseguramos que el indice corresponde a una unidad valida y existente
					while (enemyArmy.get(attackGroup).isEmpty()){
						randomUnitIndexAttack = new Random(); 		
						randomUnit = randomUnitIndexAttack.nextInt(enemyArmy.get(attackGroup).size());
					}
					
					unitAttacking = (MilitaryUnit) enemyArmy.get(attackGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Attack Unit
					
					//unidad defensora
					Random randomUnitIndexDefense = new Random(); 	
					randomUnit = randomUnitIndexDefense.nextInt(myArmy.get(defenseGroup).size());
					
					//aseguramos que el indice corresponde a una unidad valida y existente
					while (myArmy.get(defenseGroup).isEmpty()){
						randomUnitIndexDefense = new Random(); 		
						randomUnit = randomUnitIndexDefense.nextInt(myArmy.get(defenseGroup).size());
					}
					
					unitDefending = (MilitaryUnit) myArmy.get(defenseGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Defense Unit
					
					
					//COMBATE:
					
					//unidad atacante golpea a defensor y baja armadura (armadura = dano - armaduraActual)
					int dmg = unitAttacking.attack();
					int armor = unitDefending.getActualArmor();
					int armorResult = armor - dmg;
					unitDefending.takeDamage(dmg);
					
					myArmy.get(defenseGroup).set(randomUnit,unitDefending);
					setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[attackGroup]+" generates the damage = "+dmg);
					setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[defenseGroup]+" stays with = "+armorResult);
					//si armadura a 0, se generan residuos (prob definida en interfaz Variable)
					if (armorResult <= 0) {
						Random waste = new Random();
						int wasteProbability = waste.nextInt(101);
						//si hay prob, se devuelve un 70% de los recursos usados alcrear unidad (solo madera y hierrp)
						if (wasteProbability <= Variables.CHANCE_GENERATING_WASTE_UNITS[defenseGroup]){
							wood_waste_Enemy += (int) ((unitDefending.getWoodCost() * Variables.PERCENTATGE_WASTE)/100);
							iron_waste_Enemy += (int) ((unitDefending.getIronCost() * Variables.PERCENTATGE_WASTE)/100);
							
						}
						
						//anadimos la perdida a la lista correspondiente
						if (defenseGroup <= 3){
							deathAttackUnitIds.add(((AttackUnit) unitDefending).getUnitId());
							
						}else if (defenseGroup <=6) {
							deathDefenseUnitIds.add(((DefenseUnit) unitDefending).getUnitId());
							
						}else if (defenseGroup <= 8) {
							deathSpecialUnitsIds.add(((SpecialUnit) unitDefending).getUnitId());
						}
						
						//Elimina la unidad con armor 0 (muere)
						myArmy.get(defenseGroup).remove(randomUnit);
						setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[defenseGroup]+" of Civilization is dead");
						
						//sumamos la unidad perdida en batalla
						civilizationDeaths[defenseGroup] += 1;
						
						
					}
					
					//metodo para calcular 20% unidades totales y, en caso afirmativo, sale del bucle 
					endBattle = remainderPercentageFleet();
					if (endBattle) {
						//probabilidad de turno extra para atacar:
						Random attackAgain = new Random();
						int chanceAttackAgain = attackAgain.nextInt(101);
						if (chanceAttackAgain <= Variables.CHANCE_ATTACK_AGAIN_UNITS[attackGroup]) {
							setBattleDevelopment(getBattleDevelopment()+ "\nExtra turn Enemy.");
							myTurn = false;
							
				
							
						}else {
							myTurn = true;
							extraTurn = false;
							
						}
					}
				}
				
			}
		}
		
			
		setBattleDevelopment(getBattleDevelopment()+ "\nEnd of the battle");
//Logica para ver que bando gana
		
		resourcesLooses = getResourcesLooses();
		int[] wasteWoodIron = new int[2];
		
		wasteWoodIron[0] = wood_waste_Civilization + wood_waste_Enemy;
		wasteWoodIron[1] = iron_waste_Civilization + iron_waste_Enemy;
		
		setWasteWoodIron(wasteWoodIron);
		
		// El valor de Points (puntuacion de cada bando en la batalla) mas alto pierde
		if (resourcesLooses[0][3] < resourcesLooses[1][3]) {
			setBattleDevelopment(getBattleDevelopment()+ "\nCivilization WIN !!!");
			
			//Anadir recursos a la civilizacion
			
		} else if (resourcesLooses[0][3] > resourcesLooses[1][3]) {
			setBattleDevelopment(getBattleDevelopment()+ "\nEnemy WIN !!!");
			
		} else {
			setBattleDevelopment(getBattleDevelopment()+ "\nThe two armies have TIED !!!");
		}
			
		
	//GUARDAR DATOS EN LA BASE DE DATOS:
		
		//intanciamos una clase conexion para poder realizar todos los cambios
		ConnectionDB cdb = new ConnectionDB();
		
		int numBattle = 0; //aqui hay que recuperar el count de batallas de la clase civilization o SELECT del ultimo num_batalla
		int civilization_Id = 1; //en un principio solo hay una civilizacion "1"

		
		//Actualizar Civilization local
		
			
		
		 	battlelistener.updatecv_after_battle(wasteWoodIron);
		
		//Actualziar EXPERIENCIA local unidades
		
		
		//Actualizar civilizacion en bd
		 cdb.actualizarDatosCivilization(battlelistener.getCV_Battle());
		 
		 
		 //eliminamos unidades muertas:
		 cdb.eliminarUnits(deathAttackUnitIds, deathDefenseUnitIds, deathSpecialUnitsIds);
		 
		 //update de las unidades restantes
		 
		 cdb.actualizarUnitsBD(myArmy);		 
		 
		 //insertar battleStats y battleLog  (REVISAR EN CLASE) -------------------------------------
		 cdb.insertarBattleStats(numBattle,civilization_Id ,getBattleReport(0));
		 cdb.insertarBattleLog(civilization_Id, numBattle, getBattleDevelopment());
		 
		
		 //INSERT apra las tablas de informacion extra de la batalla:
		
		 //CivilizationUnits
		 for (int i = 0; i < initialArmies[0].length; i++) {
			 int unitType = i + 1;
			 
			 if (i < 4) {
				//battle_attackunits_stats: civilization_id, num_battle, unit_type, initial, death
				 cdb.insertBattleAttackUnitStats(civilization_Id, numBattle, unitType, initialArmies[0][i], civilizationDeaths[i]);
				 
			 }else if (i >= 4 && i <= 6){
				//battle_defenseunits_stats: civilization_id, num_battle, unit_type, initial, death
				 cdb.insertBattleDefenseUnitStats(civilization_Id, numBattle, unitType, initialArmies[0][i], civilizationDeaths[i]);
				 
			 }else if (i > 6) {
				//battle_specialunits_stats: civilization_id, num_battle, unit_type, initial, death
				 cdb.insertBattleSpecialUnitStats(civilization_Id, numBattle, unitType,initialArmies[0][i], civilizationDeaths[i]);		 
			 }			
		 }
		 
		 //EnemyUnits
		 for (int i = 0; i < initialArmies[1].length; i++) {
			 int unitType = i+1;
			//enemy_attack_stats: civilization_id, num_battle, unit_type, initial, death
			 cdb.insertBattleEnemyUnitStats(civilization_Id, numBattle, unitType, initialArmies[1][i], enemyDeaths[i]);
		 } 
	}
}
	

