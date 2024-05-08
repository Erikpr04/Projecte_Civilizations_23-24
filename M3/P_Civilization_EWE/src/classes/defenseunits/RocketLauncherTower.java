package classes.defenseunits;

public class RocketLauncherTower extends DefenseUnit {
	

	//CONSTRUCTOR 1

	public RocketLauncherTower(int armor, int baseDamage) {
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

		public void setExperience(int n) {
			setExperience(n);
		}

		public int getExperience() {
			return getExperience();
		}
}
