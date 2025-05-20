import javax.swing.*;

import java.awt.*;
import java.io.*;

//TODO Turn reusable code into UIUtilities
//TODO Break it up into files
//TODO Overload GridBagUtilities

class RoundedButtonS extends JButton {
  private int cornerRadius = 50;
  private Color backgroundColor;
  private Color textColor = UIUtilities.CREAM;

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

      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Create shadow beneath
      g2.setColor(new Color(0, 0, 0, 60));
      g2.fillRoundRect(0, 5, getWidth() - 4, getHeight() - 4, cornerRadius, cornerRadius);

      // Create the round rectangle
      g2.setColor(getBackground());
      g2.fillRoundRect(0, 0, getWidth() - 5, getHeight() - 5, cornerRadius, cornerRadius);

      g2.dispose();
      super.paintComponent(g); 
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

  public void setTextColor(Color color) {
    this.textColor = color;
    setForeground(color);
    repaint();
  }

}

class GridBagUtilities {
  public static void addObject (Component component, 
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
  
  protected static ImageIcon resizeImage(ImageIcon img, int w, int h){
    Image scaledImage = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);

    return new ImageIcon(scaledImage);
  }

  public static ImageIcon getImage(String path){
    ImageIcon image = new ImageIcon(ImageUtilities.class.getResource(path));
    return image;
  }

  public static ImageIcon getImage(String path, int w, int h){
    ImageIcon image = new ImageIcon(ImageUtilities.class.getResource(path));
    image = resizeImage(image, w, h);
    return image;
  }

}

class UIUtilities {

  public static Font DEFAULT_FONT;
  public static Font baseFont;
  public static final Color DARK_GREEN = new Color(6, 82, 4);
  public static final Color RESTO_GRAY = new Color(80, 80, 80);
  public static final Color CREAM = new Color(255, 245, 225);
  public static final Color RESTO_BROWN = new Color(82, 45, 26);
  public static final Color INVIS = new Color(000, true);
  public static final Color ORANGE = new Color(213, 160, 90);

  static {
    try {
      InputStream is = UIUtilities.class.getResourceAsStream("/font/MontserratBold.ttf");
      baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
      DEFAULT_FONT = baseFont.deriveFont(Font.PLAIN, 16f);

      UIDefaults defaults = UIManager.getLookAndFeelDefaults();
      defaults.put("Button.focus", INVIS);

    } catch (Exception e){
      System.err.print(e.getMessage());
    }
  }

  public static void setFontSize(Component c, float size){
    c.setFont(baseFont.deriveFont(Font.PLAIN, size));
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
    UIManager.put("Label.foreground", RESTO_GRAY);
    UIManager.put("Button.foreground", RESTO_GRAY);
    UIManager.put("TextField.foreground", RESTO_GRAY);
    UIManager.put("TextArea.foreground", RESTO_GRAY);
    UIManager.put("ComboBox.foreground", RESTO_GRAY);
    UIManager.put("TabbedPane.foreground", RESTO_GRAY);
    UIManager.put("CheckBox.foreground", RESTO_GRAY);
    UIManager.put("RadioButton.foreground", RESTO_GRAY);
  }



  public static void applyDefaultBackground() {
    UIManager.put("Panel.background", CREAM);
  }

  

}



class MainFrameView extends JFrame {

  // Instantiate the panels
  private RestaurantModel model = new RestaurantModel();
  private Controller mvc = new Controller(this, model); 
  private JPanel mainPanel = new JPanel();
  private TopBar topBar = new TopBar(mvc);
  private BottomBar bottomBar = new BottomBar(mvc);
  private StartPanel startPanel = new StartPanel(mvc);
  private MenuPanel menuPanel = new MenuPanel(mvc);
  private AllergensPanel allergensPanel = new AllergensPanel(mvc);
  private CheckoutPanel checkoutPanel = new CheckoutPanel(mvc);

  // Variables
  CardLayout cLayout = new CardLayout();

  MainFrameView(){

    mvc.displayCategory("MainDish");

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
    UIUtilities.applyDefaultBackground();
    UIUtilities.applyDefaultForeground();
    UIUtilities.applyDefaultFont();
    new MainFrameView();
  }

}


