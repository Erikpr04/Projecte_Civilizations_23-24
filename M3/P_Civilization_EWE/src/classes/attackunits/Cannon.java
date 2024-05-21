package classes.attackunits;

import exceptions.MiSQLException;
import interfaces.Variables;

public class Cannon extends AttackUnit{
	
	
	
	//CONSTRUCTOR 1

	public Cannon(int armor, int baseDamage) throws MiSQLException {
		super(armor, baseDamage);
	}
	
	
	//CONSTRUCTOR 2
	
	public Cannon() throws MiSQLException {
		super(ARMOR_CANNON, BASE_DAMAGE_CANNON);
	}

	
	//METODOS DE MILITARY UNIT
	
	public int attack() {
		int damage = getBaseDamage() + (getExperience() * Variables.PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT);
		
		if (isSanctified()) {
			damage += ((getBaseDamage()  * Variables.PLUS_ATTACK_UNIT_SANCTIFIED) /100);
			
		}
		
		return damage;
	}

	public void takeDamage(int receivedDamage) {
		//Se resta el daño a la armadura
		setArmor(getActualArmor() - receivedDamage);
		
		//Si al restar, la armadura es negativa, se cambia a 0
        if (getArmor() < 0) {
            setArmor(0);
        }
	}

	public int getActualArmor() {
		int armadura = getArmor() + (getExperience() * Variables.PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT);
		
		if (isSanctified()) {
			armadura += ((getArmor() * Variables.PLUS_ARMOR_UNIT_SANCTIFIED) /100);
		
		}
		
		
		return armadura;
	}

	public int getFoodCost() {
		return FOOD_COST_CANNON;
	}

	public int getWoodCost() {
		return WOOD_COST_CANNON;
	}

	public int getIronCost() {
		return IRON_COST_CANNON;
	}

	public int getManaCost() {
		return MANA_COST_CANNON;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CANNON;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CANNON;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}

}
