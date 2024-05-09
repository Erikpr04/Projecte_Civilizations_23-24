package classes.attackunits;

import interfaces.Variables;

public class Cannon extends AttackUnit{
	
	
	
	//CONSTRUCTOR 1


	public Cannon(int armor, int baseDamage) {
		super(armor, baseDamage);
	}
	
	
	//CONSTRUCTOR 2
	
	
	public Cannon() {
		super(ARMOR_CANNON, BASE_DAMAGE_CANNON);
	}

	
	//METODOS DE MILITARY UNIT
	
	public int attack() {
		int damage = 0;
		
		if (isSanctified()) {
			damage = getBaseDamage() + (getBaseDamage()  * Variables.PLUS_ATTACK_UNIT_SANCTIFIED /100);
			
		} else {
			damage = getBaseDamage();
		}
		
		return damage;
	}

	public void takeDamage(int receivedDamage) {
		//Se resta el daño a la armadura
		setArmor(getArmor() - receivedDamage);
		
		//Si al restar, la armadura es negativa, se cambia a 0
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

	public void setExperience(int n) {
		setExperience(n);
	}

	public int getExperience() {
		return getExperience();
	}

}
