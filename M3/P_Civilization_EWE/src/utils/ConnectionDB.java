package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classes.Civilization;
import interfaces.MilitaryUnit;
import exceptions.MiSQLException;

public class ConnectionDB {
	
	private String url = "jdbc:mysql://localhost/civilizationEWE?serverTimezone=UTC&autoReconnect=true&useSSL=false";
	private String user = "AdminCivilization";
	private String pass = "Civi1234";
	
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
	public void insertarBattleLog(int civilizationId, int battleId, String logEntry) throws MiSQLException {
	    try {
	        Connection conn = openConnectionDB();
	        String query = "INSERT INTO Battle_log (civilization_id, num_battle, log_entry) VALUES (?, ?, ?)";
	        
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, civilizationId);
	        pstmt.setInt(2, battleId);
	        pstmt.setString(3, logEntry);
	        
	        int rowsAffected = pstmt.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Se ha insertado el registro en la tabla battle_log.");
	        } else {
	            System.out.println("No se ha insertado el registro en la tabla battle_log.");
	        }
	        
	        conn.close();
	    } catch (SQLException e) {
	        throw new MiSQLException("Error al ejecutar la inserción: " + e.getMessage());
	    }
	}

	
	public void sacarBattleLog(int civilizationId, int battleId) throws MiSQLException {
	    try {
	        Connection conn = openConnectionDB();
	        String query = "SELECT log_entry FROM Battle_log WHERE civilization_id = ? AND num_battle = ?";
	        
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, civilizationId);
	        pstmt.setInt(2, battleId);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            String logEntry = rs.getString("log_entry");
	            System.out.println(logEntry);
	        } else {
	            System.out.println("No se encontraron registros para civilization_id = " + civilizationId +
	                               " y num_battle = " + battleId);
	        }
	        
	        conn.close();
	    } catch (SQLException e) {
	        throw new MiSQLException("Error al ejecutar la consulta: " + e.getMessage());
	    }
	}

	public void actualizarDatosCivilization(Civilization civilization) throws MiSQLException {
		
        try {
        	Connection conn = openConnectionDB();
        	String update = "UPDATE civilizations_stats SET wood = ?, iron = ?, food = ?, mana = ?, "
        					  + "farm_amount = ?, smithy_amount = ?, carpentry_amount = ?, church_amount = ?, magicTower_amount = ?, "
        					  + "technology_attack_level = ?, technology_defense_level = ?, battles_count = ? WHERE civilization_id = ?  ;";
            PreparedStatement ps = conn.prepareStatement(update);
            

            ps.setInt(1, civilization.getWood());
            ps.setInt(2, civilization.getIron());
            ps.setInt(3, civilization.getFood());
            ps.setInt(4, civilization.getMana());
            ps.setInt(5, civilization.getMagicTower());
            ps.setInt(6, civilization.getFarm());
            ps.setInt(7, civilization.getChurch());
            ps.setInt(8, civilization.getSmithy());
            ps.setInt(9, civilization.getCarpentry());
            ps.setInt(10, civilization.getBattles());
            ps.setInt(11, civilization.getTechnologyAttack());
            ps.setInt(12, civilization.getTechnologyDefense());
            ps.setInt(13, 1);
            
            int resultado = ps.executeUpdate();
            System.out.println(resultado + " filas actualizadas correctamente.");
            
//            conn.commit();
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
	
	public static void main(String[] args){
	    String url = "jdbc:mysql://localhost/civilizationEWE?serverTimezone=UTC&autoReconnect=true&useSSL=false";
	    String user = "AdminCivilization";
	    String pass = "Civi1234";

	    ConnectionDB cbd = new ConnectionDB(url, user, pass);

	    try {
	        // Establecer la conexión con la base de datos
	        Connection conn = cbd.openConnectionDB();
	        if (conn != null) {
	        	cbd.sacarBattleLog(1, 1);
	            try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        } else {
	            System.out.println("Error al conectar con la base de datos.");
	        }
	    } catch (MiSQLException e) {
	        System.out.println("Error al establecer la conexión: " + e.getMessage());
	    }
	}

	
}



