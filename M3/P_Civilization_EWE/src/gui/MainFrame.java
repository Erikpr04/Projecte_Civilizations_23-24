package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("BorderLayout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.RED);
        // Agrega componentes al panel izquierdo si es necesario

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLUE);
        // Agrega componentes al panel derecho si es necesario

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.GREEN);
        // Agrega componentes al panel superior si es necesario

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.YELLOW);
        // Agrega componentes al panel inferior si es necesario

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        // Agrega componentes al panel central si es necesario

        add(leftPanel, BorderLayout.WEST); // Agrega el panel izquierdo
        add(rightPanel, BorderLayout.EAST); // Agrega el panel derecho
        add(topPanel, BorderLayout.NORTH); // Agrega el panel superior
        add(bottomPanel, BorderLayout.SOUTH); // Agrega el panel inferior
        add(centerPanel, BorderLayout.CENTER); // Agrega el panel central

        pack();
        setSize(new Dimension(700,700));
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

