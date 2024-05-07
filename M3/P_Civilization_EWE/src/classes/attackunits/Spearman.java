package classes.attackunits;

public class Spearman extends AttackUnit {
	
	
	//CONSTRUCTOR 1

	public Spearman(int armor, int baseDamage) {
		super(armor, baseDamage);
	}
	
	//CONSTRUCTOR 2
	
	
		public Spearman() {
			super(ARMOR_SPEARMAN, BASE_DAMAGE_SPEARMAN);
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
		return FOOD_COST_SPEARMAN;
	}

	public int getWoodCost() {
		return WOOD_COST_SPEARMAN;
	}

	public int getIronCost() {
		return IRON_COST_SPEARMAN;
	}

	public int getManaCost() {
		return MANA_COST_SPEARMAN;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_SPEARMAN;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_SPEARMAN;
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
