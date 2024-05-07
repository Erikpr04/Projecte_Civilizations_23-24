package classes.defenseunits;

public abstract class DefenseUnit {
	
	//Atributos
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	private boolean sanctified;
	
	//CONSTRUCTOR 1
	
	public DefenseUnit(int armor, int baseDamage) {
		super();
		this.armor = armor;
		this.baseDamage = baseDamage;
	}
}
