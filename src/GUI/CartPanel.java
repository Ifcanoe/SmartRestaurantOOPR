package GUI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import Utilities.GridBagUtilities;
import Utilities.UIUtilities;


public class CartPanel extends JPanel{

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
  private JScrollPane oPScrollPane = new JScrollPane();
  private Controller mvc;

  private OrderConfirm orderConfirmDialog;

  private int item_count = 0;

  GridBagLayout CartPanelLayout = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();
  
  GridBagLayout ordersPanelLayout = new GridBagLayout();
  GridBagConstraints oplgbc = new GridBagConstraints();

  public OrderConfirm getOrderConfirm(){
    return orderConfirmDialog;
  }

  CartPanel(Controller mvc){
  
    setBackground(UIUtilities.CREAM);
    setLayout(CartPanelLayout);
    
    this.mvc = mvc;
    orderConfirmDialog = new OrderConfirm(mvc);

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
    cancelAllButton.addActionListener(e -> {
      mvc.emptyCart();
    });
    
    // Cancel All Button Settings
    payButton.setHorizontalAlignment(SwingConstants.CENTER);
    payButton.setFocusPainted(false);       
    payButton.setBorderPainted(false);
    payButton.setBackground(UIUtilities.DARK_GREEN);
    payButton.setForeground(UIUtilities.CREAM);
    payButton.setText("PROCEED TO PAY");
    payButton.addActionListener(e -> {
      // mvc.printCart();

      orderConfirmDialog.displayDialog(payButton);

      //? Should we use this?
      // mvc.switchPanel("CheckoutP");
      // mvc.prepareTopCheckout();
    });

    // Orders Panel Scroll Pane Settings
    oPScrollPane.setViewportView(ordersPanel);
    oPScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    oPScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    oPScrollPane.setBorder(null);
    oPScrollPane.getVerticalScrollBar().setUnitIncrement(20);

    // Orders Panel Settings
    ordersPanel.setBackground(Color.WHITE);
    ordersPanel.setLayout(ordersPanelLayout);

    //! Debug
    // System.out.print(ordersPanel.getPreferredSize());

    gbc.weightx = 0.1;
    gbc.weighty = 0.1;
    GridBagUtilities.addObject(checkoutCartLabel, this, CartPanelLayout, gbc, 0, 0, 3, 1);

    gbc.weighty = 1;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(0, 10, 0, 10);
    GridBagUtilities.addObject(oPScrollPane, this, CartPanelLayout, gbc, 1, 0, 3, 1);

    gbc.fill = GridBagConstraints.NONE;
    gbc.weighty = 0.1;
    gbc.weightx = 0.1;
    GridBagUtilities.addObject(cancelAllButton, this, CartPanelLayout, gbc, 2, 0, 1, 1);
    GridBagUtilities.addObject(payButton, this, CartPanelLayout, gbc, 2, 2, 1, 1);

  }

  public Object getOrderConfirmDialog() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOrderConfirmDialog'");
  }
}