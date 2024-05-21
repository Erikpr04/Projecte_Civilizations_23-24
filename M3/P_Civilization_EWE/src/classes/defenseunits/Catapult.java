package classes.defenseunits;

import exceptions.MiSQLException;
import interfaces.Variables;

public class Catapult extends DefenseUnit {
	
	
	//CONSTRUCTOR 1

	public Catapult(int armor, int baseDamage) throws MiSQLException {
		super(armor, baseDamage);
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
		return FOOD_COST_CATAPULT;
	}

	public int getWoodCost() {
		return WOOD_COST_CATAPULT;
	}

	public int getIronCost() {
		return IRON_COST_CATAPULT;
	}

	public int getManaCost() {
		return MANA_COST_CATAPULT;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CATAPULT;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CATAPULT;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
}
