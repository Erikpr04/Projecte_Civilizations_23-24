package classes;

import java.io.ObjectInputStream.GetField;
import java.text.ChoiceFormat;
import java.util.ArrayList;
import java.util.Iterator;

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
import exceptions.ResourceException;
import interfaces.MilitaryUnit;
import interfaces.Variables;

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
	
	
	//METODOS PARA CREAR UNIDADES
	
	public void new_Swordsman(int i) throws ResourceException {
		//Calculamos la armadura total y el ataque total
		int total_armor = Variables.ARMOR_SWORDSMAN+((getTechnologyDefense()*Variables.PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		int total_attack = Variables.BASE_DAMAGE_SWORDSMAN+((getTechnologyAttack()*Variables.PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			//Comprobamos que tenga los recursos
			if (getFood() >= Variables.FOOD_COST_SWORDSMAN ||getWood() >= Variables.WOOD_COST_SWORDSMAN || getIron() >= Variables.IRON_COST_SWORDSMAN) {
				//Si los tiene, lo añadimos al ArrayList
				army.get(0).add(new Swordsman(total_armor,total_attack));

			}
			else {
				//Si no los tiene, creamos excepcion de Resources
				throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Swordsman");
			}
			
			//Y asi con las demás unidades
		}
	}
	
	
	public void new_Spearman(int i) throws ResourceException {
		int total_armor = Variables.ARMOR_SPEARMAN+((getTechnologyDefense()*Variables.PLUS_ARMOR_SPEARMAN_BY_TECHNOLOGY)*1000/100);
		int total_attack = Variables.BASE_DAMAGE_SPEARMAN+((getTechnologyAttack()*Variables.PLUS_ATTACK_SPEARMAN_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			
			if (getFood() >= Variables.FOOD_COST_SPEARMAN ||getWood() >= Variables.WOOD_COST_SPEARMAN || getIron() >= Variables.IRON_COST_SPEARMAN) {
				army.get(1).add(new Spearman(total_armor,total_attack));

			}
			else {
				throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Spearman");
			}
			
			
		}
	}
	
	
	public void new_Crossbow(int i) throws ResourceException {
		int total_armor = Variables.ARMOR_CROSSBOW+((getTechnologyDefense()*Variables.PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY)*1000/100);
		int total_attack = Variables.BASE_DAMAGE_CROSSBOW+((getTechnologyAttack()*Variables.PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			
			if (getFood() >= Variables.FOOD_COST_CROSSBOW ||getWood() >= Variables.WOOD_COST_CROSSBOW || getIron() >= Variables.IRON_COST_CROSSBOW) {
				army.get(2).add(new CrossBow(total_armor,total_attack));

			}
			else {
				throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Crossbow");
			}
			
			
		}
	}
	
	
	public void new_Cannon(int i) throws ResourceException {
		int total_armor = Variables.ARMOR_CANNON+((getTechnologyDefense()*Variables.PLUS_ARMOR_CANNON_BY_TECHNOLOGY)*1000/100);
		int total_attack = Variables.BASE_DAMAGE_CANNON+((getTechnologyAttack()*Variables.PLUS_ATTACK_CANNON_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			
			if (getFood() >= Variables.FOOD_COST_CANNON ||getWood() >= Variables.WOOD_COST_CANNON || getIron() >= Variables.IRON_COST_CANNON) {
				army.get(3).add(new Cannon(total_armor,total_attack));

			}
			else {
				throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Cannon");
			}
			
			
		}
	}
	
	public void new_ArrowTower(int i) throws ResourceException {
		int total_armor = Variables.ARMOR_ARROWTOWER+((getTechnologyDefense()*Variables.PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY)*1000/100);
		int total_attack = Variables.BASE_DAMAGE_ARROWTOWER+((getTechnologyAttack()*Variables.PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			
			if (getFood() >= Variables.FOOD_COST_ARROWTOWER ||getWood() >= Variables.WOOD_COST_ARROWTOWER || getIron() >= Variables.IRON_COST_ARROWTOWER) {
				army.get(4).add(new ArrowTower(total_armor,total_attack));

			}
			else {
				throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Arrow Tower");
			}
			
			
		}
	}
	
	
	public void new_Catapult(int i) throws ResourceException {
		int total_armor = Variables.ARMOR_CATAPULT+((getTechnologyDefense()*Variables.PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY)*1000/100);
		int total_attack = Variables.BASE_DAMAGE_CATAPULT+((getTechnologyAttack()*Variables.PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			
			if (getFood() >= Variables.FOOD_COST_CATAPULT ||getWood() >= Variables.WOOD_COST_CATAPULT || getIron() >= Variables.IRON_COST_CATAPULT) {
				army.get(5).add(new Catapult(total_armor,total_attack));

			}
			else {
				throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Catapult");
			}
			
			
		}
	}
	
	
	public void new_RocketLauncher(int i) throws ResourceException {
		int total_armor = Variables.ARMOR_ROCKETLAUNCHERTOWER+((getTechnologyDefense()*Variables.PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*1000/100);
		int total_attack = Variables.BASE_DAMAGE_ROCKETLAUNCHERTOWER+((getTechnologyAttack()*Variables.PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			
			if (getFood() >= Variables.FOOD_COST_ROCKETLAUNCHERTOWER ||getWood() >= Variables.WOOD_COST_ROCKETLAUNCHERTOWER || getIron() >= Variables.IRON_COST_ROCKETLAUNCHERTOWER) {
				army.get(6).add(new RocketLauncherTower(total_armor,total_attack));

			}
			else {
				throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Rocket Launcher");
			}
			
			
		}
	}
	
	
	public void new_Magician(int i) throws ResourceException, BuildingException {
		//Para el Mago su armadura es 0
		int total_armor = 0;
		int total_attack = Variables.BASE_DAMAGE_MAGICIAN+((getTechnologyAttack()*Variables.PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			//Y hay que comprobar que tengas almenos 1 Magic Tower 
			if (getMagicTower() >= 1) {
				if (getFood() >= Variables.FOOD_COST_MAGICIAN ||getWood() >= Variables.WOOD_COST_MAGICIAN || getIron() >= Variables.IRON_COST_MAGICIAN || getMana() >= Variables.MANA_COST_MAGICIAN) {
					army.get(7).add(new Magician(total_armor,total_attack));
	
				}
				else {
					throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Magician");
				}
			} 
			else {
				//Si no la tienes, se genera excepcion de Building
				throw new BuildingException("Not enough Magic Towers to create unit");
			}
			
		}
	}
	
	
	public void new_Priest(int i) throws ResourceException, BuildingException {
		//Para el Sacerdote la armadura y el ataque son 0
		int total_armor = 0;
		int total_attack = 0;
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			//Y hay que comprobar que tengas almenos 1 iglesia
			if (getChurch() >= 1) {
				if (getFood() >= Variables.FOOD_COST_PRIEST ||getWood() >= Variables.WOOD_COST_PRIEST || getIron() >= Variables.IRON_COST_PRIEST || getMana() >= Variables.MANA_COST_PRIEST) {
					army.get(8).add(new Priest(total_armor,total_attack));
	
				}
				else {
					throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Priest");
				}
			}
			else {
				//Y si no la tienes, se genera excepcion de Building
				throw new BuildingException("Not enough Churchs to create unit");
			}
			
		}
	}
	
	
	//METODOS PARA CREAR EDIFICIOS
	
	
	public void new_Church() throws ResourceException {
		//Se comprueba que tengas los recursos para crear el building
		if (getFood() >= Variables.FOOD_COST_CHURCH || getWood() >= Variables.WOOD_COST_CHURCH || getIron() >= Variables.IRON_COST_CHURCH || getMana() >= Variables.MANA_COST_CHURCH) {
			//Si puedes crearla, se añade 1 al contador de iglesias
			church += 1;
		}
		else {
			//Si no tienes los recursos, se genera excepcion de Resource
			throw new ResourceException("Not enough resources to create building");
		}
	} //Y igual para las demás
	
	
	public void new_MagicTower() throws ResourceException {
		if (getFood() >= Variables.FOOD_COST_MAGICTOWER || getWood() >= Variables.WOOD_COST_MAGICTOWER || getIron() >= Variables.IRON_COST_MAGICTOWER) {
			magicTower += 1;
		}
		else {
			throw new ResourceException("Not enough resources to create building");
		}
	}
	
	
	public void new_Farm() throws ResourceException {
		if (getFood() >= Variables.FOOD_COST_FARM || getWood() >= Variables.WOOD_COST_FARM || getIron() >= Variables.IRON_COST_FARM) {
			farm += 1;
		}
		else {
			throw new ResourceException("Not enough resources to create building");
		}
	}
	
	public void new_Carpentry() throws ResourceException {
		if (getFood() >= Variables.FOOD_COST_CARPENTRY || getWood() >= Variables.WOOD_COST_CARPENTRY || getIron() >= Variables.IRON_COST_CARPENTRY) {
			carpentry += 1;
		}
		else {
			throw new ResourceException("Not enough resources to create building");
		}
	}
	
	
	public void new_Smithy() throws ResourceException {
		if (getFood() >= Variables.FOOD_COST_SMITHY || getWood() >= Variables.WOOD_COST_SMITHY || getIron() >= Variables.IRON_COST_SMITHY) {
			smithy += 1;
		}
		else {
			throw new ResourceException("Not enough resources to create building");
		}
	}
	
	//METODOS PARA MEJORAR TECNOLOGIA
	
	public void upgradeTechnologyDefense() throws ResourceException {
		//Se comprueba que tengas los recursos para mejorar la tecnologia
		if (getWood() >= Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST + (Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST * technologyDefense)  || getIron() >= Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST + (Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST * technologyDefense)) {
			//Si tienes los recursos, se añade 1 al nivel de defensa
			technologyDefense += 1;
		}
		else {
			//Si no tienes los recursos, se genera excepcion de Resource
			throw new ResourceException("Not enough resources to upgrade defense technology");
		}
	} //Y igual para la tecnologia de ataque
	
	
	public void upgradeTechnologyAttack() throws ResourceException {
		if (getWood() >= Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST + (Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST * technologyAttack)  || getIron() >= Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST + (Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST * technologyAttack)) {
			technologyAttack += 1;
		}
		else {
			throw new ResourceException("Not enough resources to upgrade attack technology");
		}
	}
}
