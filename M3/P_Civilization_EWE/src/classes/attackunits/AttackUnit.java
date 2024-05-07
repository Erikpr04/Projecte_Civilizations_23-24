package classes.attackunits;

public abstract class AttackUnit {
	
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
		this.baseDamage = baseDamage;
	}
	
	
	
	
}
