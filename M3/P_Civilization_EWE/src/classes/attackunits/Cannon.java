package classes.attackunits;

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
