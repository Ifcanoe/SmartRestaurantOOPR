package GUI;
import javax.swing.*;
import javax.swing.border.*;

import Utilities.ImageUtilities;
import Utilities.UIUtilities;

import java.awt.*;

public class MenuItemContainer extends JPanel{
    private JLabel menuItemImage;
    private JLabel menuItemName;
    private JLabel menuItemCalCount;
    private JLabel menuItemPrice;
    private JButton orderButton;
    
    // private MenuItemData menuItemData;
    // private Controller mvc;

    private JPanel orderPricePanel;
    private BoxLayout layout;
    private BoxLayout MICLayout;
    

    MenuItemContainer(MenuItemData data, Controller mvc){ 
      menuItemImage = new JLabel();
      menuItemName = new JLabel();
      menuItemCalCount = new JLabel();
      menuItemPrice = new JLabel();
      orderButton = new JButton();
      orderPricePanel = new JPanel();
      
      layout = new BoxLayout(orderPricePanel, BoxLayout.LINE_AXIS);
      MICLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

      setLayout(MICLayout);
      setBackground(UIUtilities.RESTO_BROWN);
      setBorder(new EmptyBorder(20, 0, 20, 0));
      setPreferredSize(new Dimension(170, 235));

      menuItemImage.setAlignmentX(CENTER_ALIGNMENT);
      menuItemImage.setIcon(ImageUtilities.getImage(data.imagePath, 170, 95));
      menuItemImage.setPreferredSize(new Dimension(170, 95));
      
      menuItemName.setAlignmentX(CENTER_ALIGNMENT);
      menuItemName.setForeground(UIUtilities.CREAM);
      menuItemName.setBackground(new Color(0, 0, 0 ,0));
      menuItemName.setFont(UIUtilities.DEFAULT_FONT);
      menuItemName.setOpaque(false);
      UIUtilities.setFontSize(menuItemName, 12f);
      menuItemName.setText(data.name);
            
      menuItemCalCount.setAlignmentX(CENTER_ALIGNMENT);
      menuItemCalCount.setForeground(UIUtilities.CREAM);
      menuItemCalCount.setText(String.format("%d Cs", data.calories));
      
      menuItemPrice.setAlignmentX(CENTER_ALIGNMENT);
      menuItemPrice.setForeground(UIUtilities.CREAM);
      menuItemPrice.setText(String.format("₱%.2f", data.price));
      
      orderButton.setAlignmentX(CENTER_ALIGNMENT);
      orderButton.setFocusPainted(false);       
      orderButton.setBorderPainted(false);
      orderButton.setForeground(UIUtilities.CREAM);
      orderButton.setBackground(UIUtilities.ORANGE);
      orderButton.setText("+");
      UIUtilities.setFontSize(orderButton, 20f);
      orderButton.addActionListener(e -> {
        mvc.addToCart(data);
      });

      orderPricePanel.setAlignmentX(CENTER_ALIGNMENT);
      orderPricePanel.setBackground(UIUtilities.ORANGE);
      orderPricePanel.setLayout(layout);
      orderPricePanel.setBorder(new EmptyBorder(0, 10, 0, 0));

      orderPricePanel.add(menuItemPrice);
      orderPricePanel.add(orderButton);

      add(menuItemImage);
      add(Box.createRigidArea(new Dimension(0, 10)));
      add(menuItemName);
      add(Box.createRigidArea(new Dimension(0, 10)));
      add(menuItemCalCount);
      add(Box.createRigidArea(new Dimension(0, 10)));
      add(orderPricePanel);
 
    }
}