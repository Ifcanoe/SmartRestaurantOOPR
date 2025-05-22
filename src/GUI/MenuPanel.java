package GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Utilities.GridBagUtilities;
import Utilities.UIUtilities;

public class MenuPanel extends JPanel{

  private int item_count = 0;

  private GridBagLayout menuPanelLayout = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();

  private GridBagLayout menuSelectorPanellLayout = new GridBagLayout();
  private GridBagConstraints gbc2 = new GridBagConstraints();
  private JPanel menuSelectorPanel = new JPanel();

  private JButton allergensB = new JButton("ALLERGENS");
  private JButton submitBudgetB = new JButton("CALORIES");
  private JTextField budgetTextField = new JTextField();

  private JLabel menuLabel = new JLabel();
  private JButton mDishesB = new JButton("MAIN DISHES");
  private JButton sidesB = new JButton("SIDES");
  private JButton drinksB = new JButton("DRINKS");
  private JButton dessertsB = new JButton("DESSERTS");

  private Controller mvc;

  private AllergensDialog allergensDialog;

  private JPanel menuItemPanel = new JPanel();
  private JScrollPane mipScrollPane = new JScrollPane();

  private GridBagLayout mipLayout = new GridBagLayout();
  
  
  public void addMenuItem(MenuItemData data){
    MenuItemContainer menuItem = new MenuItemContainer(data, mvc);
    GridBagConstraints gbc = new GridBagConstraints();
    
    gbc.weightx = 0.1/0.3;
    gbc.insets = new Insets(2, 2, 2, 2);
    
    GridBagUtilities.addObject(menuItem, menuItemPanel, mipLayout, gbc, item_count / 3, item_count % 3, 1, 1);
    item_count++;
    
    menuItemPanel.revalidate();
    menuItemPanel.repaint();
  }
  
  
  
  public void resetDisplay(){
    item_count = 0;
    
    menuItemPanel.removeAll();
    menuItemPanel.revalidate();
    menuItemPanel.repaint();

  }
  
  public JPanel getMenuPanel() {
    return menuItemPanel;
  }
  
  public AllergensDialog getAllergensDialog(){
    return allergensDialog;
  }

  public String getBudgetText(){
    if (budgetTextField.getText().isEmpty()){ return null; }
    return budgetTextField.getText();
  }

  MenuPanel(Controller mvc){

    this.mvc = mvc;
    this.allergensDialog = new AllergensDialog(this.mvc);

    // Menu Panel Settings
    setBackground(UIUtilities.CREAM);
    setLayout(menuPanelLayout);
    setBorder(new EmptyBorder(10, 10, 10, 10));
    
    // Menu Label Settings
    menuLabel.setText("MENU");
    menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
    menuLabel.setForeground(UIUtilities.RESTO_BROWN);
    UIUtilities.setFontSize(menuLabel, 20f);

    // Main Dishes Button Settings
    mDishesB.setHorizontalAlignment(SwingConstants.CENTER);
    mDishesB.setFocusPainted(false);       
    mDishesB.setBorderPainted(false);
    mDishesB.setBackground(UIUtilities.DARK_GREEN);
    mDishesB.setForeground(UIUtilities.CREAM);
    mDishesB.setFont(UIUtilities.baseFont.deriveFont(Font.PLAIN, 12f));
    mDishesB.addActionListener(e -> {
      mvc.displayCategory("MainDish");
    });

    // Sides Button Settings
    sidesB.setHorizontalAlignment(SwingConstants.CENTER);
    sidesB.setFocusPainted(false);       
    sidesB.setBorderPainted(false);
    sidesB.setBackground(UIUtilities.DARK_GREEN);
    sidesB.setForeground(UIUtilities.CREAM);
    sidesB.setFont(UIUtilities.baseFont.deriveFont(Font.PLAIN, 12f));
    sidesB.addActionListener(e -> {
      mvc.displayCategory("SideDish");
    });

    // Drink Button Settings
    drinksB.setHorizontalAlignment(SwingConstants.CENTER);
    drinksB.setFocusPainted(false);       
    drinksB.setBorderPainted(false);
    drinksB.setBackground(UIUtilities.DARK_GREEN);
    drinksB.setForeground(UIUtilities.CREAM);
    drinksB.setFont(UIUtilities.baseFont.deriveFont(Font.PLAIN, 12f));
    drinksB.addActionListener(e -> {
      mvc.displayCategory("Drinks");
    });

    // Desserts Button Settings
    dessertsB.setHorizontalAlignment(SwingConstants.CENTER);
    dessertsB.setFocusPainted(false);       
    dessertsB.setBorderPainted(false);
    dessertsB.setBackground(UIUtilities.DARK_GREEN);
    dessertsB.setForeground(UIUtilities.CREAM);
    dessertsB.setFont(UIUtilities.baseFont.deriveFont(Font.PLAIN, 12f));
    dessertsB.addActionListener(e -> {
      mvc.displayCategory("Dessert");
    });

    // Allergens Button Item Settings
    allergensB.setHorizontalAlignment(SwingConstants.CENTER);
    allergensB.setFocusPainted(false);
    allergensB.setBorderPainted(false);
    allergensB.setBackground(UIUtilities.DARK_GREEN);
    allergensB.setForeground(UIUtilities.CREAM);
    allergensB.addActionListener(e -> {
      mvc.displayAllergens();
      allergensDialog.displayDialog();
    });

    // Calories Button Settings
    submitBudgetB.setHorizontalAlignment(SwingConstants.CENTER);
    submitBudgetB.setFocusPainted(false);       
    submitBudgetB.setBorderPainted(false);
    submitBudgetB.setBackground(UIUtilities.DARK_GREEN);
    submitBudgetB.setForeground(UIUtilities.CREAM);
    submitBudgetB.setVisible(true);
    submitBudgetB.addActionListener(e -> {
      mvc.doSomethingToGetText(getBudgetText());
    });


    // Budget Text Field Settings
    budgetTextField.setHorizontalAlignment(JTextField.CENTER);
    budgetTextField.setBackground(UIUtilities.DARK_GREEN);
    budgetTextField.setForeground(UIUtilities.CREAM);
    budgetTextField.setBorder(null);
    budgetTextField.setText("BUDGET");
    budgetTextField.addFocusListener(new FocusAdapter() {
      @Override
      public void focusGained(FocusEvent e) {
        budgetTextField.setText("");
      }

      @Override
      public void focusLost(FocusEvent e) {
        budgetTextField.setText("BUDGET");
      }
    });
    // budgetTextField.setVisible(false);
    
    // Menu Item Panel Settings
    menuItemPanel.setBackground(Color.WHITE);
    menuItemPanel.setLayout(mipLayout);
  
    // Menu Item Panel Scroll Pane Settings 
    mipScrollPane.setViewportView(menuItemPanel);
    mipScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    mipScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    mipScrollPane.setBorder(null);
    mipScrollPane.getVerticalScrollBar().setUnitIncrement(20);

    // Menu Selector Panel Settings
    menuSelectorPanel.setLayout(menuSelectorPanellLayout);
        
    gbc.weightx = 0.1;
    gbc.insets = new Insets(0, 10, 0, 10);
    GridBagUtilities.addObject(allergensB, this, menuPanelLayout, gbc, 0, 0, 1, 1);
    GridBagUtilities.addObject(submitBudgetB, this, menuPanelLayout, gbc, 0, 1, 1, 1);
    
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.ipady = 10;
    GridBagUtilities.addObject(budgetTextField, this, menuPanelLayout, gbc, 0, 2, 1, 1);

    gbc.ipady = 0;
    gbc.fill = GridBagConstraints.BOTH;
    GridBagUtilities.addObject(menuSelectorPanel, this, menuPanelLayout, gbc, 1, 0, 3, 1);

    gbc.ipadx = 0;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(10, 10, 0, 10);
    GridBagUtilities.addObject(mipScrollPane, this, menuPanelLayout, gbc, 2, 0, 3, 1);
    
    gbc2.weightx = 1;
    gbc2.ipady = 10;
    gbc2.insets = new Insets(10, 0, 10, 0);
    GridBagUtilities.addObject(menuLabel, menuSelectorPanel, menuSelectorPanellLayout, gbc2, 0, 0, 4, 1);

    gbc2.fill = GridBagConstraints.HORIZONTAL;
    gbc2.insets = new Insets(0, 5, 0, 5);
    gbc2.ipady = 5;
    gbc2.weightx = 0.2;
    GridBagUtilities.addObject(mDishesB, menuSelectorPanel, menuSelectorPanellLayout, gbc2, 1, 0, 1, 1);
    GridBagUtilities.addObject(sidesB, menuSelectorPanel, menuSelectorPanellLayout, gbc2, 1, 1, 1, 1);
    GridBagUtilities.addObject(drinksB, menuSelectorPanel, menuSelectorPanellLayout, gbc2, 1, 2, 1, 1);
    GridBagUtilities.addObject(dessertsB, menuSelectorPanel, menuSelectorPanellLayout, gbc2, 1, 3, 1, 1);

  }

  
}

