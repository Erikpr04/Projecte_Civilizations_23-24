package utils;

import java.util.ArrayList;
import java.util.Random;

import classes.attackunits.AttackUnit;
import classes.defenseunits.DefenseUnit;
import interfaces.MilitaryUnit;
import interfaces.Variables;

public class Battle {
	private ArrayList<ArrayList> civilizationArmy;
	private ArrayList<ArrayList> enemyArmy;
//******
	private ArrayList<ArrayList> armies = new ArrayList(2); //ArrayList de 2 filas y 9 columnas
	
	private String battleDevelopment;
	private int[][] initialCostFleet; //Array de 2 filas 
	private int initialNumberUnitsCivilization;
	private int initialNumberUnitsEnemy;
	
//***
	private int[][] wasteWoodIron; //Array [madera, iron]
	
	private int [] enemyDeaths;
	private int [] civilizationDeaths;
	
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
	public int[][] getWasteWoodIron() {
		return wasteWoodIron;
	}
	public void setWasteWoodIron(int[][] wasteWoodIron) {
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
//****
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
	
	
//	public void initialFleetNumber(ArrayList<ArrayList> army) {
//		//Calcular numero de unidades iniciales de cada ejercito
//		
//	}
	
	//recuento de las tropas de cada bando (usamos initialArmies para calcular el recuento total de tropas)
	private boolean remainderPercentageFleet() {
		
		boolean stop = false;
		
		int initialCivilizationUnits = 0;
		for (int i = 0; i<initialArmies[0].length; i++) {
			initialCivilizationUnits += initialArmies[0][i]; 
		}
		int initialEnemyUnits = 0;
		for (int i = 0; i<initialArmies[1].length; i++) {
			initialEnemyUnits += initialArmies[1][i]; 
		}
		
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
			stop = true;
		}		
		
		return stop;
	}
	
	private int getGroupDefender(ArrayList<ArrayList> armyDefend) {
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
	
	private int getCivilizationGroupAttacker() {
		//Escoger el grupo atacante de la civilizacion
		Random random = new Random();
	    int attackerGroup = random.nextInt(9);
	    
		return attackerGroup;
	}
	
	private int getEnemyGroupAttacker() {
		//Escoger el grupo atacante del enemigo
		Random random = new Random();
	    int attackerGroup = random.nextInt(4);
	    
		return attackerGroup;

	}
	
	private void resetArmyArmor() {
		//Resetear armadura del ejercito (QUITAR??)
		
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
		MilitaryUnit unitReferenced;
		
		for (int i = 0; i < army.size() ; i++) {
			if(! army.get(i).isEmpty()) {
				if (army.size() == 9) { //army si es Civilization
					unitReferenced = (MilitaryUnit) civilizationArmy.get(i).get(0);
					foodCost += (int) (unitReferenced.getFoodCost() * civilizationDeaths[i]);
					woodCost += (int) (unitReferenced.getWoodCost() * civilizationDeaths[i]);
					ironCost += (int) (unitReferenced.getIronCost() * civilizationDeaths[i]);
					
				}else if(army.size() == 4) { //army si es enemy
					unitReferenced = (MilitaryUnit) enemyArmy.get(i).get(0);
					foodCost += (int) (unitReferenced.getFoodCost() * enemyDeaths[i]);
					woodCost += (int) (unitReferenced.getWoodCost() * enemyDeaths[i]);
					ironCost += (int) (unitReferenced.getIronCost() * enemyDeaths[i]);
				}
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
	
	public void mainBattle(ArrayList<ArrayList> myArmy, ArrayList<ArrayList> enemyArmy) {
		//metodo donde trascurre la logica de la batalla
		
		
		//Generacion de los ejercitos de la batalla: 
		setCivilizationArmy(myArmy);
		setEnemyArmy(enemyArmy);
		initInitialArmies();
		
//Registramos los costes iniciales del ejercito
		initialCostFleet = getInitialCostFleet();
		
		//variables para almacenar los residuos
		int wood_waste_Civilization = 0;
		int iron_waste_Civilization = 0;
		int wood_waste_Enemy = 0;
		int iron_waste_Enemy = 0;
	
//lista de las unidades muertas
		
		civilizationDeaths = new int[9];
		enemyDeaths = new int [4];
			
		setActualNumberUnitsCivilization(getActualNumberUnitsCivilization());
		setActualNumberUnitsEnemy(getActualNumberUnitsEnemy());
		
		String[] nameUnits= {"Swordsman", "Spearman", "Crossbow", "Cannon", 
								"Arrow Tower", "Catapult","Rocket Launcher Tower","Magician","Priest"};
		
		//Seleccionamos el orden de ataque
		
		boolean myTurn = false;
		boolean enemyTurn = false;
		boolean endBattle = false;
	
		
		Random comienzo =  new Random();
		
		if (comienzo.nextInt() == 0) {
			myTurn = true;
			
		
		}else {
			enemyTurn = true;
		}
		
		Random  grupoAleatorio = new Random();
		Random  chanceAttack = new Random();
		
		//Turno de Civilizacion
		while (myTurn && !endBattle) {
			//metodo para calcular 20% unidades totales y, en caso afirmativo, sale del bucle 
			endBattle = remainderPercentageFleet();
			setBattleDevelopment(getBattleDevelopment()+ "\n****************** Civilization Turn ******************");
			int attackGroup = getCivilizationGroupAttacker();
			int defenseGroup = getGroupDefender(enemyArmy);
			int randomUnit;
			AttackUnit unitAttacking;
			DefenseUnit unitDefending;
			
			
			if (chanceAttack.nextInt(100) <= Variables.CHANCE_ATTACK_CIVILIZATION_UNITS[attackGroup]){
				setBattleDevelopment(getBattleDevelopment()+ "\nCivilization Unit: "+nameUnits[attackGroup]+" will attack...");
				setBattleDevelopment(getBattleDevelopment()+ "\nEnemy Unit: "+nameUnits[defenseGroup]+" will defend...");
				
				
				//Seleccionamos una unidad ATACANTE aleatoria y una unidad DEFENSORA aleatoria:
				
				//unidad atacante
				Random randomUnitIndexAttack = new Random(myArmy.get(attackGroup).size()); 		
				randomUnit = randomUnitIndexAttack.nextInt(myArmy.get(attackGroup).size());
				
				//aseguramos que el indice corresponde a una unidad valida y existente
				while (myArmy.get(attackGroup).isEmpty()){
					randomUnitIndexAttack = new Random(myArmy.get(attackGroup).size()); 		
					randomUnit = randomUnitIndexAttack.nextInt(myArmy.get(attackGroup).size());
				}
				
				unitAttacking = (AttackUnit) myArmy.get(attackGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Attack Unit
				
				//unidad defensora
				Random randomUnitIndexDefense = new Random(enemyArmy.get(defenseGroup).size()); 	
				randomUnit = randomUnitIndexDefense.nextInt(enemyArmy.get(defenseGroup).size());
				
				//aseguramos que el indice corresponde a una unidad valida y existente
				while (enemyArmy.get(defenseGroup).isEmpty()){
					randomUnitIndexDefense = new Random(enemyArmy.get(defenseGroup).size()); 		
					randomUnit = randomUnitIndexDefense.nextInt(enemyArmy.get(defenseGroup).size());
				}
				
				unitDefending = (DefenseUnit) enemyArmy.get(defenseGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Defense Unit
				
				
				//COMBATE:
				
				//unidad atacante golpea a defensor y baja armadura (armadura = dano - armadura)
				int dmg = unitAttacking.attack();
				int armor = unitDefending.getActualArmor();
				int armorResult = dmg - armor;
				unitDefending.takeDamage(dmg);
				enemyArmy.get(defenseGroup).set(randomUnit,unitDefending);
				setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[attackGroup]+" generates the damage = "+dmg);
				setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[defenseGroup]+" stays with = "+armorResult);
				
				//si armadura a 0, se generan residuos (prob definida en interfaz Variable)
				if (armorResult == 0) {
					Random waste = new Random();
					int wasteProbability = waste.nextInt(101);
					//si hay prob, se devuelve un 70% de los recursos usados alcrear unidad (solo madera y hierrp)
					if (wasteProbability <= Variables.CHANCE_GENERATING_WASTE_UNITS[defenseGroup]){
						wood_waste_Civilization += (int) (unitDefending.getWoodCost() * (Variables.PERCENTATGE_WASTE/100));
						iron_waste_Civilization += (int) (unitDefending.getIronCost() * (Variables.PERCENTATGE_WASTE/100));
					}
					//Elimina la unidad con armor 0 (muere)
					enemyArmy.get(defenseGroup).remove(randomUnit);
					setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[defenseGroup]+" of Enemy is dead");
					
					//sumamos la unidad perdida en batalla
					enemyDeaths[defenseGroup] += 1;
					
				}
				
				//probabilidad de turno extra para atacar:
				Random attackAgain = new Random();
				int chanceAttackAgain = attackAgain.nextInt(101);
				if (chanceAttackAgain <= Variables.CHANCE_ATTACK_AGAIN_UNITS[attackGroup]) {
					myTurn = true;
					enemyTurn = false;
		
					
				}else {
					//falta poner condicion turno extra
					myTurn = false;
					enemyTurn = true;
				}
			}	
		}
		
		//Turno de Enemigos
		while (enemyTurn && !endBattle) {
			
			//metodo para calcular 20% unidades totales y, en caso afirmativo, sale del bucle 
			endBattle = remainderPercentageFleet();
			setBattleDevelopment(getBattleDevelopment()+ "\n****************** Enemy Turn ******************");
			int attackGroup = getEnemyGroupAttacker();
			int defenseGroup = getGroupDefender(myArmy);
			int randomUnit;
			AttackUnit unitAttacking;
			DefenseUnit unitDefending;
			if (chanceAttack.nextInt(100) <= Variables.CHANCE_ATTACK_ENEMY_UNITS[attackGroup]){
				setBattleDevelopment(getBattleDevelopment()+ "\nEnemy Unit: "+nameUnits[attackGroup]+" will attack...");
				setBattleDevelopment(getBattleDevelopment()+ "\nCivilization Unit: "+nameUnits[defenseGroup]+" will defend...");
				
				
				//Seleccionamos una unidad ATACANTE aleatoria y una unidad DEFENSORA aleatoria:
				
				//unidad atacante
				Random randomUnitIndexAttack = new Random(enemyArmy.get(attackGroup).size()); 		
				randomUnit = randomUnitIndexAttack.nextInt(enemyArmy.get(attackGroup).size());
				
				//aseguramos que el indice corresponde a una unidad valida y existente
				while (enemyArmy.get(attackGroup).isEmpty()){
					randomUnitIndexAttack = new Random(enemyArmy.get(attackGroup).size()); 		
					randomUnit = randomUnitIndexAttack.nextInt(enemyArmy.get(attackGroup).size());
				}
				
				unitAttacking = (AttackUnit) enemyArmy.get(attackGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Attack Unit
				
				//unidad defensora
				Random randomUnitIndexDefense = new Random(myArmy.get(defenseGroup).size()); 	
				randomUnit = randomUnitIndexDefense.nextInt(myArmy.get(defenseGroup).size());
				
				//aseguramos que el indice corresponde a una unidad valida y existente
				while (myArmy.get(defenseGroup).isEmpty()){
					randomUnitIndexDefense = new Random(myArmy.get(defenseGroup).size()); 		
					randomUnit = randomUnitIndexDefense.nextInt(myArmy.get(defenseGroup).size());
				}
				
				unitDefending = (DefenseUnit) myArmy.get(defenseGroup).get(randomUnit); //parse al objecto extraido para convertirlo en Defense Unit
				
				
				//COMBATE:
				
				//unidad atacante golpea a defensor y baja armadura (armadura = dano - armaduraActual)
				int dmg = unitAttacking.attack();
				int armor = unitDefending.getActualArmor();
				int armorResult = dmg - armor;
				unitDefending.takeDamage(dmg);
				
				myArmy.get(defenseGroup).set(randomUnit,unitDefending);
				setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[attackGroup]+" generates the damage = "+dmg);
				setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[defenseGroup]+" stays with = "+armorResult);
				//si armadura a 0, se generan residuos (prob definida en interfaz Variable)
				if (armorResult == 0) {
					Random waste = new Random();
					int wasteProbability = waste.nextInt(101);
					//si hay prob, se devuelve un 70% de los recursos usados alcrear unidad (solo madera y hierrp)
					if (wasteProbability <= Variables.CHANCE_GENERATING_WASTE_UNITS[defenseGroup]){
						wood_waste_Enemy += (int) (unitDefending.getWoodCost() * (Variables.PERCENTATGE_WASTE/100));
						iron_waste_Enemy += (int) (unitDefending.getIronCost() * (Variables.PERCENTATGE_WASTE/100));
					}
					//Elimina la unidad con armor 0 (muere)
					myArmy.get(defenseGroup).remove(randomUnit);
					setBattleDevelopment(getBattleDevelopment()+ "\n"+nameUnits[defenseGroup]+" of Civilization is dead");
					
					//sumamos la unidad perdida en batalla
					civilizationDeaths[defenseGroup] += 1;
				}
				
				//probabilidad de turno extra para atacar:
				Random attackAgain = new Random();
				int chanceAttackAgain = attackAgain.nextInt(101);
				if (chanceAttackAgain <= Variables.CHANCE_ATTACK_AGAIN_UNITS[attackGroup]) {
					myTurn = false;
					enemyTurn = true;
		
					
				}else {
					
					enemyTurn = false;
					myTurn = true;
					
				}
			}	
		}
		
		
				
		setBattleDevelopment(getBattleDevelopment()+ "\nEnd of the battle");
//Logica para ver que bando gana
		
		resourcesLooses = getResourcesLooses();
		
		// El valor de Points (puntuacion de cada bando en la batalla) mas alto pierde
		if (resourcesLooses[0][3] < resourcesLooses[1][3]) {
			setBattleDevelopment(getBattleDevelopment()+ "\nCivilization WIN !!!");
			//Anadir recursos a la civilizacion
			
		} else if (resourcesLooses[0][3] > resourcesLooses[1][3]) {
			setBattleDevelopment(getBattleDevelopment()+ "\nEnemy WIN !!!");
			// ????
		} else {
			setBattleDevelopment(getBattleDevelopment()+ "\nThe two armies have TIED !!!");
		}
		

		
	}
	
}
