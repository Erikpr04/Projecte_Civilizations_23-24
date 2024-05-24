package interfaces;

import java.util.Timer;

import exceptions.MiSQLException;

public interface GameGuiListener {
    void load_game_gui();
    void load_db_data();
    
    //actualizar recursos en base de datos
    
    
    void update_resources_db(int food,int wood, int iron, int mana);
    
    

    
    
    //actualizar recursos gui
    void update_resources(int food,int wood, int iron, int mana);
    
    
    
    void update_army_db();
    
    void update_structures_db(String structuretype,int number_structures);

    void update_technologies_db();
    
    int[] getcv_army_values(); // primeras 9 posiciones army, posicion 10 attack level, posicion 11 defense level
    void create_troop(int TroopNumber,int Unitstocreate) throws MiSQLException;
    
    
    void create_farm();
    void create_church();
    void create_carpentry();
    void create_smithy();
    void create_magic_tower();
    boolean sanctifyunits();
    
}
