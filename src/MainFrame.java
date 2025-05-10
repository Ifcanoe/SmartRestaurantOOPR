import javax.swing.*;
import java.awt.*;

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

  TopBar(Controller mvc){
    
    
    ReturnMainMenuB.addActionListener(e -> mvc.switchPanel("MenuP"));
    add(ReturnMainMenuB);
  }

}

class MainPanel extends JPanel{

  MainPanel(Controller mvc){

  }

}

class StartPanel extends JPanel{

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
      add(MainPanel);
    
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
