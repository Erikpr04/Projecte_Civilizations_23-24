package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exceptions.MiSQLException;
import exceptions.ResourceException;
import interfaces.GameGuiListener;
import interfaces.MainMenuListener;
import interfaces.Variables;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import gui.dc_gui;

public class Game_gui extends JPanel {
    private static final int PANEL_SIZE = 50;
    private static final int INIT_ZOOM = 50;
    private int zoomLevel = INIT_ZOOM;
    private int rows, cols;
    private MiPanelito[][] subPanels;
    private MiPanelito panelright2;
    private static int offsetX = 0;
    private static int offsetY = 0;
    private int lastMouseX;
    private int lastMouseY;
    private JPanel mainPanel1, containerpanel, mainPanel2, panelright, panelup, panelright1;
    private JLayeredPane mainpanel = new JLayeredPane();
    private int mouseX;
    private int mouseY;
    private boolean isRightClicked;
    private int food;
    private int wood;
    private int iron;
    private int mana;
    private int defenseint = 1;
    private int attackint = 1;

    private ImageIcon resources_img,woodicon,ironicon,manaicon,foodicon,panelbackgroundimage;
    private JPanel panelright_food,panelright_wood,panelright_iron,panelright_mana;
    private JLabel woodlabel,foodlabel,ironlabel,manalabel;
	private GameGuiListener listener;
    boolean isUpgradePressed = false;
    boolean isStatsButtonPressed = false;
    int[] cv_values = {
            0, 0, 0, 0, 0, 
            0, 0, 0, 0, 
            0, 0
        };
    String[] soldierNames = {"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher Tower", "Magician", "Priest"};
    BackgroundPanel statsPanel;
	private Timer maintimer;
	private String username;
	private JLabel usernameLabel;
	private String ppindex = "0";
	private ImageIcon[] ppphotos = new ImageIcon[9];
    JLabel imageLabel;
    ImageIcon selectedpp;
    private Font gameFont,gameFont_Big;



	
	

    

	

    
    

    

    public int getAttackint() {
		return attackint;
	}

	public void setAttackint(int attackint) {
		this.attackint = attackint;
	}

	public int getDefenseint() {
		return defenseint;
	}

	public void setDefenseint(int defenseint) {
		this.defenseint = defenseint;
	}

	public String getPpindex() {
		return ppindex;
	}

	public void setPpindex(String ppindex) {
		this.ppindex = ppindex;
	    selectedpp = ppphotos[Integer.parseInt(ppindex)-1];
	    Image originalImage = selectedpp.getImage();
	    Image resizedImage = originalImage.getScaledInstance(280, 200, Image.SCALE_SMOOTH);
	    ImageIcon scaledIcon = new ImageIcon(resizedImage);
	    imageLabel.setIcon(scaledIcon);
	    imageLabel.revalidate();
	    imageLabel.repaint();

		
	}

	public JLabel getUsernameLabel() {
		return usernameLabel;
	}

	public void setUsernameLabel(JLabel usernameLabel) {
		this.usernameLabel = usernameLabel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		usernameLabel.setText(username);
		usernameLabel.revalidate();
		usernameLabel.repaint();
	}

	public GameGuiListener getListener() {
		return listener;
	}

	public void setListener(GameGuiListener listener) {
		this.listener = listener;
	}

	// Método para establecer el listener
    public void setGameguiListener(GameGuiListener listener) {
        this.listener = listener;
    }
    
    public Game_gui(GameGuiListener listener) {
        this.listener = listener; // Asignar el listener recibido al atributo listener de MainMenu
    }
    
    


	public Game_gui(int rows, int cols) throws Exception {
		
		//cargamos partida
		//updatePanels();
		
		gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("./src/gui/game_font.ttf")).deriveFont(18f);
		gameFont_Big = Font.createFont(Font.TRUETYPE_FONT, new File("./src/gui/game_font.ttf")).deriveFont(30f);


		
        
       

        this.rows = rows;
        this.cols = cols;
        
        
        woodlabel = new JLabel(Integer.toString(wood));
        foodlabel = new JLabel(Integer.toString(food));
        ironlabel = new JLabel(Integer.toString(iron));
        manalabel = new JLabel(Integer.toString(mana));

        

        
        subPanels = new MiPanelito[rows][cols];
        mainPanel1 = new JPanel(new BorderLayout());
        containerpanel = new JPanel();
        mainPanel2 = new JPanel(new GridLayout(rows, cols));
        panelright = new JPanel(new BorderLayout());
        panelup = new JPanel(new BorderLayout());
        panelright1 = new JPanel(new GridLayout(4, 1));
        panelright2 = new MiPanelito(new BorderLayout());


        //  the resources_img icon
        try {
            resources_img = new ImageIcon(ImageIO.read(new File("./src/gui/brown.png")));
            woodicon = new ImageIcon(ImageIO.read(new File("./src/gui/wood_icon.png")));
            foodicon = new ImageIcon(ImageIO.read(new File("./src/gui/food_icon.png")));
            ironicon = new ImageIcon(ImageIO.read(new File("./src/gui/iron_beam_icon.png")));
            manaicon = new ImageIcon(ImageIO.read(new File("./src/gui/mana_icon.png")));
            panelbackgroundimage = new ImageIcon(ImageIO.read(new File("./src/gui/panel_background_img.jpg")));




        } catch (IOException e) {
            e.printStackTrace();
        }

      //paneles de recursos
        panelright_food = new JPanel(new BorderLayout());
        panelright_wood = new JPanel(new BorderLayout());
        panelright_iron = new JPanel(new BorderLayout());
        panelright_mana = new JPanel(new BorderLayout());
        
        
       //creamos iconos
        
        panelright2.setCurrentImage(panelbackgroundimage);
        
        


        // Añadir paneles a subpanel
        panelright1.add(panelright_food);
        agregarImagenTexto(panelright_food, "./src/gui/brown.png",foodlabel,foodicon);
        //update_resources_quantity(this.getFood(),foodlabel,"food");
        
        
        panelright1.add(panelright_wood);
        agregarImagenTexto(panelright_wood, "./src/gui/brown.png",woodlabel,woodicon);
        //update_resources_quantity(this.getWood(),woodlabel,"wood");


        panelright1.add(panelright_iron);
        agregarImagenTexto(panelright_iron, "./src/gui/brown.png",ironlabel,ironicon);
        //update_resources_quantity(this.getIron(),ironlabel,"iron");


        panelright1.add(panelright_mana);
        agregarImagenTexto(panelright_mana, "./src/gui/brown.png",manalabel,manaicon);
        //update_resources_quantity(this.getMana(),manalabel,"mana");



        
        
        
        mainPanel1.setPreferredSize(new Dimension(1920,1080));

        setLayout(new BorderLayout());
        add(mainpanel,BorderLayout.CENTER);
        mainpanel.setPreferredSize(new Dimension(1920,1080));
        mainpanel.add(mainPanel1);

        //mainpanel.add(mainPanel1,JLayeredPane.DEFAULT_LAYER);

        mainPanel1.add(containerpanel, BorderLayout.WEST);
        mainPanel1.add(panelright, BorderLayout.EAST);
        mainPanel1.add(panelup, BorderLayout.NORTH);

        panelright.add(panelright1, BorderLayout.NORTH);
        panelright.add(panelright2, BorderLayout.SOUTH);

        containerpanel.add(mainPanel2);
        
        mainPanel1.setBounds(0, 0, 1920, 1080); // Aquí puedes ajustar las coordenadas y el tamaño según tus necesidades

        

        
        containerpanel.setPreferredSize(new Dimension(1500, 1500));
        mainPanel2.setPreferredSize(new Dimension(1500, 1500));
        

        panelright.setPreferredSize(new Dimension(420, 30));
        panelright1.setPreferredSize(new Dimension(300, 400));
        panelright2.setPreferredSize(new Dimension(425, 675));
        panelup.setPreferredSize(new Dimension(1400, 20));

        
        
        
        //Boton para ver logs
        
     // En el constructor de Game_gui, después de crear panelup
        JButton openButton = new JButton("Battle Logs");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	battlelog_frame();
        		//updatePanels();

                
            }
        });
        openButton.setPreferredSize(new Dimension(70,50));
        panelup.add(openButton,BorderLayout.EAST);


     
         //panel de batalla     
        //showBattleWindow("This is the first line.\nThis is the second line.\nThis is the third line.\nThis is the fourth line.\nThis is the fifth line.");
        
        
        
        

        
        
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    subPanels[i][j] = new MiPanelito();
                    mainPanel2.add(subPanels[i][j]);
                }
            }
            			


    
        // Agregar MouseListener a cada panel en subPanels
        for (MiPanelito[] row : subPanels) {
            for (MiPanelito panel : row) {
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        panel.setBorder(BorderFactory.createLineBorder(Color.black));
                        panel.repaint(); // Repintar el panel para reflejar el cambio
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        panel.setBorder(null);
                        panel.repaint(); // Repintar el panel para reflejar el cambio
                    }
                });
            }
        }
        
        
        mainPanel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    isRightClicked = true;
                    mouseX = e.getXOnScreen();
                    mouseY = e.getYOnScreen();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    isRightClicked = false;
                }
            }
        });

        mainPanel2.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isRightClicked) {
                    int newX = e.getXOnScreen();
                    int newY = e.getYOnScreen();

                    int deltaX = newX - mouseX;
                    int deltaY = newY - mouseY;

                    Point currentLocation = mainPanel2.getLocation();
                    mainPanel2.setLocation(currentLocation.x + deltaX, currentLocation.y + deltaY);

                    mouseX = newX;
                    mouseY = newY;
                }
            }
        });



        // Agregar MouseWheelListener para el zoom
        mainPanel2.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();

                if (notches < 0) {
                    zoomIn(mainPanel2);
                } else {
                    zoomOut(mainPanel2);
                }
            }
        });
    
    
    
    
    
    // En el constructor de Game_gui, después de crear panelup
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    JButton upgrade_cvbutton = new JButton();
    upgrade_cvbutton.setContentAreaFilled(false); // No rellenar el área del contenido
    upgrade_cvbutton.setBorderPainted(false); // No pintar el borde del botón
    upgrade_cvbutton.setMaximumSize(new Dimension(196, 64));
    upgrade_cvbutton.setAlignmentX(Component.CENTER_ALIGNMENT);

    JButton see_cvstats = new JButton();
    see_cvstats.setContentAreaFilled(false); // No rellenar el área del contenido
    see_cvstats.setBorderPainted(false); // No pintar el borde del botón


    see_cvstats.setMaximumSize(new Dimension(196, 64));
    see_cvstats.setAlignmentX(Component.CENTER_ALIGNMENT);

    JButton sanctify_button = new JButton();
    sanctify_button.setContentAreaFilled(false); // No rellenar el área del contenido
    sanctify_button.setBorderPainted(false); // No pintar el borde del botón


    sanctify_button.setMaximumSize(new Dimension(196, 64));
    sanctify_button.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Cargar la imagen de fondo del botón
    ImageIcon backgroundImage = new ImageIcon("./src/gui/woodbutton.png"); // Reemplaza "ruta_de_la_imagen.jpg" con la ruta de tu imagen
    Image backgroundImg = backgroundImage.getImage().getScaledInstance(196, 64, Image.SCALE_SMOOTH); // Ajustar el tamaño según sea necesario
    ImageIcon scaledBackground = new ImageIcon(backgroundImg);
    
    
    // Establecer la imagen de fondo del botón
    upgrade_cvbutton.setIcon(scaledBackground);

    // Establecer el título del botón
    upgrade_cvbutton.setText("Upgrade Civilization");

    // Ajustar el diseño del botón para que las letras sean visibles
    upgrade_cvbutton.setForeground(Color.WHITE); // Establecer el color de las letras

    // Ajustar la posición del texto dentro del botón
    upgrade_cvbutton.setHorizontalTextPosition(JButton.CENTER); // Centrar horizontalmente el texto
    upgrade_cvbutton.setVerticalTextPosition(JButton.CENTER);
    upgrade_cvbutton.setFont(gameFont);
    
    
    // Establecer la imagen de fondo del botón
    see_cvstats.setIcon(scaledBackground);

    // Establecer el título del botón
    see_cvstats.setText("Civilization Stats");

    // Ajustar el diseño del botón para que las letras sean visibles
    see_cvstats.setForeground(Color.WHITE); // Establecer el color de las letras

    // Ajustar la posición del texto dentro del botón
    see_cvstats.setHorizontalTextPosition(JButton.CENTER); // Centrar horizontalmente el texto
    see_cvstats.setVerticalTextPosition(JButton.CENTER);
    see_cvstats.setFont(gameFont);


    // Establecer la imagen de fondo del botón
    sanctify_button.setIcon(scaledBackground);
    sanctify_button.setText("Sanctify Units");
    sanctify_button.setFont(gameFont);

    // Ajustar el diseño del botón para que las letras sean visibles
    sanctify_button.setForeground(Color.WHITE); // Establecer el color de las letras

    // Ajustar la posición del texto dentro del botón
    sanctify_button.setHorizontalTextPosition(JButton.CENTER); // Centrar horizontalmente el texto
    sanctify_button.setVerticalTextPosition(JButton.CENTER);
    
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre los botones
    buttonPanel.add(upgrade_cvbutton);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre los botones
    buttonPanel.add(see_cvstats);
    buttonPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre los botones
    buttonPanel.add(sanctify_button);

    buttonPanel.setOpaque(false);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); // Margen superior
    
    
    
    
    //sanctify units panel
    
    sanctify_button.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//comprobamos si hay priest en nuestra army
			if (cv_values[8] >= 1) {
				if (listener.sanctifyunits()) {
					JOptionPane.showMessageDialog(null, "All units were successfully sanctified","Units Sanctified", JOptionPane.INFORMATION_MESSAGE);

				}else {
	        		JOptionPane.showMessageDialog(null, "No units to sanctify found","No units found error", JOptionPane.ERROR_MESSAGE);
				}

			}else {
        		JOptionPane.showMessageDialog(null, "No priest found, can't sanctify units","No Priest Error", JOptionPane.ERROR_MESSAGE);


			}}
	});
    
    
    
    

    panelright2.add(buttonPanel, BorderLayout.NORTH);

    // Crear el panel de estadísticas
    statsPanel = new BackgroundPanel(new GridLayout(12, 2));

    // Crear un borde compuesto que combine un borde vacío y un borde de línea
    statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

    statsPanel.setPreferredSize(new Dimension(500, 200));

    // Añadir etiquetas a la primera columna
    String[] labels = {
        "Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", 
        "Catapult", "Rocket Launcher Tower", "Magician", "Priest", 
        "Civilization Attack Level", "Civilization Defense Level"
    };

    // Ejemplo de valores para la segunda columna
    for (int i = 0; i < labels.length; i++) {
        JLabel label = new JLabel(labels[i]);
        label.setForeground(Color.WHITE); // Texto blanco
        statsPanel.add(label);
        
        JLabel valueLabel = new JLabel(Integer.toString(cv_values[i]));
        valueLabel.setForeground(Color.WHITE); // Texto blanco
        statsPanel.add(valueLabel);
    }

    // Añadir el container al JLayeredPane en la capa modal
    mainpanel.add(statsPanel, JLayeredPane.PALETTE_LAYER);
    statsPanel.setBounds(1075, 420, 425, 675);
    statsPanel.setVisible(false);

    // Booleano para controlar el estado del botón
    // ActionListener para see_cvstats
    see_cvstats.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            getcv_data();
            statsPanel.removeAll();

            // Añadir los nuevos componentes con los datos actualizados
            for (int i = 0; i < labels.length; i++) {
                JLabel label = new JLabel(labels[i]);
                label.setForeground(Color.WHITE); // Texto blanco
                statsPanel.add(label);
                
                JLabel valueLabel = new JLabel(Integer.toString(cv_values[i]));
                valueLabel.setForeground(Color.WHITE); // Texto blanco
                statsPanel.add(valueLabel);
            }
            statsPanel.revalidate();
            statsPanel.repaint();

            isStatsButtonPressed = !isStatsButtonPressed;
            statsPanel.setVisible(isStatsButtonPressed);
            see_cvstats.getModel().setPressed(isStatsButtonPressed);
        }
    });

    
    
    
    
    //panel de actualizacion de civilizacion

    
    CvUpgradeGui cvUpgradeGui = new CvUpgradeGui();
    JPanel panelpestañas = new JPanel(new BorderLayout());
    JTabbedPane tabbedPane = new JTabbedPane();

    JPanel upgradeArmyPanel = new JPanel();
    upgradeArmyPanel.setLayout(new GridLayout(3, 3));
    String[] soldierImages = {"./src/gui/unit_assets/swordsman.png", "./src/gui/unit_assets/spearman.png", "./src/gui/unit_assets/crossbow.png", "./src/gui/unit_assets/cannon.png", "./src/gui/unit_assets/arrow_tower.png", "./src/gui/unit_assets/catapult.png", "./src/gui/unit_assets/rocket_launcher.png", "./src/gui/unit_assets/magician.png", "./src/gui/unit_assets/priest.png"};

    int[][] soldierCosts = {
        {Variables.FOOD_COST_SWORDSMAN, Variables.WOOD_COST_SWORDSMAN, Variables.IRON_COST_SWORDSMAN, Variables.MANA_COST_SWORDSMAN},
        {Variables.FOOD_COST_SPEARMAN, Variables.WOOD_COST_SPEARMAN, Variables.IRON_COST_SPEARMAN, Variables.MANA_COST_SPEARMAN},
        {Variables.FOOD_COST_CROSSBOW, Variables.WOOD_COST_CROSSBOW, Variables.IRON_COST_CROSSBOW, Variables.MANA_COST_CROSSBOW},
        {Variables.FOOD_COST_CANNON, Variables.WOOD_COST_CANNON, Variables.IRON_COST_CANNON, Variables.MANA_COST_CANNON},
        {Variables.FOOD_COST_ARROWTOWER, Variables.WOOD_COST_ARROWTOWER, Variables.IRON_COST_ARROWTOWER, Variables.MANA_COST_ARROWTOWER},
        {Variables.FOOD_COST_CATAPULT, Variables.WOOD_COST_CATAPULT, Variables.IRON_COST_CATAPULT, Variables.MANA_COST_CATAPULT},
        {Variables.FOOD_COST_ROCKETLAUNCHERTOWER, Variables.WOOD_COST_ROCKETLAUNCHERTOWER, Variables.IRON_COST_ROCKETLAUNCHERTOWER, Variables.MANA_COST_ROCKETLAUNCHERTOWER},
        {Variables.FOOD_COST_MAGICIAN, Variables.WOOD_COST_MAGICIAN, Variables.IRON_COST_MAGICIAN, Variables.MANA_COST_MAGICIAN},
        {Variables.FOOD_COST_PRIEST, Variables.WOOD_COST_PRIEST, Variables.IRON_COST_PRIEST, Variables.MANA_COST_PRIEST}
    };

    for (int i = 0; i < soldierNames.length; i++) {
        upgradeArmyPanel.add(cvUpgradeGui.createSoldierPanel(i, soldierCosts[i], soldierImages[i]));
    }

    JPanel upgradeTechnologyPanel = cvUpgradeGui.new TechnologyUpgradePanel();
    tabbedPane.add("Upgrade Army", upgradeArmyPanel);
    tabbedPane.add("Upgrade Technology", upgradeTechnologyPanel);

    panelpestañas.add(tabbedPane, BorderLayout.CENTER);
    mainpanel.add(panelpestañas, JLayeredPane.PALETTE_LAYER);
    panelpestañas.setBounds(0, 0, 1500, 1080);
    panelpestañas.setVisible(false);

    upgrade_cvbutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            isUpgradePressed = !isUpgradePressed;
            panelpestañas.setVisible(isUpgradePressed);
            upgrade_cvbutton.getModel().setPressed(isUpgradePressed);
        }
    });

    JPanel userPanel = new JPanel();
    userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
    
	ppphotos[0] = new ImageIcon("./src/gui/ppphotos/pp1.png");
	ppphotos[1] = new ImageIcon("./src/gui/ppphotos/pp2.png");
	ppphotos[2] = new ImageIcon("./src/gui/ppphotos/pp3.png");
	ppphotos[3] = new ImageIcon("./src/gui/ppphotos/pp4.png");
	ppphotos[4] = new ImageIcon("./src/gui/ppphotos/pp5.png");
	ppphotos[5] = new ImageIcon("./src/gui/ppphotos/pp6.png");
	ppphotos[6] = new ImageIcon("./src/gui/ppphotos/pp7.png");
	ppphotos[7] = new ImageIcon("./src/gui/ppphotos/pp8.png");
	ppphotos[8] = new ImageIcon("./src/gui/ppphotos/pp9.png");


    selectedpp = ppphotos[Integer.parseInt(ppindex)];
    Image originalImage = selectedpp.getImage();
    Image resizedImage = originalImage.getScaledInstance(280, 200, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(resizedImage);
    imageLabel = new JLabel(scaledIcon);
    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    userPanel.add(imageLabel);
    imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

    usernameLabel = new JLabel("username");
    usernameLabel.setFont(gameFont);
    usernameLabel.setForeground(Color.WHITE);
    usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    userPanel.add(usernameLabel);

    JLabel timerLabel = new JLabel("0:00", JLabel.CENTER);
    timerLabel.setFont(gameFont);
    timerLabel.setForeground(Color.WHITE);
    timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    userPanel.add(timerLabel);
    userPanel.setOpaque(false);

    Timer timer = new Timer(1000, new ActionListener() {
        int seconds = 0;
        int minutes = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
            }
            String timeString = String.format("%d:%02d", minutes, seconds);
            timerLabel.setText(timeString);
        }
    });
    timer.start();
    userPanel.setPreferredSize(new Dimension(300, 300));

    panelright2.add(userPanel, BorderLayout.SOUTH);
	 }
    
    
    
	public void showBattleWindow(String text) {
	    // Crear la ventana
	    JPanel battleFrame = new JPanel();
	    battleFrame.setSize(500, 600);
	    battleFrame.setLayout(new BorderLayout());

	    // Crear y agregar el título
	    JLabel titleLabel = new JLabel("The battle begins!", SwingConstants.CENTER);
	    titleLabel.setFont(gameFont);
	    battleFrame.add(titleLabel, BorderLayout.NORTH);

	    // Crear y agregar el área de texto
	    JTextArea textArea = new JTextArea();
	    textArea.setEditable(false);
	    textArea.setFont(gameFont);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    JScrollPane scrollPane = new JScrollPane(textArea);
	    battleFrame.add(scrollPane, BorderLayout.CENTER);

	    // Crear el botón para mostrar todo el texto
	    JButton showAllButton = new JButton("Show All");
	    showAllButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            textArea.setText(text); // Mostrar todo el texto
	            showAllButton.setEnabled(false); // Deshabilitar el botón después de hacer clic
	            JButton deleteButton = new JButton("Delete Panel");
	            deleteButton.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    battleFrame.setVisible(false); // Ocultar el panel
	                    battleFrame.getParent().remove(battleFrame); // Eliminar el panel del contenedor principal
	                }
	            });
	            battleFrame.add(deleteButton, BorderLayout.SOUTH); // Agregar el botón para eliminar el panel
	        }
	    });
	    battleFrame.add(showAllButton, BorderLayout.SOUTH);

	    // Dividir el texto en líneas
	    String[] lines = text.split("\n");
	    int[] currentLineIndex = {0}; // Usar un array para permitir acceso en la clase anónima

	    // Agregar el KeyListener para la tecla Enter
	    textArea.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                if (currentLineIndex[0] < lines.length) {
	                    textArea.append(lines[currentLineIndex[0]] + "\n");
	                    currentLineIndex[0]++;
	                    if (currentLineIndex[0] >= lines.length) {
	                        showAllButton.setEnabled(false); // Si ya se han mostrado todas las líneas, deshabilitar el botón
	                        JButton deleteButton = new JButton("Delete Panel");
	                        deleteButton.addActionListener(new ActionListener() {
	                            @Override
	                            public void actionPerformed(ActionEvent e) {
	                                battleFrame.setVisible(false); // Ocultar el panel
	                                battleFrame.getParent().remove(battleFrame); // Eliminar el panel del contenedor principal
	                            }
	                        });
	                        battleFrame.add(deleteButton, BorderLayout.NORTH); // Agregar el botón para eliminar el panel
	                    }
	                }
	            }
	        }
	    });

	    mainpanel.add(battleFrame, JLayeredPane.PALETTE_LAYER);
	    battleFrame.setLocation(650, 280);

	    // Hacer visible la ventana
	    battleFrame.setVisible(true);
	    // Solicitar el foco para el textArea para que reciba eventos de teclado
	    textArea.requestFocusInWindow();
	}


    
//metodo notificacion
    
public void showCustomPanel(JLayeredPane parentComponent, String s) {
        JPanel customPanel = new BackgroundPanel();
        ImageIcon originalIcon = new ImageIcon("./src/gui/battleicon.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Create JTextArea for multiline text display
        JTextArea customTextArea = new JTextArea(s);
        customTextArea.setFont(gameFont);
        customTextArea.setForeground(Color.WHITE);
        customTextArea.setLineWrap(true);
        customTextArea.setWrapStyleWord(true);
        customTextArea.setOpaque(false); 
        customTextArea.setEditable(false); 

        // Create a JLabel for the icon
        JLabel iconLabel = new JLabel(resizedIcon);

        customPanel.setLayout(new BorderLayout());
        customPanel.add(iconLabel, BorderLayout.WEST);
        customPanel.add(customTextArea, BorderLayout.CENTER);
        customPanel.setBackground(Color.red);

        int parentWidth = parentComponent.getWidth();
        int panelWidth = 500; // Ancho del panel personalizado
        int panelHeight = 200; // Alto del panel personalizado
        int panelX =  panelWidth +200; // Posición X para centrar 
        int panelYStart = panelHeight - 600; // Posición inicial Y para coloczar arriba del JLayeredPane
        int panelYEnd = 25; // Posición final Y para centrar verticalmente

        customPanel.setBounds(panelX, panelYStart, panelWidth, panelHeight);

        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            private int yOffset = panelYStart;
            private int state = 0; // Estado para controlar las partes de la animación
            private int pauseCount = 0; // Contador para la pausa

            @Override
            public void actionPerformed(ActionEvent e) {
                // Primera parte: movimiento hacia abajo
                if (state == 0) {
                    if (yOffset < panelYEnd) {
                        yOffset += 5; // Ajusta la velocidad de la animación cambiando este valor
                        customPanel.setLocation(panelX, yOffset);
                    } else {
                        state = 1; // Cambiar al siguiente estado
                        pauseCount = 0; // Reiniciar el contador de pausa
                    }
                }
                // Segunda parte: pausa de 5 segundos
                else if (state == 1) {
                    pauseCount++;
                    if (pauseCount >= 150) { // 150 * 30ms = 4500ms = 4.5 segundos
                        state = 2; // Cambiar al siguiente estado
                        pauseCount = 0; // Reiniciar el contador de pausa
                    }
                }
                // Tercera parte: movimiento hacia arriba para esconder el panel
                else if (state == 2) {
                    if (yOffset > panelYStart) {
                        yOffset -= 5; // Ajusta la velocidad de la animación cambiando este valor
                        customPanel.setLocation(panelX, yOffset);
                    } else {
                        timer.stop();
                        parentComponent.remove(customPanel); // Eliminar el panel del componente principal
                        parentComponent.revalidate(); // Actualizar la interfaz
                    }
                }
            }
        });

        parentComponent.add(customPanel, JLayeredPane.PALETTE_LAYER); // Agregar el panel al componente principal
        parentComponent.revalidate(); // Actualizar la interfaz

        timer.start(); // Iniciar la animación
    }	
	
    private void getcv_data() {
    	
	    int[] cv_array = new int[11]; // Declaración y creación del array

	    cv_array[0] = listener.getcv_army_values()[0];
	    cv_array[1] =listener.getcv_army_values()[1];
	    cv_array[2] =listener.getcv_army_values()[2];
	    cv_array[3] =listener.getcv_army_values()[3];
	    cv_array[4] =listener.getcv_army_values()[4];
	    cv_array[5] =listener.getcv_army_values()[5];
	    cv_array[6] =listener.getcv_army_values()[6];
	    cv_array[7] =listener.getcv_army_values()[7];
	    cv_array[8] = listener.getcv_army_values()[8];
	    cv_array[9] =listener.getcv_army_values()[9];
	    cv_array[10] =listener.getcv_army_values()[10];	
	    this.cv_values = cv_array;
	}

	//metodo para guardar paneles
    
	
    public int getFood() {
        return food;
    }

    public void setFood(int food) {
    	this.food = food;
    }

    public int getWood() {
        return this.wood;
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
    
    
    
    
    public MiPanelito[][] getSubPanels() {
		return subPanels;
	}

	public void setSubPanels(MiPanelito[][] subPanels) {
		this.subPanels = subPanels;
	}

	public Game_gui() {


	}

	//metodos

	
	

	
	
    

	
	
    
    //este metodo actualiza el label que queramos con un numero
    
    public void update_resources_quantity() throws Exception {
    	
    		
    		getFoodlabel().setText(Integer.toString(getFood()));

    		
    		getWoodlabel().setText(Integer.toString(getWood()));



    		getIronlabel().setText(Integer.toString(getIron()));


    		getManalabel().setText(Integer.toString(getMana()));
    		
    		getFoodlabel().repaint();
    		getWoodlabel().repaint();
    		getIronlabel().repaint();
    		getManalabel().repaint();




    	
    }
    
    
    

    private void agregarTexto(JPanel panel, String text) {
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(gameFont);
        panel.add(textLabel);
    }

    private void agregarImagenTexto(JPanel panel, String imagePath, JLabel textLabel, ImageIcon icono) {
        try {
            Image image = ImageIO.read(new File(imagePath));
            panel.setOpaque(true); // Establecer el panel como no transparente
            panel.setLayout(new BorderLayout());

            JLabel imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(10, 10, Image.SCALE_SMOOTH))) {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(image.getWidth(this), image.getHeight(this));
                }
            };

         // Agregar icono junto con el texto
            textLabel.setForeground(Color.WHITE); // Color del texto
            textLabel.setFont(gameFont);
            textLabel.setHorizontalAlignment(SwingConstants.LEFT); // Centrar el texto horizontalmente
            // Añadir margen al JLabel
            textLabel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0)); // Añadir un margen de 5 píxeles en los cuatro lados

            textLabel.setVerticalAlignment(SwingConstants.CENTER); // Centrar el texto verticalmente

            // Configurar el icono
            icono.setImage(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)); // Cambiar tamaño del icono
            textLabel.setIcon(icono); // Establecer el icono
            textLabel.setHorizontalTextPosition(SwingConstants.RIGHT); // Posicionar el texto a la derecha del icono
            textLabel.setVerticalTextPosition(SwingConstants.CENTER); // Posicionar el texto en el centro verticalmente
            textLabel.setIconTextGap(15); // Espacio entre el icono y el texto

            imageLabel.setLayout(new BorderLayout()); // Establecer un BorderLayout para centrar el texto e icono
            imageLabel.add(textLabel, BorderLayout.CENTER); // Agregar el texto e icono al centro del JLabel

            panel.add(imageLabel, BorderLayout.CENTER);
            panel.revalidate(); // Validar el diseño del panel
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void actualizarTexto(JPanel panel, String nuevoTexto) {
        Component[] components = panel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setText(nuevoTexto);
                panel.revalidate(); // Validar el diseño del panel después de actualizar el texto
                return; // Salir del bucle después de actualizar el primer JLabel
            }
        }
    }









// Método para hacer zoom in en un panel
    private void zoomIn(JPanel panel) {
        float scale = 1.1f; // Factor de escala para el zoom in
        zoom(panel, scale);
    }

    // Método para hacer zoom out en un panel
    private void zoomOut(JPanel panel) {
        float scale = 0.9f; // Factor de escala para el zoom out
        zoom(panel, scale);
    }

    // Método para realizar el zoom en un panel con un factor de escala dado
    private void zoom(JPanel panel, float scale) {
        // Obtener el tamaño actual del panel
        Dimension size = panel.getSize();

        // Calcular el nuevo tamaño del panel después del zoom
        int newWidth = (int) (size.width * scale);
        int newHeight = (int) (size.height * scale);

        // Establecer el nuevo tamaño del panel
        panel.setPreferredSize(new Dimension(newWidth, newHeight));

        // Revalidar y repintar el panel para reflejar el cambio
        panel.revalidate();
        panel.repaint();
    }
    
    


    

    void battlelog_frame() {
        JFrame popupFrame = new JFrame("Battle Log");
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Crear 5 pestañas, cada una con un JTextArea
        for (int i = 1; i <= 5; i++) {
            JTextArea textArea = new JTextArea(10, 30);
            textArea.setText("Battle " + i);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            tabbedPane.addTab("Tab " + i, scrollPane);
        }

        // Cargar la imagen battleicon.png
        ImageIcon battleIcon = new ImageIcon("./src/gui/battleicon.png");
        Image scaledImage = battleIcon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear un JPanel para contener la imagen y el JTabbedPane
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BorderLayout());
        panelContainer.add(iconLabel, BorderLayout.NORTH);
        panelContainer.add(tabbedPane, BorderLayout.CENTER);

        // Agregar el JPanel al JFrame
        popupFrame.getContentPane().add(panelContainer);

        // Ajustar el tamaño de la ventana y hacerla visible
        popupFrame.pack();
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setVisible(true);
    }

    
    
    


public class MiPanelito extends JPanel {
    private int lastMouseX;
    private int lastMouseY;
    private Color panelColor = Color.GREEN;
    private Color borderColor = Color.black;
    private ImageIcon[] buildingImages = new ImageIcon[5];
    private ImageIcon currentImage,main_texture;
    private dc_gui dcGui;
    private boolean isoccupied;
    private String panelcontent;
	String future_structure;


	
    
    
    
    

    public boolean isIsoccupied() {
		return isoccupied;
	}

	public void setIsoccupied(boolean isoccupied) {
		this.isoccupied = isoccupied;
	}

	public MiPanelito() {
    	
    	main_texture = new ImageIcon("./src/gui/main_grass_texture.jpg");
		this.setCurrentImage(main_texture);
		//setBorder(BorderFactory.createLineBorder(borderColor));

		getBuildingImages()[0] = new ImageIcon("./src/gui/farm.png");
		getBuildingImages()[1] = new ImageIcon("./src/gui/smithy.png");
		getBuildingImages()[2] = new ImageIcon("./src/gui/church.png");
		getBuildingImages()[3] = new ImageIcon("./src/gui/magic_tower.png");
		getBuildingImages()[4] = new ImageIcon("./src/gui/carpentry.png");


		addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        if (SwingUtilities.isRightMouseButton(e)) {
		            lastMouseX = e.getX();
		            lastMouseY = e.getY();
		        } else if (SwingUtilities.isLeftMouseButton(e)) {
		        	
		        	if (isoccupied) {
		        		JOptionPane.showMessageDialog(null, "Cannot build here", "Error", JOptionPane.ERROR_MESSAGE);

		        	}else {
		            try {
		                String[] options = {"Farm", "Smithy", "Church", "Magic Tower", "Carpentry"};
		                JButton[] buttons = new JButton[options.length];
		                for (int i = 0; i < options.length; i++) {
		                    buttons[i] = new JButton(options[i]);
		                    buttons[i].addMouseListener(new MouseAdapter() {
		                        @Override
		                        public void mouseEntered(MouseEvent e) {
		                            // Obtener el texto del botón sobre el que pasó el ratón
		                            String buttonText = ((JButton) e.getSource()).getText();
		                            future_structure = ((JButton) e.getSource()).getText();
		                            // Obtener información de costo y mostrar el menú emergente
		                            String costInfo = getSpecificCostInfo(buttonText);
		                            JPopupMenu popupMenu = new JPopupMenu();
		                            popupMenu.add(new JLabel(costInfo));
		                            popupMenu.show((Component) e.getSource(), 0, ((Component) e.getSource()).getHeight());
		                        }

		                        @Override
		                        public void mouseClicked(MouseEvent e) {
		                            int woodCost = 0;
		                            int ironCost = 0;
		                            int foodCost = 0;
		                            int manaCost = 0;
		                            // Obtener el índice del botón clickeado
		                            int index = Arrays.asList(buttons).indexOf(e.getSource());
		                         // Asignar los costos según la opción seleccionada
		                            switch (index) {
		                                case 0: // Farm
		                                    woodCost = Variables.WOOD_COST_FARM;
		                                    ironCost = Variables.IRON_COST_FARM;
		                                    foodCost = Variables.FOOD_COST_FARM;
		                                    break;
		                                case 1: // Smithy
		                                    woodCost = Variables.WOOD_COST_SMITHY;
		                                    ironCost = Variables.IRON_COST_SMITHY;
		                                    foodCost = Variables.FOOD_COST_SMITHY;
		                                    break;
		                                case 2: // Church
		                                    woodCost = Variables.WOOD_COST_CHURCH;
		                                    ironCost = Variables.IRON_COST_CHURCH;
		                                    foodCost = Variables.FOOD_COST_CHURCH;
		                                    manaCost = Variables.MANA_COST_CHURCH;
		                                    break;
		                                case 3: // Magic Tower
		                                    woodCost = Variables.WOOD_COST_MAGICTOWER;
		                                    ironCost = Variables.IRON_COST_MAGICTOWER;
		                                    foodCost = Variables.FOOD_COST_MAGICTOWER;
		                                    break;
		                                case 4: // Carpentry
		                                    woodCost = Variables.WOOD_COST_CARPENTRY;
		                                    ironCost = Variables.IRON_COST_CARPENTRY;
		                                    foodCost = Variables.FOOD_COST_CARPENTRY;
		                                    break;
		                                default:
		                                    break;
		                            }
		                            // Verificar si el jugador tiene los recursos necesarios
		                            if (getWood() >= woodCost && getIron() >= ironCost && getFood() >= foodCost && getMana() >= manaCost) {
		                                // Descontar los recursos necesarios
		                                setWood(getWood() - woodCost);
		                                setIron(getIron() - ironCost);
		                                setFood(getFood() - foodCost);
		                                setMana(getMana() - manaCost);
		                                
		                                try {
											update_resources_quantity();
		                                    
		                                    switch (index) {
		                                    case 0: // Farm
		                                    	listener.create_farm();
		                                        break;
		                                    case 1: // Smithy
		                                    	listener.create_smithy();

		                                        break;
		                                    case 2: // Church
		                                    	listener.create_church();

		                                        break;
		                                    case 3: // Magic Tower
		                                    	listener.create_magic_tower();

		                                        break;
		                                    case 4: // Carpentry
		                                    	listener.create_carpentry();
		                                        break;
		                                    default:
		                                        break;
		                                    }
		                                    //guardamos datos en base de datos
		                                    listener.update_resources_db();

										} catch (Exception e1) {
										
											e1.printStackTrace();
										}



		                                // Cambiar la imagen del panel al edificio correspondiente
		                                setCurrentImage(buildingImages[index]);
		                                repaint();
		                                isoccupied = true;
		                                panelcontent = future_structure;
		                                
		                            } else {
		               
		                            	//throw new ResourceException("Not enough resources to build structure");
		                            }

		                            // Cerrar el JOptionPane
		                            Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
		                            window.dispose();
		                        }
		                    });
		                }
		                
		                // Mostrar el diálogo de opción con los botones personalizados
		                int choice = JOptionPane.showOptionDialog(null, "Select a building to create:", "Create Building",
		                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);


		            } catch (Exception x) {
		                x.printStackTrace();
		                System.out.println("error");
		            }}
		        }
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        if (e.getSource() instanceof JButton) {
		            JButton button = (JButton) e.getSource();
		            String buttonText = button.getText();
		            String costInfo = getSpecificCostInfo(buttonText); // Obtener la información de costo personalizado
		            JPopupMenu popupMenu = new JPopupMenu();
		            popupMenu.add(new JLabel(costInfo));
		            popupMenu.show(button, button.getWidth(), 0);
		        }
		    }
		});







    }

    public MiPanelito(BorderLayout borderLayout) {
    	super(new BorderLayout());
	}

	public MiPanelito(FlowLayout flowLayout) {
		super(flowLayout);
	}

	public MiPanelito(BoxLayout boxLayout) {
		super(boxLayout);
	}

	public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            Image image = currentImage.getImage();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void setCurrentImage(ImageIcon image) {
        this.currentImage = image;
    }

	public ImageIcon[] getBuildingImages() {
		return buildingImages;
	}

	public void setBuildingImages(ImageIcon[] buildingImages) {
		this.buildingImages = buildingImages;
	}
	
	// Método para obtener la información de costo del edificio
	private String getSpecificCostInfo(String buildingName) {
	    // Aquí puedes definir la información de costo específica para cada botón
	    switch (buildingName) {
        case "Farm":
            return "Farm Cost Info: Wood - " + Variables.WOOD_COST_FARM + ", Iron - " + Variables.IRON_COST_FARM + ", Mana - 0, Food - " + Variables.FOOD_COST_FARM;
        case "Smithy":
            return "Smithy Cost Info: Wood - " + Variables.WOOD_COST_SMITHY + ", Iron - " + Variables.IRON_COST_SMITHY + ", Mana - 0, Food - " + Variables.FOOD_COST_SMITHY;
        case "Church":
            return "Church Cost Info: Wood - " + Variables.WOOD_COST_CHURCH + ", Iron - " + Variables.IRON_COST_CHURCH + ", Mana "+Variables.MANA_COST_CHURCH+", Food - " + Variables.FOOD_COST_CHURCH;
        case "Magic Tower":
            return "Magic Tower Cost Info: Wood - " + Variables.WOOD_COST_MAGICTOWER + ", Iron - " + Variables.IRON_COST_MAGICTOWER + ", Mana - 0, Food - " + Variables.FOOD_COST_MAGICTOWER;
        case "Carpentry":
            return "Carpentry Cost Info: Wood - " + Variables.WOOD_COST_CARPENTRY + ", Iron - " + Variables.IRON_COST_CARPENTRY + ", Mana - 0, Food - " + Variables.FOOD_COST_CARPENTRY;
        default:
            return "";
    }
	}

}





public JLabel getWoodlabel() {
	return woodlabel;
}

public void setWoodlabel(JLabel woodlabel) {
	this.woodlabel = woodlabel;
}

public JLabel getFoodlabel() {
	return foodlabel;
}

public void setFoodlabel(JLabel foodlabel) {
	this.foodlabel = foodlabel;
}

public JLabel getIronlabel() {
	return ironlabel;
}

public void setIronlabel(JLabel ironlabel) {
	this.ironlabel = ironlabel;
}

public JLabel getManalabel() {
	return manalabel;
}

public void setManalabel(JLabel manalabel) {
	this.manalabel = manalabel;
}


//Clases Paneles para mejorar Civilizacion


public JLayeredPane getMainpanel() {
	return mainpanel;
}

public void setMainpanel(JLayeredPane mainpanel) {
	this.mainpanel = mainpanel;
}





class CvUpgradeGui {
    
public class TechnologyUpgradePanel extends BackgroundPanel {

    public TechnologyUpgradePanel() {
        setLayout(new BorderLayout());

        // Panel para mejorar la tecnología de ataque
        JPanel attackPanel = new JPanel(new BorderLayout());
        attackPanel.setOpaque(false);
        attackPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 200, 100));

        // Título del panel de ataque
        JLabel attackTitle = new JLabel("Upgrade Attack Technology");
        attackTitle.setForeground(Color.white);
        attackTitle.setFont(gameFont_Big);
        attackTitle.setHorizontalAlignment(JLabel.CENTER);
        attackTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Margen superior
        attackPanel.add(attackTitle, BorderLayout.NORTH);

        // Imagen en el centro del panel de ataque
        JLabel attackImage = new JLabel();
        attackImage.setIcon(new ImageIcon(resizeImage("./src/gui/sword.png", 300, 300)));
        attackImage.setHorizontalAlignment(JLabel.CENTER);
        attackPanel.add(attackImage, BorderLayout.CENTER);

        // Panel inferior para el spinner y el botón en el panel de ataque
        JPanel attackBottomPanel = new JPanel(new BorderLayout());
        attackBottomPanel.setOpaque(false);

        // Panel intermedio para el spinner y el botón con FlowLayout
        JPanel spinnerButtonPanelAttack = new JPanel(new FlowLayout(FlowLayout.CENTER));
        spinnerButtonPanelAttack.setOpaque(false);

        SpinnerNumberModel spinnerModelAttack = new SpinnerNumberModel(1, 1, 5, 1);
        JSpinner levelSelectorAttack = new JSpinner(spinnerModelAttack);
        levelSelectorAttack.setPreferredSize(new Dimension(50, 30));

        // Etiqueta de hierro
        JLabel ironLabelAttack = new JLabel("Attack Technology level: " + getAttackint() + " || Iron: " + Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST, JLabel.CENTER);
        ironLabelAttack.setFont(gameFont);
        ironLabelAttack.setForeground(Color.white);

        // Listener para el selector de nivel
        levelSelectorAttack.addChangeListener(e -> {
            int value = (int) levelSelectorAttack.getValue() * Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;
            ironLabelAttack.setText("Attack Technology level: " + getAttackint() + " || Iron: " + value);
            if (getIron() < value) {
                ironLabelAttack.setForeground(Color.RED); // Cambiar el color del texto a rojo si no hay suficientes recursos
            } else {
                ironLabelAttack.setForeground(Color.WHITE); // Cambiar el color del texto a blanco si hay suficientes recursos
            }
            ironLabelAttack.repaint();
        });

        // Botón de mejora
        JButton upgradeButtonAttack = new JButton("Upgrade");
        upgradeButtonAttack.addActionListener(e -> {
            int units = (int) levelSelectorAttack.getValue();
            int ironCost = Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST * units;

            // Verificar si hay suficientes recursos disponibles
            if (getIron() >= ironCost) {
                setIron(getIron() - ironCost);
                listener.update_technologies();

                try {
                    update_resources_quantity();
                    listener.update_resources_db();
                    getcv_data();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(attackBottomPanel,
                        "You've upgraded your attack level correctly",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                getcv_data();
            } else {
                JOptionPane.showMessageDialog(attackBottomPanel,
                        "Not enough resources.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel inferior para el selector de nivel y el botón de creación
        JPanel bottomPanelAttack = new JPanel(new FlowLayout());
        bottomPanelAttack.add(levelSelectorAttack);
        bottomPanelAttack.add(upgradeButtonAttack);

        attackBottomPanel.add(ironLabelAttack, BorderLayout.NORTH);
        attackBottomPanel.add(bottomPanelAttack, BorderLayout.SOUTH);
        attackPanel.add(attackBottomPanel, BorderLayout.SOUTH);

        // Agrega el panel de ataque al panel principal
        add(attackPanel, BorderLayout.WEST);

        // Panel para mejorar la tecnología de defensa
        JPanel defensePanel = new JPanel(new BorderLayout());
        defensePanel.setOpaque(false);
        defensePanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 200, 100));

        // Título del panel de defensa
        JLabel defenseTitle = new JLabel("Upgrade Defense Technology");
        defenseTitle.setForeground(Color.white);
        defenseTitle.setFont(gameFont_Big);
        defenseTitle.setHorizontalAlignment(JLabel.CENTER);
        defenseTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Margen superior
        defensePanel.add(defenseTitle, BorderLayout.NORTH);

        // Imagen en el centro del panel de defensa
        JLabel defenseImage = new JLabel();
        defenseImage.setIcon(new ImageIcon(resizeImage("./src/gui/shield.png", 300, 300)));
        defenseImage.setHorizontalAlignment(JLabel.CENTER);
        defensePanel.add(defenseImage, BorderLayout.CENTER);

        // Panel inferior para el spinner y el botón en el panel de defensa
        JPanel defenseBottomPanel = new JPanel(new BorderLayout());
        defenseBottomPanel.setOpaque(false);

        // Panel intermedio para el spinner y el botón con FlowLayout
        JPanel spinnerButtonPanelDefense = new JPanel(new FlowLayout(FlowLayout.CENTER));
        spinnerButtonPanelDefense.setOpaque(false);

        SpinnerNumberModel spinnerModelDefense = new SpinnerNumberModel(1, 1, 5, 1);
        JSpinner levelSelectorDefense = new JSpinner(spinnerModelDefense);
        levelSelectorDefense.setPreferredSize(new Dimension(50, 30));

        // Etiqueta de hierro
        JLabel ironLabelDefense = new JLabel("Actual Defense Technology level: " + getDefenseint() + " || Iron: " + Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST, JLabel.CENTER);
        ironLabelDefense.setFont(gameFont);
        ironLabelDefense.setForeground(Color.white);

        // Listener para el selector de nivel
        levelSelectorDefense.addChangeListener(e -> {
            int value = (int) levelSelectorDefense.getValue() * Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;
            ironLabelDefense.setText("Actual Defense Technology level: " + getDefenseint() + " || Iron: " + value);
            if (getIron() < value) {
                ironLabelDefense.setForeground(Color.RED); // Cambiar el color del texto a rojo si no hay suficientes recursos
            } else {
                ironLabelDefense.setForeground(Color.WHITE); // Cambiar el color del texto a blanco si hay suficientes recursos
            }
            ironLabelDefense.repaint();
        });

        // Botón de mejora
        JButton upgradeButtonDefense = new JButton("Upgrade");
        upgradeButtonDefense.addActionListener(e -> {
            int units = (int) levelSelectorDefense.getValue();
            int ironCost = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST * units;

            // Verificar si hay suficientes recursos disponibles
            if (getIron() >= ironCost) {
                setIron(getIron() - ironCost);
                listener.update_technologies();

                try {
                    update_resources_quantity();

                    listener.update_resources_db();
                    getcv_data();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(defenseBottomPanel,
                        "You've upgraded your defense level correctly",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                getcv_data();
            } else {
                JOptionPane.showMessageDialog(defenseBottomPanel,
                        "Not enough resources.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel inferior para el selector de nivel y el botón de creación
        JPanel bottomPanelDefense = new JPanel(new FlowLayout());
        bottomPanelDefense.add(levelSelectorDefense);
        bottomPanelDefense.add(upgradeButtonDefense);

        defenseBottomPanel.add(ironLabelDefense, BorderLayout.NORTH);
        defenseBottomPanel.add(bottomPanelDefense, BorderLayout.SOUTH);
        defensePanel.add(defenseBottomPanel, BorderLayout.SOUTH);

        // Agrega el panel de defensa al panel principal
        add(defensePanel, BorderLayout.EAST);
    }
}
    
    // Método para redimensionar una imagen
    private static Image resizeImage(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return resizedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

 // Método para verificar si hay suficientes recursos disponibles y cambiar el color del texto
    private void checkResourceAvailability(JLabel label, int cost, int available) {
        if (available < cost) {
            label.setForeground(Color.RED);
            label.repaint();// Cambiar el color del texto a rojo si no hay suficientes recursos
        
        } else {
            label.setForeground(Color.WHITE); 
            label.repaint();// Cambiar el color del texto a blanco si hay suficientes recursos
        }
    }

    // Método para crear el panel de creación de soldados
    JPanel createSoldierPanel(int soldierIndex, int[] costs, String imagePath) {
        JPanel panel = new BackgroundPanel();

        // Creamos un JPanel interno para contener la imagen y lo configuramos con FlowLayout centrado
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setOpaque(false);

        JLabel imageLabel = new JLabel();
        ImageIcon soldierIcon = new ImageIcon(imagePath);
        Image scaledImage = soldierIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        soldierIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(soldierIcon);

        // Agregamos el JLabel al JPanel interno
        imagePanel.add(imageLabel);

        // Agregamos el JPanel interno al JPanel principal con BorderLayout.NORTH
        panel.add(imagePanel, BorderLayout.NORTH);

        JPanel costPanel = new JPanel();
        costPanel.setLayout(new GridLayout(1, 4));
        costPanel.setOpaque(false);

        JLabel foodLabel = new JLabel("Food: " + costs[0]);
        JLabel woodLabel = new JLabel("Wood: " + costs[1]);
        JLabel ironLabel = new JLabel("Iron: " + costs[2]);
        JLabel manaLabel = new JLabel("Mana: " + costs[3]);
        

        // Establecer el color inicial del texto de los JLabels como blanco
        foodLabel.setForeground(Color.WHITE);
        woodLabel.setForeground(Color.WHITE);
        ironLabel.setForeground(Color.WHITE);
        manaLabel.setForeground(Color.WHITE);

        foodLabel.setFont(gameFont);
        woodLabel.setFont(gameFont);
        ironLabel.setFont(gameFont);
        manaLabel.setFont(gameFont);

        costPanel.add(foodLabel);
        costPanel.add(woodLabel);
        costPanel.add(ironLabel);
        costPanel.add(manaLabel);

        panel.add(costPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel(soldierNames[soldierIndex] + " units to create");
        nameLabel.setFont(gameFont);
        nameLabel.setForeground(Color.WHITE);

        bottomPanel.setPreferredSize(new Dimension(500, 100));
        bottomPanel.add(nameLabel);
        bottomPanel.setOpaque(false);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, 99, 1);
        JSpinner unitSelector = new JSpinner(numberModel);
        unitSelector.setPreferredSize(new Dimension(50, 30));
        unitSelector.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = (int) unitSelector.getValue();
                // Actualizar el texto de los JLabels de recursos según la cantidad seleccionada
                foodLabel.setText("Food: " + costs[0] * value);
                woodLabel.setText("Wood: " + costs[1] * value);
                ironLabel.setText("Iron: " + costs[2] * value);
                manaLabel.setText("Mana: " + costs[3] * value);
                // Cambiar el color del texto según si hay suficientes recursos disponibles
                checkResourceAvailability(foodLabel, costs[0] * value, getFood());
                checkResourceAvailability(woodLabel, costs[1] * value, getWood());
                checkResourceAvailability(ironLabel, costs[2] * value, getIron());
                checkResourceAvailability(manaLabel, costs[3] * value, getMana());
            }
        });
        bottomPanel.add(unitSelector);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int units = (int) unitSelector.getValue();
                int foodCost = costs[0] * units;
                int woodCost = costs[1] * units;
                int ironCost = costs[2] * units;
                int manaCost = costs[3] * units;

                // Verificar si hay suficientes recursos disponibles
                if (getFood() >= foodCost && getWood() >= woodCost && getIron() >= ironCost && getMana() >= manaCost) {
                    // Restar los recursos necesarios para crear las unidades
                    setFood(getFood() - foodCost);
                    setWood(getWood() - woodCost);
                    setIron(getIron() - ironCost);
                    setMana(getMana() - manaCost);
                    if (soldierIndex == 7 && listener.getcvmagic_tower() <1) {
                        JOptionPane.showMessageDialog(panel,
                                "Can't create Magician, No MagicTower Found",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    	
                    }else if (soldierIndex == 8 && listener.getcvchurch() <1) {
                        JOptionPane.showMessageDialog(panel,
                                "Can't create Priest, No Church Found",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    	
                    }else {

                        
                        System.out.println(soldierIndex);
                        
                        try {
    						listener.create_troop(soldierIndex, units);
    					} catch (MiSQLException e1) {
    						e1.printStackTrace();
    					}
                        
                        try {
    						update_resources_quantity();

                            //guardamos datos en base de datos y clase
                            listener.update_resources_db();
                            getcv_data();

                            

    					} catch (Exception e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
                        

                        JOptionPane.showMessageDialog(panel,
                                "You've created " + units + " units of " + soldierNames[soldierIndex],
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        getcv_data();
                    	
                    }

                    } else {
                    JOptionPane.showMessageDialog(panel,
                            "Not enough resources.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bottomPanel.add(createButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        panel.setOpaque(false);

        return panel;
    }


}
    
    
    
    
    
    
    class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File("./src/gui/panel_background_img.jpg"));
                this.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public BackgroundPanel(BorderLayout borderLayout) {
        	super(borderLayout);
            try {
                backgroundImage = ImageIO.read(new File("./src/gui/panel_background_img.jpg"));
                this.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}

		public BackgroundPanel(GridLayout gridLayout) {
			super(gridLayout);
            try {
                backgroundImage = ImageIO.read(new File("./src/gui/panel_background_img.jpg"));
                this.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}

		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Dibujar la imagen de fondo
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
    
    
    


