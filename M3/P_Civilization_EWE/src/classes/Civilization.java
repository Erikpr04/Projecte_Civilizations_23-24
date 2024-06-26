package classes;


import java.util.ArrayList;


import classes.attackunits.*;
import classes.defenseunits.*;
import classes.specialunits.*;

import exceptions.BuildingException;
import exceptions.MiSQLException;
import exceptions.ResourceException;

import interfaces.Variables;
import utils.ConnectionDB;

public class Civilization {
	
	//Atributos
	private int technologyDefense;
	private int technologyAttack;
	private int wood;
	private int iron;
	private int food;
	private int mana;
	private int magicTower;
	private int farm;
	private int church;
	private int smithy;
	private int carpentry;
	private int battles;
	private ArrayList<ArrayList> army = new ArrayList<ArrayList>(9);
	private int id = 1;
	private String name;
	
	
	//CONSTRUCTOR PARA INICIALIZAR EL ARRAY
	
	public Civilization() {
        // Inicializar el ArrayList de army con 9 listas vacías
        army = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            army.add(new ArrayList<>());
       }
	}
	
	//getters and setters
	
	public int getTechnologyDefense() {
		return technologyDefense;
	}
	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}
	public int getTechnologyAttack() {
		return technologyAttack;
	}
	public void setTechnologyAttack(int technologyAttack) {
		this.technologyAttack = technologyAttack;
	}
	public int getWood() {
		return wood;
	}
	public void setWood(int wood) {
		this.wood = wood;
	}
	public int getIron() {
		return iron;
	}
	public void setIron(int iron) {
		this.iron = iron;
	}
	public int getFood() {
		return food;
	}
	public void setFood(int food) {
		this.food = food;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getMagicTower() {
		return magicTower;
	}
	public void setMagicTower(int magicTower) {
		this.magicTower = magicTower;
	}
	public int getFarm() {
		return farm;
	}
	public void setFarm(int farm) {
		this.farm = farm;
	}
	public int getChurch() {
		return church;
	}
	public void setChurch(int church) {
		this.church = church;
	}
	public int getSmithy() {
		return smithy;
	}
	public void setSmithy(int smithy) {
		this.smithy = smithy;
	}
	public int getCarpentry() {
		return carpentry;
	}
	public void setCarpentry(int carpentry) {
		this.carpentry = carpentry;
	}
	public int getBattles() {
		return battles;
	}
	public void setBattles(int battles) {
		this.battles = battles;
	}
	public ArrayList<ArrayList> getArmy() {
		return army;
	}
	public void setArmy(ArrayList<ArrayList> army) {
		this.army = army;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName( String name) {
		this.name = name;
	}
	//METODOS PARA CREAR UNIDADES
	
	public void new_Swordsman(int i) throws ResourceException, MiSQLException {
		//Calculamos la armadura total y el ataque total
		int total_armor = Variables.ARMOR_SWORDSMAN+((getTechnologyDefense()*Variables.PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)*Variables.ARMOR_SWORDSMAN/100);
		int total_attack = Variables.BASE_DAMAGE_SWORDSMAN+((getTechnologyAttack()*Variables.PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)*Variables.BASE_DAMAGE_SWORDSMAN/100);
		int iterator_army;

		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			//Si los tiene, lo añadimos al ArrayList
			Swordsman newUnit = new Swordsman(total_armor,total_attack);
			army.get(0).add(newUnit);
			
			ConnectionDB cdb = new ConnectionDB();
			cdb.crearUnit(newUnit);
			
			
			setWood(wood-Variables.WOOD_COST_SWORDSMAN);
			setFood(food-Variables.FOOD_COST_SWORDSMAN);
			setIron(iron-Variables.IRON_COST_SWORDSMAN);	
		}
	}
	
	
	public void new_Spearman(int i) throws ResourceException, MiSQLException {
		int total_armor = Variables.ARMOR_SPEARMAN+((getTechnologyDefense()*Variables.PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY)*Variables.ARMOR_SPEARMAN/100);
		int total_attack = Variables.BASE_DAMAGE_SPEARMAN+((getTechnologyAttack()*Variables.PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY)*Variables.BASE_DAMAGE_SPEARMAN/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			Spearman newUnit = new Spearman(total_armor, total_attack);
			army.get(1).add(newUnit);
			
			ConnectionDB cdb = new ConnectionDB();
			cdb.crearUnit(newUnit);
			
			setWood(wood-Variables.WOOD_COST_SPEARMAN);
			setFood(food-Variables.FOOD_COST_SPEARMAN);
			setIron(iron-Variables.IRON_COST_SPEARMAN);

		}		
	}
	
	
	public void new_Crossbow(int i) throws ResourceException, MiSQLException {
		int total_armor = Variables.ARMOR_CROSSBOW+((getTechnologyDefense()*Variables.PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY)*Variables.ARMOR_CROSSBOW/100);
		int total_attack = Variables.BASE_DAMAGE_CROSSBOW+((getTechnologyAttack()*Variables.PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY)*Variables.BASE_DAMAGE_CROSSBOW/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
		
			CrossBow newUnit = new CrossBow(total_armor, total_attack);
			army.get(2).add(newUnit);
			
			ConnectionDB cdb = new ConnectionDB();
			cdb.crearUnit(newUnit);
			
			setWood(wood-Variables.WOOD_COST_CROSSBOW);
			setFood(food-Variables.FOOD_COST_CROSSBOW);
			setIron(iron-Variables.IRON_COST_CROSSBOW);	
		}
	}
	
	
	public void new_Cannon(int i) throws ResourceException, MiSQLException {
		int total_armor = Variables.ARMOR_CANNON+((getTechnologyDefense()*Variables.PLUS_ARMOR_CANNON_BY_TECHNOLOGY)*Variables.ARMOR_CANNON/100);
		int total_attack = Variables.BASE_DAMAGE_CANNON+((getTechnologyAttack()*Variables.PLUS_ATTACK_CANNON_BY_TECHNOLOGY)*Variables.BASE_DAMAGE_CANNON/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			Cannon newUnit = new Cannon(total_armor, total_attack);
			army.get(3).add(newUnit);
			
			ConnectionDB cdb = new ConnectionDB();
			cdb.crearUnit(newUnit);
			
			setWood(wood-Variables.WOOD_COST_CANNON);
			setFood(food-Variables.FOOD_COST_CANNON);
			setIron(iron-Variables.IRON_COST_CANNON);

		}

	}
	
	public void new_ArrowTower(int i) throws ResourceException, MiSQLException {
		int total_armor = Variables.ARMOR_ARROWTOWER+((getTechnologyDefense()*Variables.PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY)*Variables.ARMOR_ARROWTOWER/100);
		int total_attack = Variables.BASE_DAMAGE_ARROWTOWER+((getTechnologyAttack()*Variables.PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)*Variables.BASE_DAMAGE_ARROWTOWER/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
				
			ArrowTower newUnit = new ArrowTower(total_armor, total_attack);
			army.get(4).add(newUnit);
			
			ConnectionDB cdb = new ConnectionDB();
			cdb.crearUnit(newUnit);
			
			setWood(wood-Variables.WOOD_COST_ARROWTOWER);
			setFood(food-Variables.FOOD_COST_ARROWTOWER);
			setIron(iron-Variables.IRON_COST_ARROWTOWER);

		}
	}
	
	
	public void new_Catapult(int i) throws ResourceException, MiSQLException {
		int total_armor = Variables.ARMOR_CATAPULT+((getTechnologyDefense()*Variables.PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY)*Variables.ARMOR_CATAPULT/100);
		int total_attack = Variables.BASE_DAMAGE_CATAPULT+((getTechnologyAttack()*Variables.PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY)*Variables.BASE_DAMAGE_CATAPULT/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
	
			Catapult newUnit = new Catapult(total_armor, total_attack);
			army.get(5).add(newUnit);
			
			ConnectionDB cdb = new ConnectionDB();
			cdb.crearUnit(newUnit);
			
			setWood(wood-Variables.WOOD_COST_CATAPULT);
			setFood(food-Variables.FOOD_COST_CATAPULT);
			setIron(iron-Variables.IRON_COST_CATAPULT);
		}
	}
	
	
	public void new_RocketLauncher(int i) throws ResourceException, MiSQLException {
		int total_armor = Variables.ARMOR_ROCKETLAUNCHERTOWER+((getTechnologyDefense()*Variables.PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*Variables.ARMOR_ROCKETLAUNCHERTOWER/100);
		int total_attack = Variables.BASE_DAMAGE_ROCKETLAUNCHERTOWER+((getTechnologyAttack()*Variables.PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*Variables.BASE_DAMAGE_ROCKETLAUNCHERTOWER/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			RocketLauncherTower newUnit = new RocketLauncherTower(total_armor, total_attack);
			army.get(6).add(newUnit);
			
			ConnectionDB cdb = new ConnectionDB();
			cdb.crearUnit(newUnit);
			
			setWood(wood-Variables.WOOD_COST_ROCKETLAUNCHERTOWER);
			setFood(food-Variables.FOOD_COST_ROCKETLAUNCHERTOWER);
			setIron(iron-Variables.IRON_COST_ROCKETLAUNCHERTOWER);
		}
	}
	
	
	public void new_Magician(int i) throws ResourceException, BuildingException, MiSQLException {
		//Para el Mago su armadura es 0
		int total_armor = 0;
		int total_attack = Variables.BASE_DAMAGE_MAGICIAN+((getTechnologyAttack()*Variables.PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY)*Variables.BASE_DAMAGE_MAGICIAN/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			//Y hay que comprobar que tengas almenos 1 Magic Tower 
			if (getMagicTower() >= 1) {
					Magician newUnit = new Magician(total_armor, total_attack);
	                army.get(7).add(newUnit);
	                
	                ConnectionDB cdb = new ConnectionDB();
	                cdb.crearUnit(newUnit);
	                
					setWood(wood-Variables.WOOD_COST_MAGICIAN);
					setFood(food-Variables.FOOD_COST_MAGICIAN);
					setIron(iron-Variables.IRON_COST_MAGICIAN);
			} 
			else {
				//Si no la tienes, se genera excepcion de Building
				throw new BuildingException("Not enough Magic Towers to create unit");
			}
			
		}
	}
	
	
	public void new_Priest(int i) throws ResourceException, BuildingException, MiSQLException {
		//Para el Sacerdote la armadura y el ataque son 0
		int total_armor = 0;
		int total_attack = 0;
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			//Y hay que comprobar que tengas almenos 1 iglesia
			if (getChurch() >= 1) {
					
					Priest newUnit = new Priest(total_armor, total_attack);
	                army.get(8).add(newUnit);
	                
	                ConnectionDB cdb = new ConnectionDB();
	                cdb.crearUnit(newUnit);
					
					setWood(wood-Variables.WOOD_COST_PRIEST);
					setFood(food-Variables.FOOD_COST_PRIEST);
					setIron(iron-Variables.IRON_COST_PRIEST);		
			}
			else {
				//Y si no la tienes, se genera excepcion de Building
				throw new BuildingException("Not enough Churchs to create unit");
			}
		}
	}
	
	//METODOS PARA CREAR EDIFICIOS
	public void new_Church() throws ResourceException {
		church += 1;

	} 
	
	public void new_MagicTower() throws ResourceException {
		magicTower += 1;

	}
	
	public void new_Farm() throws ResourceException {
		farm += 1;
	}
	
	public void new_Carpentry() throws ResourceException {
		carpentry += 1;
	}
	
	
	public void new_Smithy() throws ResourceException {
		smithy += 1;

	}
	
	//METODOS PARA MEJORAR TECNOLOGIA
	
	public void upgradeTechnologyDefense(int numUpgrade) throws ResourceException {
		technologyDefense += numUpgrade;

	} 
	
	
	public void upgradeTechnologyAttack(int numUpgrade) throws ResourceException {
		technologyAttack += numUpgrade;

	}
	
	
	
	//METODO PARA SANTIFICAR UNIDADES
	
	public void sanctifyUnits() {
		for (int i = 0; i < getArmy().size(); i++) {
			for (int j = 0; j < getArmy().get(i).size(); j++) {
				if (i < 4) {
					AttackUnit atacante = ((AttackUnit) getArmy().get(i).get(j));
					
					atacante.setSanctified(true);
				
				} else if (i >= 4 && i < 7) {
					DefenseUnit defensa = ((DefenseUnit) getArmy().get(i).get(j));
					
					defensa.setSanctified(true);
				
				}
			}
		}
	}
}
