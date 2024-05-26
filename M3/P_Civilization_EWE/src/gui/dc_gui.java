package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import classes.Civilization;
import exceptions.MiSQLException;
import exceptions.ResourceException;
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
    private ImageIcon gamelogo = new ImageIcon("./src/gui/game_logo.png");


	
    public Game_gui getGui_obj() {
		return gui_obj;
	}


	public void setGui_obj(Game_gui gui_obj) {
		this.gui_obj = gui_obj;
	}

	private Civilization civilization;

	
	

	
	

    


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getProfileindex() {
		return profileindex;
	}


	public void setProfileindex(int profileindex) {
		this.profileindex = profileindex;
	}


	public dc_gui(Civilization civilization) {
        super();
        this.civilization = civilization;



    	mmi = new MainMenuListener() {
			



			@Override
			public void loadgame() throws MiSQLException {
		        invoke_game_gui();	
				if (!load_game()) {
				    JOptionPane.showMessageDialog(null, "Error loading Database Data", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else {
			        mainMenuFrame.dispose(); 
			        GameFrame.setVisible(true);

				}



			}

			public void startnewgame(String username1,int photoindex1) throws MiSQLException, ResourceException {
				
				
				
				//borrar datos de la bd si hay
		        mainMenuFrame.dispose(); 
		        username = username1;
		        profileindex = photoindex1;
		        invoke_game_gui();
		        ggl.clear_and_startdb();
		        ggl.load_game_gui();
		        gui_obj.setPpindex(photoindex1);
		        GameFrame.setVisible(true);
		        gui_obj.createAndShowTutorial();
		        
		        if (gui_obj.getSubPanels() != null && gui_obj.getSubPanels().length > 0 && gui_obj.getSubPanels()[0].length > 0) {
		            Random random = new Random();

		            // Seleccionar una coordenada y aleatoria que no esté ocupada
		            int y;
		            do {
		                y = random.nextInt(gui_obj.getSubPanels().length);
		            } while (gui_obj.getSubPanels()[y][0].isIsoccupied());

		            // Seleccionar una coordenada x inicial aleatoria para el río, asegurando que haya espacio para los tiles del río

		            // Iterar sobre los tiles del río
		            for (int i = 0; i < gui_obj.getSubPanels()[0].length; i++) {
		                // Verificar que el panel no esté ocupado
		                if (!gui_obj.getSubPanels()[y][i].isIsoccupied()) {
		                    // Establecer la nueva textura principal
		                	gui_obj.getSubPanels()[y][i].setIsoccupied(true);
		                    gui_obj.getSubPanels()[y][i].setFuture_structure("River");
		                    gui_obj.getSubPanels()[y][i].setCurrentImage(new ImageIcon("./src/gui/rivertile.png"));

		                    // Revalidar y repintar el panel para asegurarse de que se apliquen los cambios
		                    gui_obj.getSubPanels()[y][i].revalidate();
		                    gui_obj.getSubPanels()[y][i].repaint();
		                } else {
		                    // Si encuentras un panel ocupado, termina la creación del río
		                    break;
		                }
		            }
		        }
		        
		        if (gui_obj.getSubPanels() != null && gui_obj.getSubPanels().length > 0 && gui_obj.getSubPanels()[0].length > 0) {
		            Random random = new Random();
		            int numberOfTrees = 15; 

		            for (int i = 0; i < numberOfTrees; i++) {
		                int x, y;
		                
		                do {
		                    x = random.nextInt(gui_obj.getSubPanels()[0].length);
		                    y = random.nextInt(gui_obj.getSubPanels().length);
		                } while (gui_obj.getSubPanels()[y][x].isIsoccupied());

		                // Establecer la nueva textura principal
		                gui_obj.getSubPanels()[y][x].setIsoccupied(true);
		                gui_obj.getSubPanels()[y][x].setFuture_structure("Tree");
		                gui_obj.getSubPanels()[y][x].setCurrentImage(new ImageIcon("./src/gui/tree.png"));

		                // Revalidar y repintar el panel para asegurarse de que se apliquen los cambios
		                gui_obj.getSubPanels()[y][x].revalidate();
		                gui_obj.getSubPanels()[y][x].repaint();
		            }
		        }
		        
		        
		        
		        // Verificar que el array subPanels no sea null y que tenga al menos una columna
		        if (gui_obj.getSubPanels() != null && gui_obj.getSubPanels().length > 0 && gui_obj.getSubPanels()[1].length > 0) {
		            // Iterar sobre la primera columna (índice 0) de subPanels
		            for (int x = 0; x <= gui_obj.getSubPanels().length; x++) {
		                // Establecer la nueva textura principal
		            	gui_obj.getSubPanels()[0][x].setIsoccupied(true);
		            	gui_obj.getSubPanels()[0][x].setFuture_structure("Rock");
		                gui_obj.getSubPanels()[0][x].setCurrentImage(new ImageIcon("./src/gui/rock.png"));
		                // Revalidar y repintar el panel para asegurarse de que se apliquen los cambios
		                gui_obj.getSubPanels()[0][x].revalidate();
		                gui_obj.getSubPanels()[0][x].repaint();
		            }
		        }
		        
				
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
        mainMenuFrame.setResizable(false);
        mainMenuFrame.setIconImage(gamelogo.getImage());
    }

 // Método para invocar la GUI del juego
    public void invoke_game_gui() {
    	
    	
            GameFrame = new JFrame("Civilization By Newel");
            GameFrame.setIconImage(gamelogo.getImage());
            GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
    			gui_obj = new Game_gui(10, 11);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
            gui_obj.setListener(this.ggl);
            
            GameFrame.add(gui_obj, BorderLayout.CENTER);
            GameFrame.setResizable(false);


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
			System.out.println(e.getMessage());
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
