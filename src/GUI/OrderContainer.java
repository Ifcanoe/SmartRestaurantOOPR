package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.border.*;

import Utilities.GridBagUtilities;
import Utilities.ImageUtilities;
import Utilities.UIUtilities;

public class OrderContainer extends JPanel{

  private JLabel nameLabel;
  private JLabel priceLabel;
  private JLabel itemImage;
  private JButton addButton;
  private JButton subtractButton;
  private JLabel quantityBoughtLabel;

  private GridBagLayout layout;
  private GridBagConstraints gbc;

  //* MenuItemData menuItemData, Controller mvc
  OrderContainer(MenuItemData itemData, Controller mvc) {

    nameLabel = new JLabel();
    priceLabel = new JLabel();
    itemImage = new JLabel();
    addButton = new JButton();
    subtractButton = new JButton();
    quantityBoughtLabel = new JLabel();

    layout = new GridBagLayout();
    gbc = new GridBagConstraints();

    setLayout(layout);
    setBackground(UIUtilities.RESTO_BROWN);
    setBorder(new EmptyBorder(10, 10, 10, 10));

    // Item Name Settings
    nameLabel.setForeground(UIUtilities.CREAM);
    nameLabel.setBackground(new Color(0, 0, 0, 0));
    nameLabel.setText(itemData.name);

    // Item Price Settings
    priceLabel.setForeground(UIUtilities.CREAM);
    priceLabel.setText(String.format("₱%.2f", itemData.price));

    // Item Image Settings
    itemImage.setIcon(ImageUtilities.getImage(itemData.imagePath, 170, 95));

    // Add Button Settings
    addButton.setContentAreaFilled(false);
    addButton.setBorderPainted(false);
    addButton.setIcon(ImageUtilities.getImage("/images/icons/add.png", 20, 20));
    addButton.addActionListener(e -> {
      mvc.addItem(itemData);
    });
    
    // Subtract Button Settings
    subtractButton.setContentAreaFilled(false);
    subtractButton.setBorderPainted(false);
    subtractButton.setIcon(ImageUtilities.getImage("/images/icons/minus.png", 20, 20));
    subtractButton.addActionListener(e -> {
      mvc.subtractItem(itemData);
    });

    // Quantity Bought
    quantityBoughtLabel.setForeground(UIUtilities.CREAM);
    quantityBoughtLabel.setText(Integer.toString(itemData.getQuantity()));

    gbc.weightx = 1;
    GridBagUtilities.addObject(nameLabel, this, layout, gbc, 0, 0, 3, 1);

    gbc.weightx = 0.1/0.5;
    GridBagUtilities.addObject(itemImage, this, layout, gbc, 1, 0, 1, 1);
    
    gbc.weightx = 0.8;
    gbc.ipadx = 50;
    GridBagUtilities.addObject(priceLabel, this, layout, gbc, 1, 1, 1, 1);

    gbc.weightx = 0.1;
    gbc.ipadx = 0;
    GridBagUtilities.addObject(addButton, this, layout, gbc, 1, 2, 1, 1);

    GridBagUtilities.addObject(quantityBoughtLabel, this, layout, gbc, 1, 3, 1, 1);
    
    GridBagUtilities.addObject(subtractButton, this, layout, gbc, 1, 4, 1, 1);
  }


}