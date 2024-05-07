package classes.specialunits;

public abstract class SpecialUnit {
	
	//Atributos
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	
	//CONSTRUCTOR 1
	
	public SpecialUnit(int armor, int baseDamage) {
		super();
		this.armor = armor;
		this.baseDamage = baseDamage;
	}
}
