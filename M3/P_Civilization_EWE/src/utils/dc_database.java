package utils;

import gui.dc_gui;
import interfaces.Variables;

public class dc_database {
    private int food, wood, iron, mana;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //getters y setters

	public dc_database() {
		super();
		 //aqui instanciariamos db_database y llamariamos a la base de datos para obtener los valores de nuestra db
		this.food = 21000;
		this.wood = 21000;
		this.mana = 21000;
		this.iron = 21000;


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


	public void updateResource(String resourcename, int newFoodValue) {
		
		
	}

}
