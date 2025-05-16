import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class StartPanel extends JPanel {

  RoundedButtonS startOrderB = new RoundedButtonS("START ORDER");
  JLabel smartDiningRestaurantTitle = new JLabel();
  JLabel logoLabel = new JLabel();
  ImageIcon logoIcon = new ImageIcon(ImageUtilities.class.getResource("/images/icons/logo.png"));
  
  GridBagConstraints gbc = new GridBagConstraints();
  GridBagLayout startPanelLayout = new GridBagLayout();

  StartPanel(Controller mvc){
    setLayout(startPanelLayout);
    setBackground(UIConstants.CREAM);
    
    // startOrderB.setPreferredSize(new Dimension(200, 100));

    smartDiningRestaurantTitle.setText("SMART DINING RESTAURANT");
    smartDiningRestaurantTitle.setFont(UIConstants.baseFont.deriveFont(Font.PLAIN, 32f));

    logoLabel.setIcon(logoIcon);

    startOrderB.setBorder(null);
    startOrderB.setForeground(UIConstants.CREAM);
    startOrderB.setBackground(UIConstants.RESTO_BROWN);
    startOrderB.addActionListener(e -> {
      mvc.switchPanel("MenuP");
      mvc.displayBars(true);
    });
    
    gbc.weightx = 0.5;
    gbc.insets = new Insets(0, 10, 0, 10);

    GridBagUtilities.addObject(smartDiningRestaurantTitle, this, startPanelLayout, gbc, 0, 0, 1, 1);
    GridBagUtilities.addObject(logoLabel, this, startPanelLayout, gbc, 1, 0, 1, 1);

    gbc.insets = new Insets(0, 0, 30, 0);
    gbc.ipadx = 30;
    gbc.ipady = 30;
    GridBagUtilities.addObject(startOrderB, this, startPanelLayout, gbc, 2, 0, 1, 1);

  }

}