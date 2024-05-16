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
	
	//Mover a variables
	private String url = "jdbc:mysql://localhost/civilizationewe?serverTimezone=UTC&autoReconnect=true&useSSL=false";
	private String user = "AdminCivilization";
	private String pass = "ewe";
	
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
	
	//Metodo para insertar el registro de una batalla en la BD (reporte)
	public void insertarBattleStats(int civilizationId, String battleReport) throws MiSQLException {
		
		try {
	        Connection conn = openConnectionDB();
	        String query = "INSERT INTO battle_stats (civilization_id, report) VALUES (?, ?)";
	        
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, civilizationId);
	        ps.setString(2, battleReport);
	        
	        int rowsAffected = ps.executeUpdate();
	        
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
	
	//Metodo para recuperar el registro de una batalla en la BD (reporte)
	public String sacarBattleStats(int civilizationId, int battleId) throws MiSQLException {
		
		String stats ="";	
		try {
	        Connection conn = openConnectionDB();
	        String query = "SELECT report FROM battle_stats WHERE civilization_id = ? AND num_battle = ?;";
	        
	        
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, civilizationId);
	        ps.setInt(2, battleId);
	        
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            stats = rs.getString("report");
	            //System.out.println(stats);
	            
	        } else {
	            System.out.println("No se encontraron registros para civilization_id = " + civilizationId +
	                               " y num_battle = " + battleId);
	        }
	        
	        conn.close();
	    } catch (SQLException e) {
	        throw new MiSQLException("Error al ejecutar la consulta: " + e.getMessage());
	    }
		return stats;
		
	}

	//Metodo para insertar el desarrollo de una batalla en la BD (log)
	public void insertarBattleLog(int civilizationId, int battleId, String logEntry) throws MiSQLException {
	    try {
	        Connection conn = openConnectionDB();
	        String query = "INSERT INTO battle_log (civilization_id, num_battle, log_entry) VALUES (?, ?, ?)";
	        
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, civilizationId);
	        ps.setInt(2, battleId);
	        ps.setString(3, logEntry);
	        
	        int rowsAffected = ps.executeUpdate();
	        
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
	
	
	//Metodo para recuperar el desarrollo de una batalla en la BD (log)
	public String sacarBattleLog(int civilizationId, int battleId) throws MiSQLException {
	    
		String logEntry = "";
		try {
	        Connection conn = openConnectionDB();
	        String query = "SELECT log_entry FROM battle_log WHERE civilization_id = ? AND num_battle = ?";
	        
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, civilizationId);
	        ps.setInt(2, battleId);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            logEntry = rs.getString("log_entry");
	            //System.out.println(logEntry);
	        } else {
	            System.out.println("No se encontraron registros para civilization_id = " + civilizationId +
	                               " y num_battle = " + battleId);
	        }
	        
	        conn.close();
	    } catch (SQLException e) {
	        throw new MiSQLException("Error al ejecutar la consulta: " + e.getMessage());
	    }
		
		return logEntry;
	}

	
	//Metodo para actualizar los datos de la Civilizacion en la base de datos(atributos de la clase civilization)
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
            System.out.println(resultado + " filas actualizadas correctamente. (Civilization)");
            
//            conn.commit();
            conn.close();

   
            
		} catch (SQLException e) {
			throw new MiSQLException("Error al ejecutar la consulta: " + e.getMessage());
		}
	}
	
	//Metodo para recuperar los datos de la Civilizacion guardada en la bd (atributos de la clase civilization)
	public void obtenerDatosCivilization(Civilization civilization) throws MiSQLException {
		
        try {
        	Connection conn = openConnectionDB();
        	String update = "SELECT wood, iron, food, mana, farm_amount, smithy_amount, carpentry_amount, church_amount, magicTower_amount, "
        			+ "technology_attack_level, technology_defense_level, battles_count FROM civilizations_stats WHERE civilization_id = ?;";
        	
        	int civilizationId = 1;
        	
            PreparedStatement ps = conn.prepareStatement(update);
            ps.setInt(1, civilizationId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	//recuperar valores de cada columna de la bd y actualizar los datos de civilizacion
            	civilization.setWood(rs.getInt("wood"));
                civilization.setIron(rs.getInt("iron"));
                civilization.setFood(rs.getInt("food"));
                civilization.setMana(rs.getInt("mana"));
                civilization.setFarm(rs.getInt("farm_amount"));
                civilization.setSmithy(rs.getInt("smithy_amount"));
                civilization.setCarpentry(rs.getInt("carpentry_amount"));
                civilization.setChurch(rs.getInt("church_amount"));
                civilization.setMagicTower(rs.getInt("magicTower_amount"));
                civilization.setTechnologyAttack(rs.getInt("technology_attack_level"));
                civilization.setTechnologyDefense(rs.getInt("technology_defense_level"));
                civilization.setBattles(rs.getInt("battles_count"));
               
                System.out.println("Datos recuperados correctamente.");
            } else {
            	System.out.println("No se encontraron registros");
            }
            
//          conn.commit();
            conn.close();
 
		} catch (SQLException e) {
			throw new MiSQLException("Error al ejecutar la consulta: " + e.getMessage());
		}
	}
	
	public static void main(String[] args){
	    String url = "jdbc:mysql://localhost/civilizationewe?serverTimezone=UTC&autoReconnect=true&useSSL=false";
	    String user = "AdminCivilization";
	    String pass = "ewe"; 

	    ConnectionDB cbd = new ConnectionDB(url, user, pass);
	    Civilization cv = new Civilization();
	    
	    cv.setWood(10000);
        cv.setIron(10000);
        cv.setFood(10000);
        cv.setMana(10000);
        cv.setFarm(4);
        cv.setSmithy(3);
        cv.setCarpentry(3);
        cv.setChurch(1);
        cv.setMagicTower(2);
        cv.setTechnologyAttack(4);
        cv.setTechnologyDefense(5);
        cv.setBattles(22);
        
        System.out.println("Datos viejos: ");
        System.out.println(cv.getWood());
        System.out.println(cv.getIron());
        System.out.println(cv.getFood());
        System.out.println(cv.getMana());
        System.out.println(cv.getFarm());
        System.out.println(cv.getSmithy());
        System.out.println(cv.getCarpentry());
        System.out.println(cv.getChurch());
        System.out.println(cv.getMagicTower());
        System.out.println(cv.getTechnologyAttack());
        System.out.println(cv.getTechnologyDefense());
        System.out.println(cv.getBattles());      
        System.out.println("__________________________\n");

//	    try {
//	        // Establecer la conexión con la base de datos
//	        Connection conn = cbd.openConnectionDB();
//	        System.out.println("Conexion OK");
//	        cbd.obtenerDatosCivilization(cv);
//	        System.out.println("Datos OK");
//	        
//	        
//	        
//	        
//	            try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//	       
//	 
//	    } catch (MiSQLException e) {
//	        System.out.println("Error al establecer la conexión: " + e.getMessage());
//	    }
	    
	    System.out.println("Datos nuevos: ");
        System.out.println(cv.getWood());
        System.out.println(cv.getIron());
        System.out.println(cv.getFood());
        System.out.println(cv.getMana());
        System.out.println(cv.getFarm());
        System.out.println(cv.getSmithy());
        System.out.println(cv.getCarpentry());
        System.out.println(cv.getChurch());
        System.out.println(cv.getMagicTower());
        System.out.println(cv.getTechnologyAttack());
        System.out.println(cv.getTechnologyDefense());
        System.out.println(cv.getBattles());      
        System.out.println("__________________________\n");
        
        

        try {
        	
			cbd.actualizarDatosCivilization(cv);
			System.out.println("Civilization actualizado con nuevos datos en bd");
		} catch (MiSQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
}



