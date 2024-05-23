package classes.attackunits;

import exceptions.MiSQLException;
import interfaces.Variables;

public class CrossBow extends AttackUnit {
	
	
	
	
	//CONSTRUCTOR 1


	public CrossBow(int armor, int baseDamage) throws MiSQLException {
		super(armor, baseDamage);
	}
	
	//CONSTRUCTOR 2
	
	
	public CrossBow() throws MiSQLException {
		super(ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW);
	}
	
	//CONSTRUCTOR 3 (BD )
	public CrossBow(int unitId, int armor, int baseDamage, int experience, boolean sanctified) throws MiSQLException {
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
		return FOOD_COST_CROSSBOW;
	}

	public int getWoodCost() {
		return WOOD_COST_CROSSBOW;
	}

	public int getIronCost() {
		return IRON_COST_CROSSBOW;
	}

	public int getManaCost() {
		return MANA_COST_CROSSBOW;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_CROSSBOW;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_CROSSBOW;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
	
	public String toString() {
		return "Crossbow: " + super.toString();
	}

}
