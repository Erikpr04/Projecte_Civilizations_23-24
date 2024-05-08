package classes.attackunits;

public class CrossBow extends AttackUnit {
	
	
	
	
	//CONSTRUCTOR 1


	public CrossBow(int armor, int baseDamage) {
		super(armor, baseDamage);
	}
	
	//CONSTRUCTOR 2
	
	
		public CrossBow() {
			super(ARMOR_CROSSBOW, BASE_DAMAGE_CROSSBOW);
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

	public void setExperience(int n) {
		setExperience(n);
	}

	public int getExperience() {
		return getExperience();
	}
}
