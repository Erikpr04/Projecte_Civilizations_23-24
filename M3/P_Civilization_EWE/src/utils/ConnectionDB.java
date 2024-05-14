package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classes.Civilization;
import interfaces.MilitaryUnit;

public class ConnectionDB {
	
	private String url = "jdbc:mysql://localhost/civilizationewe?serverTimezone=UTC&autoReconnect=true&useSSL=false";
	private String user = "root";
	private String pass = "P@ssw0rd";
	
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
	
	public void actualizarDatosCivilization(Civilization civilization) throws MiSQLException {
		
        try {
        	Connection conn = openConnectionDB();
        	String update = "UPDATE civilizations_stats SET wood = ?, iron = ?, food = ?, mana = ?, "
        					  + "farm_amount = ?, smithy_amount = ?, carpentry_amount = ?, church_amount = ?, magicTower_amount = ?, "
        					  + "technology_attack_level = ?, technology_defense_level = ?, battles_count = ? WHERE civilization_id = ?  ;";
            PreparedStatement ps = conn.prepareStatement(update);
            
            ps.setInt(1, civilization.getTechnologyDefense());
            ps.setInt(2, civilization.getTechnologyAttack());
            ps.setInt(3, civilization.getWood());
            ps.setInt(4, civilization.getIron());
            ps.setInt(5, civilization.getFood());
            ps.setInt(6, civilization.getMana());
            ps.setInt(7, civilization.getMagicTower());
            ps.setInt(8, civilization.getFarm());
            ps.setInt(9, civilization.getChurch());
            ps.setInt(10, civilization.getSmithy());
            ps.setInt(11, civilization.getCarpentry());
            ps.setInt(12, civilization.getBattles());
            ps.setInt(13, civilization.getId());
            
            int resultado = ps.executeUpdate();
            System.out.println(resultado + " filas actualizadas correctamente.");
            
            conn.commit();
            conn.close();
         // ResultSet rs = stmnt.executeQuery(query);
         // while (rs.next()) {
         //     MilitaryUnit unit = new MilitaryUnit(rs.getString("name"), rs.getInt("strength"));
         //     armyList.add(unit);
         // }
   
            
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
