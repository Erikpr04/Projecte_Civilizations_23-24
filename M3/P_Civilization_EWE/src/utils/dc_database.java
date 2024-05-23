package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gui.dc_gui;
import interfaces.Variables;

public class dc_database {
    private int food, wood, iron, mana;
    private static final String URL = "jdbc:mysql://localhost/civilizationewe?serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "123123";

        public ResultSet getOccupiedPanels() {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                connection = DriverManager.getConnection(URL, USER, PASS);
                String query = "SELECT structure_type, is_occupied, x_position, y_position FROM gui WHERE is_occupied = true";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();
                return resultSet;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    
    
    
    
    
    
    
    
    
    
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
