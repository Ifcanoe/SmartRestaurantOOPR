import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;


public class CheckoutPanel extends JPanel{
  private JLabel checkoutCartLabel = new JLabel();
  private JPanel ordersPanel = new JPanel();
  private JButton cancelAllButton = new JButton();
  private JButton payButton = new JButton();

  GridBagLayout checkoutPanelLayout = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();

  CheckoutPanel(Controller mvc){
    setBackground(UIConstants.CREAM);
    setLayout(checkoutPanelLayout);

    // Checkout Cart Label Settings
    checkoutCartLabel.setText("CART");
    checkoutCartLabel.setForeground(UIConstants.RESTO_BROWN);
    UIConstants.setFontSize(checkoutCartLabel, 20f);

    // Pay Button Settings
    cancelAllButton.setHorizontalAlignment(SwingConstants.CENTER);
    cancelAllButton.setFocusPainted(false);       
    cancelAllButton.setBorderPainted(false);
    cancelAllButton.setBackground(UIConstants.DARK_GREEN);
    cancelAllButton.setForeground(UIConstants.CREAM);
    cancelAllButton.setText("CANCEL ALL");

    // Cancel All Button Settings
    payButton.setHorizontalAlignment(SwingConstants.CENTER);
    payButton.setFocusPainted(false);       
    payButton.setBorderPainted(false);
    payButton.setBackground(UIConstants.DARK_GREEN);
    payButton.setForeground(UIConstants.CREAM);
    payButton.setText("PROCEED TO PAY");

    ordersPanel.setBackground(Color.WHITE);
    ordersPanel.add(new JLabel("fake"));

    gbc.weightx = 0.1;
    gbc.weighty = 0.1;
    GridBagUtilities.addObject(checkoutCartLabel, this, checkoutPanelLayout, gbc, 0, 0, 3, 1);

    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    GridBagUtilities.addObject(ordersPanel, this, checkoutPanelLayout, gbc, 1, 0, 3, 1);

    gbc.fill = GridBagConstraints.NONE;
    gbc.weighty = 0.1;
    gbc.weightx = 0.1;
    GridBagUtilities.addObject(cancelAllButton, this, checkoutPanelLayout, gbc, 2, 0, 1, 1);
    GridBagUtilities.addObject(payButton, this, checkoutPanelLayout, gbc, 2, 2, 1, 1);

  }
}