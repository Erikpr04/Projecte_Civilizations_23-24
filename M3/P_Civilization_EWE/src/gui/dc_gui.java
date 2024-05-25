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
import javax.swing.JOptionPane;

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
	private int profileindex = 1;
	private JFrame GameFrame;

	
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
		        invoke_game_gui();	
				if (!load_game()) {
				    JOptionPane.showMessageDialog(null, "Error: no game loaded in database", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else {
			        mainMenuFrame.dispose(); 
			        GameFrame.setVisible(true);

				}



			}

			public void startnewgame(String username1,int photoindex1) throws MiSQLException {
				
				
				
				//borrar datos de la bd si hay
		        mainMenuFrame.dispose(); // Dispose of the main menu frame
		        username = username1;
		        profileindex = photoindex1;
		        invoke_game_gui();
		        ggl.clear_and_startdb(username,photoindex1);
		        
		        ggl.load_game_gui();
		        GameFrame.setVisible(true);
		        
				
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
    	
        GameFrame = new JFrame("Game GUI");
        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
			gui_obj = new Game_gui(10, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        gui_obj.setListener(this.ggl);
        
        GameFrame.add(gui_obj, BorderLayout.CENTER);

        GameFrame.pack();
        GameFrame.setLocationRelativeTo(null);

                
        gui_obj.setUsername(username);
        gui_obj.setPpindex(profileindex);



        
    }


    //metodo para actualizar recursos en game gui
    
    
    
    
    //metodo cargar partida
    
    public boolean load_game()  {
    	try {
	    	ggl.load_game_gui();
			ggl.load_db_data();
		} catch (Exception e) {
			return false;
		}
		return true;

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
