package classes.attackunits;

public class Swordsman extends AttackUnit {

	
	
	
	//CONSTRUCTOR 1
	
	public Swordsman(int armor, int baseDamage) {
		super(armor, baseDamage);
	}
	
	//CONSTRUCTOR 2
	
	
		public Swordsman() {
			super(ARMOR_SWORDSMAN, BASE_DAMAGE_SWORDSMAN);
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

		public void setExperience(int n) {
			setExperience(n);
		}

		public int getExperience() {
			return getExperience();
		}
	

}
