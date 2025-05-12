import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.border.*;

import com.mysql.cj.xdevapi.RowFactory;

import javax.swing.ImageIcon;
import java.awt.*;
import java.io.*;



class RoundedButton extends JButton {
  private int cornerRadius = 50;
  private Color backgroundColor;
  private Color textColor = UIConstants.CREAM;

  public RoundedButton(String text) {
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

  public static void applyDefaultBackground() {
    UIManager.put("Panel.background", CREAM);
  }

  

}

class Controller {
  private MainFrameView view;
  private RestaurantModel model;

  public Controller(MainFrameView view, RestaurantModel model){
    this.view = view;
    this.model = model;
  }

  public void switchPanel(String panelName){
    view.displayPanel(panelName);
  }

  public void displayBars(boolean state){
    view.displayComponent(view.topBar, state);
    view.displayComponent(view.bottomBar, state);
  }

  

}

class TopBar extends JPanel {
  JButton returnMainMenuB = new JButton("BACK TO MAIN MENU");
  RoundedButton cartButton = new RoundedButton("CART");
  JLabel logoLabel = new JLabel();
  ImageIcon cartIcon = new ImageIcon(getClass().getResource("/images/icons/cart2.png"));
  ImageIcon logoIcon = new ImageIcon(ImageUtilities.class.getResource("/images/icons/logo.png"));
  GridBagConstraints gbc = new GridBagConstraints();
  GridBagLayout topBarLayout = new GridBagLayout();
  
  TopBar(Controller mvc){

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

    cartButton.setHorizontalAlignment(SwingConstants.CENTER);
    cartButton.setContentAreaFilled(false);
    cartButton.setBackground(UIConstants.RESTO_BROWN);
    cartButton.setForeground(UIConstants.CREAM);
    cartButton.setBorder(null);
    cartButton.setOpaque(false);
    cartButton.setIcon(cartIcon);
    cartButton.addActionListener(e -> mvc.switchPanel("CheckoutP"));
    

    setLayout(topBarLayout);
    setBackground(UIConstants.CREAM);

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
    GridBagUtilities.addObject(cartButton, this, topBarLayout, gbc, 0, 2, 1, 1);
  }

}

class BottomBar extends JPanel {
  JLabel totalLabel = new JLabel();
  JTextField totalTextField = new JTextField();
  JLabel cartLabel = ImageUtilities.getCartLabel();
  GridBagLayout bottomBarLayout = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();

  BottomBar(Controller mvc){

    setBackground(UIConstants.DARK_GREEN);
    setLayout(bottomBarLayout);
    setBorder(new LineBorder(new Color(220, 220, 220), 5));

    // TotalLabel Settings
    totalLabel.setText("Total:");
    totalLabel.setForeground(UIConstants.CREAM);
    totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Total Text Field Settings
    totalTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
    totalTextField.setSize(100, 100);
    totalTextField.setHorizontalAlignment(SwingConstants.CENTER);

    // Cart Label Settings
    cartLabel.setHorizontalAlignment(SwingConstants.CENTER);
    

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.5;
    gbc.insets = new Insets(0, 10, 0, 10);
    // gbc.


    GridBagUtilities.addObject(totalLabel, this, bottomBarLayout, gbc, 0, 0, 1, 1);
    GridBagUtilities.addObject(totalTextField, this, bottomBarLayout, gbc, 0, 1, 1, 1);
    GridBagUtilities.addObject(cartLabel, this, bottomBarLayout, gbc, 0, 2, 1, 1);


  }
}

class MainPanel extends JPanel{

  MainPanel(Controller mvc){
    
  }

}

class StartPanel extends JPanel {

  RoundedButton startOrderB = new RoundedButton("START ORDER");
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

    startOrderB.setContentAreaFilled(false);
    startOrderB.setOpaque(false);
    startOrderB.setFocusPainted(false);
    startOrderB.setBorder(null);
    startOrderB.setForeground(UIConstants.CREAM);
    startOrderB.setBackground(UIConstants.RESTO_BROWN);
    startOrderB.addActionListener(e -> {
      mvc.switchPanel("MenuP");
      mvc.displayBars(true);

    });
    startOrderB.setMargin(getInsets());

    
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

  JButton goToOrdersB = new JButton("Go to Orders");

  MenuPanel(Controller mvc){
    setBackground(UIConstants.CREAM);

    add(new JLabel("MenuPanel"));
    goToOrdersB.addActionListener(e -> mvc.switchPanel("OrdersP"));
    add(goToOrdersB);

  }
}

class AllergensPanel extends JPanel {

  AllergensPanel(Controller mvc){
    setBackground(UIConstants.CREAM);
    
  }

}

class OrdersPanel extends JPanel{
  OrdersPanel(Controller mvc){
    setBackground(UIConstants.CREAM);

    add(new JLabel("Orders"));
    add(new JButton("Press me3"));
  }
}

class CheckoutPanel extends JPanel{
  CheckoutPanel(Controller mvc){
    setBackground(UIConstants.CREAM);

    add(new JLabel("Checkout"));
    add(new JButton("Press me4"));
  }
}

class MainFrameView extends JFrame {

  // Instantiate the panels
  RestaurantModel model = new RestaurantModel();
  Controller mvc = new Controller(this, model);
  MainPanel mainPanel = new MainPanel(mvc);
  TopBar topBar = new TopBar(mvc);
  BottomBar bottomBar = new BottomBar(mvc);
  StartPanel startPanel = new StartPanel(mvc);
  MenuPanel menuPanel = new MenuPanel(mvc);
  AllergensPanel allergensPanel = new AllergensPanel(mvc);
  OrdersPanel ordersPanel = new OrdersPanel(mvc);
  CheckoutPanel checkoutPanel = new CheckoutPanel(mvc);

  // Variables
  CardLayout cLayout = new CardLayout();

  MainFrameView(){

    // Settings for MainFrameView
      setLayout(new BorderLayout());
      setTitle("Smart Restaurant");
      setSize(600, 770);
      setVisible(true);
      

    // Set the main panel
      add(topBar, BorderLayout.PAGE_START);
      add(bottomBar, BorderLayout.PAGE_END);
      add(mainPanel, BorderLayout.CENTER);
    
    // Main Panel Settings
      mainPanel.setLayout(cLayout);

    // Add panels to the mainPanel
      mainPanel.add(startPanel, "StartP");  
      mainPanel.add(menuPanel, "MenuP"); 
      mainPanel.add(allergensPanel, "AllergenP");
      mainPanel.add(ordersPanel, "OrdersP");
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

}

public class Main {
  
  public static void main(String[] args){
    UIConstants.applyDefaultFont();
    new MainFrameView();
  }

}


