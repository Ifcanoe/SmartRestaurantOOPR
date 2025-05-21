package GUI;
import javax.swing.*;

import java.awt.*;

public class MainFrameView extends JFrame {

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
  private CartPanel CartPanel = new CartPanel(mvc);

  // Variables
  CardLayout cLayout = new CardLayout();

  public MainFrameView(){

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
      mainPanel.add(CartPanel, "CartP");
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

  public CartPanel getCartPanel() {
    return CartPanel;
  }

  
}