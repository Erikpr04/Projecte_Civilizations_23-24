package gui;

import utils.dc_database;

public class dc_gui {
	
	
    private int food, wood, iron, mana;
    private dc_database dc_database;

    
    
    public dc_gui() {
        super();
        this.dc_database = new dc_database();
        this.food = dc_database.getFood();
        this.wood = dc_database.getWood();
        this.mana = dc_database.getMana();
        this.iron = dc_database.getIron();
    }

    public void updateFood(int newFoodValue) {
        this.food = newFoodValue;
        dc_database.updateResource("food", newFoodValue);
    }
    public void updateWood(int newFoodValue) {
        this.food = newFoodValue;
        dc_database.updateResource("wood", newFoodValue);
    }
    public void updateIron(int newFoodValue) {
        this.food = newFoodValue;
        dc_database.updateResource("iron", newFoodValue);
    }
    public void updateMana(int newFoodValue) {
        this.food = newFoodValue;
        dc_database.updateResource("mana", newFoodValue);
    }
    
    
    
    
    
    
    

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}
	



	
	
	
	
	

}
