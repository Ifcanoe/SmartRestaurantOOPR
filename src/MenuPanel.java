import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


public class MenuPanel extends JPanel{
  
  //! Deprecated classes until needed
  // static class MainDishesPanel extends JPanel {
  //   MainDishesPanel(){
      
  //   }
  // }
  
  // static class DrinksPanel extends JPanel {
  //   DrinksPanel(){
      
  //   }
  // }

  // static class SidesPanel extends JPanel {
    

  //   SidesPanel(){
      
  //   }
  // }

  // static class DessertsPanel extends JPanel {
  //   DessertsPanel(){
      
  //   }
  // }

  //? Example stuff

  static class MenuItemContainer extends JPanel {
  JLabel menuItemImage = new JLabel();
  JLabel menuItemName = new JLabel();
  JLabel menuItemCalCount = new JLabel();
  JLabel menuItemPrice = new JLabel();
  
    MenuItemContainer(){

    }

  }

  //! Deprecated until needed
  // SidesPanel sidesPanel = new SidesPanel();
  // MainDishesPanel mainDishesPanel = new MainDishesPanel();
  // DessertsPanel dessertsPanel = new DessertsPanel();
  // DrinksPanel drinksPanel = new DrinksPanel();

  private GridBagLayout menuPanelLayout = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();

  private GridBagLayout menuSelectorPanellLayout = new GridBagLayout();
  private GridBagConstraints gbc2 = new GridBagConstraints();
  private JPanel menuSelectorPanel = new JPanel();

  private JButton allergensB = new JButton("ALLERGENS");
  private JButton caloriesB = new JButton("CALORIES");
  private JTextField budgetTextField = new JTextField();

  private JLabel menuLabel = new JLabel();
  private JButton mDishesB = new JButton("MAIN DISHES");
  private JButton sidesB = new JButton("SIDES");
  private JButton drinksB = new JButton("DRINKS");
  private JButton dessertsB = new JButton("DESSERTS");
  
  private JPanel menuItemPanel = new JPanel();
  
  MenuPanel(Controller mvc){

    // Menu Panel Settings
    setBackground(UIConstants.CREAM);
    setLayout(menuPanelLayout);
    setBorder(new EmptyBorder(10, 10, 10, 10));
    
    // Menu Label Settings
    menuLabel.setText("MENU");
    menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
    menuLabel.setForeground(UIConstants.RESTO_BROWN);
    UIConstants.setFontSize(menuLabel, 20f);

    // Main Dishes Button Settings
    mDishesB.setHorizontalAlignment(SwingConstants.CENTER);
    mDishesB.setFocusPainted(false);       
    mDishesB.setBorderPainted(false);
    mDishesB.setBackground(UIConstants.DARK_GREEN);
    mDishesB.setForeground(UIConstants.CREAM);
    mDishesB.setFont(UIConstants.baseFont.deriveFont(Font.PLAIN, 12f));

    // Sides Button Settings
    sidesB.setHorizontalAlignment(SwingConstants.CENTER);
    sidesB.setFocusPainted(false);       
    sidesB.setBorderPainted(false);
    sidesB.setBackground(UIConstants.DARK_GREEN);
    sidesB.setForeground(UIConstants.CREAM);
    sidesB.setFont(UIConstants.baseFont.deriveFont(Font.PLAIN, 12f));

    // Drink Button Settings
    drinksB.setHorizontalAlignment(SwingConstants.CENTER);
    drinksB.setFocusPainted(false);       
    drinksB.setBorderPainted(false);
    drinksB.setBackground(UIConstants.DARK_GREEN);
    drinksB.setForeground(UIConstants.CREAM);
    drinksB.setFont(UIConstants.baseFont.deriveFont(Font.PLAIN, 12f));

    // Desserts Button Settings
    dessertsB.setHorizontalAlignment(SwingConstants.CENTER);
    dessertsB.setFocusPainted(false);       
    dessertsB.setBorderPainted(false);
    dessertsB.setBackground(UIConstants.DARK_GREEN);
    dessertsB.setForeground(UIConstants.CREAM);
    dessertsB.setFont(UIConstants.baseFont.deriveFont(Font.PLAIN, 12f));

    // Allergens Button Item Settings
    allergensB.setHorizontalAlignment(SwingConstants.CENTER);
    allergensB.setFocusPainted(false);       
    allergensB.setBorderPainted(false);
    allergensB.setBackground(UIConstants.DARK_GREEN);
    allergensB.setForeground(UIConstants.CREAM);

    // Calories Button Settings
    caloriesB.setHorizontalAlignment(SwingConstants.CENTER);
    caloriesB.setFocusPainted(false);       
    caloriesB.setBorderPainted(false);
    caloriesB.setBackground(UIConstants.DARK_GREEN);
    caloriesB.setForeground(UIConstants.CREAM);

    // Budget Text Field Settings
    budgetTextField.setHorizontalAlignment(JTextField.CENTER);
    budgetTextField.setBackground(UIConstants.DARK_GREEN);
    budgetTextField.setForeground(UIConstants.CREAM);
    budgetTextField.setBorder(null);
    
    // Menu Item Panel Settings
    menuItemPanel.setBackground(Color.WHITE);
    menuItemPanel.setLayout(new BorderLayout());

    // Menu Selector Panel Settings
    menuSelectorPanel.setLayout(menuSelectorPanellLayout);

    //! Menu Item Panel Additions
    // menuItemPanel.add(mainDishesPanel, "MainDishesP");
    // menuItemPanel.add(sidesPanel, "SidesP");
    // menuItemPanel.add(drinksPanel, "DrinksP");
    // menuItemPanel.add(dessertsPanel, "DessertsP");
        
    gbc.weightx = 0.1;
    gbc.insets = new Insets(0, 10, 0, 10);
    GridBagUtilities.addObject(allergensB, this, menuPanelLayout, gbc, 0, 0, 1, 1);
    GridBagUtilities.addObject(caloriesB, this, menuPanelLayout, gbc, 0, 1, 1, 1);
    
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
    GridBagUtilities.addObject(menuItemPanel, this, menuPanelLayout, gbc, 2, 0, 3, 1);
    
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

  public JPanel getMenuPanel() {
    return menuItemPanel;
  }

}

