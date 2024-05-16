package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import interfaces.Variables;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArmyUpgradeApp {

    private static int resources = 1000;
    private static int multiplier = 1; // Variable para el multiplicador de costos


    public static void main(String[] args) {
        JFrame frame = new JFrame("Army and Technology Upgrade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Upgrade Army tab
        JPanel upgradeArmyPanel = new JPanel();
        upgradeArmyPanel.setLayout(new GridLayout(3, 3)); 

        String[] soldierNames = {"Swordsman", "Spearman", "Crossbowman", "Cannon", "Arrow Tower", "Catapult", "Rocket Launcher Tower", "Magician", "Priest"};
        String[] soldierImages = {"./src/gui/warrior_png.png", "images/spearman.png", "images/crossbow.png", "images/cannon.png", "images/arrow_tower.png", "images/catapult.png", "images/rocket_launcher.png", "images/magician.png", "images/priest.png"};

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
            upgradeArmyPanel.add(createSoldierPanel(soldierNames[i], soldierCosts[i], soldierImages[i]));
        }

        tabbedPane.add("Upgrade Army", upgradeArmyPanel);

        // Upgrade Technology tab (currently empty)
        JPanel upgradeTechnologyPanel = new JPanel();
        tabbedPane.add("Upgrade Technology", upgradeTechnologyPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel createSoldierPanel(String soldierName, int[] costs, String imagePath) {
        JPanel panel = new JPanel(new BorderLayout());

        // Creamos un JPanel interno para contener la imagen y lo configuramos con FlowLayout centrado
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
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

        JLabel foodLabel = new JLabel("Food: " + costs[0]);
        JLabel woodLabel = new JLabel("Wood: " + costs[1]);
        JLabel ironLabel = new JLabel("Iron: " + costs[2]);
        JLabel manaLabel = new JLabel("Mana: " + costs[3]);
        costPanel.add(foodLabel);
        costPanel.add(woodLabel);
        costPanel.add(ironLabel);
        costPanel.add(manaLabel);

        panel.add(costPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel(soldierName + " units to create");
        bottomPanel.add(nameLabel);

        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, 5, 1);
        JSpinner unitSelector = new JSpinner(numberModel);
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

        return panel;
    }
}

