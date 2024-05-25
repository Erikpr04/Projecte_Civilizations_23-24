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
                String query = "SELECT structure_type, is_occupied, x_position, y_position, ppindex FROM gui WHERE is_occupied = 1";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();
                return resultSet;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    

    public void uploadPanels(MiPanelito[][] subPanels, int ppindex) {
    	
    	for (MiPanelito[] row : subPanels) {
    	    for (MiPanelito subPanel : row) {
    	        if (subPanel.isIsoccupied()) {
    	            System.out.println("oCUPADO");
    	        }
    	    }
    	}

        try {
            Connection connection = DriverManager.getConnection(Variables.url, Variables.user, Variables.pass);
            
            // Eliminar todos los registros existentes en la tabla gui
            String deleteQuery = "DELETE FROM gui";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.executeUpdate();
            deleteStatement.close();
            
            // Preparar la instrucción de inserción
            String insertQuery = "INSERT INTO gui (civilization_id, structure_type, is_occupied, x_position, y_position, ppindex) VALUES (?, ?, ?, ?, ?, ?)";

            for (int y = 0; y < subPanels.length; y++) {
                for (int x = 0; x < subPanels[y].length; x++) {
                    MiPanelito panel = subPanels[y][x];
                    String structureType = panel.getFuture_structure(); // Obtener el tipo de estructura del panel
                    boolean isOccupied = panel.isIsoccupied(); // Verificar si está ocupado
                    int xPosition = x; // Posición X del panel en la matriz
                    int yPosition = y; // Posición Y del panel en la matriz
                    if (isOccupied){
                        // Crear una nueva instancia de PreparedStatement para cada iteración
                        PreparedStatement statement = connection.prepareStatement(insertQuery);
                        // Establecer los valores en la sentencia SQL
                        statement.setInt(1, 1); // civilization_id
                        statement.setString(2, structureType);
                        statement.setBoolean(3, isOccupied); // Convertir a entero
                        statement.setInt(4, xPosition);
                        statement.setInt(5, yPosition);
                        statement.setInt(6, ppindex);

                        // Ejecutar la inserción
                        statement.executeUpdate();
                        // Cerrar la instancia de PreparedStatement
                        statement.close();
                    }
                }
            }

            // Cerrar la conexión
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
