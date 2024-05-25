package interfaces;

import java.util.Timer;

import exceptions.MiSQLException;
import exceptions.ResourceException;
import gui.Game_gui.MiPanelito;

public interface GameGuiListener {
    void load_game_gui();
    void load_db_data() throws MiSQLException;
    
    //actualizar recursos en base de datos
    
    
    void update_resources_db()throws MiSQLException;
    
    //actualizar recursos gui
    void update_resources();
    
    
    
    void update_army_db( )throws MiSQLException;
 

    void update_technologies();
    
    int[] getcv_army_values(); // primeras 9 posiciones army, posicion 10 attack level, posicion 11 defense level
    void create_troop(int TroopNumber,int Unitstocreate) throws MiSQLException;
    
    
    void create_farm( );
    void create_church( );
    void create_carpentry( );
    void create_smithy();
    void create_magic_tower();
    boolean sanctifyunits();
    
    int getcvchurch();
    int getcvmagic_tower();
	void clear_and_startdb() throws MiSQLException, ResourceException;
	void refresh_battle_logs_reports();
    
}
