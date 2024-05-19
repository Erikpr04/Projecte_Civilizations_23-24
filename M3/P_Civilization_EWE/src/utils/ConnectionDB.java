package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classes.Civilization;
import classes.attackunits.*;
import classes.defenseunits.*;
import classes.specialunits.*;
import interfaces.MilitaryUnit;
import interfaces.Variables;
import exceptions.MiSQLException;

public class ConnectionDB {
	
	private String url;
	private String user;
	private String pass;


	//Mover a variables
	
	public ConnectionDB(String url, String user, String pass){
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	//Metodo para crear una conexion con la bd
	public Connection openConnectionDB() throws MiSQLException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(Variables.url,Variables.user,Variables.pass);
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
	
	//Metodo para almacenar el ejercito en la bd
	
//	public void guardarArmy(Civilization civilization) throws MiSQLException {
//
//		
//	}
	
	public void guardarUnit(MilitaryUnit unit) throws MiSQLException {
		
		try {
			
			Connection conn = openConnectionDB(); 
			PreparedStatement ps = null;
			
			if (unit instanceof AttackUnit) {
				
				String query = "INSERT INTO attackunits (unit_id, civilization_id, unit_type, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, ((AttackUnit) unit).getUnitId());
	            ps.setInt(2, 1);
	            
	            //detecta el tipo de unidad correspondiente
	            ps.setInt(3, tipoUnitDB(unit));
	            ps.setInt(4, ((AttackUnit) unit).getArmor());
	            ps.setInt(5, ((AttackUnit) unit).getBaseDamage());
	            ps.setInt(6, ((AttackUnit) unit).getExperience());
	            ps.setBoolean(7, ((AttackUnit) unit).isSanctified());
	
	            
				
			} else if (unit instanceof DefenseUnit) {
				
				String query = "INSERT INTO defenseunits (unit_id, civilization_id, unit_type, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, ((DefenseUnit) unit).getUnitId());
	            ps.setInt(2, 1);
	            
	            //detecta el tipo de unidad correspondiente
	            ps.setInt(3, tipoUnitDB(unit));
	            ps.setInt(4, ((DefenseUnit) unit).getArmor());
	            ps.setInt(5, ((DefenseUnit) unit).getBaseDamage());
	            ps.setInt(6, ((DefenseUnit) unit).getExperience());
	            ps.setBoolean(7, ((DefenseUnit) unit).isSanctified());
				
			} else if (unit instanceof SpecialUnit) {
				
				String query = "INSERT INTO specialunits (unit_id, civilization_id, unit_type, armor, base_damage, experience) VALUES (?, ?, ?, ?, ?, ?)";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, ((SpecialUnit) unit).getUnitId());
	            ps.setInt(2, 1);
	            
	            //detecta el tipo de unidad correspondiente
	            ps.setInt(3, tipoUnitDB(unit));
	            ps.setInt(4, ((SpecialUnit) unit).getArmor());
	            ps.setInt(5, ((SpecialUnit) unit).getBaseDamage());
	            ps.setInt(6, ((SpecialUnit) unit).getExperience());
				
			}
			
			//comprobar que no sea nulo el ps apra evitar errores
			if (ps != null) {
				int insertRealizado = ps.executeUpdate();
				System.out.println("insert OK: "+ insertRealizado);
				
				ps.close();
			}
			
			conn.close();
			
		}catch (Exception e) {
			throw new MiSQLException("Error al insertar la unidad: " + e.getMessage());
		}
		
		
	}
	
	
	//detecta el tipo de unidad correspondiente para la BD
	public int tipoUnitDB(MilitaryUnit unit){
		
		int id = 0;
		
		if (unit instanceof AttackUnit) {
			
			if (unit instanceof Swordsman) {
				id = 1;
			}else if (unit instanceof Spearman) {
				id = 2;
				
			}else if (unit instanceof CrossBow) {
				id = 3;
				
			}else if (unit instanceof Cannon) {
				id = 4;
			}
			
		}else if (unit instanceof DefenseUnit) {
			
			if (unit instanceof ArrowTower) {
				id = 5;
				
			}else if (unit instanceof Catapult) {
				id = 6;
				
			}else if (unit instanceof RocketLauncherTower) {
				id = 7;
			}
			
		}else if (unit instanceof SpecialUnit) {
			
			if (unit instanceof Magician) {
				id = 8;
				
			}else if (unit instanceof Priest) {
				id = 9;
			}
		}
		
		return id;
	}
	
	
	//pruebas locales 
	
	public static void main(String[] args){
		
		
		 ConnectionDB cdb = new ConnectionDB(Variables.url, Variables.user, Variables.pass);
		 try {
			Swordsman sw1 = new Swordsman(100,100);
			System.out.println("ID Unidad 1: " + sw1.getUnitId());
			cdb.guardarUnit(sw1);
			System.out.println("Unidad 1 guardada");
			
			Spearman sp1 = new Spearman(100,100);
			System.out.println("ID Unidad 2: " + sp1.getUnitId());
			cdb.guardarUnit(sp1);
			System.out.println("Unidad 2 guardada");
			
			
			ArrowTower at1 = new ArrowTower(100, 100);
			System.out.println("ID Unidad 3: " + at1.getUnitId());
			cdb.guardarUnit(at1);
			System.out.println("Unidad 3 guardada");
			
			Magician m1 = new Magician(100, 100);
			System.out.println("ID Unidad 4: " + m1.getUnitId());
			cdb.guardarUnit(m1);
			System.out.println("Unidad 4 guardada");
			
		} catch (MiSQLException e) {
			
			e.printStackTrace();
		}
	}


	
	

	
}



