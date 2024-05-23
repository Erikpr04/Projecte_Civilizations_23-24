package classes.defenseunits;

import exceptions.MiSQLException;
import interfaces.Variables;

public class ArrowTower extends DefenseUnit{
	
	
	//CONSTRUCTOR 1

	public ArrowTower(int armor, int baseDamage) throws MiSQLException {
		super(armor, baseDamage);
	}
	
	//CONSTRUCTOR 2 (BD )
	public ArrowTower(int unitId, int armor, int baseDamage, int experience, boolean sanctified) throws MiSQLException {
		super(unitId, armor, baseDamage, experience, sanctified);
		
	}
	
	
	//METODOS DE MILITARY UNIT
	
	public int attack() {
		int damage = 0;
		
		if (isSanctified()) {
			damage = getBaseDamage() + ((getBaseDamage()  * Variables.PLUS_ATTACK_UNIT_SANCTIFIED) /100);
			
		} else {
			damage = getBaseDamage();
		}
		
		return damage;
	}

	public void takeDamage(int receivedDamage) {
		setArmor(getArmor() - receivedDamage);
        if (getArmor() < 0) {
            setArmor(0);
        }
	}

	public int getActualArmor() {
		int armadura = 0;
		
		if (isSanctified()) {
			armadura = getArmor() + ((getArmor() * Variables.PLUS_ARMOR_UNIT_SANCTIFIED) /100);
		
		} else {
			armadura = getArmor();
		}
		
		
		return armadura;
	}

	public int getFoodCost() {
		return FOOD_COST_ARROWTOWER;
	}

	public int getWoodCost() {
		return WOOD_COST_ARROWTOWER;
	}

	public int getIronCost() {
		return IRON_COST_ARROWTOWER;
	}

	public int getManaCost() {
		return MANA_COST_ARROWTOWER;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_ARROWTOWER;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ARROWTOWER;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
	public String toString() {
		return "ArrowTower" + super.toString();
	}


}
