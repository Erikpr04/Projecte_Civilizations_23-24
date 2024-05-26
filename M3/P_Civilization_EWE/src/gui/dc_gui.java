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
		        mainMenuFrame.dispose(); // Dispose of the main menu frame
		        username = username1;
		        profileindex = photoindex1;
		        invoke_game_gui();
		        ggl.clear_and_startdb();
		        ggl.load_game_gui();
		        gui_obj.setPpindex(photoindex1);
		        GameFrame.setVisible(true);
		        gui_obj.createAndShowTutorial();
		        
		        // Verificar que el array subPanels no sea null y que tenga al menos una columna
		        if (gui_obj.getSubPanels() != null && gui_obj.getSubPanels().length > 0 && gui_obj.getSubPanels()[0].length > 0) {
		            // Iterar sobre la primera columna (índice 0) de subPanels
		            for (int y = 0; y < gui_obj.getSubPanels().length; y++) {
		                // Establecer la nueva textura principal
		            	gui_obj.getSubPanels()[y][0].setIsoccupied(true);
		            	gui_obj.getSubPanels()[y][0].setFuture_structure("River");
		                gui_obj.getSubPanels()[y][0].setCurrentImage(new ImageIcon("./src/gui/rivertile.png"));
		                // Revalidar y repintar el panel para asegurarse de que se apliquen los cambios
		                gui_obj.getSubPanels()[y][0].revalidate();
		                gui_obj.getSubPanels()[y][0].repaint();
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
    	
    	
    	Object[] options = {"Probar ventana normal", "Probar pantalla completa"};

        int choice = JOptionPane.showOptionDialog(null,
                "Elige una opción (Pruebas, Decidme como os va):", "Opción",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            System.out.println("Probar ventana normal seleccionada");
            GameFrame = new JFrame("Game GUI");
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




            
        }else if (choice == JOptionPane.NO_OPTION) {
            System.out.println("Probar pantalla completa seleccionada");
            GameFrame = new JFrame("Game GUI");
            GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                gui_obj = new Game_gui(10, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            gui_obj.setListener(this.ggl);
            GameFrame.add(gui_obj, BorderLayout.CENTER);
            GameFrame.setIconImage(gamelogo.getImage());


            // Obtener el dispositivo gráfico (monitor)
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            if (gd.isFullScreenSupported()) {
                // Configurar el JFrame en modo pantalla completa antes de hacerlo visible
                GameFrame.setUndecorated(true);
                gd.setFullScreenWindow(GameFrame);
            } else {
                System.err.println("Pantalla completa no soportada");
                GameFrame.setSize(800, 600); // Tamaño por defecto si no es soportado
            }

            GameFrame.setVisible(true); // Hacer visible el JFrame después de todas las configuraciones

            gui_obj.setUsername(username);
            gui_obj.setPpindex(profileindex);        } else {
            System.out.println("Diálogo cerrado");
            // Acción si se cierra el diálogo sin seleccionar ninguna opción
        }
    	
    	

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
