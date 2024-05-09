package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import classes.attackunits.AttackUnit;
import classes.defenseunits.DefenseUnit;
import interfaces.MilitaryUnit;
import interfaces.Variables;

public class Battle {
	private ArrayList<ArrayList> civilizationArmy;
	private ArrayList<ArrayList> enemyArmy;
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
		//Resumen de la batalla, battles es el numero de batallas que hemos acumulado
		return "";
	}
	
	public String BattleDevelopment1() {
		//Paso a paso de la batalla
		return "";
	}
	
	public void initInitialArmies() {
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
	
	public void updateResourcesLooses() {
		//Generar el Array de perdidas
		
	}
	
	public void fleetResourceCoust(ArrayList<ArrayList> army) {
		//Calcular costes del ejercito
		
	}
	
	public void initialFleetNumber(ArrayList<ArrayList> army) {
		//Calcular numero de unidades iniciales de cada ejercito
		
	}
	
	public int remainderPercentageFleet(ArrayList<ArrayList> army) {
		//Calcular porcentajes de unidades que quedan respecto a los ejercitos iniciales
		return 0;
	}
	
	public int getGroupDefender(ArrayList<ArrayList> armyDefend) {
		int randomCount,selectUnitPorbability;
		int totalUnits = 0;
		int defenseGroup = 0;
		Random selectEnemyUnit = new Random();
		
		//Creamos un if para verificar is es Civ o Enemy seun su tamano;
		
		if (armyDefend.size() == 4) { //enemy
			
			//Numero total de tropas :
			for(int i = 0; i < armyDefend.size(); i++) {
				totalUnits += actualNumberUnitsEnemy[i];
			}
			
			// For para seleccionar el grupo de unidades del enemigo de forma aleatoria aplicando el algoritomo
			selectUnitPorbability = selectEnemyUnit.nextInt(1,totalUnits);
			randomCount = actualNumberUnitsEnemy[0]; 

			for (int i = 0; i < armyDefend.size(); i++ ) {
				if (actualNumberUnitsEnemy[i] > 0) {
					
					randomCount += actualNumberUnitsEnemy[i];
					if(randomCount <= selectUnitPorbability) {
						defenseGroup = i;
						break;	
					}
				}
			}
		}
		else { //Civ
			//Numero total de tropas :
			for(int i = 0; i < armyDefend.size(); i++) {
				totalUnits += actualNumberUnitsCivilization[i];
			}
			
			// For para seleccionar el grupo de unidades del enemigo de forma aleatoria aplicando el algoritomo
			selectUnitPorbability = selectEnemyUnit.nextInt(1,totalUnits);
			randomCount = actualNumberUnitsCivilization[0]; 

			for (int i = 0; i < armyDefend.size(); i++ ) {
				if (actualNumberUnitsCivilization[i] > 0) {
					
					randomCount += actualNumberUnitsCivilization[i];
					if(randomCount <= selectUnitPorbability) {
						defenseGroup = i;
						break;	
					}
				}
			}
		}
		return defenseGroup;
	}
	
	public int getCivilizationGroupAttacker() {
		//Escoger el grupo atacante de la civilizacion
		Random random = new Random();
	    int attackerGroup = random.nextInt(9);
	    
		return attackerGroup;
	}
	
	public int getEnemyGroupAttacker() {
		//Escoger el grupo atacante del enemigo
		Random random = new Random();
	    int attackerGroup = random.nextInt(4);
	    
		return attackerGroup;

	}
	
	public void resetArmyArmor() {
		//Resetear armadura del ejercito (QUITAR??)
		
	}
	
	
	//OTROS METODOS
	public void mainBattle(ArrayList<ArrayList> myArmy, ArrayList<ArrayList> enemyArmy) {
		//metodo donde trascurre la logica de la batalla
		
		
		//Generacion de los ejercitos de la batalla: 
		setCivilizationArmy(myArmy);
		setEnemyArmy(enemyArmy);
		initInitialArmies();
		
		//variables para almacenar los residuos
		int wood_waste_Civilization = 0;
		int iron_waste_Civilization = 0;
		int wood_waste_Enemy = 0;
		int iron_waste_Enemy = 0;
		
		//recuento de las tropas de cada bando 
		setActualNumberUnitsCivilization(getActualNumberUnitsCivilization());
		setActualNumberUnitsEnemy(getActualNumberUnitsEnemy());
		
		String[] nameUnits= {"Swordsman", "Spearman", "Crossbow", "Cannon", 
								"Arrow Tower", "Catapult","Rocket Launcher Tower","Magician","Priest"};
		
		//Seleccionamos el orden de ataque
		
		boolean myTurn = false;
		boolean enemyTurn = false;
		
		Random comienzo =  new Random();
		
		if (comienzo.nextInt() == 0) {
			myTurn = true;
			
		
		}else {
			enemyTurn = true;
		}
		
		Random  grupoAleatorio = new Random();
		Random  chanceAttack = new Random();
		
		//Turno de Civilizacion
		while (myTurn) {
			setBattleDevelopment(getBattleDevelopment()+ "\nCivilization turn...\n");
			int attackGroup = getCivilizationGroupAttacker();
			int defenseGroup = getGroupDefender(enemyArmy);
			int randomUnit;
			AttackUnit unitAttacking;
			DefenseUnit unitDefending;
			
			
			if (chanceAttack.nextInt(100) <= Variables.CHANCE_ATTACK_CIVILIZATION_UNITS[attackGroup]){
				setBattleDevelopment(getBattleDevelopment()+ "\nCivilization Unit: "+nameUnits[attackGroup]+" will attack...");
				
				
				//Seleccionamos una unidad ATACANTE aleatoria y una unidad DEFENSORA aleatoria:
				
				//unidad atacante
				Random randomUnitIndexAttack = new Random(myArmy.get(attackGroup).size()); 		
				randomUnit = randomUnitIndexAttack.nextInt(myArmy.get(attackGroup).size());
				unitAttacking = (AttackUnit) myArmy.get(attackGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Attack Unit
				
				//unidad defensora
				Random randomUnitIndexDefense = new Random(enemyArmy.get(defenseGroup).size()); 	
				randomUnit = randomUnitIndexDefense.nextInt(enemyArmy.get(defenseGroup).size());
				unitDefending = (DefenseUnit) enemyArmy.get(defenseGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Defense Unit
				
				
				//COMBATE:
				
				//unidad atacante golpea a defensor y baja armadura (armaduroa = daño - armadura)
				int dmg = unitAttacking.attack();
				int armor = unitDefending.getActualArmor();
				int armorResult = dmg - armor;
				unitDefending.setArmor(armorResult);
				enemyArmy.get(defenseGroup).set(randomUnit,unitDefending);
				
			// FALTA BAJAR ARMADURA DE LA UNIDAD
				
				//si armadura a 0, se generan residuos (prob definida en interfaz Variable)
				if (armorResult <= 0) {
					Random waste= new Random();
					int wasteProbability = waste.nextInt(101);
					//si hay prob, se devuelve un 70% de los recursos usados alcrear unidad (solo madera y hierrp)
					if (wasteProbability <= Variables.CHANCE_GENERATING_WASTE_UNITS[defenseGroup]){
						wood_waste_Civilization += unitDefending.getWoodCost() * (Variables.PERCENTATGE_WASTE/100);
						iron_waste_Civilization += unitDefending.getIronCost() * (Variables.PERCENTATGE_WASTE/100);
					}		
				}
				
				//probabilidad de turno extra para atacar:
				Random attackAgain = new Random();
				int chanceAttackAgain = attackAgain.nextInt(101);
				if (chanceAttackAgain <= Variables.CHANCE_ATTACK_AGAIN_UNITS[attackGroup]) {
					myTurn = true;
					enemyTurn = false;
					
					//metodo para calcular 20 unidades totales
					
				}else {
					//falta poner condicion turno extra
					myTurn = false;
					enemyTurn = true;
				}
			}	
		}
		
		//Turno de Enemigos
		while (enemyTurn) {
			setBattleDevelopment(getBattleDevelopment()+ "Enemy Starts...");
			setBattleDevelopment(getBattleDevelopment()+ "\nCivilization turn...\n");
			int attackGroup = getEnemyGroupAttacker();
			int defenseGroup = getGroupDefender(myArmy);
			
			int totalEnemyUnits = 0;
			int totalCivilizationUnits = 0;
			int randomUnit,randomCount;
			MilitaryUnit unitAttacking, unitDefending;
			
			
			//falta poner condicion turno extra
			enemyTurn = false;
			myTurn = true;
		}
	}
	//setBattleDevelopment(getBattleDevelopment()+ "Enemy Starts...");
	
}
