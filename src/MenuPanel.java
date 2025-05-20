import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


public class MenuPanel extends JPanel{

  private int item_count = 0;

  public void addMenuItem(MenuItemData data){
    MenuItemContainer menuItem = new MenuItemContainer(data);
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

  private class MenuItemContainer extends JPanel{
    private JLabel menuItemImage;
    private JLabel menuItemName;
    private JLabel menuItemCalCount;
    private JLabel menuItemPrice;
    private JButton orderButton;
    
    private MenuItemData menuItemData;

    private JPanel orderPricePanel;
    private BoxLayout layout;
    private BoxLayout MICLayout;
    
    MenuItemContainer(MenuItemData data){ 
      menuItemImage = new JLabel();
      menuItemName = new JLabel();
      menuItemCalCount = new JLabel();
      menuItemPrice = new JLabel();
      orderButton = new JButton();
      orderPricePanel = new JPanel();

      this.menuItemData = data;
      
      layout = new BoxLayout(orderPricePanel, BoxLayout.LINE_AXIS);
      MICLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

      setLayout(MICLayout);
      setBackground(UIUtilities.RESTO_BROWN);
      setBorder(new EmptyBorder(20, 0, 20, 0));
      setPreferredSize(new Dimension(170, 235));

      menuItemImage.setAlignmentX(CENTER_ALIGNMENT);
      menuItemImage.setIcon(ImageUtilities.getImage(data.imagePath, 170, 95));
      menuItemImage.setPreferredSize(new Dimension(170, 95));
      
      menuItemName.setAlignmentX(CENTER_ALIGNMENT);
      menuItemName.setForeground(UIUtilities.CREAM);
      menuItemName.setText(data.name);
      
      menuItemCalCount.setAlignmentX(CENTER_ALIGNMENT);
      menuItemCalCount.setForeground(UIUtilities.CREAM);
      menuItemCalCount.setText(Integer.toString(data.calories));
      
      menuItemPrice.setAlignmentX(CENTER_ALIGNMENT);
      menuItemPrice.setForeground(UIUtilities.CREAM);
      menuItemPrice.setText(Float.toString(data.price));
      
      orderButton.setAlignmentX(CENTER_ALIGNMENT);
      orderButton.setFocusPainted(false);       
      orderButton.setBorderPainted(false);
      orderButton.setForeground(UIUtilities.CREAM);
      orderButton.setBackground(UIUtilities.ORANGE);
      orderButton.setText("+");
      UIUtilities.setFontSize(orderButton, 20f);
      orderButton.addActionListener(e -> {
        // * mvc.addOrder();
      });


      orderPricePanel.setAlignmentX(CENTER_ALIGNMENT);
      orderPricePanel.setBackground(UIUtilities.ORANGE);
      orderPricePanel.setLayout(layout);
      orderPricePanel.setBorder(new EmptyBorder(0, 10, 0, 0));

      orderPricePanel.add(menuItemPrice);
      orderPricePanel.add(orderButton);

      add(menuItemImage);
      add(Box.createRigidArea(new Dimension(0, 10)));
      add(menuItemName);
      add(Box.createRigidArea(new Dimension(0, 10)));
      add(menuItemCalCount);
      add(Box.createRigidArea(new Dimension(0, 10)));
      add(orderPricePanel);

    }
  
    // * Get data through order
    public MenuItemData getMenuItemData(){
      return menuItemData;
    }

  }

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
  private JScrollPane mipScrollPane = new JScrollPane();

  private GridBagLayout mipLayout = new GridBagLayout();
    
    MenuPanel(Controller mvc){

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

    // Calories Button Settings
    caloriesB.setHorizontalAlignment(SwingConstants.CENTER);
    caloriesB.setFocusPainted(false);       
    caloriesB.setBorderPainted(false);
    caloriesB.setBackground(UIUtilities.DARK_GREEN);
    caloriesB.setForeground(UIUtilities.CREAM);

    // Budget Text Field Settings
    budgetTextField.setHorizontalAlignment(JTextField.CENTER);
    budgetTextField.setBackground(UIUtilities.DARK_GREEN);
    budgetTextField.setForeground(UIUtilities.CREAM);
    budgetTextField.setBorder(null);
    
    // IMPORTANT Menu Item Panel Settings //
    menuItemPanel.setBackground(Color.WHITE);
    menuItemPanel.setLayout(mipLayout);
  
    mipScrollPane.setViewportView(menuItemPanel);
    mipScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    mipScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    // Menu Selector Panel Settings
    menuSelectorPanel.setLayout(menuSelectorPanellLayout);
        
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

  public JPanel getMenuPanel() {
    return menuItemPanel;
  }

}

