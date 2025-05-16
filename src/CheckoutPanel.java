import javax.swing.*;
import java.awt.*;


public class CheckoutPanel extends JPanel{
  private JLabel checkoutCartLabel = new JLabel("CART");
  private JPanel ordersPanel = new JPanel();

  CheckoutPanel(Controller mvc){
    setBackground(UIConstants.CREAM);

    add(new JLabel("Checkout"));
    add(new JButton("Press me4"));
  }
}