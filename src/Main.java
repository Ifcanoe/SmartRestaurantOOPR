import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

//TODO Turn reusable code into UIUtilities
//TODO Break it up into files
//TODO Overload GridBagUtilities

class RoundedButtonS extends JButton {
  private int cornerRadius = 50;
  private Color backgroundColor;
  private Color textColor = UIConstants.CREAM;

  public RoundedButtonS(String text) {
    super(text);
    setOpaque(false);             
    setFocusPainted(false);       
    setForeground(textColor);
    setContentAreaFilled(false);  
    setBorderPainted(false);   
  }

  @Override
    protected void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g.create();

      // Smooth edges
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      g2.setColor(new Color(0, 0, 0, 60));
      g2.fillRoundRect(0, 5, getWidth() - 4, getHeight() - 4, cornerRadius, cornerRadius);

      // Draw filled rounded rectangle
      g2.setColor(getBackground());
      g2.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, cornerRadius, cornerRadius);

      g2.dispose();
      super.paintComponent(g); // Draw the text/icon
  }

  @Override
  public void setBackground(Color bg) {
    this.backgroundColor = bg;
    super.setBackground(bg);
  }

  @Override
  public Insets getInsets() {
    return new Insets(0, 0, 5, 0); // Internal padding for text/icon
  }

  @Override
  public Dimension getPreferredSize() {
    Dimension size = super.getPreferredSize();
    Insets insets = getInsets();
    return new Dimension(
      size.width - insets.left - insets.right,
      size.height - insets.top - insets.bottom
    );
  }

  public void setCornerRadius(int radius) {
    this.cornerRadius = radius;
    repaint();
  }

  //! Deprecated
  // public void setBorderColor(Color color) {
  //   this.borderColor = color;
  //   repaint();
  // }

  public void setTextColor(Color color) {
    this.textColor = color;
    setForeground(color);
    repaint();
  }

}

class GridBagUtilities {
  protected static void addObject (Component component, 
    Container yourcontainer, 
    GridBagLayout layout, 
    GridBagConstraints gbc, int row, int col, int gridwidth, int gridheight){

    gbc.gridy = row;
    gbc.gridx = col;

    gbc.gridwidth = gridwidth;
    gbc.gridheight = gridheight;

    layout.setConstraints(component, gbc);
    yourcontainer.add(component);
  }
}

class ImageUtilities {

  ImageIcon cartIcon;
  private static JLabel cartLabel;
  
  protected static ImageIcon resizeImage(ImageIcon img, int w, int h){
    Image scaledImage = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);

    return new ImageIcon(scaledImage);
  }
  
  static {
    ImageIcon cartIcon = new ImageIcon(ImageUtilities.class.getResource("/images/icons/cart1.png"));
    cartIcon = resizeImage(cartIcon, 50, 50);
    cartLabel = new JLabel();
    cartLabel.setIcon(cartIcon);
  }

  public static JLabel getCartLabel() {
    return cartLabel;
  }

}

class UIConstants {

  public static Font DEFAULT_FONT;
  public static Font baseFont;
  public static final Color DARK_GREEN = new Color(6, 82, 4);
  public static final Color CREAM = new Color(255, 245, 225);
  public static final Color RESTO_BROWN = new Color(82, 45, 26);
  public static final Color INVIS = new Color(000, true);

  static {
    try {
      InputStream is = UIConstants.class.getResourceAsStream("/font/MontserratBold.ttf");
      baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
      DEFAULT_FONT = baseFont.deriveFont(Font.PLAIN, 16f);

      UIDefaults defaults = UIManager.getLookAndFeelDefaults();
      defaults.put("Button.focus", INVIS);
      //! defaults.put("Button.opaque", false);

    } catch (Exception e){
      System.err.print(e.getMessage());
    }
    
  }

  public static void applyDefaultFont() {
    UIManager.put("Label.font", DEFAULT_FONT);
    UIManager.put("Button.font", DEFAULT_FONT);
    UIManager.put("TextField.font", DEFAULT_FONT);
    UIManager.put("TextArea.font", DEFAULT_FONT);
    UIManager.put("ComboBox.font", DEFAULT_FONT);
    UIManager.put("TabbedPane.font", DEFAULT_FONT);
    UIManager.put("CheckBox.font", DEFAULT_FONT);
    UIManager.put("RadioButton.font", DEFAULT_FONT);
  }

  public static void applyDefaultForeground() {
    UIManager.put("Label.foreground", Color.BLACK);
    UIManager.put("Button.foreground", Color.BLACK);
    UIManager.put("TextField.foreground", Color.BLACK);
    UIManager.put("TextArea.foreground", Color.BLACK);
    UIManager.put("ComboBox.foreground", Color.BLACK);
    UIManager.put("TabbedPane.foreground", Color.BLACK);
    UIManager.put("CheckBox.foreground", Color.BLACK);
    UIManager.put("RadioButton.foreground", Color.BLACK);
  }



  public static void applyDefaultBackground() {
    UIManager.put("Panel.background", CREAM);
  }

  

}

class Controller {
  private MainFrameView view;
  private RestaurantModel model;

  private BottomBar bottomBar;
  private StartPanel startPanel;
  private MenuPanel menuPanel;
  private AllergensPanel allergensPanel;
  private CheckoutPanel checkoutPanel;


  public Controller(MainFrameView view, RestaurantModel model){
    this.view = view;
    this.model = model;
  }

  public void switchPanel(String panelName){
    view.displayPanel(panelName);
  }

  public void displayBars(boolean state){
    view.displayComponent(view.getTopBar(), state);
    view.displayComponent(view.getBottomBar(), state);
  }

  public void prepareTopCheckout(){
    TopBar topBar = view.getTopBar();

    topBar.configureCartButton(false, e -> {});
    topBar.configureReturnButton("BACK", e -> {
      view.displayPanel("MenuP");
      resetTopBarBehavior();
    });
  
  }

  public void resetTopBarBehavior(){
    TopBar topBar = view.getTopBar();

    topBar.configureCartButton(true, e -> {
      switchPanel("CheckoutP");
      prepareTopCheckout();
    });

    topBar.configureReturnButton("BACK TO MAIN MENU", e -> {
      switchPanel("StartP");
      displayBars(false);
    });


  }

}

class TopBar extends JPanel{ 

  private JButton returnMainMenuB = new JButton("BACK TO MAIN MENU");
  private RoundedButtonS cartB = new RoundedButtonS("CART");
  private JLabel logoLabel = new JLabel();
  private ImageIcon cartIcon = new ImageIcon(getClass().getResource("/images/icons/cart2.png"));
  private ImageIcon logoIcon = new ImageIcon(ImageUtilities.class.getResource("/images/icons/logo.png"));
  private GridBagConstraints gbc = new GridBagConstraints();
  private GridBagLayout topBarLayout = new GridBagLayout();
  
  TopBar(Controller mvc){

    setLayout(topBarLayout);
    setBackground(UIConstants.CREAM);

    cartIcon = ImageUtilities.resizeImage(cartIcon, 35, 35);
    logoIcon = ImageUtilities.resizeImage(logoIcon, 68, 59);

    returnMainMenuB.setHorizontalAlignment(SwingConstants.LEFT);
    returnMainMenuB.setContentAreaFilled(false);
    returnMainMenuB.setForeground(UIConstants.RESTO_BROWN);
    returnMainMenuB.setBorder(new EmptyBorder(10, 10, 10, 10));
    returnMainMenuB.setOpaque(false);
    returnMainMenuB.addActionListener(e -> {
      mvc.switchPanel("StartP");
      mvc.displayBars(false);
    });

    logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    logoLabel.setIcon(logoIcon);
    logoLabel.setBackground(Color.BLACK);

    cartB.setHorizontalAlignment(SwingConstants.CENTER);
    cartB.setContentAreaFilled(false);
    cartB.setBackground(UIConstants.RESTO_BROWN);
    cartB.setForeground(UIConstants.CREAM);
    cartB.setBorder(null);
    cartB.setOpaque(false);
    cartB.setIcon(cartIcon);
    cartB.addActionListener(e -> {
      mvc.switchPanel("CheckoutP");
      mvc.prepareTopCheckout();
    });

    gbc.weightx = 0.5;
    gbc.insets = new Insets(0, 10, 0, 0);
    gbc.anchor = GridBagConstraints.LINE_START;
    
    GridBagUtilities.addObject(returnMainMenuB, this, topBarLayout, gbc, 0, 0, 1, 1);
    
    gbc.insets = new Insets(0, 10, 0, 80);
    gbc.anchor = GridBagConstraints.CENTER;
    GridBagUtilities.addObject(logoLabel, this, topBarLayout, gbc, 0, 1, 1, 1);

    gbc.insets = new Insets(0, 0, 0, 0);
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.ipadx = 30;
    gbc.ipady = 10;
    GridBagUtilities.addObject(cartB, this, topBarLayout, gbc, 0, 2, 1, 1);

  }

  public void configureReturnButton(String text, ActionListener listener) {
    returnMainMenuB.setText(text);
    
    for (ActionListener al : returnMainMenuB.getActionListeners()) {
      returnMainMenuB.removeActionListener(al);
    }

    returnMainMenuB.addActionListener(listener);
  }

  public void configureCartButton(boolean state, ActionListener listener) {
    cartB.setVisible(state);

    for (ActionListener al : cartB.getActionListeners()) {
      cartB.removeActionListener(al);
    }

    cartB.addActionListener(listener);
  }

}

class BottomBar extends JPanel {
  JLabel totalLabel = new JLabel();
  JTextField totalTextPane = new JTextField();
  JLabel cartLabel = ImageUtilities.getCartLabel();
  JLabel currencySignLabel = new JLabel("₱");
  GridBagLayout bottomBarLayout = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();

  BottomBar(Controller mvc){

    setBackground(UIConstants.DARK_GREEN);
    setLayout(bottomBarLayout);
    setBorder(new LineBorder(new Color(220, 220, 220), 5));

    // TotalLabel Settings
    totalLabel.setText("TOTAL: ");
    totalLabel.setForeground(UIConstants.CREAM);
    totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Currency Sign Label Settings
    currencySignLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    currencySignLabel.setForeground(UIConstants.CREAM);

    // Total Text Field Settings
    totalTextPane.setBorder(new EmptyBorder(0, 0, 0, 0));
    totalTextPane.setSize(100, 100);
    totalTextPane.setHorizontalAlignment(SwingConstants.CENTER);
    totalTextPane.setFont(UIConstants.baseFont.deriveFont(Font.PLAIN, 24f));
    totalTextPane.setEditable(false);

    // Cart Label Settings
    cartLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.1/0.3;
    gbc.insets = new Insets(0, 10, 0, 10);

    GridBagUtilities.addObject(totalLabel, this, bottomBarLayout, gbc, 0, 0, 1, 1);

    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.1;
    GridBagUtilities.addObject(currencySignLabel, this, bottomBarLayout, gbc, 0, 1, 1, 1);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    gbc.ipady = 40;
    GridBagUtilities.addObject(totalTextPane, this, bottomBarLayout, gbc, 0, 2, 1, 1);

    gbc.weightx = 0.1/0.3;
    gbc.insets = new Insets(0, 10, 0, 10);
    GridBagUtilities.addObject(cartLabel, this, bottomBarLayout, gbc, 0, 3, 1, 1);

  }
}

class MainPanel extends JPanel{

  MainPanel(Controller mvc){
    
  }

}

class StartPanel extends JPanel {

  RoundedButtonS startOrderB = new RoundedButtonS("START ORDER");
  JLabel smartDiningRestaurantTitle = new JLabel();
  JLabel logoLabel = new JLabel();
  ImageIcon logoIcon = new ImageIcon(ImageUtilities.class.getResource("/images/icons/logo.png"));
  
  GridBagConstraints gbc = new GridBagConstraints();
  GridBagLayout startPanelLayout = new GridBagLayout();

  StartPanel(Controller mvc){
    setLayout(startPanelLayout);
    setBackground(UIConstants.CREAM);
    
    // startOrderB.setPreferredSize(new Dimension(200, 100));

    smartDiningRestaurantTitle.setText("SMART DINING RESTAURANT");
    smartDiningRestaurantTitle.setFont(UIConstants.baseFont.deriveFont(Font.PLAIN, 32f));

    logoLabel.setIcon(logoIcon);

    startOrderB.setBorder(null);
    startOrderB.setForeground(UIConstants.CREAM);
    startOrderB.setBackground(UIConstants.RESTO_BROWN);
    startOrderB.addActionListener(e -> {
      mvc.switchPanel("MenuP");
      mvc.displayBars(true);
    });
    
    gbc.weightx = 0.5;
    gbc.insets = new Insets(0, 10, 0, 10);

    GridBagUtilities.addObject(smartDiningRestaurantTitle, this, startPanelLayout, gbc, 0, 0, 1, 1);
    GridBagUtilities.addObject(logoLabel, this, startPanelLayout, gbc, 1, 0, 1, 1);

    gbc.insets = new Insets(0, 0, 30, 0);
    gbc.ipadx = 30;
    gbc.ipady = 30;
    GridBagUtilities.addObject(startOrderB, this, startPanelLayout, gbc, 2, 0, 1, 1);

  }

}

class MenuPanel extends JPanel{
  
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
  private CardLayout menuItemPanelLayout = new CardLayout();
  
  MenuPanel(Controller mvc){

    // Menu Panel Settings
    setBackground(UIConstants.CREAM);
    setLayout(menuPanelLayout);
    setBorder(new EmptyBorder(10, 10, 10, 10));
    

    // Menu Label Settings
    menuLabel.setText("MENU");
    menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
    menuLabel.setForeground(UIConstants.RESTO_BROWN);

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
    gbc2.insets = new Insets(30, 0, 0, 0);
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

class AllergensPanel extends JPanel {

  AllergensPanel(Controller mvc){
    setBackground(UIConstants.CREAM);
    
  }

}

class CheckoutPanel extends JPanel{
  private JLabel checkoutCartLabel = new JLabel("CART");
  private JPanel ordersPanel = new JPanel();


  CheckoutPanel(Controller mvc){
    setBackground(UIConstants.CREAM);

    add(new JLabel("Checkout"));
    add(new JButton("Press me4"));
  }
}

class MainFrameView extends JFrame {

  // Instantiate the panels
  private RestaurantModel model = new RestaurantModel();
  private Controller mvc = new Controller(this, model); 
  private MainPanel mainPanel = new MainPanel(mvc);
  private TopBar topBar = new TopBar(mvc);
  private BottomBar bottomBar = new BottomBar(mvc);
  private StartPanel startPanel = new StartPanel(mvc);
  private MenuPanel menuPanel = new MenuPanel(mvc);
  private AllergensPanel allergensPanel = new AllergensPanel(mvc);
  private CheckoutPanel checkoutPanel = new CheckoutPanel(mvc);

  // Variables
  CardLayout cLayout = new CardLayout();

  MainFrameView(){

    // Settings for MainFrameView
      setLayout(new BorderLayout());
      setTitle("Smart Restaurant");
      setSize(600, 770);
      setVisible(true);
      

    // Set the top, bottom, and main panels
      add(topBar, BorderLayout.PAGE_START);
      add(bottomBar, BorderLayout.PAGE_END);
      add(mainPanel, BorderLayout.CENTER);
    
    // Main Panel Settings
      mainPanel.setLayout(cLayout);

    // Add panels to the mainPanel
      mainPanel.add(startPanel, "StartP");  
      mainPanel.add(menuPanel, "MenuP"); 
      mainPanel.add(allergensPanel, "AllergenP");
      mainPanel.add(checkoutPanel, "CheckoutP");
    
    // Visibility Checks
      topBar.setVisible(false);
      bottomBar.setVisible(false);
      mainPanel.setVisible(true);
       

  }

  public void displayPanel(String name){
    cLayout.show(mainPanel, name);
  }

  public void displayComponent(Component c, boolean state){
    c.setVisible(state);
  }

  public MainFrameView getView(){
    return this;
  }



  public MenuPanel getMenuPanel(){
    return menuPanel;
  }

  public TopBar getTopBar(){
    return topBar;
  }

  public BottomBar getBottomBar(){
    return bottomBar;
  }

  public AllergensPanel getAllergensPanel(){
    return allergensPanel;
  }

  public CheckoutPanel getCheckoutPanel() {
    return checkoutPanel;
  }

  
}

public class Main {
  
  public static void main(String[] args){
    UIConstants.applyDefaultBackground();
    UIConstants.applyDefaultForeground();
    UIConstants.applyDefaultFont();
    new MainFrameView();
  }

}


