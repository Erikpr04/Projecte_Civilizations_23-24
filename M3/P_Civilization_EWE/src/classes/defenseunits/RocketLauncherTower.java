package classes.defenseunits;

import exceptions.MiSQLException;
import interfaces.Variables;

public class RocketLauncherTower extends DefenseUnit {
	

	//CONSTRUCTOR 1

	public RocketLauncherTower(int armor, int baseDamage) throws MiSQLException {
		super(armor, baseDamage);
	}
	
	//CONSTRUCTOR 2 (BD )
	public RocketLauncherTower(int unitId, int armor, int baseDamage, int experience, boolean sanctified) throws MiSQLException {
		super(unitId, armor, baseDamage, experience, sanctified);
		
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
		return FOOD_COST_ROCKETLAUNCHERTOWER;
	}

	public int getWoodCost() {
		return WOOD_COST_ROCKETLAUNCHERTOWER;
	}

	public int getIronCost() {
		return IRON_COST_ROCKETLAUNCHERTOWER;
	}

	public int getManaCost() {
		return MANA_COST_ROCKETLAUNCHERTOWER;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
	
	public String toString() {
		return "RocketLauncherTower" + super.toString();
	}

}
