import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

class BottomBar extends JPanel {
  JLabel totalLabel = new JLabel();
  JTextField totalTextPane = new JTextField();
  JLabel cartLabel = ImageUtilities.getCartLabel();
  JLabel currencySignLabel = new JLabel("₱");
  GridBagLayout bottomBarLayout = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();

  BottomBar(Controller mvc){

    setBackground(UIConstants.DARK_GREEN);
    setLayout(bottomBarLayout);
    setBorder(new LineBorder(new Color(220, 220, 220), 5));

    // TotalLabel Settings
    totalLabel.setText("TOTAL: ");
    totalLabel.setForeground(UIConstants.CREAM);
    totalLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Currency Sign Label Settings
    currencySignLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    currencySignLabel.setForeground(UIConstants.CREAM);

    // Total Text Field Settings
    totalTextPane.setBorder(new EmptyBorder(0, 0, 0, 0));
    totalTextPane.setSize(100, 100);
    totalTextPane.setHorizontalAlignment(SwingConstants.CENTER);
    totalTextPane.setFont(UIConstants.baseFont.deriveFont(Font.PLAIN, 24f));
    totalTextPane.setEditable(false);

    // Cart Label Settings
    cartLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.1/0.3;
    gbc.insets = new Insets(0, 10, 0, 10);

    GridBagUtilities.addObject(totalLabel, this, bottomBarLayout, gbc, 0, 0, 1, 1);

    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.1;
    GridBagUtilities.addObject(currencySignLabel, this, bottomBarLayout, gbc, 0, 1, 1, 1);

    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1;
    gbc.insets = new Insets(0, 0, 0, 0);
    gbc.ipady = 40;
    GridBagUtilities.addObject(totalTextPane, this, bottomBarLayout, gbc, 0, 2, 1, 1);

    gbc.weightx = 0.1/0.3;
    gbc.insets = new Insets(0, 10, 0, 10);
    GridBagUtilities.addObject(cartLabel, this, bottomBarLayout, gbc, 0, 3, 1, 1);

  }
}
