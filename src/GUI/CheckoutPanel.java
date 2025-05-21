package GUI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import Utilities.GridBagUtilities;
import Utilities.UIUtilities;


public class CheckoutPanel extends JPanel{

  public void addOrderItem(MenuItemData data){
    OrderContainer orderItem = new OrderContainer(data, mvc);
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.insets = new Insets(2, 2, 2, 2);
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    
    GridBagUtilities.addObject(orderItem, ordersPanel, ordersPanelLayout, gbc, item_count, 0, 1, 1);
    item_count++;

    ordersPanel.revalidate();
    ordersPanel.repaint();
  }

  //? Use for future implementation
  public void fixDisplaySubtract(){
    item_count--;

    ordersPanel.revalidate();
    orderPanel.repaint();
  }

  public void resetDisplay(){
    item_count = 0;

    ordersPanel.removeAll();
    ordersPanel.revalidate();
    ordersPanel.repaint();
  }

  private JLabel checkoutCartLabel = new JLabel();
  private JPanel ordersPanel = new JPanel();
  private JButton cancelAllButton = new JButton();
  private JButton payButton = new JButton();
  private JPanel orderPanel = new JPanel();
  private JScrollPane oPScrollPane = new JScrollPane();
  private Controller mvc;

  private int item_count = 0;

  GridBagLayout checkoutPanelLayout = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();
  
  GridBagLayout ordersPanelLayout = new GridBagLayout();
  GridBagConstraints oplgbc = new GridBagConstraints();


  CheckoutPanel(Controller mvc){
  
    setBackground(UIUtilities.CREAM);
    setLayout(checkoutPanelLayout);
    
    this.mvc = mvc;

    // Checkout Cart Label Settings
    checkoutCartLabel.setText("CART");
    checkoutCartLabel.setForeground(UIUtilities.RESTO_BROWN);
    UIUtilities.setFontSize(checkoutCartLabel, 20f);

    // Pay Button Settings
    cancelAllButton.setHorizontalAlignment(SwingConstants.CENTER);
    cancelAllButton.setFocusPainted(false);       
    cancelAllButton.setBorderPainted(false);
    cancelAllButton.setBackground(UIUtilities.DARK_GREEN);
    cancelAllButton.setForeground(UIUtilities.CREAM);
    cancelAllButton.setText("CANCEL ALL");

    // Cancel All Button Settings
    payButton.setHorizontalAlignment(SwingConstants.CENTER);
    payButton.setFocusPainted(false);       
    payButton.setBorderPainted(false);
    payButton.setBackground(UIUtilities.DARK_GREEN);
    payButton.setForeground(UIUtilities.CREAM);
    payButton.setText("PROCEED TO PAY");
    payButton.addActionListener(e -> {
      mvc.printCart();
    });

    // Orders Panel Scroll Pane Settings
    oPScrollPane.setViewportView(ordersPanel);
    oPScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    oPScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    oPScrollPane.getVerticalScrollBar().setUnitIncrement(20);

    // Orders Panel Settings
    ordersPanel.setBackground(Color.WHITE);
    ordersPanel.setLayout(ordersPanelLayout);

    //! Debug
    // System.out.print(ordersPanel.getPreferredSize());

    gbc.weightx = 0.1;
    gbc.weighty = 0.1;
    GridBagUtilities.addObject(checkoutCartLabel, this, checkoutPanelLayout, gbc, 0, 0, 3, 1);

    gbc.weighty = 1;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(0, 10, 0, 10);
    GridBagUtilities.addObject(oPScrollPane, this, checkoutPanelLayout, gbc, 1, 0, 3, 1);

    gbc.fill = GridBagConstraints.NONE;
    gbc.weighty = 0.1;
    gbc.weightx = 0.1;
    GridBagUtilities.addObject(cancelAllButton, this, checkoutPanelLayout, gbc, 2, 0, 1, 1);
    GridBagUtilities.addObject(payButton, this, checkoutPanelLayout, gbc, 2, 2, 1, 1);

  }
}