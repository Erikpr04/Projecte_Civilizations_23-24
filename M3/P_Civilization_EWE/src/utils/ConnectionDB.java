package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import classes.Civilization;
import classes.attackunits.*;
import classes.defenseunits.*;
import classes.specialunits.*;
import interfaces.MilitaryUnit;
import interfaces.Variables;
import exceptions.BuildingException;
import exceptions.MiSQLException;
import exceptions.ResourceException;

public class ConnectionDB {
	
	private String url;
	private String user;
	private String pass;


	//Mover a variables
	
	public ConnectionDB() {
		this.url = Variables.url;
		this.user = Variables.user;
		this.pass = Variables.pass;

	}

	//Metodo para crear una conexion con la bd
	public Connection openConnectionDB() throws MiSQLException{
		
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(Variables.url,Variables.user,Variables.pass);
			return conn;
			
		} catch (SQLException e) {
			throw new MiSQLException("Error en la conexión con la base de datos: " + e.getMessage());
		} catch (Exception e) {
			throw new MiSQLException("Error inesperado: " + e.getMessage());
		}
	}
	
	//Metodo para insertar el registro de una batalla en la BD (reporte)
	public void insertarBattleStats(int battle_id, int civilizationId, String battleReport) throws MiSQLException {
		
		try {
	        Connection conn = openConnectionDB();
	        String query = "INSERT INTO battle_stats (num_battle,civilization_id, report) VALUES (?, ?, ?)";
	        
	        PreparedStatement ps = conn.prepareStatement(query);
	        ps.setInt(1, battle_id);
	        ps.setInt(2, civilizationId);
	        ps.setString(3, battleReport);
	        
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
	        String query = "SELECT report FROM battle_stats WHERE civilization_id = ? AND num_battle = ?";
	        
	        
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
	        String query = "INSERT INTO Battle_log (civilization_id, num_battle, log_entry) VALUES (?, ?, ?)";
	        
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
	        String query = "SELECT log_entry FROM Battle_log WHERE civilization_id = ? AND num_battle = ?";
	        
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
	
	
	//Metodo para crear una civilization nueva
	public void crearDatosCivilization(String name) throws MiSQLException {
	    try {
	        Connection conn = openConnectionDB();
	        String update = "INSERT INTO civilizations_stats (civilization_id, c_name, wood, iron, food, mana, "
	                      + "farm_amount, smithy_amount, carpentry_amount, church_amount, magicTower_amount, "
	                      + "technology_attack_level, technology_defense_level, battles_count) "
	                      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(update);

	        ps.setInt(1, 1);         // civilization_id
	        ps.setString(2, name);   // c_name
	        ps.setInt(3, 0);         // wood
	        ps.setInt(4, 0);         // iron
	        ps.setInt(5, 0);         // food
	        ps.setInt(6, 0);         // mana
	        ps.setInt(7, 0);         // farm_amount
	        ps.setInt(8, 0);         // smithy_amount
	        ps.setInt(9, 0);         // carpentry_amount
	        ps.setInt(10, 0);        // church_amount
	        ps.setInt(11, 0);        // magicTower_amount
	        ps.setInt(12, 0);        // technology_attack_level
	        ps.setInt(13, 0);        // technology_defense_level
	        ps.setInt(14, 0);        // battles_count

	        int resultado = ps.executeUpdate();
	        System.out.println(resultado + " filas insertadas correctamente. (Civilization)");

	        conn.close();
	    } catch (SQLException e) {
	        throw new MiSQLException("Error al ejecutar la consulta: " + e.getMessage());
	    }
	}

	
	//Metodo para actualizar los datos de la Civilizacion en la base de datos(atributos de la clase civilization)
	public void actualizarDatosCivilization(Civilization civilization) throws MiSQLException {
		
        try {
        	Connection conn = openConnectionDB();
        	String update = "UPDATE civilizations_stats SET wood = ?, iron = ?, food = ?, mana = ?, "
        					  + "farm_amount = ?, smithy_amount = ?, carpentry_amount = ?, church_amount = ?, magicTower_amount = ?, "
        					  + "technology_attack_level = ?, technology_defense_level = ?, battles_count = ? WHERE civilization_id = ? ";
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
            
            //Actualizamos la army en la bd usando la info local
            ArrayList<ArrayList> CivilizationArmy = civilization.getArmy();
            actualizarUnitsBD(CivilizationArmy);
            
            //actualizar datos paneles
            
            
            
            
//          conn.commit();
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
        			+ "technology_attack_level, technology_defense_level, battles_count, c_name FROM civilizations_stats WHERE civilization_id = ?";
        	
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
                civilization.setName(rs.getString("c_name"));
               
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
	
	public void crearUnit(MilitaryUnit unit) throws MiSQLException {
	    try {
	        Connection conn = openConnectionDB(); 
	        PreparedStatement ps = null;

	        if (unit instanceof AttackUnit) {
	            String query = "INSERT INTO attackunits (unit_id, civilization_id, unit_type, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, ((AttackUnit) unit).getUnitId());
	            ps.setInt(2, 1);

	            // Detecta el tipo de unidad correspondiente
	            ps.setInt(3, tipoUnitDB(unit));
	            ps.setInt(4, ((AttackUnit) unit).getArmor());
	            ps.setInt(5, ((AttackUnit) unit).getBaseDamage());
	            ps.setInt(6, 0);

	            // Convertir BOOL a NUMBER(1) (BD)
	            if (((AttackUnit) unit).isSanctified()) {
	                ps.setInt(7, 1);
	            } else {
	                ps.setInt(7, 0);
	            }

	        } else if (unit instanceof DefenseUnit) {
	            String query = "INSERT INTO defenseunits (unit_id, civilization_id, unit_type, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, ((DefenseUnit) unit).getUnitId());
	            ps.setInt(2, 1);

	            // Detecta el tipo de unidad correspondiente
	            ps.setInt(3, tipoUnitDB(unit));
	            ps.setInt(4, ((DefenseUnit) unit).getArmor());
	            ps.setInt(5, ((DefenseUnit) unit).getBaseDamage());
	            ps.setInt(6, 0);

	            // Convertir BOOL a NUMBER(1) (BD)
	            if (((DefenseUnit) unit).isSanctified()) {
	                ps.setInt(7, 1);
	            } else {
	                ps.setInt(7, 0);
	            }

	        } else if (unit instanceof SpecialUnit) {
	            String query = "INSERT INTO specialunits (unit_id, civilization_id, unit_type, armor, base_damage, experience) VALUES (?, ?, ?, ?, ?, ?)";
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, ((SpecialUnit) unit).getUnitId());
	            ps.setInt(2, 1);

	            // Detecta el tipo de unidad correspondiente
	            ps.setInt(3, tipoUnitDB(unit));
	            ps.setInt(4, ((SpecialUnit) unit).getArmor());
	            ps.setInt(5, ((SpecialUnit) unit).getBaseDamage());
	            ps.setInt(6, 0);
	        }

	        // Comprobar que no sea nulo el ps para evitar errores
	        if (ps != null) {
	            int insertRealizado = ps.executeUpdate();
	            System.out.println("insert OK: " + insertRealizado);

	            ps.close();
	        }

	        conn.close();

	    } catch (SQLException e) {
	        throw new MiSQLException("Error al insertar la unidad: " + e.getMessage());
	    }
	}

	
	
	//detecta el tipo de unidad correspondiente para la BD
	private int tipoUnitDB(MilitaryUnit unit){
		
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
	
//METODO PARA ELIMINAR UNIDADES SEGUN ID EN LA BD
	
	public void eliminarUnits(ArrayList<Integer> attackUnitIds, ArrayList<Integer> defenseUnitIds, ArrayList<Integer> specialUnitIds) throws MiSQLException {
	    try {
	        Connection conn = openConnectionDB();
	        int unidadesEliminadas = 0; 
	        // Elimina las unidades de ataque
	        if (!attackUnitIds.isEmpty()) {
	            String query = "DELETE FROM attackunits WHERE unit_id IN (";
	            for (int i = 0; i < attackUnitIds.size(); i++) {
	                query += "?";
	                if (i < attackUnitIds.size() - 1) {
	                    query += ", ";
	                }
	            }
	            query += ")";

	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                for (int i = 0; i < attackUnitIds.size(); i++) {
	                    ps.setInt(i + 1, attackUnitIds.get(i));
	                }
	                unidadesEliminadas += ps.executeUpdate();
	            }
	        }

	        // Elimina las unidades de defensa
	        if (!defenseUnitIds.isEmpty()) {
	            String query = "DELETE FROM defenseunits WHERE unit_id IN (";
	            for (int i = 0; i < defenseUnitIds.size(); i++) {
	                query += "?";
	                if (i < defenseUnitIds.size() - 1) {
	                    query += ", ";
	                }
	            }
	            query += ")";

	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                for (int i = 0; i < defenseUnitIds.size(); i++) {
	                    ps.setInt(i + 1, defenseUnitIds.get(i));
	                }
	                unidadesEliminadas += ps.executeUpdate();
	            }
	        }

	        // Elimina las unidades especiales
	        if (!specialUnitIds.isEmpty()) {
	            String query = "DELETE FROM specialunits WHERE unit_id IN (";
	            for (int i = 0; i < specialUnitIds.size(); i++) {
	                query += "?";
	                if (i < specialUnitIds.size() - 1) {
	                    query += ", ";
	                }
	            }
	            query += ")";

	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                for (int i = 0; i < specialUnitIds.size(); i++) {
	                    ps.setInt(i + 1, specialUnitIds.get(i));
	                }
	                unidadesEliminadas += ps.executeUpdate();
	            }
	        }
	        System.out.println("Total de unidades eliminadas = " +unidadesEliminadas);

	        conn.close();
	    } catch (SQLException e) {
	        throw new MiSQLException("Error al actualizar la base de datos con las unidades eliminadas: " + e.getMessage());
	    }
	}

//METODO PARA ACTUALIZAR LAS UNIDADES RESTANTES EN LA BD
	
	public void actualizarUnitsBD(ArrayList<ArrayList> myArmy) throws MiSQLException {
	    try {
	        Connection conn = openConnectionDB();

	        // Iterar sobre las sublistas de myArmy apra obtener los Grupos
	        for (int i = 0; i < myArmy.size(); i++) {
	            ArrayList<MilitaryUnit> units = myArmy.get(i);

	            // Determina la tabla segun el indice del grupo
	            String table;
	            if (i <= 3) {
	                table = "attackunits";
	            } else if (i <= 6) {
	                table = "defenseunits";
	            } else {
	                table = "specialunits";
	            }

	            // Iterar sobre las unidades en la sublista
	            for (MilitaryUnit unit : units) {
	                // Obtener el ID de la unidad según su tipo
	                int unitId;
	                if (unit instanceof AttackUnit) {
	                    unitId = ((AttackUnit) unit).getUnitId();
	                } else if (unit instanceof DefenseUnit) {
	                    unitId = ((DefenseUnit) unit).getUnitId();
	                } else if (unit instanceof SpecialUnit) {
	                    unitId = ((SpecialUnit) unit).getUnitId();
	                } else {
	                    throw new MiSQLException("Tipo de unidad no válido: " + unit.getClass().getName());
	                }

	                // Actualizar los campos correspondientes en la base de datos
	                String updateQuery;
	                
	                //detecta si es special unit (No tiene sanctified)
	                if (unit instanceof SpecialUnit) {
	                    updateQuery = "UPDATE " + table + " SET armor = ?, experience = ? WHERE unit_id = ?";
	                } else {
	                    updateQuery = "UPDATE " + table + " SET armor = ?, experience = ?, sanctified = ? WHERE unit_id = ?";
	                }
	                	                
	                try (PreparedStatement ps = conn.prepareStatement(updateQuery)) {
	                    ps.setInt(1, unit.getActualArmor());
	                    ps.setInt(2, unit.getExperience());
	                    
	                    //otro if para detectar si es special unit o no
	                    if (!(unit instanceof SpecialUnit)) {
	                    	
	                    	//por defecto 0 (False)
	                    	int sanctified = 0;
	                        if (unit instanceof AttackUnit) {
	                            if (((AttackUnit) unit).isSanctified()){
	                            	sanctified = 1;
	                            }
	                            	                            
	                        } else if (unit instanceof DefenseUnit) {
	                        	if (((DefenseUnit) unit).isSanctified()){
	                            	sanctified = 1;
	                            }
	                        }
	                        ps.setInt(3, sanctified);
	                        ps.setInt(4, unitId);
	                    } else {
	                        ps.setInt(3, unitId);
	                    }
	                    ps.executeUpdate();
	                }
	            }
	        }

	        conn.close();
	    } catch (SQLException e) {
	        throw new MiSQLException("Error al actualizar las unidades en la base de datos: " + e.getMessage());
	    }
	}
	
	//METODO PARA RECUPERAR LA ARMY DE LA BD
	public ArrayList<ArrayList> cargarUnitsBD() throws MiSQLException {
        ArrayList<ArrayList> myArmy = new ArrayList<>();

        // Inicializar la lista con 9 sublistas vacías
        for (int i = 0; i < 9; i++) {
            myArmy.add(new ArrayList<MilitaryUnit>());
        }

        try {
            Connection conn = openConnectionDB();

            // Cargar unidades de ataque
            String queryAttack = "SELECT unit_id, unit_type, armor, base_damage, experience, sanctified FROM attackunits";
            try (PreparedStatement ps = conn.prepareStatement(queryAttack); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int unitId = rs.getInt("unit_id");
                    int unitType = rs.getInt("unit_type");
                    int armor = rs.getInt("armor");
                    int baseDamage = rs.getInt("base_damage");
                    int experience = rs.getInt("experience");
                    boolean sanctified = rs.getInt("sanctified") == 1;

                    MilitaryUnit unit = createAttackUnit(unitId, unitType, armor, baseDamage, experience, sanctified);
                    addUnitToArmy(unit, myArmy);
                }
            }

            // Cargar unidades de defensa
            String queryDefense = "SELECT unit_id, unit_type, armor, base_damage, experience, sanctified FROM defenseunits";
            try (PreparedStatement ps = conn.prepareStatement(queryDefense); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int unitId = rs.getInt("unit_id");
                    int unitType = rs.getInt("unit_type");
                    int armor = rs.getInt("armor");
                    int baseDamage = rs.getInt("base_damage");
                    int experience = rs.getInt("experience");
                    boolean sanctified = rs.getInt("sanctified") == 1;

                    MilitaryUnit unit = createDefenseUnit(unitId, unitType, armor, baseDamage, experience, sanctified);
                    addUnitToArmy(unit, myArmy);
                }
            }

            // Cargar unidades especiales
            String querySpecial = "SELECT unit_id, unit_type, armor, base_damage, experience FROM specialunits";
            try (PreparedStatement ps = conn.prepareStatement(querySpecial); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int unitId = rs.getInt("unit_id");
                    int unitType = rs.getInt("unit_type");
                    int armor = rs.getInt("armor");
                    int baseDamage = rs.getInt("base_damage");
                    int experience = rs.getInt("experience");

                    MilitaryUnit unit = createSpecialUnit(unitId, unitType, armor, baseDamage, experience);
                    addUnitToArmy(unit, myArmy);
                }
            }

            conn.close();
        } catch (SQLException e) {
            throw new MiSQLException("Error al cargar las unidades desde la base de datos: " + e.getMessage());
        }

        return myArmy;
    }

    // Métodos para crear unidades específicas
    private MilitaryUnit createAttackUnit(int unitId, int unitType, int armor, int baseDamage, int experience, boolean sanctified) throws MiSQLException {
        switch (unitType) {
            case 1:
                return new Swordsman(unitId, armor, baseDamage, experience, sanctified);
            case 2:
                return new Spearman(unitId, armor, baseDamage, experience, sanctified);
            case 3:
                return new CrossBow(unitId, armor, baseDamage, experience, sanctified);
            case 4:
                return new Cannon(unitId, armor, baseDamage, experience, sanctified);
            default:
                throw new MiSQLException("Tipo de unidad de ataque no válido: " + unitType);
        }
    }

    private MilitaryUnit createDefenseUnit(int unitId, int unitType, int armor, int baseDamage, int experience, boolean sanctified) throws MiSQLException {
        switch (unitType) {
            case 5:
                return new ArrowTower(unitId, armor, baseDamage, experience, sanctified);
            case 6:
                return new Catapult(unitId, armor, baseDamage, experience, sanctified);
            case 7:
                return new RocketLauncherTower(unitId, armor, baseDamage, experience, sanctified);
            default:
                throw new MiSQLException("Tipo de unidad de defensa no válido: " + unitType);
        }
    }

    private MilitaryUnit createSpecialUnit(int unitId, int unitType, int armor, int baseDamage, int experience) throws MiSQLException {
        switch (unitType) {
            case 8:
                return new Magician(unitId, armor, baseDamage, experience);
            case 9:
                return new Priest(unitId, armor, baseDamage, experience);
            default:
                throw new MiSQLException("Tipo de unidad especial no válido: " + unitType);
        }
    }

    // Método para añadir una unidad a su sublista correspondiente
    private void addUnitToArmy(MilitaryUnit unit, ArrayList<ArrayList> myArmy) throws MiSQLException {
        int index;
        if (unit instanceof Swordsman) {
            index = 0;
        } else if (unit instanceof Spearman) {
            index = 1;
        } else if (unit instanceof CrossBow) {
            index = 2;
        } else if (unit instanceof Cannon) {
            index = 3;
        } else if (unit instanceof ArrowTower) {
            index = 4;
        } else if (unit instanceof Catapult) {
            index = 5;
        } else if (unit instanceof RocketLauncherTower) {
            index = 6;
        } else if (unit instanceof Magician) {
            index = 7;
        } else if (unit instanceof Priest) {
            index = 8;
        } else {
            throw new MiSQLException("Tipo de unidad no válido: " + unit.getClass().getName());
        }
        myArmy.get(index).add(unit);
    }
//Metodos para guardar la info de las unidades de la batalla:
	
	public void insertBattleAttackUnitStats(int civilizationId, int numBattle, int unitType, int initial, int death) throws MiSQLException {
        try {
            Connection conn = openConnectionDB();
            String query = "INSERT INTO battle_attackunits_stats (civilization_id, num_battle, unit_type, c_initial, death) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, civilizationId);
            ps.setInt(2, numBattle);
            ps.setInt(3, unitType);
            ps.setInt(4, initial);
            ps.setInt(5, death);
            ps.executeUpdate();
            System.out.println("Se ha insertado el registro en la tabla battle_attackunits_stats.");
            conn.close();
        } catch (SQLException e) {
            throw new MiSQLException("Error al ejecutar la inserción: " + e.getMessage());
        }
	}
	public void insertBattleDefenseUnitStats(int civilizationId, int numBattle, int unitType, int initial, int death) throws MiSQLException {
        try {
            Connection conn = openConnectionDB();
            String query = "INSERT INTO battle_defenseunits_stats (civilization_id, num_battle, unit_type, c_initial, death) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, civilizationId);
            ps.setInt(2, numBattle);
            ps.setInt(3, unitType);
            ps.setInt(4, initial);
            ps.setInt(5, death);
            ps.executeUpdate();
            System.out.println("Se ha insertado el registro en la tabla battle_defenseunits_stats.");
            conn.close();
        } catch (SQLException e) {
            throw new MiSQLException("Error al ejecutar la inserción: " + e.getMessage());
        }
    }
	public void insertBattleSpecialUnitStats(int civilizationId, int numBattle, int unitType, int initial, int death) throws MiSQLException {
        try {
            Connection conn = openConnectionDB();
            String query = "INSERT INTO battle_specialunits_stats (civilization_id, num_battle, unit_type, c_initial, death) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, civilizationId);
            ps.setInt(2, numBattle);
            ps.setInt(3, unitType);
            ps.setInt(4, initial);
            ps.setInt(5, death);
            ps.executeUpdate();
            System.out.println("Se ha insertado el registro en la tabla battle_specialunits_stats.");
            conn.close();
        } catch (SQLException e) {
            throw new MiSQLException("Error al ejecutar la inserción: " + e.getMessage());
        }
    }
	
	public void insertBattleEnemyUnitStats(int civilizationId, int numBattle, int unitType, int initial, int death) throws MiSQLException {
        try {
            Connection conn = openConnectionDB();
            String query = "INSERT INTO enemy_attack_stats (civilization_id, num_battle, unit_type, c_initial, death) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, civilizationId);
            ps.setInt(2, numBattle);
            ps.setInt(3, unitType);
            ps.setInt(4, initial);
            ps.setInt(5, death);
            ps.executeUpdate();
            System.out.println("Se ha insertado el registro en la tabla enemy_attack_stats.");
            conn.close();
        } catch (SQLException e) {
            throw new MiSQLException("Error al ejecutar la inserción: " + e.getMessage());
        }
    }	
}



