package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

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
    private int mouseX;
    private int mouseY;
    private boolean isRightClicked;
    private int food, wood, iron, mana;
    private ImageIcon resources_img,woodicon,ironicon,manaicon,foodicon,panelbackgroundimage;
    private JPanel panelright_food,panelright_wood,panelright_iron,panelright_mana;
    private JLabel woodlabel,foodlabel,ironlabel,manalabel;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Game_gui gameGui = new Game_gui(10, 10);
            frame.add(gameGui, BorderLayout.CENTER);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public Game_gui(int rows, int cols) {
        this.food = 1000;
        this.wood = 1000;
        this.iron = 1000;
        this.mana = 1010;

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

        // Load the resources_img icon
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
        update_resources_quantity(1300,foodlabel);
        
        
        panelright1.add(panelright_wood);
        agregarImagenTexto(panelright_wood, "./src/gui/brown.png",woodlabel,woodicon);

        panelright1.add(panelright_iron);
        agregarImagenTexto(panelright_iron, "./src/gui/brown.png",ironlabel,ironicon);

        panelright1.add(panelright_mana);
        agregarImagenTexto(panelright_mana, "./src/gui/brown.png",manalabel,manaicon);


        // Rest of the constructor code...
   

        setLayout(new BorderLayout());

        add(mainPanel1, BorderLayout.CENTER);

        mainPanel1.add(containerpanel, BorderLayout.WEST);
        mainPanel1.add(panelright, BorderLayout.EAST);
        mainPanel1.add(panelup, BorderLayout.NORTH);

        panelright.add(panelright1, BorderLayout.NORTH);
        panelright.add(panelright2, BorderLayout.SOUTH);

        containerpanel.add(mainPanel2);
        
        
        

        mainPanel1.setPreferredSize(new Dimension(1920, 1080));
        
        containerpanel.setPreferredSize(new Dimension(1500, 1300));
        mainPanel2.setPreferredSize(new Dimension(1500, 1400));
        
        
        panelright.setPreferredSize(new Dimension(420, 1400));
        panelright1.setPreferredSize(new Dimension(425, 500));
        panelright2.setPreferredSize(new Dimension(425, 700));
        panelup.setPreferredSize(new Dimension(1400, 20));
        

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
}
    
    
    
    
    //metodos
    
    //este metodo actualiza el label que queramos con un numero
    
    private void update_resources_quantity(int quantity,JLabel labeltoupdate) {
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


}

class MiPanelito extends JPanel {
    private int lastMouseX;
    private int lastMouseY;
    private Color panelColor = Color.GREEN;
    private Color borderColor = Color.black;
    private ImageIcon[] buildingImages = new ImageIcon[5];
    private ImageIcon currentImage,main_texture;

    public MiPanelito() {
    	main_texture = new ImageIcon("./src/gui/main_grass_texture.jpg");
    	this.setCurrentImage(main_texture);
        //setBorder(BorderFactory.createLineBorder(borderColor));

        buildingImages[0] = new ImageIcon("path/to/farm.png");
        buildingImages[1] = new ImageIcon("path/to/smithy.png");
        buildingImages[2] = new ImageIcon("./src/gui/church.png");
        buildingImages[3] = new ImageIcon("path/to/magic_tower.png");
        buildingImages[4] = new ImageIcon("path/to/carpentry.png");

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    lastMouseX = e.getX();
                    lastMouseY = e.getY();
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    String[] options = {"Farm", "Smithy", "Church", "Magic Tower", "Carpentry"};
                    int choice = JOptionPane.showOptionDialog(null, "Select a building to create:", "Create Building",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                    setCurrentImage(buildingImages[choice]);
                    repaint();
                }
            }
        });
    }

    public MiPanelito(BorderLayout borderLayout) {
    	super(new BorderLayout());
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
}