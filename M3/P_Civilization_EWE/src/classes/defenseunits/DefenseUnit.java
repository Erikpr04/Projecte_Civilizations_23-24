package classes.defenseunits;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.MiSQLException;
import interfaces.MilitaryUnit;
import interfaces.Variables;
import utils.ConnectionDB;

public abstract class DefenseUnit implements MilitaryUnit, Variables{
	
	//Atributos
	private int armor;
	private int initialArmor;
	private int baseDamage;
	private int experience;
	private boolean sanctified;
	
	private int unitId;
	
	//CONSTRUCTOR 1
	
	public DefenseUnit(int armor, int baseDamage) throws MiSQLException {
		super();
		this.armor = armor;
		this.initialArmor = armor;
		this.baseDamage = baseDamage;
		this.experience = 0;
		this.sanctified = false;
		this.unitId = lastIdDefenseUnit();
	}

	
	//GETTERS Y SETTERS
	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getInitialArmor() {
		return initialArmor;
	}

	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public boolean isSanctified() {
		return sanctified;
	}

	public void setSanctified(boolean sanctified) {
		this.sanctified = sanctified;
	}
	
	
	public int getUnitId() {
		return unitId;
	}
	//metodo para calcular la ultima id
	public int lastIdDefenseUnit() throws MiSQLException {
		int last_id = 0;
		
		try {
			ConnectionDB cbd = new ConnectionDB(Variables.url, Variables.user, Variables.pass);
			Connection conn = cbd.openConnectionDB();
			
			String query = "SELECT MAX(unit_id) AS last_id FROM defenseunits";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				last_id =  rs.getInt("last_Id");
			}
			
			//cerramos consutas y conexion
			rs.close();
			stmt.close();
			conn.close();
			
			
		}catch (SQLException e) {
			throw new MiSQLException("Error al obtener el ultimo id: " + e.getMessage());
		}
		

		return last_id +1;
		
	}
	
	
}
