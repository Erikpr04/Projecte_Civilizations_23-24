package classes;

import java.text.ChoiceFormat;
import java.util.ArrayList;
import java.util.Iterator;

import classes.attackunits.Swordsman;
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
	private int curch;
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
	public int getCurch() {
		return curch;
	}
	public void setCurch(int curch) {
		this.curch = curch;
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
	
	
	
	//METODOS
	
	
	//METODOS PARA CREAR UNIDADES
	
	public void new_Swordsman(int i) throws ResourceException {
		int total_armor = Variables.ARMOR_SWORDSMAN+((getTechnologyDefense()*Variables.PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		int total_attack = Variables.BASE_DAMAGE_SWORDSMAN+((getTechnologyAttack()*Variables.PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)*1000/100);
		int iterator_army;
		
		for (iterator_army = 0 ;iterator_army < i;iterator_army++) {
			
			
			if (getFood() >= Variables.FOOD_COST_SWORDSMAN ||getFood() >= Variables.WOOD_COST_SWORDSMAN || getIron() >= Variables.IRON_COST_SWORDSMAN) {
				army.get(0).add(new Swordsman(total_armor,total_attack));

			}
			else {
				throw new ResourceException("Not enough resources to create unit, created " +iterator_army+ " Swordsman");
			}
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
