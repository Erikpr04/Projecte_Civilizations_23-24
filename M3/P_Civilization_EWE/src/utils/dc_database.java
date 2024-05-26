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
        try {
            Connection connection = DriverManager.getConnection(Variables.url, Variables.user, Variables.pass);

            // Preparar la consulta SELECT para verificar la existencia de cada panel
            String selectQuery = "SELECT COUNT(*) FROM gui WHERE x_position = ? AND y_position = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);

            // Preparar la instrucción de inserción
            String insertQuery = "INSERT INTO gui (civilization_id, structure_type, is_occupied, x_position, y_position, ppindex) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            for (int y = 0; y < subPanels.length; y++) {
                for (int x = 0; x < subPanels[y].length; x++) {
                    MiPanelito panel = subPanels[y][x];
                    boolean isOccupied = panel.isIsoccupied(); // Verificar si está ocupado

                    // Solo insertar si el panel está ocupado
                    if (isOccupied) {
                        int xPosition = x; // Posición X del panel en la matriz
                        int yPosition = y; // Posición Y del panel en la matriz

                        // Configurar parámetros para la consulta SELECT
                        selectStatement.setInt(1, xPosition);
                        selectStatement.setInt(2, yPosition);

                        // Ejecutar la consulta SELECT
                        ResultSet resultSet = selectStatement.executeQuery();
                        resultSet.next();
                        int rowCount = resultSet.getInt(1);

                        if (rowCount == 0) {
                            // Si no hay filas con las mismas coordenadas, proceder con la inserción
                            insertStatement.setInt(1, 1); // civilization_id
                            insertStatement.setString(2, panel.getFuture_structure());
                            insertStatement.setBoolean(3, isOccupied);
                            insertStatement.setInt(4, xPosition);
                            insertStatement.setInt(5, yPosition);
                            insertStatement.setInt(6, ppindex);

                            // Ejecutar la inserción
                            insertStatement.executeUpdate();
                        }
                    }
                }
            }

            // Cerrar los statements y la conexión
            selectStatement.close();
            insertStatement.close();
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
