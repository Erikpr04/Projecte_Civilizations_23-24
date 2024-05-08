package classes.attackunits;

import interfaces.MilitaryUnit;
import interfaces.Variables;

public abstract class AttackUnit implements MilitaryUnit, Variables {
	
	//Atributos
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	private boolean sanctified;
	
	
	 
	//CONSTRUCTOR 1
	
	public AttackUnit(int armor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = armor;
		this.baseDamage = baseDamage;
		this.experience = 0;
		this.sanctified = false;
	}


	//GETTERS Y SETTERS

	public int getArmor() {
		return armor;
	}



	public void setArmor(int armor) {
		this.armor = armor;
	}



	public int getInitialArmor() {
		return initialArmor;
	}



	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
	}



	public int getBaseDamage() {
		return baseDamage;
	}



	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}



	public int getExperience() {
		return experience;
	}



	public void setExperience(int experience) {
		this.experience = experience;
	}



	public boolean isSanctified() {
		return sanctified;
	}



	public void setSanctified(boolean sanctified) {
		this.sanctified = sanctified;
	}
	
	
	
	
}
