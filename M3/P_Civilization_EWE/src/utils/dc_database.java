package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gui.Game_gui.MiPanelito;
import gui.dc_gui;
import interfaces.Variables;

public class dc_database {
    private int food, wood, iron, mana;
    private ConnectionDB  cbd = new ConnectionDB();

    public ResultSet getOccupiedPanels() {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                connection = DriverManager.getConnection(Variables.url, Variables.user, Variables.pass);
                String query = "SELECT structure_type, is_occupied, x_position, y_position FROM gui WHERE is_occupied = 1";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();
                return resultSet;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    

    public void uploadPanels(MiPanelito[][] subPanels) {
        try {
            Connection connection = DriverManager.getConnection(Variables.url, Variables.user, Variables.pass);
            String query = "INSERT INTO gui (structure_type, is_occupied, x_position, y_position) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            for (int y = 0; y < subPanels.length; y++) {
                for (int x = 0; x < subPanels[y].length; x++) {
                    MiPanelito panel = subPanels[y][x];
                    String structureType = panel.getFuture_structure(); // Obtener el tipo de estructura del panel
                    boolean isOccupied = panel.isIsoccupied(); // Verificar si está ocupado
                    int xPosition = x; // Posición X del panel en la matriz
                    int yPosition = y; // Posición Y del panel en la matriz

                    // Establecer los valores en la sentencia SQL
                    statement.setString(1, structureType);
                    statement.setInt(2, isOccupied ? 1 : 0); // Convertir a entero
                    statement.setInt(3, xPosition);
                    statement.setInt(4, yPosition);

                    // Ejecutar la inserción
                    statement.executeUpdate();
                }
            }

            // Cerrar la conexión y liberar recursos
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

	public ConnectionDB getConnectionDB() {
		return cbd;
	}

}
