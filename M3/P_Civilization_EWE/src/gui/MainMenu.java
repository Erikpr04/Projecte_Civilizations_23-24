package gui;
import javax.imageio.ImageIO;
import javax.swing.*;
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



	
	
	
	
    public MainMenu() {
    	
    	int sizebutton1 = 250;
    	int sizebutton2 = 100;
    	
   
    	
    	
    	// Establecer el diseño del panel principal
    	setLayout(new BorderLayout());

    	// Crear el título del juego
    	JLabel titleLabel = new JLabel("Civilization");
    	titleLabel.setForeground(Color.WHITE);
    	
    	titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
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
                startGame();

				
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
    	
    	//options button
    	LabelButton optionsButton = new LabelButton("");
    	originalIcon = new ImageIcon("./src/gui/mm_assets/options_button.png"); // Reemplaza "ruta_de_la_imagen.jpg" con la ruta de tu imagen
    	originalImage = originalIcon.getImage();
    	resizedImage = originalImage.getScaledInstance(sizebutton1, sizebutton2, Image.SCALE_SMOOTH); // Ajusta el tamaño según sea necesario
    	resizedIcon = new ImageIcon(resizedImage);
    	optionsButton.setIcon(resizedIcon);
    	
    	optionsButton.addMouseListener(new MouseListener() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
                openOptionsWindow();

				
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
    	
    	
    	
    	
    	ImageBackgroundPanel mainpanel = new ImageBackgroundPanel("./src/gui/background_title.jpg");
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
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    private void startGame() {
        // Obtener la referencia a la ventana del menú principal
        JFrame mainMenuFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        // Cerrar la ventana del menú principal
        mainMenuFrame.dispose();

        // Crear una nueva ventana para el juego
        JFrame gameFrame = new JFrame("Game GUI");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de Game_gui y agregarla al frame
        Game_gui gameGui = new Game_gui(10, 10);
        gameFrame.add(gameGui, BorderLayout.CENTER);

        // Mostrar la ventana del juego
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    
    private void openOptionsWindow() {
        // Crear una nueva ventana de opciones
        JFrame optionsFrame = new JFrame("Options");
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setSize(400, 300);
        optionsFrame.setLocationRelativeTo(null);

        // Agregar componentes, configurar paneles, etc.

        optionsFrame.setVisible(true);
    }
    
    private void dispose_main_menu() {
            // Obtener la referencia a la ventana del menú principal
            JFrame mainMenuFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

            // Cerrar la ventana del menú principal
            mainMenuFrame.dispose();
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	
        	JFrame mainMenuFrame = new JFrame("Main Menu");

            // Crear una instancia del menú principal y agregarla al frame
            MainMenu mainMenu = new MainMenu();
            mainMenuFrame.add(mainMenu);

            // Mostrar la ventana del menú principal
            mainMenuFrame.pack();
            mainMenuFrame.setLocationRelativeTo(null);
            mainMenuFrame.setVisible(true);
        });
    }
}










//CLASE PARA PANEL DE FONDO




class ImageBackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;

    public ImageBackgroundPanel(String imagePath) {
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

class LabelButton extends JLabel {
    public LabelButton(String text) {
        super(text);
        setForeground(Color.BLACK); // Color de texto predeterminado
        setBorder(BorderFactory.createEmptyBorder()); // Borde predeterminado

//        addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent e) {
//                // Cambiar el color del texto y agregar un borde al entrar el mouse
//                setForeground(Color.WHITE);
//                setBorder(BorderFactory.createLineBorder(Color.WHITE));
//            }
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                // Acción al hacer clic en el JLabel
//                // Puedes agregar el comportamiento deseado aquí
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                // Restaurar el color del texto y eliminar el borde al salir el mouse
//                setForeground(Color.BLACK);
//                setBorder(BorderFactory.createEmptyBorder());
//            }
//        });
    }
}
    
