package classes.attackunits;

import exceptions.MiSQLException;
import interfaces.Variables;

public class Swordsman extends AttackUnit {

	
	
	
	//CONSTRUCTOR 1
	
	public Swordsman(int armor, int baseDamage) throws MiSQLException {
		super(armor, baseDamage);
	}
	
	//CONSTRUCTOR 2
	
	
	public Swordsman() throws MiSQLException {
		super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
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
		return FOOD_COST_SWORDSMAN;
	}

	public int getWoodCost() {
		return WOOD_COST_SWORDSMAN;
	}

	public int getIronCost() {
		return IRON_COST_SWORDSMAN;
	}

	public int getManaCost() {
		return MANA_COST_SWORDSMAN;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_SWORDSMAN;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SWORDSMAN;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
}
