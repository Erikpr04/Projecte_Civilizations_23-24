package gui;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.MiSQLException;
import interfaces.MainMenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JPanel {
	private ImageIcon lg_button  = new ImageIcon("./src/gui/mm_assets/lg_button.png");
	private ImageIcon sg_button  = new ImageIcon("./src/gui/mm_assets/sg_button.png");
	private ImageIcon options_button  = new ImageIcon("./src/gui/mm_assets/options_button.png");
	private ImageIcon quit_button  = new ImageIcon("./src/gui/mm_assets/quit_button.png");
	private ImageIcon play_button  = new ImageIcon("./src/gui/mm_assets/play_button.png");
	private ImageIcon settings_button  = new ImageIcon("./src/gui/mm_assets/settings_button.png");
    private MainMenuListener listener;
    private String[] userData;
    private JFrame creategameframe;
    private Font gameFont;
    private ImageIcon gamelogo = new ImageIcon("./src/gui/game_logo.png");






   
    
    public String[] getUserData() {
		return userData;
	}


	public void setUserData(String[] userData) {
		this.userData = userData;
	}


	// Método para establecer el listener
    public void setMainMenuListener(MainMenuListener listener) {
        this.listener = listener;
    }
	
    
    public MainMenu(MainMenuListener listener) {
        this.listener = listener; // Asignar el listener recibido al atributo listener de MainMenu
        // Otro código de inicialización de MainMenu
    }

	
	
    public MainMenu() {
    	
    	
        try {
			gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("./src/gui/Monocraft.ttf")).deriveFont(18f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	
    	
    	
    	int sizebutton1 = 250;
    	int sizebutton2 = 100;
    	
   
    	
    	
    	// Establecer el diseño del panel principal
    	setLayout(new BorderLayout());

    	// Crear el título del juego
        ImageIcon gamelogo = new ImageIcon("./src/gui/GAME LOGO.png");
        

    	JLabel titleLabel = new JLabel(gamelogo);
    	
    	titleLabel.setForeground(Color.WHITE);
    	
    	titleLabel.setFont(gameFont);
    	titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

    	// Crear los botones del menú
    	//savegame button
    	LabelButton newGameButton = new LabelButton("");
    	ImageIcon originalIcon = new ImageIcon("./src/gui/mm_assets/play_button.png"); // Reemplaza "ruta_de_la_imagen.jpg" con la ruta de tu imagen
    	Image originalImage = originalIcon.getImage();
    	Image resizedImage = originalImage.getScaledInstance(sizebutton1, sizebutton2, Image.SCALE_SMOOTH); // Ajusta el tamaño según sea necesario
    	ImageIcon resizedIcon = new ImageIcon(resizedImage);
    	newGameButton.setIcon(resizedIcon);
    	
    	
    	newGameButton.addMouseListener(new MouseListener() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
                try {
                	createAndShowGUI();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	
    	
    	
    	
    	
    	
    	//loadgame button
    	LabelButton loadGameButton = new LabelButton("");
    	originalIcon = new ImageIcon("./src/gui/mm_assets/lg_button.png"); // Reemplaza "ruta_de_la_imagen.jpg" con la ruta de tu imagen
    	originalImage = originalIcon.getImage();
    	resizedImage = originalImage.getScaledInstance(sizebutton1, sizebutton2, Image.SCALE_SMOOTH); // Ajusta el tamaño según sea necesario
    	resizedIcon = new ImageIcon(resizedImage);
    	loadGameButton.setIcon(resizedIcon);
    	
    	
    	
    	loadGameButton.addMouseListener(new MouseListener() {
			

			public void mouseReleased(MouseEvent e) {
				
			}
			

			public void mousePressed(MouseEvent e) {
				try {
					listener.loadgame();
				} catch (MiSQLException e1) {

					e1.printStackTrace();
				}	

			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	//options button
    	LabelButton optionsButton = new LabelButton("");
    	originalIcon = new ImageIcon("./src/gui/mm_assets/CREDITS.png"); // Reemplaza "ruta_de_la_imagen.jpg" con la ruta de tu imagen
    	originalImage = originalIcon.getImage();
    	resizedImage = originalImage.getScaledInstance(sizebutton1, sizebutton2, Image.SCALE_SMOOTH); // Ajusta el tamaño según sea necesario
    	resizedIcon = new ImageIcon(resizedImage);
    	optionsButton.setIcon(resizedIcon);
    	
    	optionsButton.addMouseListener(new MouseListener() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
                openCreditsWindow();

				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	
    	
    	
    	LabelButton exitButton = new LabelButton("");
    	originalIcon = new ImageIcon("./src/gui/mm_assets/quit_button.png"); // Reemplaza "ruta_de_la_imagen.jpg" con la ruta de tu imagen
    	originalImage = originalIcon.getImage();
    	resizedImage = originalImage.getScaledInstance(sizebutton1, sizebutton2, Image.SCALE_SMOOTH); // Ajusta el tamaño según sea necesario
    	resizedIcon = new ImageIcon(resizedImage);
    	exitButton.setIcon(resizedIcon);


    	
    	exitButton.addMouseListener(new MouseListener() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
		        // Cerrar la ventana del menú principal
				dispose_main_menu();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	
    	
    	
    	BackgroundAnimatedPanel mainpanel = new BackgroundAnimatedPanel();
    	add(mainpanel);
    	// Agregar ActionListener a cada botón


    	JPanel leftPanel = new JPanel(new BorderLayout());

    	// Establecer la imagen de fondo
    	leftPanel.setOpaque(false);
    	leftPanel.setBackground(new Color(0, 0, 0, 0)); // Color transparente
    	JPanel rightPanel = new JPanel(new BorderLayout());

    	// Establecer la imagen de fondo
    	rightPanel.setOpaque(false);
    	rightPanel.setBackground(new Color(0, 0, 0, 0)); // Color transparente

    	// Crear un panel para los botones y agregarlos
    	JPanel buttonPanelContainer = new JPanel();
    	buttonPanelContainer.setLayout(new BoxLayout(buttonPanelContainer, BoxLayout.Y_AXIS)); // BoxLayout para que los componentes se apilen verticalmente

    	JPanel buttonPanel = new JPanel(new GridLayout(0, 1)); // Un GridLayout de una columna
    	buttonPanel.add(newGameButton);
    	buttonPanel.add(loadGameButton);
    	buttonPanel.add(optionsButton);
    	buttonPanel.add(exitButton);
    	buttonPanel.setOpaque(false);
    	buttonPanel.setBackground(new Color(0, 0, 0, 0)); // Color transparente
    	

    	buttonPanelContainer.add(Box.createVerticalGlue()); // Espacio para que los componentes estén centrados verticalmente
    	buttonPanelContainer.add(buttonPanel);
    	buttonPanelContainer.add(Box.createVerticalGlue()); // Espacio para que los componentes estén centrados verticalmente
    	buttonPanelContainer.setOpaque(false);
    	buttonPanelContainer.setBackground(new Color(0, 0, 0, 0)); // Color transparente
    	

    	// Agregar buttonPanelContainer al centro de rightPanel
    	rightPanel.add(buttonPanelContainer, BorderLayout.CENTER);

    	// Establecer tamaños preferidos
    	leftPanel.setPreferredSize(new Dimension(1280, 1080));
    	rightPanel.setPreferredSize(new Dimension(400, 1080)); // Cambia el tamaño de acuerdo a tus necesidades

    	// Agregar los componentes al panel principal
    	mainpanel.add(leftPanel, BorderLayout.EAST);
    	mainpanel.add(rightPanel, BorderLayout.WEST);
    	mainpanel.setPreferredSize(new Dimension(1920,1080));

    	leftPanel.add(titleLabel,BorderLayout.CENTER);
    	



    }
    
    


    
    
    
    
    
    
    
    
   

    private void startGame(){
        dispose_main_menu();



    }

    
private void openCreditsWindow() {
    // Crear una nueva ventana de opciones
    JFrame optionsFrame = new JFrame("About Us");
    optionsFrame.setIconImage(gamelogo.getImage());
    optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    optionsFrame.setSize(600, 400);
    optionsFrame.setLocationRelativeTo(null);

    // Crear un panel principal con BorderLayout
    ImageBackgroundPanel mainPanel = new ImageBackgroundPanel(new BorderLayout(), "./src/gui/panel_background_img.jpg");
    optionsFrame.add(mainPanel);


 // Crear y agregar el título centrado en la parte superior con un borde superior de 30 píxeles
    JLabel titleLabel = new JLabel("About Us:", SwingConstants.CENTER);
    titleLabel.setFont(gameFont);
    titleLabel.setOpaque(false);
    titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // 30 píxeles de borde superior
    mainPanel.add(titleLabel, BorderLayout.NORTH);


    // Crear un panel para los nombres
    JPanel namesPanel = new JPanel();
    namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.Y_AXIS));
    namesPanel.add(Box.createRigidArea(new Dimension(0, 30))); // 30 píxeles de espacio en blanco

    namesPanel.setOpaque(false);

    // Agregar espacio en blanco al principio del panel para aumentar el margen superior
    // Agregar el panel de nombres al panel principal
    mainPanel.add(namesPanel, BorderLayout.CENTER);

    // Crear y agregar el texto "Made by NEWEL" en la parte superior de namesPanel
    JLabel madeByLabel = new JLabel("Made by NEWEL: ", SwingConstants.CENTER);
    madeByLabel.setFont(gameFont);
    madeByLabel.setBorder(BorderFactory.createEmptyBorder(0, 220, 0, 20)); // Agregar margen a la izquierda y derecha

    namesPanel.add(madeByLabel);

    // Nombres de los integrantes
    String[] names = {"William Molina", "Erik Rojas", "Erik Pinto"};

 // Añadir animación de fade in para los nombres
 for (String name : names) {
     JLabel nameLabel = new JLabel(name); // Centra el texto horizontalmente
     nameLabel.setLayout(new BoxLayout(nameLabel, BoxLayout.Y_AXIS));

     nameLabel.setFont(gameFont);
     nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 220, 0, 0)); // Agregar margen a la izquierda y derecha
     nameLabel.setForeground(Color.white);
     nameLabel.setForeground(new Color(0, 0, 0, 0)); // Inicialmente transparente
     namesPanel.add(nameLabel);

     Timer timer = new Timer(0, new ActionListener() {
         private int alpha = 0;

         @Override
         public void actionPerformed(ActionEvent e) {
             alpha += 10;
             if (alpha > 255) {
                 alpha = 255;
                 ((Timer) e.getSource()).stop();
             }
             nameLabel.setForeground(new Color(0, 0, 0, alpha));
         }
     });
     timer.setInitialDelay(0); // Esperar antes de empezar el fade in
     timer.start();

     try {
         Thread.sleep(0); // Esperar antes de empezar con el siguiente nombre
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
 }


    optionsFrame.setVisible(true);
}
    
    private void dispose_main_menu() {
            // Obtener la referencia a la ventana del menú principal
            JFrame mainMenuFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

            // Cerrar la ventana del menú principal
            mainMenuFrame.dispose();
        }


    
    
    

    private void createAndShowGUI() {
        creategameframe = new JFrame("Create Game");
        creategameframe.setIconImage(gamelogo.getImage());

        creategameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        creategameframe.setSize(1000, 800);
        creategameframe.setLayout(new BorderLayout());
        creategameframe.setLocationRelativeTo(null);

        // Panel principal con margen
        ImageBackgroundPanel mainPanel = new ImageBackgroundPanel(new BorderLayout(),"./src/gui/panel_background_img.jpg");
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel para los inputs
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10)); // Añadir espacio entre componentes
        inputPanel.setOpaque(false);

        // Panel para el nombre de usuario
        JPanel usernamePanel = new JPanel();
        usernamePanel.setOpaque(false);

        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(gameFont);

        usernameLabel.setForeground(Color.white);

        usernameLabel.setFont(gameFont);
        JTextField usernameField = new JTextField();
        usernameField.setFont(gameFont);


        usernamePanel.add(usernameLabel);
        usernamePanel.add(Box.createVerticalStrut(5)); // Espacio entre etiqueta y campo de texto
        usernamePanel.add(usernameField);

        inputPanel.add(usernamePanel, BorderLayout.NORTH);

        // Panel para las fotos de perfil
        JPanel profilePhotoPanel = new JPanel(new BorderLayout(10, 10));
        profilePhotoPanel.setOpaque(false);

        JPanel photoLabelPanel = new JPanel(new BorderLayout());
        photoLabelPanel.setOpaque(false);

        JLabel pickPhotoLabel = new JLabel("Pick one profile photo:");
        pickPhotoLabel.setForeground(Color.white);
        pickPhotoLabel.setFont(gameFont);
        photoLabelPanel.add(pickPhotoLabel, BorderLayout.WEST);
        photoLabelPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER); // Añadir espacio para ocupar el ancho

        profilePhotoPanel.add(photoLabelPanel, BorderLayout.NORTH);
        JPanel photoGridPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        photoGridPanel.setOpaque(false);
        photoGridPanel.setPreferredSize(new Dimension(200,200));
        ButtonGroup photoGroup = new ButtonGroup();
        
        
        
    	ImageIcon[] ppphotos= new ImageIcon[9];
		ppphotos[0] = new ImageIcon("./src/gui/ppphotos/pp1.png");
    	ppphotos[1] = new ImageIcon("./src/gui/ppphotos/pp2.png");
    	ppphotos[2] = new ImageIcon("./src/gui/ppphotos/pp3.png");
    	ppphotos[3] = new ImageIcon("./src/gui/ppphotos/pp4.png");
    	ppphotos[4] = new ImageIcon("./src/gui/ppphotos/pp5.png");
    	ppphotos[5] = new ImageIcon("./src/gui/ppphotos/pp6.png");
    	ppphotos[6] = new ImageIcon("./src/gui/ppphotos/pp7.png");
    	ppphotos[7] = new ImageIcon("./src/gui/ppphotos/pp8.png");
    	ppphotos[8] = new ImageIcon("./src/gui/ppphotos/pp9.png");


    	for (int i = 0; i < 9; i++) {
    	    ImageIcon originalIcon = ppphotos[i];
    	    Image originalImage = originalIcon.getImage();
    	    Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
    	    ImageIcon resizedIcon = new ImageIcon(resizedImage);
    	    
    	    JToggleButton photoButton = new JToggleButton(resizedIcon);
    	    photoButton.setContentAreaFilled(false);
    	    photoButton.setBorderPainted(false);
    	    photoButton.setActionCommand(String.valueOf(i + 1)); // Índice base 1
    	    photoGroup.add(photoButton);
    	    photoGridPanel.add(photoButton);
    	}



        profilePhotoPanel.add(photoGridPanel, BorderLayout.CENTER);
        inputPanel.add(profilePhotoPanel, BorderLayout.CENTER);

        // Botón "Play"
        JButton playButton = new JButton("Play");
        playButton.setFont(gameFont);
        playButton.setEnabled(false);

        // Habilitar el botón "Play" cuando se han llenado ambos campos
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { checkFields(); }
            public void removeUpdate(DocumentEvent e) { checkFields(); }
            public void changedUpdate(DocumentEvent e) { checkFields(); }
            private void checkFields() {
                playButton.setEnabled(!usernameField.getText().trim().isEmpty() && photoGroup.getSelection() != null);
            }
        });

        for (Component comp : photoGridPanel.getComponents()) {
            if (comp instanceof JToggleButton) {
                ((JToggleButton) comp).addItemListener(e -> {
                    playButton.setEnabled(!usernameField.getText().trim().isEmpty() && photoGroup.getSelection() != null);
                });
            }
        }

        // Acción al presionar el botón "Play"
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playButton.isEnabled()) {
                    String username = usernameField.getText();
                    int photoIndex = Integer.parseInt(photoGroup.getSelection().getActionCommand());
                    try {
                    	//listener para empezar nueva partida                    	
    					listener.startnewgame(username,photoIndex);
    					creategameframe.dispose();
    					} catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Agregar componentes al panel principal
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(playButton, BorderLayout.SOUTH);

        // Agregar el panel principal al frame
        creategameframe.add(mainPanel);
        creategameframe.setVisible(true);
        creategameframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


}










//CLASE PARA PANEL DE FONDO

//panel de fondo animado

class BackgroundAnimatedPanel extends JPanel {
    private BufferedImage[] images;
    private BufferedImage currentImage;
    private BufferedImage nextImage;
    private int currentIndex = 0;
    private int fadeDuration = 10000; // Duración del desvanecimiento en milisegundos
    private Timer fadeTimer;

    public BackgroundAnimatedPanel() {
        // Cargar imágenes localmente
        images = new BufferedImage[3];
        try {
            images[0] = ImageIO.read(new File("./src/gui/bck_img1.jpg"));
            images[1] = ImageIO.read(new File("./src/gui/bck_img2.jpg"));
            images[2] = ImageIO.read(new File("./src/gui/bck_img3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentImage = images[0]; // Establecer la primera imagen como la actual
        nextImage = images[1]; // Establecer la segunda imagen como la siguiente

        fadeTimer = new Timer(fadeDuration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar a la siguiente imagen en el array (cíclicamente)
                currentIndex = (currentIndex + 1) % images.length;
                currentImage = nextImage;
                nextImage = images[(currentIndex + 1) % images.length];
                repaint();
                // Reiniciar el temporizador de desvanecimiento
                fadeTimer.restart();
            }
        });
        fadeTimer.setRepeats(false); // Solo una ejecución del temporizador
        fadeTimer.start(); // Iniciar el temporizador
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null && nextImage != null) {
            g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), null);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(nextImage, 0, 0, getWidth(), getHeight(), null);
            g2d.dispose();
        }
    }

    // Método para detener el temporizador de desvanecimiento (opcional)
    public void stopFadeTimer() {
        fadeTimer.stop();
    }
}


class ImageBackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;

    public ImageBackgroundPanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageBackgroundPanel(BorderLayout borderLayout,String imagePath) {
    	super(borderLayout);
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Escalar la imagen para que se ajuste al tamaño del panel
            Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, null);
        }
    }
}




//CLASE BOTON LABEL

class LabelButton extends JButton {
    public LabelButton(String text) {
        super(text);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        setForeground(Color.BLACK); // Color de texto predeterminado

    }
}


    
