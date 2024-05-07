package classes.defenseunits;

public class Catapult extends DefenseUnit {
	
	
	//CONSTRUCTOR 1

	public Catapult(int armor, int baseDamage) {
		super(armor, baseDamage);
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

		public void setExperience(int n) {
			setExperience(n);
		}

		public int getExperience() {
			return getExperience();
		}
}
