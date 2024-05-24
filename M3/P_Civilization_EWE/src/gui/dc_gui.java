package gui;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import classes.Civilization;
import exceptions.MiSQLException;
import gui.Game_gui.MiPanelito;
import interfaces.GameGuiListener;
import interfaces.MainMenuListener;
import utils.dc_database;

public class dc_gui  {
    private int food, wood, iron, mana;
    private dc_database dc_database;
    private MainMenu mainMenu;
    private JFrame mainMenuFrame;
    private MainMenuListener mmi;
    private GameGuiListener ggl;

	private Game_gui gui_obj;
	private String username = "username";
	private String profileindex = "3";

	
    public Game_gui getGui_obj() {
		return gui_obj;
	}


	public void setGui_obj(Game_gui gui_obj) {
		this.gui_obj = gui_obj;
	}

	private Civilization civilization;

	


	
	

    


	public dc_gui(Civilization civilization) {
        super();
        this.civilization = civilization;



    	mmi = new MainMenuListener() {
			



			@Override
			public void loadgame() throws MiSQLException {
		        mainMenuFrame.dispose(); 
		        invoke_game_gui();	
				load_game();

			}

			public void startnewgame(String username1,String photoindex) {
				
				
				username = username1;
				profileindex = photoindex;
				
				//borrar datos de la bd si hay
		        mainMenuFrame.dispose(); // Dispose of the main menu frame
		        invoke_game_gui();
		        ggl.clear_and_startdb();
		        ggl.load_game_gui();
		        
				
			}

		};
		

        this.dc_database = new dc_database();
    }


    // Método para invocar el menú principal
    public void invoke_main_menu() {
        mainMenu = new MainMenu();
        mainMenu.setMainMenuListener(mmi);
        mainMenuFrame = new JFrame("Main Menu");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.add(mainMenu);
        mainMenuFrame.pack();
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
    }

    // Método para invocar la GUI del juego
    public void invoke_game_gui() {
    	
    	Timer timer = new Timer();


        JFrame frame = new JFrame("Game GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
			gui_obj = new Game_gui(10, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        gui_obj.setListener(this.ggl);
        
        frame.add(gui_obj, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
                
        gui_obj.setUsername(username);
        gui_obj.setPpindex(profileindex);



        
    }


    //metodo para actualizar recursos en game gui
    
    
    
    
    //metodo cargar partida
    
    public void load_game() throws MiSQLException {
    	ggl.load_game_gui();
    	ggl.load_db_data();

    }
    
    public void update_army_db(int tipo_tropa) {

    }
    
    public void update_structures_db(int number_structures) {

    }
    public void update_technologies_db(int attack_technology,int defense_technology) {

    }
    
    
    
    
    //metodo para actualizar casillas en base de datos
    
    
    
    
    
    //setters y getters
    
	public GameGuiListener getGgl() {
		return ggl;
	}

	public void setGgl(GameGuiListener ggl) {
		this.ggl = ggl;
	}

    

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}







	



	
	
	
	
	

}
