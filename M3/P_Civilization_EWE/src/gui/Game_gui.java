package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import interfaces.GameGuiListener;
import interfaces.MainMenuListener;
import interfaces.Variables;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import gui.dc_gui;
import gui.CvUpgradeGui.TechnologyUpgradePanel;

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
    private JLayeredPane mainpanel;
    private int mouseX;
    private int mouseY;
    private boolean isRightClicked;
    private int food;
    private int wood;
    private int iron;
    private int mana;

    private ImageIcon resources_img,woodicon,ironicon,manaicon,foodicon,panelbackgroundimage;
    private JPanel panelright_food,panelright_wood,panelright_iron,panelright_mana;
    private JLabel woodlabel,foodlabel,ironlabel,manalabel;
    private dc_gui dcGui = new dc_gui(); // Instancia de dc_gui
	private GameGuiListener listener;
    boolean isUpgradePressed = false;


    
    

    

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
        update_resources_quantity(this.getFood(),foodlabel,"food");
        
        
        panelright1.add(panelright_wood);
        agregarImagenTexto(panelright_wood, "./src/gui/brown.png",woodlabel,woodicon);
        update_resources_quantity(this.getWood(),woodlabel,"wood");


        panelright1.add(panelright_iron);
        agregarImagenTexto(panelright_iron, "./src/gui/brown.png",ironlabel,ironicon);
        update_resources_quantity(this.getIron(),ironlabel,"iron");


        panelright1.add(panelright_mana);
        agregarImagenTexto(panelright_mana, "./src/gui/brown.png",manalabel,manaicon);
        update_resources_quantity(this.getMana(),manalabel,"mana");



        
        
        
        JLayeredPane mainpanel = new JLayeredPane();
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


     
    	showCustomPanel(mainpanel);

        
        
        
        
        
        
        
        

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                subPanels[i][j] = new MiPanelito(dcGui);
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
        
        
        // Agregar MouseWheelListener para el zoom
        mainPanel2.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Obtener la dirección del movimiento de la rueda del ratón
                int notches = e.getWheelRotation();

                // Realizar el zoom en base a la dirección de desplazamiento
                if (notches < 0) {
                    // Zoom in
                    zoomIn(mainPanel2);
                } else {
                    // Zoom out
                    zoomOut(mainPanel2);
                }
            }
        });
        
        
        
        
        
        
        
        
        
    
    mainPanel2.addMouseMotionListener(new MouseAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (isRightClicked) {
                int newX = e.getXOnScreen();
                int newY = e.getYOnScreen();

                int deltaX = (int) ((newX - mouseX) / (zoomLevel / INIT_ZOOM));
                int deltaY = (int) ((newY - mouseY) / (zoomLevel / INIT_ZOOM));

                Point currentLocation = mainPanel2.getLocation();
                mainPanel2.setLocation(currentLocation.x + deltaX, currentLocation.y + deltaY);

                mouseX = newX;
                mouseY = newY;
            }
        }
    });
    
    
    
    
    
    // En el constructor de Game_gui, después de crear panelup
    JButton upgrade_cvbutton = new JButton("Upgrade Civilization");
    upgrade_cvbutton.setPreferredSize(new Dimension(300,70));
    panelright2.setBorder(BorderFactory.createEmptyBorder(80, 20, 50, 20));
    panelright2.add(upgrade_cvbutton,BorderLayout.NORTH);
    isUpgradePressed = false;
    JPanel panelpestañas = new JPanel(new BorderLayout());
    
    
    //cargamos paneles de mejora de civilizacion al empezar la partida
    
    CvUpgradeGui cvUpgradeGui = new CvUpgradeGui(); // Instancia de la clase CvUpgradeGui

    // Crear el panel para las pestañas

       // Crear el JTabbedPane para las pestañas
       JTabbedPane tabbedPane = new JTabbedPane();

       // Upgrade Army tab
       JPanel upgradeArmyPanel = new JPanel();
       upgradeArmyPanel.setLayout(new GridLayout(3, 3));
       String[] soldierNames = {"Swordsman", "Spearman", "Crossbow", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher Tower", "Magician", "Priest"};
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
           upgradeArmyPanel.add(cvUpgradeGui.createSoldierPanel(soldierNames[i], soldierCosts[i], soldierImages[i])); // Llamada al método createSoldierPanel a través de la instancia
       }

       // Upgrade Technology tab
       JPanel upgradeTechnologyPanel = cvUpgradeGui.new TechnologyUpgradePanel(); // Utiliza la nueva clase TechnologyUpgradePanel

       // Agregar los paneles de actualización de ejército y tecnología al panel de pestañas
       tabbedPane.add("Upgrade Army", upgradeArmyPanel);
       tabbedPane.add("Upgrade Technology", upgradeTechnologyPanel);

       // Agregar el JTabbedPane al panel de pestañas
       panelpestañas.add(tabbedPane, BorderLayout.CENTER);


       // Agregar los paneles al mainPanel en diferentes capas
       mainpanel.add(panelpestañas, JLayeredPane.PALETTE_LAYER); // Capa por defecto

       // Configurar las posiciones y tamaños de los paneles
       panelpestañas.setBounds(0, 0, 1500, 1080);
       
       panelpestañas.setVisible(false);



    upgrade_cvbutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Alternar el estado del botón
            isUpgradePressed = !isUpgradePressed;


            if (isUpgradePressed) {
                panelpestañas.setVisible(true);
                upgrade_cvbutton.getModel().setPressed(isUpgradePressed);

               
            } else {
            	panelpestañas.setVisible(false);            }
        }
    });
    
    
    
    //añadimos panel en panelright2
    
    JPanel userPanel = new JPanel();
    userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

    // Imagen cuadrada
    ImageIcon imageIcon = new ImageIcon("./src/gui/user_image.png");
    Image originalImage = imageIcon.getImage();

    Image resizedImage = originalImage.getScaledInstance(280, 200, Image.SCALE_SMOOTH);

    // Crear un ImageIcon con la imagen redimensionada
    ImageIcon scaledIcon = new ImageIcon(resizedImage);
    JLabel imageLabel = new JLabel(scaledIcon);
    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    userPanel.add(imageLabel);
    imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));


    // Texto de usuario
    JLabel usernameLabel = new JLabel("Username");
    usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    userPanel.add(usernameLabel);

    // Temporizador estilo 0:00
    JLabel timerLabel = new JLabel("0:00", JLabel.CENTER);
    timerLabel.setFont(new Font("Arial", Font.BOLD, 40));
    timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    userPanel.add(timerLabel);
    userPanel.setOpaque(false);
    // Temporizador
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
    userPanel.setPreferredSize(new Dimension(300,300));


    panelright2.add(userPanel,BorderLayout.SOUTH);

    
    
    
}
	
	
    //metodo para guardar paneles
    
    public void updatePanels() {
        this.listener.load_game_gui();

    }
	
    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        listener.update_resources(food, getWood(), getIron(), getMana());
    }

    public int getWood() {
        return this.wood;
    }

    public void setWood(int wood) {
        listener.update_resources(getFood(), wood, getIron(), getMana());
    	
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        listener.update_resources(getFood(), getWood(), iron, getMana());
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        listener.update_resources(getFood(), getWood(), getIron(), mana);
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
    
    public void update_resources_quantity(int quantity,JLabel labeltoupdate, String resource) throws Exception {
    	
    	
    	if (resource == "food") {
    		this.food = quantity;
    		
    	}else if (resource == "wood") {
    		this.wood = quantity;

    	}else if (resource == "iron") {
    		this.iron = quantity;

    	}else if (resource == "mana") {
    		this.mana = quantity;

    	}else {
    		
    		throw new Exception("Incorrect Label");
    	}
    	int resource_update = quantity;
    	labeltoupdate.setText(Integer.toString(resource_update));
    	labeltoupdate.getParent().revalidate();
    	labeltoupdate.getParent().repaint();
    	
    }
    
    
    

    private void agregarTexto(JPanel panel, String text) {
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 16));
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
            textLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
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
    
    //metodo notificacion
    
    private void showCustomPanel(JLayeredPane parentComponent) {
        JPanel customPanel = new JPanel();
    	ImageIcon originalIcon = new ImageIcon("./src/gui/battleicon.png"); // Reemplaza "ruta_de_la_imagen.jpg" con la ruta de tu imagen
    	Image originalImage = originalIcon.getImage();
    	Image resizedImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Ajusta el tamaño según sea necesario
    	ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel customLabel = new JLabel("ATTACK INCOMING");
    	customLabel.setIcon(resizedIcon);
        customLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        customLabel.setSize(new Dimension(200, 200));
        customPanel.add(customLabel);
        customPanel.setBackground(Color.red);

        int parentWidth = parentComponent.getWidth();
        int panelWidth = 500; // Ancho del panel personalizado
        int panelHeight = 200; // Alto del panel personalizado
        int panelX =  panelWidth +200; // Posición X para centrar horizontalmente
        int panelYStart = -panelHeight; // Posición inicial Y para colocar arriba del JLayeredPane
        int panelYEnd = (parentComponent.getHeight() - panelHeight +200) / 2; // Posición final Y para centrar verticalmente

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





    
    
    
    
    
    
    
    
    
    
    

    void battlelog_frame(){
		

    	JFrame popupFrame = new JFrame("Battle Log");
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un JTextArea y colocarlo en un JScrollPane
        JTextArea textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Agregar texto al JTextArea
        textArea.setText("Texto en la ventana emergente.");

        // Crear un JPanel para contener los componentes
        JPanel panelContainer = new JPanel();
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));

        // Cargar la imagen battleicon.png
        ImageIcon battleIcon = new ImageIcon("./src/gui/battleicon.png");
        Image scaledImage = battleIcon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Agregar la imagen al JPanel
        panelContainer.add(iconLabel);

        // Agregar el JScrollPane al JPanel
        panelContainer.add(scrollPane);

        // Agregar el JPanel al JFrame
        popupFrame.getContentPane().add(panelContainer, BorderLayout.CENTER);

        // Ajustar el tamaño de la ventana y hacerla visible
        popupFrame.pack();
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setVisible(true);
    	}
    
    
    


class MiPanelito extends JPanel {
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



    
    
    
    

    public MiPanelito(dc_gui dcGui) {

        this.dcGui = dcGui;

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
											update_resources_quantity(getWood(),woodlabel,"wood");
	                                        update_resources_quantity(getIron(),ironlabel,"iron");
	                                        update_resources_quantity(getMana(),manalabel,"mana");
	                                        update_resources_quantity(getFood(),foodlabel,"food");
	                                        //guardamos datos en base de datos
	                                        listener.update_resources_db(getFood(), getWood(), getIron(), getMana());

										} catch (Exception e1) {
										
											e1.printStackTrace();
										}



                                        // Cambiar la imagen del panel al edificio correspondiente
                                        setCurrentImage(buildingImages[index]);
                                        repaint();
                                        isoccupied = true;
                                        panelcontent = future_structure;
                                        
                                    } else {
                                    	System.out.println(getWood());

                                        JOptionPane.showMessageDialog(null, "¡No tienes suficientes recursos para construir este edificio!", "Recursos insuficientes", JOptionPane.ERROR_MESSAGE);
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
}

//Clases Paneles para mejorar Civilizacion


class CvUpgradeGui {


    private static int resources = 1000;
    private static int multiplier = 1; // Variable para el multiplicador de costos


    
    public class TechnologyUpgradePanel extends BackgroundPanel {
        public TechnologyUpgradePanel() {
            setLayout(new BorderLayout());

            JPanel attackPanel = new JPanel(new BorderLayout());
         // Panel para Upgrade Attack Technology
            attackPanel.setOpaque(false);
            attackPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 200, 100));
            JLabel attackTitle = new JLabel("Upgrade Attack Technology");
            attackTitle.setFont(new Font("Arial", Font.BOLD, 25));  // Aumenta el tamaño de la fuente
            attackTitle.setHorizontalAlignment(JLabel.CENTER);
            attackTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Margen superior
            attackPanel.add(attackTitle, BorderLayout.NORTH);

            // Agrega una imagen en el centro
            JLabel attackImage = new JLabel();
            attackImage.setIcon(new ImageIcon(resizeImage("./src/gui/sword.png", 300, 300)));
            attackImage.setHorizontalAlignment(JLabel.CENTER);
            attackPanel.add(attackImage, BorderLayout.CENTER);

            // Panel para el spinner y el botón
            JPanel attackBottomPanel = new JPanel(new BorderLayout());

            // Nivel de tecnología actual
            JLabel currentLevelLabel = new JLabel("Nivel de Tecnología Actual: 1", JLabel.CENTER);
            currentLevelLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Aumenta el tamaño de la fuente
            attackBottomPanel.add(currentLevelLabel, BorderLayout.NORTH);

            // Panel intermedio para el spinner y el botón con FlowLayout
            JPanel spinnerButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            
            // Agrega un JSpinner y un botón Upgrade
            JSpinner levelSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
            levelSpinner.setFont(new Font("Arial", Font.PLAIN, 20));  // Aumenta el tamaño de la fuente
            Dimension spinnerSize = new Dimension(80, 40);  // Aumenta el tamaño del spinner
            levelSpinner.setPreferredSize(spinnerSize);
            levelSpinner.addChangeListener(e -> {
                int value = (int) levelSpinner.getValue();
                currentLevelLabel.setText("Nivel de Tecnología Actual: " + value);
            });
            spinnerButtonPanel.add(levelSpinner);

            JButton upgradeButton = new JButton("Upgrade");
            upgradeButton.setFont(new Font("Arial", Font.BOLD, 20));  // Aumenta el tamaño de la fuente
            Dimension buttonSize = new Dimension(150, 40);  // Aumenta el tamaño del botón
            upgradeButton.setPreferredSize(buttonSize);
            spinnerButtonPanel.add(upgradeButton);

            attackBottomPanel.add(spinnerButtonPanel, BorderLayout.CENTER);

            attackPanel.add(attackBottomPanel, BorderLayout.SOUTH);

            // Agrega el panel al upgradeTechnologyPanel
            add(attackPanel, BorderLayout.WEST);            // ...

         // Panel para Upgrade Defense Technology
            JPanel defensePanel = new JPanel(new BorderLayout());
            defensePanel.setOpaque(false);
            defensePanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 200, 100));

            JLabel defenseTitle = new JLabel("Upgrade Defense Technology");
            defenseTitle.setFont(new Font("Arial", Font.BOLD, 25));  // Aumenta el tamaño de la fuente
            defenseTitle.setHorizontalAlignment(JLabel.CENTER);
            defenseTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Margen superior
            defensePanel.add(defenseTitle, BorderLayout.NORTH);

            // Agrega una imagen en el centro
            JLabel defenseImage = new JLabel();
            defenseImage.setIcon(new ImageIcon(resizeImage("./src/gui/shield.png", 300, 300)));
            defenseImage.setHorizontalAlignment(JLabel.CENTER);
            defensePanel.add(defenseImage, BorderLayout.CENTER);

            // Panel para el spinner y el botón
            JPanel defenseBottomPanel = new JPanel(new BorderLayout());

            // Nivel de tecnología actual
            JLabel currentLevelLabelDefense = new JLabel("Nivel de Tecnología Actual: 1", JLabel.CENTER);
            currentLevelLabelDefense.setFont(new Font("Arial", Font.BOLD, 20));  // Aumenta el tamaño de la fuente
            defenseBottomPanel.add(currentLevelLabelDefense, BorderLayout.NORTH);

            // Panel intermedio para el spinner y el botón con FlowLayout
            JPanel spinnerButtonPanelDefense = new JPanel(new FlowLayout(FlowLayout.CENTER));

            // Agrega un JSpinner y un botón Upgrade
            JSpinner leveltSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
            leveltSpinner.setFont(new Font("Arial", Font.PLAIN, 20));  // Aumenta el tamaño de la fuente
            leveltSpinner.setPreferredSize(spinnerSize);  // Usa el tamaño aumentado del spinner
            leveltSpinner.addChangeListener(e -> {
                int value = (int) leveltSpinner.getValue();
                currentLevelLabelDefense.setText("Nivel de Tecnología Actual: " + value);
            });
            spinnerButtonPanelDefense.add(leveltSpinner);

            JButton upgradeButton1 = new JButton("Upgrade");
            upgradeButton1.setFont(new Font("Arial", Font.BOLD, 20));  // Aumenta el tamaño de la fuente
            upgradeButton1.setPreferredSize(buttonSize);  // Usa el tamaño aumentado del botón
            spinnerButtonPanelDefense.add(upgradeButton1);

            defenseBottomPanel.add(spinnerButtonPanelDefense, BorderLayout.CENTER);

            defensePanel.add(defenseBottomPanel, BorderLayout.SOUTH);


            add(attackPanel, BorderLayout.WEST);
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


    JPanel createSoldierPanel(String soldierName, int[] costs, String imagePath) {
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

        Font fuente = new Font("Arial", Font.PLAIN, 20);

        JLabel foodLabel = new JLabel("Food: " + costs[0]);
        JLabel woodLabel = new JLabel("Wood: " + costs[1]);
        JLabel ironLabel = new JLabel("Iron: " + costs[2]);
        JLabel manaLabel = new JLabel("Mana: " + costs[3]);
        
        foodLabel.setForeground(Color.white);
        woodLabel.setForeground(Color.white);
        ironLabel.setForeground(Color.white);
        manaLabel.setForeground(Color.white);


        foodLabel.setFont(fuente);
        woodLabel.setFont(fuente);
        ironLabel.setFont(fuente);
        manaLabel.setFont(fuente);

        costPanel.add(foodLabel);
        costPanel.add(woodLabel);
        costPanel.add(ironLabel);
        costPanel.add(manaLabel);

        panel.add(costPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel(soldierName + " units to create");
        nameLabel.setForeground(Color.white);

        nameLabel.setFont(fuente);

        bottomPanel.setPreferredSize(new Dimension(500,100));
        bottomPanel.add(nameLabel);
        bottomPanel.setOpaque(false);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, 5, 1);
        JSpinner unitSelector = new JSpinner(numberModel);
        unitSelector.setPreferredSize(new Dimension(50,30));
        unitSelector.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = (int) unitSelector.getValue();
                foodLabel.setText("Food: " + costs[0] * value);
                woodLabel.setText("Wood: " + costs[1] * value);
                ironLabel.setText("Iron: " + costs[2] * value);
                manaLabel.setText("Mana: " + costs[3] * value);
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

                if (resources >= foodCost && resources >= woodCost && resources >= ironCost && resources >= manaCost) {
                    resources -= foodCost;
                    resources -= woodCost;
                    resources -= ironCost;
                    resources -= manaCost;
                    JOptionPane.showMessageDialog(panel,
                            "You've created " + units + " units of " + soldierName + ".\nCost: " + (foodCost + woodCost + ironCost + manaCost) + "\nRemaining Resources: " + resources,
                            "Success", JOptionPane.INFORMATION_MESSAGE);
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
    
    
    
    
    
    
    
    private class BackgroundPanel extends JPanel {
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

