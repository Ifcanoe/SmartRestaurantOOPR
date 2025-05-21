package GUI;
import javax.swing.*;
import javax.swing.border.*;

import Utilities.GridBagUtilities;
import Utilities.ImageUtilities;
import Utilities.UIUtilities;
import GUI.CustomComponents.RoundedButtonS;

import java.awt.*;
import java.awt.event.ActionListener;


public class TopBar extends JPanel {
  private JButton returnMainMenuB = new JButton("BACK TO MAIN MENU");
  private RoundedButtonS cartB = new RoundedButtonS("CART");
  private JLabel logoLabel = new JLabel();
  private ImageIcon cartIcon = new ImageIcon(getClass().getResource("/images/icons/cart2.png"));
  private ImageIcon logoIcon = new ImageIcon(ImageUtilities.class.getResource("/images/icons/logo.png"));
  private GridBagConstraints gbc = new GridBagConstraints();
  private GridBagLayout topBarLayout = new GridBagLayout();
  
  TopBar(Controller mvc){

    setLayout(topBarLayout);
    setBackground(UIUtilities.CREAM);

    cartIcon = ImageUtilities.resizeImage(cartIcon, 35, 35);
    logoIcon = ImageUtilities.resizeImage(logoIcon, 38, 50);

    returnMainMenuB.setHorizontalAlignment(SwingConstants.LEFT);
    returnMainMenuB.setContentAreaFilled(false);
    returnMainMenuB.setForeground(UIUtilities.RESTO_BROWN);
    returnMainMenuB.setBorder(new EmptyBorder(10, 10, 10, 10));
    returnMainMenuB.setOpaque(false);
    returnMainMenuB.addActionListener(e -> {
      mvc.switchPanel("StartP");
      mvc.displayBars(false);
    });

    logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
    logoLabel.setIcon(logoIcon);
    logoLabel.setBackground(Color.BLACK);

    cartB.setHorizontalAlignment(SwingConstants.CENTER);
    cartB.setBackground(UIUtilities.RESTO_BROWN);
    cartB.setForeground(UIUtilities.CREAM);
    cartB.setIcon(cartIcon);
    
    cartB.addActionListener(e -> {
      mvc.switchPanel("CheckoutP");
      mvc.prepareTopCheckout();
    });

    gbc.weightx = 0.1/0.2;
    gbc.insets = new Insets(0, 0, 0, 0);
    gbc.anchor = GridBagConstraints.LINE_START;
    
    GridBagUtilities.addObject(returnMainMenuB, this, topBarLayout, gbc, 0, 0, 1, 1);
    
    gbc.weightx = 1;
    gbc.insets = new Insets(10, 0, 0, 85);
    gbc.anchor = GridBagConstraints.CENTER;
    GridBagUtilities.addObject(logoLabel, this, topBarLayout, gbc, 0, 1, 1, 1);

    gbc.weightx = 0.1/0.2;
    gbc.insets = new Insets(10, 0, 0, 0);
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
