package Utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GridBagUtilities {
  public static void addObject (Component component, 
    Container yourcontainer, 
    GridBagLayout layout, 
    GridBagConstraints gbc, int row, int col, int gridwidth, int gridheight){

    gbc.gridy = row;
    gbc.gridx = col;

    gbc.gridwidth = gridwidth;
    gbc.gridheight = gridheight;

    layout.setConstraints(component, gbc);
    yourcontainer.add(component);
  }
}