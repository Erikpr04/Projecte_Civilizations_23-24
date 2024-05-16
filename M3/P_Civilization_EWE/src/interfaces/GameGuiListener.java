package interfaces;

public interface GameGuiListener {
    void load_game_gui();
    
    //actualizar recursos en base de datos
    
    
    void update_resources_db(int food,int wood, int iron, int mana);
    
    

    
    
    //actualizar recursos gui
    void update_resources(int food,int wood, int iron, int mana);
    
    
    
    void update_army_db(int tipo_tropa);
    
    void update_structures_db(String structuretype,int number_structures);

    void update_technologies_db(int attack_technology,int defense_technology);
    
}
