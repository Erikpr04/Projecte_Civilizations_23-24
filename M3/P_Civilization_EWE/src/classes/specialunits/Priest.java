package classes.specialunits;

import exceptions.MiSQLException;

public class Priest extends SpecialUnit {
	
	
	//CONSTRUCTOR 1

	public Priest(int armor, int baseDamage) throws MiSQLException {
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
		return FOOD_COST_PRIEST;
	}

	public int getWoodCost() {
		return WOOD_COST_PRIEST;
	}

	public int getIronCost() {
		return IRON_COST_PRIEST;
	}

	public int getManaCost() {
		return MANA_COST_PRIEST;
	}

	public int getChanceGeneratinWaste() {
		return CHANCE_GENERATNG_WASTE_PRIEST;
	}

	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_PRIEST;
	}

	public void resetArmor() {
		setArmor(getInitialArmor());
	}
}
