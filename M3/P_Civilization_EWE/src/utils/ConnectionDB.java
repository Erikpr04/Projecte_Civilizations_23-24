package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import interfaces.MilitaryUnit;

public class ConnectionDB {
	
	private String url, user, pass;
	
	public ConnectionDB(String url, String user, String pass){
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	//Metodo para crear una conexion con la bd
	public Connection openConnectionDB() throws MiSQLException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,user,pass);
			return conn;
			
		} catch (SQLException e) {
			throw new MiSQLException("Error en la conexión con la base de datos: " + e.getMessage());
		} catch (Exception e) {
			throw new MiSQLException("Error inesperado: " + e.getMessage());
		}
	}
	
	// metodos para hacer consultas a la base de datos:
	
	public ArrayList<MilitaryUnit> obtenerArmy() throws MiSQLException {
		
        try {
        	Connection conn = openConnectionDB();
            Statement stmnt = conn.createStatement();
            String query = "Select * from army;";
            ArrayList<MilitaryUnit> armyList = new ArrayList<>();
         // ResultSet rs = stmnt.executeQuery(query);
         // while (rs.next()) {
         //     MilitaryUnit unit = new MilitaryUnit(rs.getString("name"), rs.getInt("strength"));
         //     armyList.add(unit);
         // }

            return armyList ;
            
		} catch (SQLException e) {
			throw new MiSQLException("Error al ejecutar la consulta: " + e.getMessage());
		}
	}
	
}




class MiSQLException extends Exception {

    public MiSQLException(String message) {
        super(message);
    }
}
