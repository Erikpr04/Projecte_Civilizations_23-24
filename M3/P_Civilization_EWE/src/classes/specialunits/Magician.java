package classes.specialunits;

import exceptions.MiSQLException;

public class Magician extends SpecialUnit {

	
	//CONSTRUCTOR 1

	public Magician(int armor, int baseDamage) throws MiSQLException {
		super(armor, baseDamage);
	}
	
	//CONSTRUCTOR 2 (BD )
	public Magician(int unitId, int armor, int baseDamage, int experience) throws MiSQLException {
		super(unitId, armor, baseDamage, experience);
		
	}
	
	
	//METODOS DE MILITARY UNIT
	
		public int attack() {
			return getBaseDamage();
		}

		public void takeDamage(int receivedDamage) {
			setArmor(getArmor() - receivedDamage);
	        if (getArmor() < 0) {
	            setArmor(0);
	        }
		}

		public int getActualArmor() {
			return getArmor();
		}

		public int getFoodCost() {
			return FOOD_COST_MAGICIAN;
		}

		public int getWoodCost() {
			return WOOD_COST_MAGICIAN;
		}

		public int getIronCost() {
			return IRON_COST_MAGICIAN;
		}

		public int getManaCost() {
			return MANA_COST_MAGICIAN;
		}

		public int getChanceGeneratinWaste() {
			return CHANCE_GENERATNG_WASTE_MAGICIAN;
		}

		public int getChanceAttackAgain() {
			return CHANCE_ATTACK_AGAIN_MAGICIAN;
		}

		public void resetArmor() {
			setArmor(getInitialArmor());
		}

	
}
