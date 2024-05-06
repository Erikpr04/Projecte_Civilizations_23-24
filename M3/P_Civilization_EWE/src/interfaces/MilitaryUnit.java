package interfaces;

public interface MilitaryUnit extends Variables {
	//Devuelve el ataque de la unidad.
	abstract int attack(); 
	
	//Resta el daño introducido al blindaje.
	abstract void takeDamage(int recievedDamage); 
	
	//Devuelve la armadura que tengamos actualmente después de recibir golpe.
	abstract int getActualArmor(); 
	
	//Devuelve el coste de comida que tiene crear la unidad.
	abstract int getFoodCost(); 
	
	//Devuelve el coste de madera que tiene crear la unidad.
	abstract int getWoodCost(); 
	
	//Devuelve el coste de hierro que tiene crear la unidad.
	abstract int getIronCost(); 
	
	//Devuelve el coste de mana que tiene crear la unidad.
	abstract int getManaCost(); 
	
	//Devuelve la probabilidad de generar residuos cuando la armadura sea menor o igual a 0.
	abstract int getChanceGeneratingWaste(); 
	
	//Devuelve la probabilidad de volver a atacar.
	abstract int getChanceAttackAgain(); 
	
	//Resetea la armadura.
	abstract void resetArmor(); 
	
	//Establecer experienca al valor introducido.
	abstract void setExperience(int n); 
	
	//Devuelve experiencia de la unidad.
	abstract void getExperience(); 
}
