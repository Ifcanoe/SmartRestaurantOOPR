import javax.swing.*;
import javax.swing.border.Border;
import java.awt.image.*;
import java.awt.*;

class ImageUtilities {
  protected static ImageIcon resizeImage(ImageIcon img, int w, int h){
    Image scaledImage = img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);

    return new ImageIcon(scaledImage);

  }
}

class Controller {
  private MainFrame view;

  public Controller(MainFrame view){
    this.view = view;
  }

  public void switchPanel(String panelName){
    view.displayPanel(panelName);
  }

}

class TopBar extends JPanel {

  JButton ReturnMainMenuB = new JButton("Return to the Main Menu");
  JLabel CartLogo = new JLabel();
  ImageIcon icon = new ImageIcon(getClass().getResource("/images/icons/cart1.png"));

  TopBar(Controller mvc){
    icon = ImageUtilities.resizeImage(icon, 50, 50);
    CartLogo.setIcon(icon);


    setBackground(Color.LIGHT_GRAY);
    ReturnMainMenuB.addActionListener(e -> mvc.switchPanel("StartP"));
    add(ReturnMainMenuB); add(CartLogo);
  }

}

class MainPanel extends JPanel{

  MainPanel(Controller mvc){

  }

}

class StartPanel extends JPanel {

  JButton goToMenuB = new JButton("Start Order");

  StartPanel(Controller mvc){
    setLayout(new GridBagLayout());

    goToMenuB.addActionListener(e -> mvc.switchPanel("MenuP"));
    goToMenuB.setPreferredSize(new Dimension(200, 100));

    add(goToMenuB);
  }

}

class MenuPanel extends JPanel{

  JButton goToOrdersB = new JButton("Go to Orders");

  MenuPanel(Controller mvc){
    add(new JLabel("MenuPanel"));
    goToOrdersB.addActionListener(e -> mvc.switchPanel("OrdersP"));
    add(goToOrdersB);

  }
}

class OrdersPanel extends JPanel{
  OrdersPanel(Controller mvc){
    add(new JLabel("Orders"));
    add(new JButton("Press me3"));
  }
}

class CheckoutPanel extends JPanel{
  CheckoutPanel(Controller mvc){
    add(new JLabel("Checkout"));
    add(new JButton("Press me4"));
  }
}



public class MainFrame extends JFrame {

  // Instantiate the panels
  Controller mvc = new Controller(this);
  MainPanel MainPanel = new MainPanel(mvc);
  TopBar TopBar = new TopBar(mvc);
  StartPanel StartPanel = new StartPanel(mvc);
  MenuPanel MenuPanel = new MenuPanel(mvc);
  OrdersPanel OrdersPanel = new OrdersPanel(mvc);
  CheckoutPanel CheckoutPanel = new CheckoutPanel(mvc);

  // Variables
  CardLayout cLayout = new CardLayout();

  MainFrame(){

    // Settings for MainFrame
      setLayout(new BorderLayout());
      setTitle("Smart Restaurant");
      setSize(600, 790);
      setVisible(true);

    // Set the main panel
      add(TopBar, BorderLayout.PAGE_START);
      add(MainPanel, BorderLayout.CENTER);
    
    // Main Panel Settings
      MainPanel.setLayout(cLayout);

    // Add panels to the MainPanel
      MainPanel.add(StartPanel, "StartP");  
      MainPanel.add(MenuPanel, "MenuP"); 
      MainPanel.add(OrdersPanel, "OrdersP"); 
      MainPanel.add(CheckoutPanel, "CheckoutP");

      MainPanel.setVisible(true);      

  }

  public void displayPanel(String name){
    cLayout.show(MainPanel, name);
  }

  public static void main(String[] args){
    new MainFrame();
    
  }
}
