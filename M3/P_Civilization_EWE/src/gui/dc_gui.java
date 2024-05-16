package gui;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	
	
	
	
	

    


	public dc_gui() {
        super();


    	mmi = new MainMenuListener() {
			
			@Override
			public void onMainMenuClosed() {
		        mainMenuFrame.dispose(); // Dispose of the main menu frame
		        invoke_game_gui();				
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
        
        gui_obj.updatePanels();
        
    }


    //metodo para actualizar recursos en game gui
    
    
    public void update_resources_gui() {
    	try {
			gui_obj.update_resources_quantity(wood,gui_obj.getWoodlabel(),"wood");
			gui_obj.update_resources_quantity(food,gui_obj.getFoodlabel(),"food");
			gui_obj.update_resources_quantity(iron,gui_obj.getIronlabel(),"iron");
			gui_obj.update_resources_quantity(mana,gui_obj.getManalabel(),"mana");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
    
    //metodo cargar partida
    
    public void load_game() {
    	update_resources_gui();
    	update_panels();
    }
    
    
    
    //metodos actualizar recursos en db

    public void updateresources_db(int food,int wood, int iron, int mana) {
        this.food = food;
        this.wood = wood;
        this.iron = iron;
        this.mana = mana;
        this.ggl.update_resources(food,wood,iron,mana);
    }
    
    public void update_army_db(int tipo_tropa) {

    }
    
    public void update_structures_db(int number_structures) {

    }
    public void update_technologies_db(int attack_technology,int defense_technology) {

    }
    
    
    
    
    //metodo para actualizar casillas en base de datos
    
    public void update_panels() {
    	
    	
    	//MOVER ESTE CODIGO A DC_DATABASE
    	
    	//aqui accederemos a la base de datos, iteraremos sobre todos los paneles y accederemos
    	// a gamegui para actualizarlos
    	
        MiPanelito[][] subPanels = gui_obj.getSubPanels(); // Obtener la matriz de subpaneles

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String url = "jdbc:mysql://localhost/civilizationewe?serverTimezone=UTC&autoReconnect=true&useSSL=false";
        String user = "root";
        String pass = "P@ssw0rd";
        String[] structureList = new String[]{"farm", "smithy", "church", "magic_tower", "carpentry"};

        try {
            // Conexión a la base de datos
            connection = DriverManager.getConnection(url, user, pass);

            // Consulta SQL para seleccionar todos los paneles con is_occupied = true
            String query = "SELECT structure_type, is_occupied, x_position, y_position FROM panels WHERE is_occupied = true";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Iterar sobre los resultados
            while (resultSet.next()) {
            	//obtenemos datos de la base de datos
                // Obtener el nombre de la estructura de la fila actual
                String structureType = resultSet.getString("structure_type");
                
                System.out.println("Hola");
                
                // Obtener el valor de is_occupied (un booleano)
                int isOccupiedInt = resultSet.getInt("is_occupied");
                boolean isOccupied = (isOccupiedInt == 1); // Convertir el entero a booleano
                
                System.out.println("Hola");

                
                // Obtener las posiciones x e y
                int xPosition = resultSet.getInt("x_position");
                int yPosition = resultSet.getInt("y_position");
                //comprobamos si el panel esta ocupado
                
                int position = -1; // Inicializamos en -1 para indicar que no se encontró el elemento

                // Iterar sobre el array y buscar el elemento
                for (int i = 0; i < structureList.length; i++) {
                    if (structureList[i].equals(structureType)) {
                        position = i; // Almacenar la posición del elemento encontrado
                        break; // Salir del bucle una vez que se ha encontrado el elemento
                    }
                }
                
                
                if (isOccupied) {
                	subPanels[yPosition][xPosition].setCurrentImage(subPanels[yPosition][xPosition].getBuildingImages()[position]);
                	subPanels[yPosition][xPosition].repaint();
                }else {
                	System.out.println("Structure isnt occupied");
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión y los recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }
    
    
    
    
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
